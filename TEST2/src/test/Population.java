package test;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

public class Population {
	LinkedList<Individual> pop = new LinkedList<Individual>();
	LinkedList<Individual> most_indivs = new LinkedList<Individual>(); // ÿ����Ⱥ�����ŵ�һ���ָ���
	public int mostindivs_number = 5;
	int Populationsize = 100;

	Individual BestIndividual; // ��Ⱥ�����Ÿ���
	int BestIndivNum; // ��Ⱥ�����Ÿ���ı��
	public int MaxGeneration = 10000; // ����������
	public double MutationRate = 0.1; // ������
	public double OnePRecomRate = 0.3; // һ���������
	public double TwoPRecomRate = 0.2; // �����������
	public double GeneRecomRate = 0.1; // �����������
	public double ISRate; // ISת������
	public int[] ISElemLength;
	public double RISRate; // RISת������
	public int[] RISElemLength;
	public double GeneTransRate;

	public FullSet fullset;
	public FeatureSet featureset;

	public void Initialization() { // ��Ⱥ��ʼ��

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

	public void EvaluateFitness() throws IOException { // ������Ⱥ��Ӧ��

		for (int i = 0; i < pop.size(); i++) {
			Individual indiv = pop.get(i); // ͬһ�����ñ�����ֵ
			indiv.work = true;
			indiv.Fitness = indiv.GetFitness();
			if (!indiv.work) { // ������ֳ���Ϊ0��������ͽ�����������������ⱨ��

				this.pop.remove(indiv);
				i--;
			}

		}
	}

	/**
	 * ѡ����Ⱥ���н���ѡ�񣬲��ö����̲���
	 * 
	 * 
	 **/
	public void Select() {
		Population NewPop = new Population();
		this.FindBestIndividual();
		this.GetWheelVaule(); // ��Ϊ�������Ӧ����ԽСԽ�ã�����������̵�Ҫ������Ҫ����һ��ת��
		for (int i = 0; i < this.most_indivs.size(); i++) {
			NewPop.pop.add(this.most_indivs.get(i)); // ��Ӣ����
		}

		double dTotal = 0;
		for (int i = 0; i < this.pop.size(); ++i) {

			dTotal += this.pop.get(i).Wheelvalue;
		}

		// ÿ������ĸ���
		double[] dRate = new double[this.pop.size()];

		if (dTotal == 0) { // ���ܵ�����ֵΪ0����ô�����ѡȡ
			for (int i = 0; i < this.pop.size(); ++i) {
				dRate[i] = 1 / (double) this.pop.size();
			}
		} else {
			for (int i = 0; i < this.pop.size(); ++i) {
				dRate[i] = this.pop.get(i).Wheelvalue / dTotal;
			}
		}

		// �ֶ���
		double[] dWheel = new double[this.pop.size()];
		for (int i = 0; i < this.pop.size(); ++i) {
			if (0 == i) {
				dWheel[i] = dRate[i];
			} else {
				dWheel[i] = dWheel[i - 1] + dRate[i];
			}
		}

		// ѡ��
		Random random = new Random();
		for (int i = this.mostindivs_number; i < this.Populationsize; ++i) {
			double d = random.nextDouble();
			int j = 0;
			for (j = 0; j < this.pop.size(); ++j) {
				if (d < dWheel[j]) { // ����ת��
					break;
				}
			}
			if (j >= this.pop.size()) {
				j = this.pop.size() - 1;
			}
			NewPop.pop.add(this.pop.get(j)); // ���뵽�µ���Ⱥ��
		}
		System.out.println("ѡ������Ժ���Ⱥ������" + NewPop.pop.size());

		for (int i = 0; i < NewPop.pop.size(); i++) { // �Ը�����п�¡������Java�еĶ��������ظ���
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
	 * �������Ÿ���
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
	 * ÿ������һ����Ŀ�ľ�Ӣ����Щ��Ӣ������뽻�����
	 */

	public void Population_sort() {
		Collections.sort(this.pop, new Comparator<Individual>() {
			/*
			 * ����Ⱥ����Ӧ�Ƚ�������������
			 */
			public int compare(Individual indiv1, Individual indiv2) {
				// ����Person�����������������
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
				most_indivs.add(this.pop.get(i).clone()); // ��ʱ��Ҫ��ֵ����¡�壬���򽻲�����ʱ����Щ���Ÿ���ͻᷢ���仯
			}
		} else {
			for (int i = 0; i < this.mostindivs_number; i++) {
				most_indivs.set(i, this.pop.get(i).clone());
			}
		}

	}

	// ����
	public void Mutation() {
		fullset = new FullSet();
		featureset = new FeatureSet();
		Random random = new Random();
		for (int i = 0; i < this.pop.size(); ++i) {

			for (int j = 0; j < this.pop.get(i).GeneLength; ++j) { // ���ÿһλ���������б��죬�����Ǹ���

				if (random.nextDouble() < this.MutationRate) {
					int nIndex = j % this.pop.get(i).GeneLength;
					int k;
					// ����ͷ��
					if (nIndex < this.pop.get(i).headlength) {
						k = random.nextInt(fullset.length);
						this.pop.get(i).Set(j, fullset.getRandom()); // ���ñ���Ļ���
					} else { // β��
						k = random.nextInt(featureset.length);
						this.pop.get(i).Set(j, featureset.getRandom()); // ���ñ���Ļ���
					}
				}
			}
		}
	}

	/**
	 * ��������
	 */
	public void RecomOnePoint() {
		int i = 0;
		int nFather;
		int nMother;
		int nPos;
		Random random = new Random();
		double dRate;
		for (i = 0; i < this.pop.size(); ++i) {

			dRate = random.nextDouble(); // �������һ�����ʷ�Χ

			if (dRate < this.OnePRecomRate) {
				// ���ѡȡ�������� �� �����

				nFather = random.nextInt(this.pop.size());// ���ѡȡ�������
				nMother = random.nextInt(this.pop.size());

				Individual Father = this.pop.get(nFather);
				Individual Mother = this.pop.get(nMother);
				nPos = random.nextInt(Father.GeneLength);
				String temp;
				// �������彻������
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
	 * ��������
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

				nFather = random.nextInt(this.pop.size());// ���ѡȡ�������
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

				// ���򽻻�
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
