package main.seeyon.hql;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 检查是否存在注入漏洞
 * @author seeyon-chelq
 *
 */
public class HQLCheckTest {
	public static void main(String[] args) {
		String sql="select count(*) from com.seeyon.ctp.common.po.affair.CtpAffair as ctpa where 1 = 1 and ctpa.app in (1,4,19,20,21,22,23,24) and ctpa.subApp != 4 and ctpa.orgAccountId in(:accountId) and ( ( ctpa.templeteId = :templeteId0 and (ctpa.activityId in (15934988126738) )) ) and ( ctpa.completeTime between :startTime and :endTime ) and ctpa.state = :ctpastate";
		if(isOK(sql)) {
			System.out.println("正常！");
		}else {
			System.out.println("存在注入漏洞！");
		}
	}
	/**
	 * 是否正常
	 * @param s sql或hql字符串
	 * @return true：正常 
	 */
	public static boolean isOK(String s) {
		Pattern pattern = Pattern.compile("([\u4e00-\u9fa5]+)|(\\d{8,})");
        Matcher m1 = pattern.matcher(s);
        if(m1.find()){
        	System.out.println("可能存在注入的区域--："+ m1.group());
            return false;
        }
        return true;
	}
}
