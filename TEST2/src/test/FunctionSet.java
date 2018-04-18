
package test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FunctionSet {

	public List<String> sFunction;
	private Map<String, Integer> FunMap; // 映射，将字符串也就是函数，和该运算符对应的参数个数联系起来
	public int MaxParamCount; // 设置最大的参数数量，这里的参数是指运算符需要的参数，比如加法需要2个参数，sin需要1个等等

	/**
	 * 初始化 设置函数集
	 */
	public FunctionSet() { // 构造函数

		this.MaxParamCount = 2; // 最大的运算符就是二元运算符
		sFunction = new LinkedList<String>();
		String[] temp = { "+", "-", "*", "/" }; // 设置函数集
		for (int i = 0; i < temp.length; ++i) {
			sFunction.add(temp[i]);
		}

		// 与参数个数 建立映射关系
		FunMap = new HashMap<String, Integer>();
		for (int i = 0; i < 4; ++i) {
			FunMap.put(temp[i], 2); // 映射类中的输入函数，前四个运算符都是二元运算符，所以对应的值都是2
		}
		for (int i = 4; i < temp.length; ++i) {
			FunMap.put(temp[i], 1); // 后面都是一元运算符，对应的数值是1
		}

	}

	/**
	 * 判断是否是函数
	 * 
	 * @param Operator
	 *            关键在你这里，你怎么想的，
	 * @return
	 */
	public boolean IsFunction(String Operator) {
		return this.sFunction.contains(Operator);
	}

	/**
	 * 返回 运算符 操作数个数
	 */
	public int GetParamCount(String Operator) { // 输入操作符，返回对应操作符操作数个数
		Integer Inte = FunMap.get(Operator);
		if (Inte == null) {
			return 0;
		} else {
			return Inte.intValue();
		}
	}

	/**
	 * 获取计算结果，传递操作符和数据，可以得到相关的结果
	 */
	public double GetResult(String Operator, double[] Data) {

		if (Operator.equals("+")) {
			return Data[0] + Data[1];
		} else if (Operator.equals("-")) {
			return Data[0] - Data[1];
		} else if (Operator.equals("*")) {
			return Data[0] * Data[1];
		} else if (Operator.equals("/")) {
			return Data[0] / Data[1]; // --------------------------------
		} else if (Operator.equals("sin")) {
			return Math.sin(Data[0]);
		} else if (Operator.equals("cos")) {
			return Math.cos(Data[0]);
		} else if (Operator.equals("sqrt")) {
			return Math.sqrt(Data[0]);
		} else if (Operator.equals("tan")) {
			return Math.tan(Data[0]);
		} else if (Operator.equals("pow2")) {
			return Math.pow(Data[0], 2);
		} else if (Operator.equals("log")) {
			return Math.log(Data[0]);
		} else if (Operator.equals("abs")) {
			return Math.abs(Data[0]);
		}

		return 0;
	}

}
