/**
 * Author seeyon-chelq
 * Rev
 * Date: 2020-12-30 17:19
 * Copyright (C) 2020 Seeyon, Inc. All rights reserved.
 * This software is the proprietary information of Seeyon, Inc.
 * Use is subject to license terms.
 */
package main.seeyon;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @date 2020-12-30 17:19
 * @since seeyon-test
 * @author seeyon-chelq
 */
public class TestCountDownLatch {
	public static ExecutorService RptExecutor = Executors.newFixedThreadPool(10, new ThreadFactory() {
		private AtomicInteger threadNum = new AtomicInteger(1);
		
		@Override
		public Thread newThread(Runnable r) {
			return new Thread(r, "Thread_" + threadNum.getAndIncrement());
		}
	});
	
	public static void main(String[] args) {
//		1：testCountDownLatch
//		testCountDownLatch();
		testCompletableFuture();
	}
	private static void testCompletableFuture(){
		Map _map = new HashMap();
		_map.put("1", 1);
		_map.put("2", 1);
		_map.put("3", 3);
		_map.put("4", 4);
		_map.put("5", 5);
		_map.put("6", 6);
		_map.put("7", 7);
		
		List<Map> listMap = mapChunk(_map,2);
		List<Future<Map<String, Integer>>> completableFutures = Lists.newArrayList();
		for (Map mapData : listMap) {
			CompletableFuture<Map<String, Integer>> completableFuture = CompletableFuture.supplyAsync(() -> {
				try {
					Thread.sleep(5*1000);
					System.out.println(Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return mapData;
			}, RptExecutor);
			completableFutures.add(completableFuture);
		}
		CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).join();
		try {
			 Map<String, Integer> tMap = new HashMap<String, Integer>();
			for(Future<Map<String, Integer>> ft :completableFutures){
				Map<String, Integer> o = ft.get();
				tMap.putAll(o);
			}
			System.out.println(tMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RptExecutor.shutdown();
	}
	private static void testCountDownLatch(){
		Map _map = new HashMap();
		_map.put("1", 1);
		_map.put("2", 1);
		_map.put("3", 3);
		_map.put("4", 4);
		_map.put("5", 5);
		_map.put("6", 6);
		_map.put("7", 7);
		
		List<Map> listMap = mapChunk(_map,2);
		CountDownLatch countDownLatch = new CountDownLatch(listMap.size());
		List<Future<Map<String, Integer>>> ftList = Lists.newArrayList();
		for (Map mapData : listMap) {
			Future<Map<String, Integer>> result = RptExecutor.submit(() -> {
				try {
					System.out.println("---------"+Thread.currentThread().getName());
					Thread.sleep(2*1000);
					return mapData;
				}finally {
					countDownLatch.countDown();
				}
			});
			ftList.add(result);
		}
		RptExecutor.shutdown();
		try {
			//等待子线程全部结束
			countDownLatch.await();
			
			Map<String, Integer> tMap = new HashMap<String, Integer>();
			for(Future<Map<String, Integer>> ft :ftList){
				Map<String, Integer> o = ft.get();
				tMap.putAll(o);
			}
			System.out.println(tMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	/**
	 * 将map 拆分成多个map
	 *
	 * @param chunkMap 被拆的 map
	 * @param chunkNum 每段的大小
	 * @param <k>      map 的 key类 型
	 * @param <v>      map 的value 类型
	 * @return List
	 */
	public static <k, v> List<Map<k, v>> mapChunk(Map<k, v> chunkMap, int chunkNum) {
		if (chunkMap == null || chunkNum <= 0) {
			List<Map<k, v>> list = new ArrayList<>();
			list.add(chunkMap);
			return list;
		}
		Set<k> keySet = chunkMap.keySet();
		Iterator<k> iterator = keySet.iterator();
		int i = 1;
		List<Map<k, v>> total = new ArrayList<>();
		Map<k, v> tem = new HashMap<>();
		while (iterator.hasNext()) {
			k next = iterator.next();
			tem.put(next, chunkMap.get(next));
			if (i == chunkNum) {
				total.add(tem);
				tem = new HashMap<>();
				i = 0;
			}
			i++;
		}
		
		if (!CollectionUtils.isEmpty(tem)) {
			total.add(tem);
		}
		return total;
	}
}
