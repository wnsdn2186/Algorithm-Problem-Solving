package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_P4012_요리사 {
	static int N;
	static int[][] map;
	static boolean[] visited;
	static int MIN;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P4012_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sz;
		int T = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= T; TC++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N + 1][N + 1];
			visited = new boolean[N + 1];
			MIN = Integer.MAX_VALUE;

			for (int i = 1; i <= N; i++) {
				sz = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					map[i][j] = Integer.parseInt(sz.nextToken());
				}
			}

			combination(1, 0);
			System.out.println("#" + TC + " " + MIN);
		}
		br.close();
	}

	public static void combination(int start, int len) {
		if (len == N / 2) {
			solve();
			return;
		}

		for (int i = start; i <= N; i++) {
			visited[i] = true;
			combination(i + 1, len + 1);
			visited[i] = false;
		}
	}

	public static void solve() {
		int x = 0, y = 0, result = 0;

		for (int i = 1; i < N; i++) {
			for (int j = i + 1; j <= N; j++) {
				if (visited[i] && visited[j]) {
					x += map[i][j] + map[j][i];
				} 
				
				else if (!visited[i] && !visited[j]) {
					y += map[i][j] + map[j][i];
				}
			}
		}
		
		result = Math.abs(x - y);
		if (result < MIN)
			MIN = result;
	}

}