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
			System.out.println("个体编号为" + i + ":" + indiv.Chrom);
			System.out.println("个体适应度为：" + indiv.Fitness);
		}
		do {
			Pop.Population_sort();
			System.out.println("代数：" + n);
			System.out.println("最佳适应度1：" + Pop.pop.get(0).Fitness);
			System.out.println("最佳适应度2：" + Pop.pop.get(4).Fitness);
			System.out.println("最佳适应度3：" + Pop.pop.get(10).Fitness);
			System.out.println("最佳适应度4：" + Pop.pop.get(30).Fitness);
			Pop.RecomOnePoint();

			Pop.RecomTwoPoint();

			Pop.Mutation();

			Pop.EvaluateFitness();

			Pop.Select();
			n++;
			System.out.println("当代种群个数：" + Pop.pop.size());

		} while (n <= 1000 && Pop.most_indivs.get(0).Fitness >= 0.5);

		System.out.println("最优个体适应度7：" + Pop.most_indivs.get(0).Fitness);
		System.out.print("该最优个体：");
		for (int i = 0; i < Pop.most_indivs.get(0).Chrom.size(); i++) {
			System.out.print('"' + Pop.most_indivs.get(0).Chrom.get(i) + '"' + ",");
		}
		System.out.println("");
		System.out.println("该最优个体的适应度：" + Pop.most_indivs.get(0).Fitness);

		System.out.println("Game Over!");
		System.out.println(n);

	}

}
