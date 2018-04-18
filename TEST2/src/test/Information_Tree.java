package test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Information_Tree {
	static Random r = new Random(); // ����һ�������������������������
	private int head_Actions = 6; // �������ö���������ʵ����ÿ���ڵ������ӽڵ���Ŀ��ѡ��ڵ㣬����ѡ����
	private int tail_Actions = 2;
	LinkedList<Information_Tree> children; // �����ӽڵ�
	double nVisits, totValue, NVisits; // nVisits�Ƿ����ܴ�����totValue���ۻ�����ֵ,totVisits�����ؿ����ܹ����ʴ���
	double fitness;

	public int deep; // ��ǰ�ڵ�����
	public int HeadLength = 10; // ����Ⱦɫ��ͷ�����ȣ�����Լ���ڵ����չ���

	Information_Tree newNode; // �����洢ÿ������չ�����Ľڵ�
	public List<String> Gene; // ÿ���ڵ㶼��һ������ģʽ�����Խ��Ľڵ��Ӧ�Ļ���ģʽλԽ��

	public Information_Tree() {
		this.NVisits = 0;
		children = new LinkedList<Information_Tree>();
		Gene = new LinkedList<String>();
	}

	public void save_population(LinkedList<Individual> pop) {

		for (Individual indiv : pop) {
			Information_Tree cur = this;
			for (int i = 0; i < indiv.Chrom.size(); i++) {
				cur.deep = i;
				cur = expand(indiv.Chrom.get(i), cur);

			}
			cur.deep = indiv.Chrom.size();

		}

	}

	public Information_Tree expand(String gene, Information_Tree farther) { // ���ǵ����ʵĽڵ���Ҷ�ӽڵ�󣬿�ʼ������չ

		for (Information_Tree children : farther.children) {
			int n = children.Gene.size();
			if (children.Gene.get(n - 1).equalsIgnoreCase(gene)) {

				return children;
			}

		}
		Information_Tree newchildren = new Information_Tree();
		for (String s : farther.Gene) {
			newchildren.Gene.add(s);
		}
		newchildren.Gene.add(gene);
		return newchildren;
	}

	public boolean isLeaf() {

		return children == null;

	}

	public void updateStats(double value) { // ��һ�½ڵ�״̬����ô���½���ֵ��
		nVisits++; // �����Ƿ��ʴ�����һ
		totValue += value; // Ȼ�����ۻ�����ֵ����

	}

	public int arity() {
		return children == null ? 0 : children.size();
	}
}