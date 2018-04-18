package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class WriteDataFile {
	static int c = 9;
	public static int data[][];
	public static int x[];
	public static int y[];
	public static int z[];
	public static int column = 200;
	public static int row = 3;
	Random random = new Random();

	FileOutputStream fop = null;
	File file;

	public int jiecheng() {
		Random a = new Random();
		int b = a.nextInt(2);
		int c = -1;
		int p = 1;
		for (int j = 0; j <= b; j++) {
			p = p * c;

		}

		return p;

	}

	public int[] Data(int column) {
		Random a = new Random();
		int[] x = new int[column];

		for (int j = 0; j < column; j++) {
			int b = a.nextInt(100) * jiecheng();
			x[j] = b;
		}
		return x;

	}

	public void onex() throws IOException {
		file = new File("E:\\DataSet\\11.txt");
		data = new int[row][column];
		x = new int[column];
		y = new int[column];
		z = new int[column];

		String expression = "z=x^2+3*x*y+2*y"; // 表达式
		FileOutputStream fos2 = new FileOutputStream(file); // 覆盖
		// FileOutputStream fos2 = new FileOutputStream(file, true);
		// 第二個参数为true表示程序每次运行都是追加字符串在原有的字符上
		// 若文件不存在,则创建它
		if (!file.exists()) {
			file.createNewFile();
		}
		// fos2.write((expression).getBytes()); // 写入表达式
		// fos2.write("\r\n".getBytes()); // 写入一个换行
		// fos2.write((" x " + " y " + " z").getBytes()); // 写入变量名
		// fos2.write("\r\n".getBytes()); // 写入一个换行

		x = Data(column); // 调用Data方法，然后返回一个数组，数组大小为column
		y = Data(column); // 数组里面的值是随机生成的是-100到100的随机整数

		for (int j = 0; j < column; j++) {

			z[j] = x[j] * x[j] + 3 * x[j] * y[j] + 2 * y[j];

			fos2.write((x[j] + "  " + y[j] + "  " + z[j]).getBytes()); // 写入表达式
			fos2.write("\r\n".getBytes()); // 写入一个换行

		}
		fos2.flush();
		fos2.close(); // 释放资源

	}

	public static void main(String[] args) throws Exception {
		WriteDataFile a = new WriteDataFile();
		a.onex(); // 调用上面的方法，写出数据集
		System.out.println("已经完成！");

	}
}
