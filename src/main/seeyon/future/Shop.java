package main.seeyon.future;

import java.util.Random;

import main.seeyon.flowline.Discount;

public class Shop {

	private String name;

	public Shop(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	/**
//	 * 1
//	 * @param product
//	 * @return
//	 */
//	public double getPrice(String product) {
//		// 查询商品的数据库，或链接其他外部服务获取折扣
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
//	}
	/**
	 * 2
	 * 
	 * @param product
	 * @return
	 */
	public String getPrice(String product) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Double price = new Random().nextDouble() * product.charAt(0) + product.charAt(1);
		Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
		return String.format("%s:%.2f:%s", name, price, code);
	}

//	public Future<Double> getPriceAsync(String product) {
//		// 创建CompletableFuture对象
//		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
//
//		new Thread(() -> {
//			try {
//				// 在另一个线程中执行计算
//				double price = getPrice(product);
//				// 需要长时间计算的任务结束并得出结果时，设置future的返回值
//				futurePrice.complete(price);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}).start();
//		return futurePrice;
//	}

//	public Future<Double> getPriceAsync(String product) {
//		return CompletableFuture.supplyAsync(() -> getPrice(product));
//	}
}
