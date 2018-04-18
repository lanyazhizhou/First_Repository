package test;

import java.io.IOException;

public class Fitness_test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Individual indiv = new Individual();
		// +, *, /, +, 0, *, /, 0, +, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1
		String[] a = { "/", "-", "-", "*", "/", "/", "/", "*", "1", "/", "0", "1", "0", "0", "1", "0", "1", "0", "0",
				"1", "1" };
		System.out.println(a);
		for (int i = 0; i < a.length; i++) {
			indiv.Chrom.add(a[i]);
		}
		indiv.GetFitness();
		System.out.println("ÊÊÓ¦¶ÈÎª£º" + indiv.Fitness);

	}

}
