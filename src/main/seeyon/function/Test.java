package main.seeyon.function;

import java.util.function.Consumer;
import java.util.function.Function;

public class Test {
	public static void main(String[] args) {
//		test1();
//		test2();
//		test3();
		test4();
		
		
	}
	
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
}
