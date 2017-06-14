package cn.raysun.demo.shiro.other;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class DesUtil {
	
	/**
	 * IV, SECRETE_KEY是测试阶段使用的加密参数
	 */
	private final static byte[] IV = 
		{ 0x12, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF };
	private static final String SECRETE_KEY = "hq84ui1x";
	
	/**
     * encrypt by DES
     * @param data
     * @param desKey
     * @param iv
     * @return
     * @throws Exception
     */
    public static String desEncrypt(String data,byte[] desKey,byte[] iv) throws Exception{
    	
    	IvParameterSpec ivSpec = new IvParameterSpec(iv);
		DESKeySpec dks = new DESKeySpec(desKey);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

		byte[] pasByte = cipher.doFinal(data.getBytes("utf-8"));
		return Hex.encodeHexString(pasByte);
    }
    
    /**
     * decrypt by DES
     * @param data
     * @param desKey
     * @param iv
     * @return
     * @throws Exception
     */
    public static String desDecrypt(String data,byte[] desKey,byte[] iv) throws Exception{
    	
    	IvParameterSpec ivSpec = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(desKey, "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);

		byte[] pasByte = new byte[]{};		
		try{
			pasByte = cipher.doFinal(Hex.decodeHex(data.toCharArray()));
		}catch(NullPointerException nE){
			System.out.println(String.format("data : {}", data));
			throw new NullPointerException("data is null.");
		}catch(DecoderException dE){
			System.out.println(String.format("data : {}", data));
			throw new DecoderException();
		}
		
		return new String(pasByte,"utf-8");
    }
	
}
