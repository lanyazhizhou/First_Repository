package test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

//注意一下，在GEP中染色体是包含多个基因的，每个基因都是一个子表达式树

/**
 * 染色体Chromosome
 * 
 **/
public class Individual implements Cloneable {
	/**
	 * 基因的适应值
	 **/
	public double Fitness;

	public double Value;
	public double Wheelvalue; // 因为赌轮盘时，值大的容易被选中，而我的适应度是设置的越小越好，这里要设置一个单独的赌轮盘值
	public boolean live; // 表明该个体是否已经被赋值，如果没有被赋值则为false，否则为ture，没什么大用，主要是用于最优适应度上
	public boolean work = true; // 表明这个公式个体是否有效，如果无效要被删除！
	public static int GeneLength = 21; // 基因长度
	private int GeneCount = 1; // 基因数量
	public int headlength = 10;
	public int taillength = 11;
	private double[][] Dataset;

	public static File file = new File("E:\\DataSet\\11.txt"); // 数据集所在的位置

	/**
	 * 染色体 字符串
	 **/
	public List<String> Chrom;

	public Individual() { // 构造函数，个体对象一旦创建，就生成对应的染色体字符串
		this.Chrom = new LinkedList<String>();
		this.live = false;

	}

	/**
	 * 求该个体的适应度
	 * 
	 * @throws IOException
	 */

	public double GetFitness() throws IOException {
		Data data = new Data(); // 根节点生成时，创建数据集
		Dataset = data.getData(file);

		FitnessFunction fit = new FitnessFunction();

		// System.out.println("数据集的长度:"+Data.length);
		// System.out.println("数据集中变量个数:"Data[0].length);
		this.Fitness = fit.GetFitness(this, Dataset); // 调用适应度函数，得到适应度
		if (!fit.work) {
			this.work = false;
			this.Fitness = 5000000;

		}
		return Fitness;

	}

	/**
	 * 添加基因
	 * 
	 * @param gene
	 */
	public void AddGene(List<String> gene) { // 添加基因，需要传递参数，将该基因添加到染色体中
		List<String> listTemp = new LinkedList<String>(); // 字符串集合，链式存储
		for (int i = 0; i < gene.size(); ++i) {
			listTemp.add(gene.get(i));
		}
		this.Chrom.addAll(listTemp); // 将该基因全部添加到染色体中，需要注意的是，这里添加字符串数组，也就是多个基因
	}

	/**
	 * Get
	 * 
	 * @param nIndex
	 * @return
	 */
	public String Get(int nIndex) { // 这里是传递索引参数，然后返回染色体中该索引对应的字符串
		return Chrom.get(nIndex);
	}

	/**
	 * Set
	 * 
	 * @param nIndex
	 * @param str
	 */
	public void Set(int nIndex, String str) { // 这里是更改索引对应的字符串
		Chrom.set(nIndex, str);
	}

	/**
	 * 设置个体的值
	 * 
	 * @param Indiv
	 */
	public void SetValue(Individual Indiv) { // 将传递过来的个体基因全部加到自己染色体后面
		this.Chrom = new LinkedList<String>();
		for (int i = 0; i < Indiv.Chrom.size(); ++i) {
			this.Chrom.add(Indiv.Chrom.get(i));
		}
	}

	/**
	 * 个体克隆
	 */
	public Individual clone() {
		Individual Indiv = null; // 创建一个空个体
		try {
			Indiv = (Individual) super.clone(); // 克隆一个个体，完全一样，某种意义上可以认为是赋值
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		Indiv.Chrom = new LinkedList<String>();
		for (int i = 0; i < this.Chrom.size(); ++i) {
			String sCode = new String(this.Chrom.get(i));
			Indiv.Chrom.add(sCode); // 这里貌似染色体没有克隆过来，所以在这里赋值一遍染色体
		}

		Indiv.Fitness = this.Fitness; // 复制适应度
		Indiv.Value = this.Value; // 复制个体的值

		return Indiv; // 返回克隆的个体
	}

	/**
	 * 获取基因 基因编号从0开始
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
