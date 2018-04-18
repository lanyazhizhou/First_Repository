package test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

//ע��һ�£���GEP��Ⱦɫ���ǰ����������ģ�ÿ��������һ���ӱ��ʽ��

/**
 * Ⱦɫ��Chromosome
 * 
 **/
public class Individual implements Cloneable {
	/**
	 * �������Ӧֵ
	 **/
	public double Fitness;

	public double Value;
	public double Wheelvalue; // ��Ϊ������ʱ��ֵ������ױ�ѡ�У����ҵ���Ӧ�������õ�ԽСԽ�ã�����Ҫ����һ�������Ķ�����ֵ
	public boolean live; // �����ø����Ƿ��Ѿ�����ֵ�����û�б���ֵ��Ϊfalse������Ϊture��ûʲô���ã���Ҫ������������Ӧ����
	public boolean work = true; // ���������ʽ�����Ƿ���Ч�������ЧҪ��ɾ����
	public static int GeneLength = 21; // ���򳤶�
	private int GeneCount = 1; // ��������
	public int headlength = 10;
	public int taillength = 11;
	private double[][] Dataset;

	public static File file = new File("E:\\DataSet\\11.txt"); // ���ݼ����ڵ�λ��

	/**
	 * Ⱦɫ�� �ַ���
	 **/
	public List<String> Chrom;

	public Individual() { // ���캯�����������һ�������������ɶ�Ӧ��Ⱦɫ���ַ���
		this.Chrom = new LinkedList<String>();
		this.live = false;

	}

	/**
	 * ��ø������Ӧ��
	 * 
	 * @throws IOException
	 */

	public double GetFitness() throws IOException {
		Data data = new Data(); // ���ڵ�����ʱ���������ݼ�
		Dataset = data.getData(file);

		FitnessFunction fit = new FitnessFunction();

		// System.out.println("���ݼ��ĳ���:"+Data.length);
		// System.out.println("���ݼ��б�������:"Data[0].length);
		this.Fitness = fit.GetFitness(this, Dataset); // ������Ӧ�Ⱥ������õ���Ӧ��
		if (!fit.work) {
			this.work = false;
			this.Fitness = 5000000;

		}
		return Fitness;

	}

	/**
	 * ��ӻ���
	 * 
	 * @param gene
	 */
	public void AddGene(List<String> gene) { // ��ӻ�����Ҫ���ݲ��������û�����ӵ�Ⱦɫ����
		List<String> listTemp = new LinkedList<String>(); // �ַ������ϣ���ʽ�洢
		for (int i = 0; i < gene.size(); ++i) {
			listTemp.add(gene.get(i));
		}
		this.Chrom.addAll(listTemp); // ���û���ȫ����ӵ�Ⱦɫ���У���Ҫע����ǣ���������ַ������飬Ҳ���Ƕ������
	}

	/**
	 * Get
	 * 
	 * @param nIndex
	 * @return
	 */
	public String Get(int nIndex) { // �����Ǵ�������������Ȼ�󷵻�Ⱦɫ���и�������Ӧ���ַ���
		return Chrom.get(nIndex);
	}

	/**
	 * Set
	 * 
	 * @param nIndex
	 * @param str
	 */
	public void Set(int nIndex, String str) { // �����Ǹ���������Ӧ���ַ���
		Chrom.set(nIndex, str);
	}

	/**
	 * ���ø����ֵ
	 * 
	 * @param Indiv
	 */
	public void SetValue(Individual Indiv) { // �����ݹ����ĸ������ȫ���ӵ��Լ�Ⱦɫ�����
		this.Chrom = new LinkedList<String>();
		for (int i = 0; i < Indiv.Chrom.size(); ++i) {
			this.Chrom.add(Indiv.Chrom.get(i));
		}
	}

	/**
	 * �����¡
	 */
	public Individual clone() {
		Individual Indiv = null; // ����һ���ո���
		try {
			Indiv = (Individual) super.clone(); // ��¡һ�����壬��ȫһ����ĳ�������Ͽ�����Ϊ�Ǹ�ֵ
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		Indiv.Chrom = new LinkedList<String>();
		for (int i = 0; i < this.Chrom.size(); ++i) {
			String sCode = new String(this.Chrom.get(i));
			Indiv.Chrom.add(sCode); // ����ò��Ⱦɫ��û�п�¡���������������︳ֵһ��Ⱦɫ��
		}

		Indiv.Fitness = this.Fitness; // ������Ӧ��
		Indiv.Value = this.Value; // ���Ƹ����ֵ

		return Indiv; // ���ؿ�¡�ĸ���
	}

	/**
	 * ��ȡ���� �����Ŵ�0��ʼ
	 * 
	 * @param n
	 * @return
	 */
	public List<String> GetGene(int n) {
		if (n < 0 || n >= GeneCount) {
			return null;
		}

		return this.Chrom.subList(n * GeneLength, n * GeneLength + GeneLength);

	}

}
