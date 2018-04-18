package test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FeatureSet {

	public List<String> sFeature;
	public int length;
	private Random random;

	/**
	 * ��ʼ�� ���ñ�����
	 */
	public FeatureSet() { // ���캯��

		sFeature = new LinkedList<String>();
		String[] temp = { "0", "1" }; // ���ñ�����
		for (int i = 0; i < temp.length; ++i) {
			sFeature.add(temp[i]);
		}

		length = sFeature.size();

	}

	public String get(int i) {
		String a = sFeature.get(i);
		return a;
	}

	public String getRandom() { // �������һ��ȫ���еķ��ţ�������
		random = new Random();
		int nIndex = random.nextInt(this.length); // �������һ����
		return this.get(nIndex); // �������Ӧȫ����һ������
	}

}
