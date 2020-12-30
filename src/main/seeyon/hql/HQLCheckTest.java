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
		String sql=" select count(*)  as count  from (  \tselect  a.TEMPLATE_ID \tfrom  \t\tRPT_WF_ANALYSIS a  \twhere  \t\t1=1  and a.RPT_YEAR = :rptYear_1 and a.RPT_MONTH = :rptMonth_1 and a.TEMP_OWNER_ACCOUNT_ID = :ownerAccountId_1 and ( a.TEMPLATE_ID in (:templateIds_1asdfghj1)  )  GROUP BY a.TEMPLATE_ID  ) c  ";
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
