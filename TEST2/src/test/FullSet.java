package test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FullSet {

	public List<String> sFullSet;
	public int length;
	private Random random;

	/**
	 * 初始化 设置函数集
	 */
	public FullSet() { // 构造函数

		sFullSet = new LinkedList<String>();
		String[] temp = { "+", "-", "*", "/", "0", "1" }; // 设置函数集
		for (int i = 0; i < temp.length; ++i) {
			sFullSet.add(temp[i]);
		}
		length = sFullSet.size();

	}

	public String get(int i) {
		String a = sFullSet.get(i);
		return a;
	}

	public String getRandom() { // 随机产生一个全集中的符号，并返回
		random = new Random();
		int nIndex = random.nextInt(this.length); // 随机生成一个数
		return this.get(nIndex); // 这个数对应全集中一个符号
	}

}
