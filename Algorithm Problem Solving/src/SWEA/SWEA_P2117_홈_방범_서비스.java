package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_P2117_홈_방범_서비스 {
	static int N; // 도시의 크기
	static int M; // 하나의 집이 지불할 수 있는 비용
	static int[][] CITY;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P2117_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sz;
		int T = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= T; TC++) {
			sz = new StringTokenizer(br.readLine());
			N = Integer.parseInt(sz.nextToken());
			M = Integer.parseInt(sz.nextToken());
			CITY = new int[N + 1][N + 1];
			
			for(int i = 1; i <= N; i++) {
				sz = new StringTokenizer(br.readLine());
				for(int j = 1; j <= N; j++) {
					CITY[i][j] = Integer.parseInt(sz.nextToken());
				}
			}
			
			
		}
		br.close();
	}
	
	public static int benefit(int k, int home) {
		return (home * M) - (int)(Math.pow(k, 2) + Math.pow(k - 1, 2));
	}
}