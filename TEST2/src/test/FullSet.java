package test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FullSet {

	public List<String> sFullSet;
	public int length;
	private Random random;

	/**
	 * ��ʼ�� ���ú�����
	 */
	public FullSet() { // ���캯��

		sFullSet = new LinkedList<String>();
		String[] temp = { "+", "-", "*", "/", "0", "1" }; // ���ú�����
		for (int i = 0; i < temp.length; ++i) {
			sFullSet.add(temp[i]);
		}
		length = sFullSet.size();

	}

	public String get(int i) {
		String a = sFullSet.get(i);
		return a;
	}

	public String getRandom() { // �������һ��ȫ���еķ��ţ�������
		random = new Random();
		int nIndex = random.nextInt(this.length); // �������һ����
		return this.get(nIndex); // �������Ӧȫ����һ������
	}

}
