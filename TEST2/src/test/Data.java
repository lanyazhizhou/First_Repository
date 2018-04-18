package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Data {

	public double Data[][];
	public int row;
	public int column;

	public Data() {

		Data = new double[WriteDataFile.column][WriteDataFile.row];
		this.row = WriteDataFile.column;

		this.column = WriteDataFile.row;

	}

	/*
	 * �����㴫�ݹ������ı������������ļ���ȡ����ȡ���ݼ������������������ö���Run����
	 */

	public double[][] getData(File file) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(file));
		String s = null;
		int j = 0;
		// System.out.println("���ݼ�Ϊ��");
		while ((s = br.readLine()) != null) {
			// System.out.println(s);
			String[] str = s.split("  ");
			for (int i = 0; i < column; i++) {

				Data[j][i] = Double.valueOf(str[i]);

			}

			j++; // ����������
		}
		br.close();

		return Data;
	}

}
