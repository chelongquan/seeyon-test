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
//		String sql="select new com.seeyon.apps.wfanalysis.vo.MemberflowDetailVO( ctpa.objectId , ctpa.id , ctpa.app , ctpa.subject , ctpa.activityId , ctpa.nodePolicy , ctpa.memberId , ctpa.receiveTime , ctpa.completeTime , ctpa.runWorktime , ctpa.deadlineDate ,( case   when ctpa.completeTime IS NOT NULL AND ctpa.expectedProcessTime < ctpa.completeTime then true  when ctpa.expectedProcessTime < :currentTime  then true  else false  end ) as  coverTime , ctpa.overWorktime,  ctpa.templeteId,  ctpa.state,  ctpa.expectedProcessTime,  ctpa.caseId, ctpa.processId )  from com.seeyon.ctp.common.po.affair.CtpAffair as ctpa  where ctpa.app in (1,4,19,20,21,22,23,24) and ( ctpa.memberId in (-9028790710386277258,-9020943202209545060,-5193197253577326073,-2328964422221673613,1459950557453502352,1590058100302235107) )  and ctpa.templeteId in(:templateIds0_, :templateIds1_, :templateIds2_, :templateIds3_, :templateIds4_, :templateIds5_, :templateIds6_, :templateIds7_, :templateIds8_, :templateIds9_, :templateIds10_, :templateIds11_, :templateIds12_, :templateIds13_, :templateIds14_, :templateIds15_, :templateIds16_, :templateIds17_)  and :currentTime = :currentTime  and ctpa.orgAccountId in(:accountId)  and ( ( ctpa.completeTime IS NULL and ctpa.state = :cstate  )  OR  ctpa.completeTime > :endTime )  and ctpa.receiveTime < :endTime  order by  ctpa.receiveTime DESC";
		String sql="select new com.seeyon.apps.wfanalysis.vo.MemberflowDetailVO( ctpa.objectId , ctpa.id , ctpa.app , ctpa.subject , ctpa.activityId , ctpa.nodePolicy , ctpa.memberId , ctpa.receiveTime , ctpa.completeTime , ctpa.runWorktime , ctpa.deadlineDate ,( case   when ctpa.completeTime IS NOT NULL AND ctpa.expectedProcessTime < ctpa.completeTime then true  when ctpa.expectedProcessTime < :currentTime  then true  else false  end ) as  coverTime , ctpa.overWorktime,  ctpa.templeteId,  ctpa.state,  ctpa.expectedProcessTime,  ctpa.caseId, ctpa.processId )  from com.seeyon.ctp.common.po.affair.CtpAffair as ctpa  where ctpa.app in (1,4,19,20,21,22,23,24) and ( ctpa.memberId in (-9028790710386277258,-9020943202209545060,-5193197253577326073,-2328964422221673613,1459950557453502352,1590058100302235107) )  and ctpa.templeteId in(:templateIds)  and :currentTime = :currentTime  and ctpa.orgAccountId in(:accountId)  and ( ( ctpa.completeTime IS NULL and ctpa.state = :cstate  )  OR  ctpa.completeTime > :endTime )  and ctpa.receiveTime < :endTime  order by  ctpa.receiveTime DESC";
		sql= "select count(*) from com.seeyon.ctp.common.po.affair.CtpAffair as ctpa  where ctpa.app in (1,4,19,20,21,22,23,24) and ( ctpa.memberId in (-9028790710386277258,-9020943202209545060,-5193197253577326073,-2328964422221673613,1459950557453502352,1590058100302235107) )  and ctpa.templeteId in(:templateIds0_, :templateIds1_, :templateIds2_, :templateIds3_, :templateIds4_, :templateIds5_, :templateIds6_, :templateIds7_, :templateIds8_, :templateIds9_, :templateIds10_, :templateIds11_, :templateIds12_, :templateIds13_, :templateIds14_, :templateIds15_, :templateIds16_, :templateIds17_)  and :currentTime = :currentTime  and ctpa.orgAccountId in(:accountId)  and ( ( ctpa.completeTime IS NULL and ctpa.state = :cstate  )  OR  ctpa.completeTime > :endTime )  and ctpa.receiveTime < :endTime";
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
