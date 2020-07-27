package main.seeyon.flowline;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import main.seeyon.future.Shop;

public class Test {
//	/**
//	 * 1：[oneprice :152.456, twoprice :98.79299999999999, threeprice :164.1775, fourprice :172.1675]8104
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		List<Shop> shops = Arrays.asList(new Shop("one"), new Shop("two"), new Shop("three"), new Shop("four"));
//		long start = System.currentTimeMillis();
//		List<String> str = shops.stream().map(shop -> shop.getPrice("hhhhh")) // 获取 one:120.10:GOLDD 格式字符串
//				.map(Quote::parse) // 转换为 Quote 对象
//				.map(Discount::applyDiscount) // 返回 Quote的shop中的折扣价格
//				.collect(Collectors.toList());
//		System.out.print(str);
//		long end = System.currentTimeMillis();
//		System.out.print((end - start));
//	}
	/**
	 * 2:[oneprice :107.613, twoprice :99.75, threeprice :150.55200000000002,
	 * fourprice :143.244]2093
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		List<Shop> shops = Arrays.asList(new Shop("one"), new Shop("two"), new Shop("three"), new Shop("four"));
		long start = System.currentTimeMillis();

		List<CompletableFuture<String>> priceFutures = shops.stream()
				// 异步获取每个shop中的价格
				.map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice("hhhhh")))
				// Quote对象存在时，对其返回值进行转换--》
				// thenApply方法：当第一个Future运行结束，返回CompletableFuture<String>对象转换为CompleTableFuture<Quote>对象。
				.map(future -> future.thenApply(Quote::parse))
				// 使用另一个异步任务构造期望的future，申请折扣--》
				// thenCompose方法：将两个异步操作进行流水线，当第一个操作完成时，将其结果作为参数传递给第二个操作。换句话说，
				// 你可以创建两个CompletableFuture对象，对第一个对象调用thenCompose，并向其传递一个函数。
				.map(future -> future
						.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote))))
				.collect(Collectors.toList());
		// 等待流中的所有Future执行完毕，提取各自的返回值
//		List<String> str = priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
		List<String> str = priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
		System.out.print(str);

		long end = System.currentTimeMillis();
		System.out.print((end - start));
	}

}
