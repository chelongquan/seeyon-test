/**
 * Author seeyon-chelq
 * Rev
 * Date: 2021-04-13 9:40
 * Copyright (C) 2021 Seeyon, Inc. All rights reserved.
 * This software is the proprietary information of Seeyon, Inc.
 * Use is subject to license terms.
 */
package main.seeyon.base64;

import java.io.UnsupportedEncodingException;

/**
 *
 * @date 2021-04-13 9:40
 * @since seeyon-test
 * @author seeyon-chelq
 */
public class Test {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String a = "aHR0cDovLzJzNTA1Mjc4dzYuNTF2aXAuYml6L3NlZXlvbi93ZWJPZmZpY2UvdjEvM3JkL2ZpbGUvc2F2ZT9fd19hcHBpZD05ZjQyMmJiMjZiNjA0ZjkwNTMxZTAwMjg0NzFmOGEyMyZfd19jYXRlZ29yeT0wJl93X2NvcHk9MSZfd19leHBvcnQ9MCZfd19maWxlSWQ9LTYwNDQ5NzI3MjQxMTMwMjk5MDUmX3dfZmlsZU5hbWU9emhlbmd3ZW4mX3dfZmlsZVR5cGU9ZG9jJl93X3Blcm1pc3Npb249d3JpdGUmX3dfcHJpbnQ9MSZfd19zaWduYXR1cmU9VWclMkJSVjRBcmxvMWFETzU4YXNDWWxnVmk3aGslM0QmX3dfdG9rZW50eXBlPTEmX3dfdXNlcmlkPTQyMjAyMTIyNzc5OTIyNDYzODImYWNjZXNzX3Rva2VuPTY3YzQ4MTIwNWM2YmNjYTg3ZDYxNjE2ZGIxMGJmNjJj";
//		String b = Base64.encodeString(a.getBytes("utf-8"));
//		System.out.println(b);
		System.out.println(Base64.decode2String(a));
	}
}
