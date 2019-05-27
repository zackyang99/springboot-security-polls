package com.example.polls.util;

import java.io.IOException;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
	private static Logger logger = LoggerFactory.getLogger(Utils.class);
	private static int CODE_LENGTH = 8;
	
	public static String generateCode() {
		Random random =  new Random();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < CODE_LENGTH; ++i) {
			sb.append(random.nextInt(10));
		}
		
		return sb.toString();
	}
}
