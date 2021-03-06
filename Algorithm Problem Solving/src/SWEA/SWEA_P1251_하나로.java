package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

class Graph implements Comparable<Graph> {
	int x;
	int y;
	double cost = 0.0;

	Graph(int x, int y, double cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}

	//Collections.sort()를 사용하기 위함
	@Override
	public int compareTo(Graph o) {
		if (this.cost < o.cost) {
			return -1;
		} else if (this.cost > o.cost) {
			return 1;
		}
		return 0;
	}
}

public class SWEA_P1251_하나로 {
	static int N;// 섬의 개수
	static double E;//세율
	static Graph[] list;//입력을 받기위한 배열
	static Stack<Graph> sorted;//두 정점과 cost를 저장하는 Stack
	static int[] parent;//부모정보
	static double SumCost;//cost의 합

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1251_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sz;
		int T = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= T; TC++) {
			sz = new StringTokenizer(br.readLine());// N의 값
			N = Integer.parseInt(sz.nextToken());

			init();// x, y, cost 초기화

			sz = new StringTokenizer(br.readLine());// x값 읽어오기
			for (int i = 1; i <= N; i++)
				list[i].x = Integer.parseInt(sz.nextToken());

			sz = new StringTokenizer(br.readLine());// y값 읽어오기
			for (int i = 1; i <= N; i++)
				list[i].y = Integer.parseInt(sz.nextToken());

			sz = new StringTokenizer(br.readLine());// E(세율)의 값
			E = Double.parseDouble(sz.nextToken());

			FindCost();
			solve();

			System.out.print("#" + TC + " ");
			System.out.printf("%.0f\n", SumCost);
		}
		br.close();
	}

	//크루스칼 알고리즘
	public static void solve() {
		Collections.sort(sorted);
		Collections.reverse(sorted);
	
		while (!sorted.isEmpty()) {
			Graph g = sorted.pop();

			if(union(g.x, g.y))//두 정점의 부모가 다르면
				SumCost += g.cost;//cost를 더해줌
		}
	}
	
	public static boolean union(int x, int y) {
		int parentX = FindParent(x);//x의 부모
		int parentY = FindParent(y);//y의 부모
		
		if(parentX == parentY)//같으면 false
			return false;
		
		else {//디르면
			parent[parentY] = parentX;//y의 부모는 x의 부모
			return true;
		}
	}

	public static int FindParent(int v) {
		if (parent[v] == v)
			return v;

		return FindParent(parent[v]);
	}

	//모든 간선의 cost를 Stack에 저장
	public static void FindCost() {
		for (int i = 1; i < N; i++) {
			for (int j = i + 1; j <= N; j++) {
				double cost = CostCalc(list[i].x, list[i].y, list[j].x, list[j].y);
				sorted.push(new Graph(i, j, cost));
			}
		}
	}

	//cost를 계산하는 공식 
	public static double CostCalc(int x1, int y1, int x2, int y2) {
		return E * Math.pow(Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)), 2);
	}

	//초기화
	public static void init() {
		list = new Graph[N + 1];
		for (int i = 0; i <= N; i++)
			list[i] = new Graph(0, 0, 0);
		
		parent = new int[N + 1];
		for (int i = 0; i <= N; i++)
			parent[i] = i;
		
		sorted = new Stack<Graph>();
		SumCost = 0;
	}
}