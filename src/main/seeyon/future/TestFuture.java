package main.seeyon.future;

import java.util.concurrent.ExecutionException;

public class TestFuture {
//	public static void main(String[] args) {
//		// 创建Executor-Service，通过他可以向线程池提交任务
//		ExecutorService executor = Executors.newCachedThreadPool();
//		// 向executor-Service提交 Callable对象
//		Future<Double> future = executor.submit(new Callable<Double>() {
//			@Override
//			public Double call() throws Exception {
//				// 异步的方式执行耗时的操作
//				return doSomeLongComputation();
//			}
//		});
//		// 异步时，做其他的事情
//		doSomethingElse();
//
//		try {
//			// 获取异步操作的结果，如果被阻塞，无法得到结果，那么最多等待1秒钟之后退出
//			Double result = future.get(1, TimeUnit.SECONDS);
//			System.out.print(result);
//		} catch (InterruptedException e) {
//			System.out.print("计算抛出一个异常");
//		} catch (ExecutionException e) {
//			System.out.print("当前线程在等待过程中被中断");
//		} catch (TimeoutException e) {
//			System.out.print("future对象完成之前已过期");
//		}
//
//	}
//	/**
//	 * 2
//	 * @param args
//	 * @throws InterruptedException
//	 * @throws ExecutionException
//	 */
//	public static void main(String[] args) throws InterruptedException, ExecutionException {
//		System.out.println("begin");
//		Future<Double> futurePrice = new Shop().getPriceAsync("ss");
//		System.out.println("doSomething");
//		System.out.println(futurePrice.get());
//		System.out.println("end");
//	}
//	/**
//	 * 3:parallelStream
//	 * 
//	 * @param args
//	 * @throws InterruptedException
//	 * @throws ExecutionException
//	 */
//	public static void main(String[] args) throws InterruptedException, ExecutionException {
//		List<Shop> shops = Arrays.asList(new Shop("one"), new Shop("two"), new Shop("three"), new Shop("four"));
//
//		long start = System.nanoTime();
////		List<String> str = shops.parallelStream()
////				.map(shop -> String.format("%s price: %.2f", shop.getName(), shop.getPrice(shop.getName())))
////				.collect(Collectors.toList());
//
//		// CompletableFuture.supplyAsync
//		List<CompletableFuture<String>> str2 = shops.stream()
//				.map(shop -> CompletableFuture.supplyAsync(
//						() -> String.format("%s price: %.2f", shop.getName(), shop.getPrice(shop.getName()))))
//				.collect(Collectors.toList());
//
//		List<String> str3 = str2.stream().map(CompletableFuture::join).collect(Collectors.toList());
//
//		System.out.print(str3);
//		long end = System.nanoTime();
//		System.out.print((end - start) / 1000000);
//	}
	/**
	 * 4
	 * 
	 * @param args
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.print(Runtime.getRuntime().availableProcessors());
	}

	public static Double doSomeLongComputation() throws InterruptedException {
		Thread.sleep(1000);
		return 3 + 4.5;
	}

	public static void doSomethingElse() {
		System.out.print("else");
	}

}
