package cn.homecredit.tsa.client;

import java.text.SimpleDateFormat;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.tsp.TimeStampToken;

import cn.homecredit.tsa.util.FileUtil;
import cn.homecredit.tsa.util.HashUtil;

public class TsrParser {
	public static void main(String[] args) {
		try {
				byte[] tsaByte = FileUtil.readFile(String.format(
						"D:/exercise/pdf/20161213-001.tsr"));
				System.out.println(String.format("timestamp time : %s \n", getTime(tsaByte)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getTime(byte[] tsaToken) throws Exception {
		try {
			CMSSignedData csd = getCMSSignedData(tsaToken);
			TimeStampToken timeStampToken = new TimeStampToken(csd);
			SimpleDateFormat dateFm = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");

			System.out.println("HASH: "
					+ HashUtil.convertByte(timeStampToken.getTimeStampInfo()
							.getMessageImprintDigest()));

			System.out.println("Algorithm: "
					+ timeStampToken.getTimeStampInfo().getHashAlgorithm()
							.getAlgorithm().getId());

			return dateFm
					.format(timeStampToken.getTimeStampInfo().getGenTime());
		} catch (Exception e) {
			throw e;
		}
	}

	private static CMSSignedData getCMSSignedData(byte[] tsaToken)
			throws CMSException {
		CMSSignedData csd = null;
		try {
			csd = new CMSSignedData(tsaToken);
		} catch (CMSException e) {
			csd = new CMSSignedData(Base64.decodeBase64(tsaToken));
		}

		return csd;
	}

}
