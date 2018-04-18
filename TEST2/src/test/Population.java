package test;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

public class Population {
	LinkedList<Individual> pop = new LinkedList<Individual>();
	LinkedList<Individual> most_indivs = new LinkedList<Individual>(); // 每代种群中最优的一部分个体
	public int mostindivs_number = 5;
	int Populationsize = 100;

	Individual BestIndividual; // 种群中最优个体
	int BestIndivNum; // 种群中最优个体的编号
	public int MaxGeneration = 10000; // 最大迭代次数
	public double MutationRate = 0.1; // 变异率
	public double OnePRecomRate = 0.3; // 一点重组概率
	public double TwoPRecomRate = 0.2; // 两点重组概率
	public double GeneRecomRate = 0.1; // 基因重组概率
	public double ISRate; // IS转做概率
	public int[] ISElemLength;
	public double RISRate; // RIS转座概率
	public int[] RISElemLength;
	public double GeneTransRate;

	public FullSet fullset;
	public FeatureSet featureset;

	public void Initialization() { // 种群初始化

		FullSet full = new FullSet();
		FeatureSet feature = new FeatureSet();
		for (int i = 0; i < Populationsize; i++) {
			int j = 0;
			Individual indiv = new Individual();
			for (; j < indiv.headlength; j++) {
				indiv.Chrom.add(full.getRandom());
			}
			for (j = 0; j < indiv.taillength; j++) {
				indiv.Chrom.add(feature.getRandom());
			}
			pop.add(indiv);

		}

	}

	public void EvaluateFitness() throws IOException { // 评估种群适应度

		for (int i = 0; i < pop.size(); i++) {
			Individual indiv = pop.get(i); // 同一个引用变量赋值
			indiv.work = true;
			indiv.Fitness = indiv.GetFitness();
			if (!indiv.work) { // 如果出现除数为0的情况，就将这个个体消除，以免报错

				this.pop.remove(indiv);
				i--;
			}

		}
	}

	/**
	 * 选择在群体中进行选择，采用赌轮盘策略
	 * 
	 * 
	 **/
	public void Select() {
		Population NewPop = new Population();
		this.FindBestIndividual();
		this.GetWheelVaule(); // 因为这里的适应度是越小越好，不满足赌轮盘的要求，所以要进行一下转换
		for (int i = 0; i < this.most_indivs.size(); i++) {
			NewPop.pop.add(this.most_indivs.get(i)); // 精英策略
		}

		double dTotal = 0;
		for (int i = 0; i < this.pop.size(); ++i) {

			dTotal += this.pop.get(i).Wheelvalue;
		}

		// 每个个体的概率
		double[] dRate = new double[this.pop.size()];

		if (dTotal == 0) { // 当总的轮盘值为0，那么就随机选取
			for (int i = 0; i < this.pop.size(); ++i) {
				dRate[i] = 1 / (double) this.pop.size();
			}
		} else {
			for (int i = 0; i < this.pop.size(); ++i) {
				dRate[i] = this.pop.get(i).Wheelvalue / dTotal;
			}
		}

		// 轮赌盘
		double[] dWheel = new double[this.pop.size()];
		for (int i = 0; i < this.pop.size(); ++i) {
			if (0 == i) {
				dWheel[i] = dRate[i];
			} else {
				dWheel[i] = dWheel[i - 1] + dRate[i];
			}
		}

		// 选择
		Random random = new Random();
		for (int i = this.mostindivs_number; i < this.Populationsize; ++i) {
			double d = random.nextDouble();
			int j = 0;
			for (j = 0; j < this.pop.size(); ++j) {
				if (d < dWheel[j]) { // 轮盘转动
					break;
				}
			}
			if (j >= this.pop.size()) {
				j = this.pop.size() - 1;
			}
			NewPop.pop.add(this.pop.get(j)); // 加入到新的种群中
		}
		System.out.println("选择完毕以后种群数量：" + NewPop.pop.size());

		for (int i = 0; i < NewPop.pop.size(); i++) { // 对个体进行克隆，避免Java中的对象引用重复！
			NewPop.pop.set(i, NewPop.pop.get(i).clone());
		}
		this.pop = NewPop.pop;

	}

	public void GetWheelVaule() {

		double minFitness = this.BestIndividual.Fitness;
		int i = 0;

		for (; i < this.pop.size(); i++) {
			this.pop.get(i).Wheelvalue = minFitness / this.pop.get(i).Fitness;

		}

	}

	/**
	 * 查找最优个体
	 */
	public void FindBestIndividual() {
		int nMin = 0;

		double dminFitness = this.pop.get(0).Fitness;
		int i;
		for (i = 1; i < this.pop.size(); ++i) {
			if (this.pop.get(i).Fitness < dminFitness) {
				dminFitness = this.pop.get(i).Fitness;
				nMin = i;
			}
		}
		this.BestIndivNum = nMin;
		this.BestIndividual = this.pop.get(nMin);
	}

	/*
	 * 每代保留一定数目的精英，这些精英不会参与交叉变异
	 */

	public void Population_sort() {
		Collections.sort(this.pop, new Comparator<Individual>() {
			/*
			 * 对种群的适应度进行排序，以升序
			 */
			public int compare(Individual indiv1, Individual indiv2) {
				// 按照Person的年龄进行升序排列
				if (indiv1.Fitness > indiv2.Fitness) {
					return 1;
				}
				if (indiv1.Fitness == indiv2.Fitness) {
					return 0;
				}
				return -1;
			}
		});
		if (most_indivs.size() == 0) {
			for (int i = 0; i < this.mostindivs_number; i++) {
				most_indivs.add(this.pop.get(i).clone()); // 这时候要赋值给克隆体，否则交叉变异的时候，这些最优个体就会发生变化
			}
		} else {
			for (int i = 0; i < this.mostindivs_number; i++) {
				most_indivs.set(i, this.pop.get(i).clone());
			}
		}

	}

	// 变异
	public void Mutation() {
		fullset = new FullSet();
		featureset = new FeatureSet();
		Random random = new Random();
		for (int i = 0; i < this.pop.size(); ++i) {

			for (int j = 0; j < this.pop.get(i).GeneLength; ++j) { // 针对每一位基因来进行变异，而不是个体

				if (random.nextDouble() < this.MutationRate) {
					int nIndex = j % this.pop.get(i).GeneLength;
					int k;
					// 基因头部
					if (nIndex < this.pop.get(i).headlength) {
						k = random.nextInt(fullset.length);
						this.pop.get(i).Set(j, fullset.getRandom()); // 设置变异的基因
					} else { // 尾部
						k = random.nextInt(featureset.length);
						this.pop.get(i).Set(j, featureset.getRandom()); // 设置变异的基因
					}
				}
			}
		}
	}

	/**
	 * 单点重组
	 */
	public void RecomOnePoint() {
		int i = 0;
		int nFather;
		int nMother;
		int nPos;
		Random random = new Random();
		double dRate;
		for (i = 0; i < this.pop.size(); ++i) {

			dRate = random.nextDouble(); // 随机产生一个概率范围

			if (dRate < this.OnePRecomRate) {
				// 随机选取两个个体 和 交叉点

				nFather = random.nextInt(this.pop.size());// 随机选取交叉个体
				nMother = random.nextInt(this.pop.size());

				Individual Father = this.pop.get(nFather);
				Individual Mother = this.pop.get(nMother);
				nPos = random.nextInt(Father.GeneLength);
				String temp;
				// 两个个体交叉重组
				for (int j = 0; j < nPos; ++j) {
					temp = Father.Get(j);
					Father.Set(j, Mother.Get(j));
					Mother.Set(j, temp);
				}
				this.pop.set(nMother, Mother);
				this.pop.set(nFather, Father);

			}
		}
	}

	/**
	 * 两点重组
	 */
	public void RecomTwoPoint() {
		int i = 0;
		int nFather;
		int nMother;
		int nPosPre;
		int nPosLast;
		Random random = new Random();
		double dRate;
		for (i = 0; i < this.pop.size(); ++i) {
			dRate = random.nextDouble();
			if (dRate < this.TwoPRecomRate) {

				nFather = random.nextInt(this.pop.size());// 随机选取交叉个体
				nMother = random.nextInt(this.pop.size());
				Individual Father = this.pop.get(nFather);
				Individual Mother = this.pop.get(nMother);

				nPosPre = random.nextInt(Father.GeneLength);
				nPosLast = random.nextInt(Father.GeneLength);

				if (nPosPre > nPosLast) {
					int nTemp = nPosLast;
					nPosLast = nPosPre;
					nPosPre = nTemp;
				}

				// 基因交换
				String sTemp;
				for (int j = nPosPre; j < nPosLast; ++j) {
					sTemp = Father.Get(j);
					Father.Set(j, Mother.Get(j));
					Mother.Set(j, sTemp);
				}
				this.pop.set(nMother, Mother);
				this.pop.set(nFather, Father);

			}

		}

	}

}
