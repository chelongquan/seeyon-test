package main.seeyon.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Test {

	
	private static void test1() {
		// 1:lambda表达式就是为了优化匿名内部类而生。
		Function<String, String> fun = (x) -> String.valueOf(x);
		String res = fun.apply("1000");
		System.out.println(res);
	}
	
	static int modifyTheValue(int valueToBeOperated, Function<Integer, Integer> function) {
		return function.apply(valueToBeOperated);
	}
	
	/**
	 * apply方法使用
	 */
	private static void test2() {
		int myNumber = 10;
		
		// 使用lambda表达式实现函数式接口
		// (x)->(x)+20 输入一个参数x，进行加法运算，返回一个结果
		// 所以该lambda表达式可以实现Function接口
		int res1 = modifyTheValue(myNumber, x -> x + 20);
		System.out.println(res1); // 30
		
		//  使用匿名内部类实现
		int res2 = modifyTheValue(myNumber, new Function<Integer, Integer>() {
			@Override
			public Integer apply(Integer t) {
				return t + 20;
			}
		});
		System.out.println(res2); // 30
	}
	/**
	 * andThen方法使用
	 */
	private static void test3() {
		Function<Integer,Integer> fun1=x->{
			System.out.println("fun1");
			return x+2;
		};
		Function<Integer,Integer> fun2=x->{
			System.out.println("fun2");
			return x+3;
		};
		System.out.println(fun1.andThen(fun2).apply(3)); ;
	}
	
	/**
	 * accept方法使用
	 */
	private static void test4() {
		Consumer<Integer> consumer = x->{
			System.out.println(x*2);
		};
		consumer.accept(2);
	}
	
	/**
	 * Predicate方法使用
	 */
	private static void test5() {
		Predicate<Integer> predicate = x->{
			return x==2;
		};
		System.out.println(predicate.test(3)); ;
	}
	
	/**
	 * Predicate方法使用
	 */
	private static void test6() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		
		// Predicate<Integer> predicate = n -> true
		// n 是一个参数传递到 Predicate 接口的 test 方法
		// n 如果存在则 test 方法返回 true
		
		System.out.println("输出所有数据:");
		
		// 传递参数 n
		eval(list, n -> true);
		
		// Predicate<Integer> predicate1 = n -> n%2 == 0
		// n 是一个参数传递到 Predicate 接口的 test 方法
		// 如果 n%2 为 0 test 方法返回 true
		
		System.out.println("\n输出所有偶数:");
		eval(list, n -> n % 2 == 0);
		
		// Predicate<Integer> predicate2 = n -> n > 3
		// n 是一个参数传递到 Predicate 接口的 test 方法
		// 如果 n 大于 3 test 方法返回 true
		
		System.out.println("\n输出大于 3 的所有数字:");
		eval(list, n -> n > 3);
		
	}
	
	public static void eval(List<Integer> list, Predicate<Integer> predicate) {
		for (Integer n : list) {
			if (predicate.test(n)) {
				System.out.print(n + " ");
			}
		}
	}
	
	
	/**
	 * Supplier供给型函数式接口
	 * 无参数，返回一个结果。
	 */
	private static void test7() {
		Supplier<Integer> supplier = ()->{
			return 2+2;
		};
		System.out.println(supplier.get()); ;
	}
	
	
	
	public static void main(String[] args) {
		/*1:Function功能型函数式接口*/
		//		test1();
		//		test2();
		//		test3();
		/*2:Consumer消费型函数式接口*/
		//		test4();
		/*3:Predicate断言型函数式接口*/
//		test5();
//		test6();
		/*3:Supplier供给型函数式接口*/
		test7();
	}
}
