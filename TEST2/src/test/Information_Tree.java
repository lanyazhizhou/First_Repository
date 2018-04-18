package test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Information_Tree {
	static Random r = new Random(); // 设置一个随机类对象，用来随机生成数字
	private int head_Actions = 6; // 这里设置动作数，其实就是每个节点的最大孩子节点数目，选择节点，就是选择动作
	private int tail_Actions = 2;
	LinkedList<Information_Tree> children; // 树孩子节点
	double nVisits, totValue, NVisits; // nVisits是访问总次数，totValue是累积奖赏值,totVisits是蒙特卡洛总共访问次数
	double fitness;

	public int deep; // 当前节点的深度
	public int HeadLength = 10; // 个体染色体头部长度，用来约束节点的扩展深度

	Information_Tree newNode; // 用来存储每次新扩展出来的节点
	public List<String> Gene; // 每个节点都有一个基因模式，深度越大的节点对应的基因模式位越多

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

	public Information_Tree expand(String gene, Information_Tree farther) { // 这是当访问的节点是叶子节点后，开始进行扩展

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

	public void updateStats(double value) { // 看一下节点状态是怎么更新奖赏值的
		nVisits++; // 首先是访问次数增一
		totValue += value; // 然后是累积奖赏值增加

	}

	public int arity() {
		return children == null ? 0 : children.size();
	}
}