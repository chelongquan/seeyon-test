/*
 * 致远互联 版权所有 Copyright@2017
 */
package main.seeyon.file;

import java.util.Random;
import java.util.UUID;

/**
 * [生成指定长度的随机字符串]
 *
 * @author tophawk
 * @date 2017/9/20 17:30.
 */
public class CodeGenUtil {
	private static final char[] CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-".toCharArray();
	private static final int CHAR_LEN = CHARS.length;
	private static final int[] DIGIT_INDEX = {57, 9, 19, 38, 1, 25, 43, 34, 48, 30};
	
	/**
	 * 获取app_secret
	 *
	 * @return
	 */
	public static String getAppsecret(String appId) {
		return getString(appId, 43);
	}
	
	/**
	 * 获取app_data_token
	 *
	 * @return
	 */
	public static String getDataToken(String appId) {
		return CodeGenUtil.getString(appId, 50);
	}
	
	/**
	 * 生成特定长度的字符串
	 *
	 * @param idSeed 实体的ID，数字
	 * @param length
	 *
	 * @return
	 */
	public static synchronized String getString(String idSeed, int length) {
		StringBuilder buf = new StringBuilder();
		//根据id seed生成前缀
		int len = idSeed.length();
		char[] seedChars = idSeed.toCharArray();
		for (int i = 0; i < len; i++) {
			int charNum = Character.getNumericValue(seedChars[i]);
			int digitIdx = DIGIT_INDEX[charNum >= DIGIT_INDEX.length ? 0 : charNum];
			buf.append(CHARS[digitIdx]);
		}
		
		if (length <= len) {
			return buf.toString();
		}
		
		//组装其他字符
		len = length - len;
		Random random = new Random();
		for (int i = 0; i < len; i++) {
			int index = random.nextInt(CHAR_LEN);
			buf.append(CHARS[index]);
		}
		return buf.toString();
	}
	
	/**
	 * 生成指定长度的字符串
	 *
	 * @param count
	 *
	 * @return
	 */
	public static String getSlat(int count) {
		StringBuilder slat = new StringBuilder();
		String uuid = UUID.randomUUID().toString();
		int length = uuid.length();
		Random random = new Random();
		for (int i = 0; i < count; i++) {
			int index = random.nextInt(length);
			slat.append(uuid.charAt(index));
		}
		return slat.toString();
	}
	
}
