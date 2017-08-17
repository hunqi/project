package com.raysun.email.demo.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

public class FileUtil {
	public static String read(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		String key = "";
		String s = br.readLine();
		
		while (StringUtils.isNotBlank(s)) {
			key += s;
			s = br.readLine();
		}		
		br.close();
		
		return key;
	}
}
