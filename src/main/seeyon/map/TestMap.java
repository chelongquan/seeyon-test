/**
 * Author seeyon-chelq
 * Rev
 * Date: 2021-04-16 14:55
 * Copyright (C) 2021 Seeyon, Inc. All rights reserved.
 * This software is the proprietary information of Seeyon, Inc.
 * Use is subject to license terms.
 */
package main.seeyon.map;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author seeyon-chelq
 * @since seeyon-test
 */
public class TestMap {
	public static void main(String[] args) {
		Map<Integer, String> HOSTING = new HashMap<>();
		HOSTING.put(1, "linode.com");
		HOSTING.put(2, "heroku.com");
		HOSTING.put(3, "digitalocean.com");
		HOSTING.put(4, "aws.amazon.com");
		HOSTING.put(5, "");
		HOSTING.put(6, null);
		
		// Before Java 8
		String result = "";
		for (Map.Entry<Integer, String> entry : HOSTING.entrySet()) {
			if ("heroku.com".equals(entry.getValue())) {
				result = entry.getValue();
			}
		}
		System.out.println("Before Java 8: " + result);
		
		// Map -> Stream -> Filter -> String
		result = HOSTING.entrySet().stream().filter(map -> "linode.com".equals(map.getValue()))
				.map(map -> map.getValue()).collect(Collectors.joining());
		System.out.println("With Java 8:" + result);
		
		// filter more values
		result = HOSTING.entrySet().stream().filter(x -> {
			if (!x.getValue().contains("amazon") && !x.getValue().contains("digital")) {
				return true;
			}
			return false;
		}).map(map -> map.getValue()).collect(Collectors.joining(","));
		
		System.out.println("With Java 8 : " + result);
	}
}
