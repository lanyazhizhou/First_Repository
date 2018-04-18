package test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FeatureSet {

	public List<String> sFeature;
	public int length;
	private Random random;

	/**
	 * 初始化 设置变量集
	 */
	public FeatureSet() { // 构造函数

		sFeature = new LinkedList<String>();
		String[] temp = { "0", "1" }; // 设置变量集
		for (int i = 0; i < temp.length; ++i) {
			sFeature.add(temp[i]);
		}

		length = sFeature.size();

	}

	public String get(int i) {
		String a = sFeature.get(i);
		return a;
	}

	public String getRandom() { // 随机产生一个全集中的符号，并返回
		random = new Random();
		int nIndex = random.nextInt(this.length); // 随机生成一个数
		return this.get(nIndex); // 这个数对应全集中一个符号
	}

}
