package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class set implements Comparable<set> {
	int row;
	int col;
	int max;

	set(int row, int col, int max) {
		this.row = row;
		this.col = col;
		this.max = max;
	}

	@Override
	public int compareTo(set o) {
		if (this.max < o.max)
			return -1;

		else if (this.max == o.max)
			return 0;

		else
			return 1;
	}
}

public class SWEA_P2115_벌꿀채취 {
	static int[][] map; // 벌통을 저장하는 2차원 배열
	static int N; // 벌통의 크기
	static int M; // 채취할 수 있는 벌통의 수
	static int C; // 채취할 수 있는 꿀의 최대양
	static int max;// 각 행에서 채취할 수 있는 벌통의 수의 최대 가치
	static ArrayList<set> list;// 최대 가치를 가지는 (행, 열)의 값과 가치를 저장

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P2115_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sz;
		int T = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= T; TC++) {
			// 초기화
			sz = new StringTokenizer(br.readLine());
			N = Integer.parseInt(sz.nextToken());
			M = Integer.parseInt(sz.nextToken());
			C = Integer.parseInt(sz.nextToken());
			map = new int[N][N];
			list = new ArrayList<set>();

			// 입력
			for (int i = 0; i < N; i++) {
				sz = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(sz.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (j + M - 1 < N) {
						int[] array = new int[M]; // 채취가능한 벌통을 임시로 저장
						int idx = 0; // array의 index
						boolean[] visited = new boolean[M]; // 조합을 위해 방문한 곳을 표시

						// 현재 위치를 기준으로 채취가능한 벌통을 저장
						for (int k = 0; k < M; k++)
							array[idx++] = map[i][j + k];

						// max값 초기화
						max = 0;

						// 1자리에서 M자리의 조합을 모두 탐색하며 그 중에서 가장 가치가 높은 값을 찾음
						for (int l = 1; l <= M; l++)
							combination(array, visited, 0, M, l);

						// 현재 위치와 max값을 ArrayList에 저장
						list.add(new set(i, j, max));
					}
				}
			}

			// ArrayList를 max기준으로 정렬한 후 뒤집어서 내림차순으로 만듦
			Collections.sort(list);
			Collections.reverse(list);

			int sum = 0; // 최종값
			int idx = 0; // list에서 두 번째로 높은 가치를 찾기위한 인덱스
			set first = list.get(idx++);// 가장 가치가 높은 것
			sum += first.max;

			while (true) {
				set second = list.get(idx++);// 두 번째로 가치가 높은 것

				// 만약 행이 다르다면 first와 겹칠 일이 없기 때문에 sum에 max값을 더하고 break
				if (first.row != second.row) {
					sum += second.max;
					break;
				}

				// 행은 같지만 두 개의 열의 차이가 최대 벌통의 개수 이상이면 겹치지 않기 때문에 sum에 max값을 더하고 break
				else if (first.row == second.row && Math.abs(first.col - second.col) >= M) {
					sum += second.max;
					break;
				}
			}

			// 출력
			System.out.println("#" + TC + " " + sum);

		}
		br.close();
	}

	/*
	 * 모든 조합을 구하는 method array : 조합을 구할 배열 visited : 방문한 곳을 check start : 시작점 n :
	 * 배열의 크기 r : 찾고자 하는 조합의 크기 Ex) n이 4이고 r이 1이면 4개중에서 1개짜리를 모두 구하는 것
	 */
	static void combination(int[] array, boolean[] visited, int start, int n, int r) {
		if (r == 0) {// 모두 찾았을 때
			int sum = 0;

			// 현재 조합의 합을 구함
			for (int i = 0; i < n; i++) {
				if (visited[i]) {
					sum += array[i];
				}
			}

			// 합이 꿀의 최대양 보다 크면 종료
			if (sum > C)
				return;

			// 합이 꿀의 최대양 보다 작으면 max을 값을 구하고 갱신
			else {
				sum = 0;

				for (int i = 0; i < n; i++) {
					if (visited[i]) {
						sum += squared(array[i]);
					}
				}

				if (sum > max)
					max = sum;
				return;
			}
		}

		// 재귀호출
		for (int i = start; i < n; i++) {
			visited[i] = true;
			combination(array, visited, i + 1, n, r - 1);
			visited[i] = false;
		}
	}

	// 제곱을 구하는 method
	public static int squared(int n) {
		return (int) Math.pow(n, 2);
	}
}