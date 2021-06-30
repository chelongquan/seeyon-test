package main.seeyon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Test {
	public static void main(String[] args) {
		System.out.println(java.nio.charset.Charset.defaultCharset().toString());
		List<User> list = new ArrayList<User>();
		list.add(new User(1L, "a1"));
		list.add(new User(2L, "a2"));
		list.add(new User(3L, "a3"));
		list.add(new User(4L, "a4"));
		list.add(new User(5L, "a5"));
		list.add(new User(6L, "a33"));
		// 1：获取list某字段的集合
		List<Long> ids = list.stream().map(User::getId).collect(Collectors.toList());
		System.out.println(ids);
		// 2:获取字段为特定值的任意对象
		Optional<User> optional = list.stream().filter(u -> u.getId().equals(3L)).findAny();
		if (optional.isPresent()) {
			User u = optional.get();
			System.out.println(u.getName());
		}
		// 3:获取字段为特定值的对象集合
		List<User> uList = list.stream().filter(u -> u.getId().equals(3L)).collect(Collectors.toList());
		System.out.println(uList.size());
		// 4:转map Map<Long, String> codeMap = ((List<?>)
		// orgInfosMap).stream().collect(Collectors.toMap(m -> MapUtils.getLong((Map) m,
		// "orgid"), m -> MapUtils.getString((Map) m, "code")));
		Map<Long, User> m = list.stream().collect(Collectors.toMap(User::getId, u -> u));
		System.out.println(4 + "-->" + m);
		// 5：修改对象属性
		Map<Long, String> m1 = new HashMap<>();
		m1.put(3L, "a33");
		list.stream().forEach(u -> {
			if (m1.get(u.getId()) != null) {
				u.setName(m1.get(u.getId()));
			}
		});
		System.out.println(5 + "-->" + list);
		//0------------
		
		User  u7=new User(7L, "a77");
		Optional<User> u7o= Optional.ofNullable(u7);
		if(u7o.isPresent()) {
			System.out.println(u7o.get());
		}
		u7o.ifPresent(user->{
			System.out.println(user);
		});;
		//-------------------------------
//		List<Map<String,Object>> ownerList = ls.stream().map(signet -> {
//			String markName = signet.getMarkName();
//			if(!user.getLoginAccount().equals(signet.getOrgAccountId())){
//				markName =  markName + "("+ Functions.getAccountShortName(signet.getOrgAccountId())+")";
//			}
//			Map<String,Object> signetObj = new HashMap<>();
//			signetObj.put("name", markName);
//			signetObj.put("id", signet.getId());
//			return signetObj;
//		}).collect(Collectors.toList());
	}

}
