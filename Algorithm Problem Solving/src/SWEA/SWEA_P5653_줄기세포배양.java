package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_P5653_줄기세포배양 {
	static int[] dir = {};
	static int N, M;
	static int R, C;
	static int L;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P5653_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sz;
		int T = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= T; TC++) {
			sz = new StringTokenizer(br.readLine());
			N = Integer.parseInt(sz.nextToken());
			M = Integer.parseInt(sz.nextToken());
			R = Integer.parseInt(sz.nextToken());
			C = Integer.parseInt(sz.nextToken());
			L = Integer.parseInt(sz.nextToken());
			
			map = new int[N][M];
			
			for(int i = 0; i < N; i++) {
				sz = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(sz.nextToken());
				}
			}
			
			
			
		}
		br.close();
	}
}