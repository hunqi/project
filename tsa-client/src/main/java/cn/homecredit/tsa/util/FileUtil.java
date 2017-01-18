package cn.homecredit.tsa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class FileUtil {
	
	/**
	 * 
	 * @param filePath
	 * @return
	 */
	public static String fileSizeBytesString(String filePath) {
		File file = new File(filePath);
		String fileSize = null;
		if (file.exists()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				fileSize = fis.available() + " bytes";
			} catch (Exception e) {
				fileSize = null;
			}
		}
		return fileSize;
	}

	/**
	 * 
	 * @param filePath
	 * @return
	 */
	public static byte[] readFile(String filePath) {
		try {
			FileInputStream fileIn = new FileInputStream(filePath);
			byte[] fileByte = new byte[fileIn.available()];
			fileIn.read(fileByte);
			fileIn.close();
			return fileByte;
		} catch (Exception e) {
		}
		return null;
	}

	public static boolean writeFile(String filePath, byte[] content) {
		if ((filePath == null) || (content == null)) {
			return false;
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(filePath));
			fos.write(content);
			fos.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
