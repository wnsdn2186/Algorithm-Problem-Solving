package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_P1247_최적경로 {
	static int N;
	static int[][] loc;
	static boolean[] visited;
	static int min;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1247_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sz;
		int T = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= T; TC++) {
			N = Integer.parseInt(br.readLine());
			loc = new int[N + 2][2];// 좌표들을 저장하는 배열
			visited = new boolean[N + 1];// 방문한 곳을 나타내기 위한 배열
			min = Integer.MAX_VALUE;// 최소거리를 찾기 위한 변수

			sz = new StringTokenizer(br.readLine());

			// 배열 처음엔 회사의 좌표
			loc[0][0] = Integer.parseInt(sz.nextToken());
			loc[0][1] = Integer.parseInt(sz.nextToken());

			// 배열 마지막엔 집의 좌표
			loc[N + 1][0] = Integer.parseInt(sz.nextToken());
			loc[N + 1][1] = Integer.parseInt(sz.nextToken());

			// 배열 사이에는 방문하는 집들의 좌표
			for (int i = 1; i <= N; i++) {
				loc[i][0] = Integer.parseInt(sz.nextToken());
				loc[i][1] = Integer.parseInt(sz.nextToken());
			}

			solve(0, loc[0][0], loc[0][1], 0);
			System.out.println("#" + TC + " " + min);
		}
		br.close();
	}

	public static void solve(int cnt, int x, int y, int len) {
		if (cnt == N) {// 전체 집을 다 돌았으면
			len += calc(x, y, loc[N + 1][0], loc[N + 1][1]);// 마지막에 집과의 거리를 더해주고
			if (len < min)// 그게 min보다 작으면 min을 갱신
				min = len;

			return;
		}

		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {// 방문하지 않았다면
				visited[i] = true;// 방문했다고 표시하고
				solve(cnt + 1, loc[i][0], loc[i][1], len + calc(x, y, loc[i][0], loc[i][1]));// 현재 위치를 시작으로 거리를 더해간다
				visited[i] = false;// 다 돌았으면 다시 재귀를 돌 수 있게 false로 바꿔줌
			}
		}
	}

	// 거리를 구하는 method
	public static int calc(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

}