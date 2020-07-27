package main.seeyon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Test2 {
	public static void main(String[] args) {
//		CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "hello";
//        });
//        while (true){}
		
//		  GregorianCalendar calendar = new GregorianCalendar();
//          calendar.setTime(new Date());
////          calendar.add(2, -1);
//          System.out.println(calendar.get(2)+1);
//		Calendar now = Calendar.getInstance();
//		System.out.println(now.get(Calendar.MONTH));
		
		Map<Long, String> m = new HashMap<>();
		m.put(1L,"1" );
		m.put(2L,"1" );
		m.put(3L,"1" );
		m.put(4L,"1" );
		Iterator<Map.Entry<Long, String>> it = m.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Long, String> entry = it.next();
			if(entry.getKey() == 2L) {
				it.remove();
				m.remove(2L);
			}
		
		}
		System.out.println(m);
		
	}

	 
}
