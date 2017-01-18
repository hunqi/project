package cn.homecredit.tsa.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.tsp.TSPAlgorithms;
import org.bouncycastle.tsp.TimeStampRequest;
import org.bouncycastle.tsp.TimeStampRequestGenerator;
import org.bouncycastle.tsp.TimeStampResponse;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.util.encoders.Base64;

import cn.homecredit.tsa.util.FileUtil;
import cn.homecredit.tsa.util.HashUtil;

public class TsaClient {

	private String TSAURL = "http://xxxxxx";
	private String tsaUsername = "demo";
	private String tsaPassword = "demo";

	public static void main(String[] args) throws Exception {
		String file = "D:\\exercise\\pdf\\20161213-001.pdf";
		TsaClient demo = new TsaClient();
		demo.applyTS(
				file, 
				"D:\\exercise\\pdf\\20161213-001.tsr");
		
		System.out.println("hashCode: " + demo.getHashString(file));
	}

	/**
	 * 
	 * Apply time stamp from TSA, and save response as .tsr file. tsr == [Time
	 * Stamp Reply]
	 * 
	 * @param fileName
	 */
	private void applyTS(String fileName, String tsrFile) {
		try {
			byte[] hashCode = getHash(fileName);

			System.out.println("hashCode: " + getHashString(fileName));

			TimeStampRequest request = buildTimeStampRequest(hashCode);

			byte[] respBytes = getTSAResponse(request.getEncoded());

			TimeStampResponse response = new TimeStampResponse(respBytes);
			response.validate(request);
			PKIFailureInfo failure = response.getFailInfo();
			int value = failure == null ? 0 : failure.intValue();
			if (value != 0) {
				throw new Exception("Invalid TSA response, code " + value);
			}
			TimeStampToken tsaToken = response.getTimeStampToken();
			if (tsaToken == null) {
				throw new Exception("TSA failed to return time stamp token");
			}
			byte[] encoded = tsaToken.getEncoded();

			FileUtil.writeFile(tsrFile, encoded);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done.............");
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public byte[] getHash(String fileName) throws Exception {

		return HashUtil.getHash(FileUtil.readFile(fileName));
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public String getHashString(String fileName) throws Exception {

		return HashUtil.getHashString(FileUtil.readFile(fileName));
	}

	/**
	 * 
	 * @param hashCode
	 * @return
	 * @throws IOException
	 */
	public TimeStampRequest buildTimeStampRequest(byte[] hashCode)
			throws IOException {

		TimeStampRequestGenerator tsqGenerator = new TimeStampRequestGenerator();
		// request for public key certificate.
		tsqGenerator.setCertReq(true);
		BigInteger nonce = BigInteger.valueOf(System.currentTimeMillis());

		TimeStampRequest request = tsqGenerator.generate(
				new ASN1ObjectIdentifier(TSPAlgorithms.SHA256), hashCode, nonce);

		return request;
	}

	/**
	 * get time stamp response from TSA
	 * 
	 * @param requestBytes
	 * @return
	 * @throws IOException
	 */
	protected byte[] getTSAResponse(byte[] requestBytes) throws IOException {

		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
				"user1", "user1Pass");
		provider.setCredentials(AuthScope.ANY, credentials);
		CloseableHttpClient httpclient = HttpClientBuilder.create()
				.setDefaultCredentialsProvider(provider).build();

		HttpPost httpPost = new HttpPost(this.TSAURL);
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(15000).setConnectTimeout(15000).build();

		httpPost.setConfig(requestConfig);

		StringEntity entity = new StringEntity("");
		entity.setContentType("application/timestamp-query");
		entity.setContentEncoding("binary");
		httpPost.setEntity(entity);
		httpclient.execute(httpPost);

		URL url = new URL(this.TSAURL);
		URLConnection tsaConnection = url.openConnection();
		tsaConnection.setDoInput(true);
		tsaConnection.setDoOutput(true);
		tsaConnection.setUseCaches(false);
		tsaConnection.setConnectTimeout(8000);
		tsaConnection.setRequestProperty("Content-Type",
				"application/timestamp-query");
		tsaConnection.setRequestProperty("Content-Transfer-Encoding", "binary");
		if ((this.tsaUsername != null) && (!this.tsaUsername.equals(""))) {
			String userPassword = this.tsaUsername + ":" + this.tsaPassword;
			tsaConnection.setRequestProperty("Authorization", "Basic "
					+ new String(Base64.encode(userPassword.getBytes())));
		}
		OutputStream out = tsaConnection.getOutputStream();
		out.write(requestBytes);
		out.close();
		InputStream inp = tsaConnection.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024]; // may need adjust
		int bytesRead = 0;
		while ((bytesRead = inp.read(buffer, 0, buffer.length)) >= 0) {
			baos.write(buffer, 0, bytesRead);
		}
		byte[] respBytes = baos.toByteArray();

		String encoding = tsaConnection.getContentEncoding();
		if ((encoding != null) && (encoding.equalsIgnoreCase("base64"))) {
			respBytes = Base64.decode(respBytes);
		}
		return respBytes;
	}

	/**
	 * get time stamp response from TSA
	 * 
	 * @param requestBytes
	 * @return
	 * @throws IOException
	 */
	protected byte[] getRespViaConnection(byte[] requestBytes)
			throws IOException {
		URL url = new URL(this.TSAURL);
		URLConnection tsaConnection = url.openConnection();
		tsaConnection.setDoInput(true);
		tsaConnection.setDoOutput(true);
		tsaConnection.setUseCaches(false);
//		tsaConnection.setConnectTimeout(15000);
		tsaConnection.setRequestProperty("Content-Type",
				"application/timestamp-query");
		tsaConnection.setRequestProperty("Content-Transfer-Encoding", "binary");
		if ((this.tsaUsername != null) && (!this.tsaUsername.equals(""))) {
			String userPassword = this.tsaUsername + ":" + this.tsaPassword;
			tsaConnection.setRequestProperty("Authorization", "Basic "
					+ new String(Base64.encode(userPassword.getBytes())));
		}
		OutputStream out = tsaConnection.getOutputStream();
		out.write(requestBytes);
		out.close();
		InputStream inp = tsaConnection.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024]; // may need adjust
		int bytesRead = 0;
		while ((bytesRead = inp.read(buffer, 0, buffer.length)) >= 0) {
			baos.write(buffer, 0, bytesRead);
		}
		byte[] respBytes = baos.toByteArray();

		String encoding = tsaConnection.getContentEncoding();
		if ((encoding != null) && (encoding.equalsIgnoreCase("base64"))) {
			respBytes = Base64.decode(respBytes);
		}
		return respBytes;
	}
}
