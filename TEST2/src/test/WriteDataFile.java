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

		String expression = "z=x^2+3*x*y+2*y"; // ���ʽ
		FileOutputStream fos2 = new FileOutputStream(file); // ����
		// FileOutputStream fos2 = new FileOutputStream(file, true);
		// �ڶ�������Ϊtrue��ʾ����ÿ�����ж���׷���ַ�����ԭ�е��ַ���
		// ���ļ�������,�򴴽���
		if (!file.exists()) {
			file.createNewFile();
		}
		// fos2.write((expression).getBytes()); // д����ʽ
		// fos2.write("\r\n".getBytes()); // д��һ������
		// fos2.write((" x " + " y " + " z").getBytes()); // д�������
		// fos2.write("\r\n".getBytes()); // д��һ������

		x = Data(column); // ����Data������Ȼ�󷵻�һ�����飬�����СΪcolumn
		y = Data(column); // ���������ֵ��������ɵ���-100��100���������

		for (int j = 0; j < column; j++) {

			z[j] = x[j] * x[j] + 3 * x[j] * y[j] + 2 * y[j];

			fos2.write((x[j] + "  " + y[j] + "  " + z[j]).getBytes()); // д����ʽ
			fos2.write("\r\n".getBytes()); // д��һ������

		}
		fos2.flush();
		fos2.close(); // �ͷ���Դ

	}

	public static void main(String[] args) throws Exception {
		WriteDataFile a = new WriteDataFile();
		a.onex(); // ��������ķ�����д�����ݼ�
		System.out.println("�Ѿ���ɣ�");

	}
}
