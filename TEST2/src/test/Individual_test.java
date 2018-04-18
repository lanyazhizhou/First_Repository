package test;

import java.io.IOException;

public class Individual_test {
	public static int n = 0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Population Pop = new Population();
		Pop.Initialization();
		Pop.EvaluateFitness();
		Individual indiv;
		for (int i = 0; i < Pop.pop.size(); i++) {
			indiv = Pop.pop.get(i);
			System.out.println("������Ϊ" + i + ":" + indiv.Chrom);
			System.out.println("������Ӧ��Ϊ��" + indiv.Fitness);
		}
		do {
			Pop.Population_sort();
			System.out.println("������" + n);
			System.out.println("�����Ӧ��1��" + Pop.pop.get(0).Fitness);
			System.out.println("�����Ӧ��2��" + Pop.pop.get(4).Fitness);
			System.out.println("�����Ӧ��3��" + Pop.pop.get(10).Fitness);
			System.out.println("�����Ӧ��4��" + Pop.pop.get(30).Fitness);
			Pop.RecomOnePoint();

			Pop.RecomTwoPoint();

			Pop.Mutation();

			Pop.EvaluateFitness();

			Pop.Select();
			n++;
			System.out.println("������Ⱥ������" + Pop.pop.size());

		} while (n <= 1000 && Pop.most_indivs.get(0).Fitness >= 0.5);

		System.out.println("���Ÿ�����Ӧ��7��" + Pop.most_indivs.get(0).Fitness);
		System.out.print("�����Ÿ��壺");
		for (int i = 0; i < Pop.most_indivs.get(0).Chrom.size(); i++) {
			System.out.print('"' + Pop.most_indivs.get(0).Chrom.get(i) + '"' + ",");
		}
		System.out.println("");
		System.out.println("�����Ÿ������Ӧ�ȣ�" + Pop.most_indivs.get(0).Fitness);

		System.out.println("Game Over!");
		System.out.println(n);

	}

}
