package com.lucky.kali.common.util;

import java.util.UUID;

/**
 * @author Elliot
 */
public class UUIDUtil {
	private static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
		"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
		"t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
		"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
		"W", "X", "Y", "Z" };
	private static String generateShortUuid() {
		StringBuilder shortBuffer = new StringBuilder();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();

	}
	/**
	 * 生成一个带前缀的不重复的ID字符串
	 * */
	public static String creatId(String firstStr){
		return (firstStr==null?"":firstStr)+generateShortUuid();
	}

	/**
	 * 生成一个不重复的ID字符串
	 * */
	public static String creatId(){
		return creatId("");
	}

	/**
	 * 生成不带"-"的uuid
	 */

	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 生成一个唯一数
	 * @param str 固定值
	 * @param ip
	 * @param dateFormat 日期格式 如 yyyyMMddHHmmssSSS
	 * @return		生成的唯一数
	 */
	public static String generateId(String str, String ip,String dateFormat){
		return ip+DateUtil.getNow("yyyyMMddHHmmssSSS");

	}

}
