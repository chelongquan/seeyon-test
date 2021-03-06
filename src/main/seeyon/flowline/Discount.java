package main.seeyon.flowline;

public class Discount {
	public enum Code {
		NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

		private final int value;

		Code(int value) {
			this.value = value;
		}
	}

	public static String applyDiscount(Quote quote) {
		return quote.getShopName() + "price :" + Discount.apply(quote.getPrice(), quote.getDiscountCode());
	}

	public static double apply(double price, Code code) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return price * (100 - code.value) / 100;
	}
}
