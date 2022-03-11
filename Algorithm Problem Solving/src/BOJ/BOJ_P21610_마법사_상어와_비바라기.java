package BOJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Magic {
	int x1, y1;
	int x2, y2;
	int x3, y3;
	int x4, y4;

	Magic(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;
		this.x4 = x4;
		this.y4 = y4;
	}
}

public class BOJ_P21610_마법사_상어와_비바라기 {
	static int[][] A;
	static boolean[][] cloud;
	static int N, M;
	static Stack<Magic> magic;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./BOJ_INPUT/BOJ_P21610_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sz;

		sz = new StringTokenizer(br.readLine());
		N = Integer.parseInt(sz.nextToken());
		M = Integer.parseInt(sz.nextToken());
		A = new int[N + 1][N + 1];
		cloud = new boolean[N + 1][N + 1];
		magic = new Stack<Magic>();

		for (int i = 1; i <= N; i++) {
			sz = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(sz.nextToken());
			}
		}

		magic.push(new Magic(N, 1, N, 2, N - 1, 1, N - 1, 2));
		cloud[N][1] = true;
		cloud[N][2] = true;
		cloud[N - 1][1] = true;
		cloud[N - 1][2] = true;

		for (int i = 0; i < M; i++) {
			sz = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(sz.nextToken());
			int s = Integer.parseInt(sz.nextToken());
		}

		br.close();
	}

	public static void solve(int d, int s) {
		int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
		int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

		Magic M = magic.peek();

	
		M.x1 = (M.x1 + dx[d]) % N;
		M.y1 = (M.y1 + dx[d]) % N;
		M.x2 = (M.x1 + dx[d]) % N;
		M.y2 = (M.y1 + dx[d]) % N;
		M.x3 = (M.x1 + dx[d]) % N;
		M.y3 = (M.y1 + dx[d]) % N;
		M.x4 = (M.x1 + dx[d]) % N;
		M.y4 = (M.y1 + dx[d]) % N;
			
		
	}
}
