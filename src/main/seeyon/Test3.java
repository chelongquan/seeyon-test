/**
 * Author seeyon-chelq
 * Rev
 * Date: 2021-05-19 21:52
 * Copyright (C) 2021 Seeyon, Inc. All rights reserved.
 * This software is the proprietary information of Seeyon, Inc.
 * Use is subject to license terms.
 */
package main.seeyon;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @date 2021-05-19 21:52
 * @since seeyon-test
 * @author seeyon-chelq
 */
public class Test3 {
	public static void main(String[] args)  {
//		Map<String ,Object> m = new HashMap<>();
//		m.put("a",1);
//		m.put("b", -4585244279208917279L);
//		Map<String,Object> n =new HashMap<>();
//		BeanUtils.copyProperties(m, n);
//		System.out.println(n);
//
		
		int month =6;
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.YEAR, 2021);
//		calendar.set(Calendar.MONTH, month-1);
//		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Calendar endCal = Calendar.getInstance();
		endCal.set(Calendar.YEAR, 2021);
		endCal.set(Calendar.MONTH, month-1);
		endCal.set(Calendar.DAY_OF_MONTH, 1);
		//		calendar.set(Calendar.HOUR_OF_DAY, 0);
//		calendar.set(Calendar.MINUTE, 0);
//		calendar.set(Calendar.SECOND, 0);
		System.out.println(month +"---"+ endCal.getTime());
		
		
	}
}
