package test;

import java.util.List;

//  ���������ݵ�������31��32
//

/**
 * ������ ��������ֵ
 **/
public class Expression {

	private FunctionSet Fun;
	private double[] Data;
	private double[] GeneData; // ������� ��

	private int nValidLen; // ��Ч����
	private int GeneLength; // ���򳤶�
	private int GeneCount; // ��������
	public boolean work = true;

	public Expression() { // ���캯��
		Fun = new FunctionSet();
		this.GeneLength = Individual.GeneLength; // �������һЩ���ݡ���������������
		// System.out.println("Ⱦɫ��ĳ��ȣ�" + GeneLength);
		this.GeneCount = 1; // ����Ҳ����һЩ���ݣ�������������
		this.GeneData = new double[GeneLength]; // �����GeneData�����洢�����з��������λ
	}

	/**
	 * ����ÿ�������ֵ
	 * 
	 * @param Gene
	 */
	public double GetGeneValue(List<String> Gene, double[] Data) { // ����ÿ�������ֵ
		this.Data = Data; // �����DataӦ�������ݼ�,ÿһ�е����ݼ�

		this.FillData(Gene); // ���������Ҫ�ǽ������������ֵȫ�����뵽һ������GeneData��
		int nButton = this.GetValidLength(Gene); // �õ��������Ч����

		int nParamCount;
		int j, k;
		int i = nButton - 1;
		for (; i >= 0; --i) { // �Ӻ���ǰ����

			nParamCount = Fun.GetParamCount(Gene.get(i)); // ��������ǲ�������Ӧ�Ĳ���
			if (nParamCount > 0) { // �����Ӧ�Ĳ�������0����ʾ�������λ��һ���������Ȼ��Ϳ��Խ���������
				double[] dData = new double[nParamCount];
				k = 0;
				for (j = nButton - nParamCount; j < nButton; ++j) {
					dData[k++] = this.GeneData[j]; // ����Ҫ�Ĳ������뵽���������������
				}

				if (Gene.get(i).equalsIgnoreCase("/") && dData[1] < 0.00000000000000001) {

					this.work = false;
					break;

				}
				this.GeneData[i] = Fun.GetResult(Gene.get(i), dData);
				nButton -= nParamCount;

			}
		}

		return GeneData[0]; // ���ظû�������ս����Ҳ����˵�Ǳ��ʽ�����
	}

	/**
	 * ����������Ч����
	 * 
	 * @return
	 */
	public int GetValidLength(List<String> Gene) { // ����Ϊ����,����ֵΪ��Ч����
		int i = 0;
		int nValidLen = 1; // �������Ч���ȵļ���
		int nParam; // ����Ǽ�������ĸ���
		do {
			// System.out.println("�õ����ȣ�" + i);
			nParam = Fun.GetParamCount(Gene.get(i)); // �����������õ��ú�����Ӧ�Ĳ�������

			nValidLen += nParam; // ע�⵱����ĵ�iλ�����������ʱ�򣬷��صĲ�������Ϊ0��Ҳ������Ч��������Ϊ0
			++i;
		} while (i < nValidLen);
		return nValidLen;
	}

	/**
	 * �������
	 * 
	 * @param Gene
	 */
	private void FillData(List<String> Gene) { // ��������õ���һ������
		int nLen = this.GetValidLength(Gene); // ��¼����������Ч����
		// System.out.println("�������Ч����Ϊ��" + nLen);
		for (int i = 0; i < nLen; ++i) { // ��һ��ʼ����Ч���ȿ�ʼ����ѭ������
			int nParam = Fun.GetParamCount(Gene.get(i)); // Fun�Ǻ�������Ķ��󣬶�ÿһ������λ�������
			if (0 == nParam) { // �������λ��Ӧ�Ĳ���λΪ0����˵����һλ��һ����ֵ
				String sNum = Gene.get(i); // �õ��û���λ����ֵ
				int nIndex = Integer.parseInt(sNum); // �����е���ֵ���Ǵ�����λ�ã�������ʵ����ֵ
				GeneData[i] = Data[nIndex]; // ���ݻ������ֵ�����õ���Ҫ����ֵ
			}
		}
	}

	public int GetIndivValidLen(Individual Indiv) { // �õ������������Ч���ȣ��������
		int i;
		int res = 0;
		// ����ÿ�������ֵ ���Ӻ���ʹ�� +
		// //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		for (i = 0; i < this.GeneCount; ++i) {
			List<String> listGene = Indiv.Chrom.subList(i * GeneLength, i * GeneLength + GeneLength);
			// Indiv����һ������ʵ����Chrom��һ��list���ϣ�String����
			res += GetValidLength(listGene);
		}
		return res;
	}

	/**
	 * ����ʹ�õ������Եĸ���
	 * 
	 * @param Indiv
	 * @return
	 */
	public int GetAttriNum(Individual Indiv) { // Ӧ��û���õ���

		int i;
		int res = 0;
		// ����ÿ�������ֵ ���Ӻ���ʹ�� +
		// //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		for (i = 0; i < this.GeneCount; ++i) {
			List<String> listGene = Indiv.Chrom.subList(i * GeneLength, i * GeneLength + GeneLength);
			int nLen = GetValidLength(listGene); // ������һ���ǵõ�һ����������ǵõ������������Ч����
			for (int j = 0; j < nLen; ++j) {
				if (0 == Fun.GetParamCount(listGene.get(j))) {
					++res; // �õ��û������Ч��ֵ����
				}
			}

		}
		return res; // ���ص�������Ⱦɫ���������Ч����ֵ�������Լ�����������
	}

}
