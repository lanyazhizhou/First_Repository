package test;

public class ExpressionTest {

	public ExpressionTest() {

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Individual indiv = new Individual();
		Expression Exp = new Expression();
		indiv.Chrom.add("+");
		indiv.Chrom.add("*");
		indiv.Chrom.add("*");
		indiv.Chrom.add("1");
		indiv.Chrom.add("0");
		for (int i = 3; i < 21; i++) {
			indiv.Chrom.add("0");
		}
		double[] data = new double[2];
		data[0] = 1;
		data[1] = 3;
		double a = Exp.GetGeneValue(indiv.Chrom, data);
		System.out.println(a);
	}

}
