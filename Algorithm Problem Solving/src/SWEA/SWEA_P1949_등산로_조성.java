package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_P1949_등산로_조성 {
	static int MAX_LEN = -1;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1949_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= T; TC++) {
			StringTokenizer sz = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(sz.nextToken());
			int Depth = Integer.parseInt(sz.nextToken());
			int MAX = -1;
			MAX_LEN = -1;// 최대길이 초기화

			// 시작 index를 저장하기 위한 ArrayList
			ArrayList<Integer> startX = new ArrayList<Integer>();
			ArrayList<Integer> startY = new ArrayList<Integer>();

			int[][] array = new int[N][N];

			// array에 값을 저장하면서 가장 높은 봉우리 찾기
			for (int i = 0; i < N; i++) {
				sz = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					array[i][j] = Integer.parseInt(sz.nextToken());
					if (array[i][j] > MAX)
						MAX = array[i][j];
				}
			}

			// 가장 높은 봉우리를 찾아 시작점을 저장하는 ArrayList에 저장
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					if (array[i][j] == MAX) {
						startX.add(i);
						startY.add(j);
					}

			// Depth를 1만큼 줄여나가면서 완전탐색
			for (int i = 0; i <= Depth; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < N; k++) {
						if (array[j][k] - i < 0)
							continue;

						else
							array[j][k] -= i;

						for (int l = 0; l < startX.size(); l++) {
							if (array[startX.get(l)][startY.get(l)] == MAX) {// Depth를 줄이면서 시작점이 아니게 된 곳은 무시
								solve(array, startX.get(l), startY.get(l), 1);
							}
						}

						array[j][k] += i;
					}
				}
			}

			System.out.println("#" + TC + " " + MAX_LEN);
		}
		br.close();
	}

	public static void solve(int[][] array, int i, int j, int cnt) {
		int[] dx = { -1, 0, 1, 0 };
		int[] dy = { 0, 1, 0, -1 };

		// 상, 우, 하, 좌를 보면서 갈 길이 있으면 재귀호출
		for (int k = 0; k < 4; k++) {
			if (i + dx[k] > -1 && j + dy[k] > -1 && i + dx[k] < array.length && j + dy[k] < array.length) {
				if (array[i][j] > array[i + dx[k]][j + dy[k]]) {
					solve(array, i + dx[k], j + dy[k], cnt + 1);
				}
			}
		}

		MAX_LEN = Math.max(MAX_LEN, cnt);
	}
}