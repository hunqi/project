package com.raysun.shirodemo.util;

public class Logger {
	
	private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger("shirodemo");
	
	public static void info(String message, Object ... args){
		logger.info(String.format(message, args));
	}
	
}
