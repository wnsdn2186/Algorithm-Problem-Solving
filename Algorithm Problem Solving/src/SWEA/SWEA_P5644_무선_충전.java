package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class BC implements Comparable<BC>{
	int x;
	int y;
	int c;
	int p;

	BC(int x, int y, int c, int p) {
		this.x = x;
		this.y = y;
		this.c = c;
		this.p = p;
	}

	@Override
	public int compareTo(BC o) {
		if(this.x < o.x)
			return -1;
		else if(this.x == o.x)
			return 0;
		else
			return 1;
	}
	
	
}

public class SWEA_P5644_무선_충전 {
	// 이동X, 상, 우, 하, 좌에 따른 좌표 변화
	static int[] dx = { 0, -1, 0, 1, 0 };
	static int[] dy = { 0, 0, 1, 0, -1 };

	static int M, A; // M : 총 이동시간 & A : BC의 개수
	static ArrayList<BC> BCList;
	static Queue<Integer> moveA; // A의 이동 정보
	static Queue<Integer> moveB; // B의 이동 정보
	static int sumA;
	static int sumB;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P5644_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sz;
		int T = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= T; TC++) {
			init();
			sz = new StringTokenizer(br.readLine());
			M = Integer.parseInt(sz.nextToken());
			A = Integer.parseInt(sz.nextToken());

			sz = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				moveA.add(Integer.parseInt(sz.nextToken()));

			sz = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++)
				moveB.add(Integer.parseInt(sz.nextToken()));

			for (int i = 1; i <= A; i++) {
				sz = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(sz.nextToken());
				int y = Integer.parseInt(sz.nextToken());
				int c = Integer.parseInt(sz.nextToken());
				int p = Integer.parseInt(sz.nextToken());

				BCList.add(new BC(x, y, c, p));
			}
		}
		br.close();
	}
	
	public static void solve() {
		int Ax = 1; int Ay = 1;
		int Bx = 10; int By = 10;
		Queue<Integer> bcA = new LinkedList<>();
		Queue<Integer> bcB = new LinkedList<>();
		
		while(!moveA.isEmpty() && !moveB.isEmpty()) {
			int dirA = moveA.poll();
			int dirB = moveB.poll();
			
			Ax += dx[dirA];
			Ay += dy[dirA];
			
			Bx += dx[dirB];
			By += dy[dirB];
			
			bcA.add(CheckChargeable(Ax, Ay));
			bcB.add(CheckChargeable(Bx, By));
		}
		

		
	}
	
	public static int CheckChargeable(int x, int y) {
		int idx = 0;
		
		for(int i = 0; i < BCList.size(); i++) {
			BC B = BCList.get(i);
			if(Math.abs(x - B.x) + Math.abs(y - B.y) <= B.c)
					idx *= 10 + i;
		}
		
		return idx;
	}
	
	public static void init() {
		BCList = new ArrayList<BC>();
		moveA = new LinkedList<Integer>();
		moveB = new LinkedList<Integer>();
		sumA = 0;
		sumB = 0;
	}
}