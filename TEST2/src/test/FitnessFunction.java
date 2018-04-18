package test;

/**
 * ��Ӧ�Ⱥ��� �Բ�ͬ��������ò�ͬ����Ӧ��
 * 
 * @author shenzhan
 *
 */
public class FitnessFunction {

	public FitnessFunction() {

	}

	/**
	 * ��������
	 */
	protected Expression Exp;
	public boolean work = true;

	/**
	 * ������Ӧ��
	 * 
	 * @param Pop
	 * @param Data
	 * @param Fitness
	 */
	public double GetFitness(Individual indiv, double[][] Data) {
		Exp = new Expression();
		int nRow = Data.length; // ���ݼ�������
		int nCol = Data[0].length; // ���ݼ�������
		double value = 0; // �ۻ�ÿ�����ݼ����ֵ

		int j, i = 0;

		for (j = 0; j < nRow; ++j) {

			double Value = Exp.GetGeneValue(indiv.Chrom, Data[j]);

			if (Exp.work) { // ����ʽ�г��ֳ���Ϊ0ʱ�����work����Ϊfalse

				value = value + Math.pow(Value - Data[j][nCol - 1], 2); // ���ֵƽ����

			} else {
				i++;

			}
			Exp.work = true;
			if (i == 3) { // ������3�μ����ϵĳ���Ϊ0�����������ʽ������
				work = false;

				break;
			}
			// ���ﻹû��д�꣬�����Ǹ������ݼ��õ���һ�����ʽ�ļ�������Ȼ��ü���ø����Ӧ����Ӧ����
			// System.out.println("��ʱ�������ÿ�����ݼ���Ӧ��ֵ�� " + Value);
			// System.out.println("��ʱ�������׼ȷ���ʽ��Ӧ��ֵ�� " + Data[j][nCol - 1]);

		}
		if (work) { // ��������ʽû�б�������������Ӧ��
			double fitness = Math.sqrt(value / nRow - i); // ��������

			return fitness;
		} else {

			return 0;
		}

	}
}
