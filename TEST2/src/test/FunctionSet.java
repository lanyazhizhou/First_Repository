
package test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FunctionSet {

	public List<String> sFunction;
	private Map<String, Integer> FunMap; // ӳ�䣬���ַ���Ҳ���Ǻ������͸��������Ӧ�Ĳ���������ϵ����
	public int MaxParamCount; // �������Ĳ�������������Ĳ�����ָ�������Ҫ�Ĳ���������ӷ���Ҫ2��������sin��Ҫ1���ȵ�

	/**
	 * ��ʼ�� ���ú�����
	 */
	public FunctionSet() { // ���캯��

		this.MaxParamCount = 2; // ������������Ƕ�Ԫ�����
		sFunction = new LinkedList<String>();
		String[] temp = { "+", "-", "*", "/" }; // ���ú�����
		for (int i = 0; i < temp.length; ++i) {
			sFunction.add(temp[i]);
		}

		// ��������� ����ӳ���ϵ
		FunMap = new HashMap<String, Integer>();
		for (int i = 0; i < 4; ++i) {
			FunMap.put(temp[i], 2); // ӳ�����е����뺯����ǰ�ĸ���������Ƕ�Ԫ����������Զ�Ӧ��ֵ����2
		}
		for (int i = 4; i < temp.length; ++i) {
			FunMap.put(temp[i], 1); // ���涼��һԪ���������Ӧ����ֵ��1
		}

	}

	/**
	 * �ж��Ƿ��Ǻ���
	 * 
	 * @param Operator
	 *            �ؼ������������ô��ģ�
	 * @return
	 */
	public boolean IsFunction(String Operator) {
		return this.sFunction.contains(Operator);
	}

	/**
	 * ���� ����� ����������
	 */
	public int GetParamCount(String Operator) { // ��������������ض�Ӧ����������������
		Integer Inte = FunMap.get(Operator);
		if (Inte == null) {
			return 0;
		} else {
			return Inte.intValue();
		}
	}

	/**
	 * ��ȡ�����������ݲ����������ݣ����Եõ���صĽ��
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
