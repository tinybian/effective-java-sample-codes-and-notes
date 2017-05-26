### 第一条 静态工厂方法 or 构造函数
静态工厂方法不是设计模式中的工厂方法，而是用过静态函数调用创建类的实例，而非使用new来通过构造函数创建实例

####静态构造方法常用名称
* getInstance —— 通过参数获取对应实例，但不一定是唯一实例。但对于Singleton，该方法没有参数，并返回唯一实例 。
* newInstance —— 与getInstance一样，但是能够确保返回的每个实例都与其他实例不同。
* getType —— 和getInstance功能类似，在工厂方法处于不同的类中的时候使用，Type表示工厂方法所返回的对象类型
* newType —— 和newInstance功能类似，在工厂方法处于不同的类中的时候使用
* valueOf —— 返回的实例和其参数具有相同的值，实际上是类型转换方法
* of —— valueOf的替代

####静态工厂方法优点
* **有名称**
	* 构造函数不能清晰地描述正被返回的对象
	* 当需要通过不同参数获取类的实例时，多个构造方法只能通过参数区分，容易出错且意义表达不明确，而使用静态工厂方法可读性更好。
``` java
	class Number{
		private int num;
		public static Number getOddNumber(Random random){...};
		public static Number getPrimNumber(Random random){...};
	}
```
* **不必每次调用时都创建对象**
	* 对于是否新建实例静态工厂方法是可控制的
	* 使得不可变的类（第15条）不会同时存在两个相等的实例，即当且仅当a == b的时候才有a.equals(b)为真。这样就可以使用==操作符来代替equals(Object)方法，提升性能，直接比较地址即可。
* **可以返回原返回类型的任意子类对象**
	* 可以返回非公有的类   ？？？
	* 可以使用接口作为返回类型，由此产生 *服务提供者框架*
``` java
	interface Type{
	}
	class Types{
		private Types(){} // 不可实例化 用作静态工具类
		public static Type newInstance(){
			return new Type();
		}
	}
```
####静态工厂方法缺点
* 如果构造函数不是public 或者 protected 返回的类型就不能被子类化
* 与静态方法没区别，在文档上不能有效区分，而构造函数在javadoc中明确标识了