package test;

/**
 * 适应度函数 对不同的问题采用不同的适应度
 * 
 * @author shenzhan
 *
 */
public class FitnessFunction {

	public FitnessFunction() {

	}

	/**
	 * 个体表达类
	 */
	protected Expression Exp;
	public boolean work = true;

	/**
	 * 计算适应度
	 * 
	 * @param Pop
	 * @param Data
	 * @param Fitness
	 */
	public double GetFitness(Individual indiv, double[][] Data) {
		Exp = new Expression();
		int nRow = Data.length; // 数据集的行数
		int nCol = Data[0].length; // 数据集的列数
		double value = 0; // 累积每行数据计算的值

		int j, i = 0;

		for (j = 0; j < nRow; ++j) {

			double Value = Exp.GetGeneValue(indiv.Chrom, Data[j]);

			if (Exp.work) { // 当公式中出现除数为0时，这个work变量为false

				value = value + Math.pow(Value - Data[j][nCol - 1], 2); // 求差值平方和

			} else {
				i++;

			}
			Exp.work = true;
			if (i == 3) { // 当出现3次即以上的除数为0情况，整个公式被抛弃
				work = false;

				break;
			}
			// 这里还没有写完，上面是根据数据集得到了一个表达式的计算结果，然后该计算该个体对应的适应度了
			// System.out.println("此时输出的是每行数据集对应的值： " + Value);
			// System.out.println("此时输出的是准确表达式对应的值： " + Data[j][nCol - 1]);

		}
		if (work) { // 如果这个公式没有被抛弃，返回适应度
			double fitness = Math.sqrt(value / nRow - i); // 求均方误差

			return fitness;
		} else {

			return 0;
		}

	}
}
