// Builder Pattern - Pages 14-15
package org.effectivejava.examples.chapter02.item02.builder;

/**
 * 不可变类使用Builder模式
 *
 * tip 1 Builder作为静态内部类，因为外部类NutritionFacts不提供public构造方法，
 * 所以外部只能通过static方式访问Builder
 * tip 2 Builder中存有一套NutritionFacts的参数的副本，且必选参数使用final保证不变性。
 * 可选参数要设置default值。
 * tip 3 Builder中对可选参数提供public初始化方法，方法名为参数含义，返回值为Builder对象。
 * tip 4 提供public Builder build()方法，返回要创建的类对象。同时可以在build()方法中
 * 对之前设置的参数进行检验，通过后再返回对象。也可在各参数自己的设置方法中进行检验。
 * tip 5 私有化要被创建的外部类的构造方法，使其只能被Builder创建。同时将Builder作为参数
 * 传入，并将其中的所有参数copy到外部类的参数域中。
 *
 */

public class NutritionFacts {
	private final int servingSize;
	private final int servings;

	private final int calories;
	private final int fat;
	private final int sodium;
	private final int carbohydrate;

	public static class Builder { // tip1. static
		// Required parameters
		// tip2 final 以及builder的参数
		private final int servingSize;
		private final int servings;

		// Optional parameters - initialized to default values
		private int calories = 0;
		private int fat = 0;
		private int carbohydrate = 0;
		private int sodium = 0;

		public Builder(int servingSize, int servings) {
			this.servingSize = servingSize;
			this.servings = servings;
		}

		//tip 3 可选参数的设置
		public Builder calories(int val) {
			calories = val;
			return this;
		}

		public Builder fat(int val) {
			fat = val;
			return this;
		}

		public Builder carbohydrate(int val) {
			carbohydrate = val;
			return this;
		}

		public Builder sodium(int val) {
			sodium = val;
			return this;
		}

		// tip 4 build方法
		public NutritionFacts build() {
			return new NutritionFacts(this);
		}
	}

	// tip 5 私有化外部类构造方法
	private NutritionFacts(Builder builder) {
		servingSize = builder.servingSize;
		servings = builder.servings;
		calories = builder.calories;
		fat = builder.fat;
		sodium = builder.sodium;
		carbohydrate = builder.carbohydrate;
	}

	public static void main(String[] args) {
		NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
				.calories(100).sodium(35).carbohydrate(27).build();
	}
}