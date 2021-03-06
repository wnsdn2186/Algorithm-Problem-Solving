package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// Core의 좌표를 나타내는 class
class Core {
	int row;
	int col;

	Core(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

public class SWEA_P1767_프로세서_연결하기 {
	static int N, maxcore, minsum; // N : 배열의 개수, maxcore : 최대 코어의 개수, minsum : 최소 전선의 합
	static int[][] CELL; // CELL을 입력받는 배열
	static ArrayList<Core> core; // Core의 좌표를 저장하는 ArrayList
	static int[] dx = { -1, 1, 0, 0 }; // 상, 하, 좌, 우에 따른 행 값
	static int[] dy = { 0, 0, -1, 1 }; // 상, 하, 좌, 우에 따른 열 값

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1767_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sz;
		int T = Integer.parseInt(br.readLine());

		// 입력 및 초기화
		for (int TC = 1; TC <= T; TC++) {
			N = Integer.parseInt(br.readLine());
			CELL = new int[N + 1][N + 1];
			core = new ArrayList<Core>();

			for (int i = 1; i <= N; i++) {
				sz = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					CELL[i][j] = Integer.parseInt(sz.nextToken());
					if (CELL[i][j] == 1 && i != 1 && i != N && j != 1 && j != N)// 가장자리에 있는 Core가 아닌 Core이면
						core.add(new Core(i, j)); // ArrayList에 추가
				}
			}

			// 최대 코어의 개수, 최소 전선의 합 초기화
			maxcore = Integer.MIN_VALUE;
			minsum = Integer.MAX_VALUE;

			// method 호출
			solve(0, 0, 0);

			System.out.println("#" + TC + " " + minsum);
		}
		br.close();
	}

	// DFS를 이용한 solution
	public static void solve(int idx, int corenum, int sum) {
		/* 종료 조건 */
		// 만약 모든 코어를 확인했을 때
		if (idx == core.size()) {
			// 실제로 전원이 들어오는 core의 개수가 maxcore보다 많다면
			if (corenum > maxcore) {
				// 최대 코어의 개수와 최소 전선의 합 값을 갱신
				maxcore = corenum;
				minsum = sum;
			}

			// 전원이 들어오는 코어의 개수가 같을 경우
			else if (corenum == maxcore)
				minsum = Math.min(minsum, sum); // 최소 전선의 합으로 갱신

			// 종료
			return;
		}

		// ArrayList에서 코어의 좌표를 꺼냄
		Core c = core.get(idx);
		int row = c.row;
		int col = c.col;

		/*
		 * (1) 상, 하, 좌, 우 4방향을 탐색한다. 
		 * (2) check(방향, 행, 열) method를 통해 전선을 연결할 수 있는지 확인한다.
		 * (3) 전선을 연결할 수 있다면 ArrayList의 다음 인덱스 번호, 코어의 개수 +1, sum에 전선의 길이를 합한 상태로 재귀 호출
		 * (4) 만약 4방향 모두 전선을 연결할 수 없다면 그냥 ArrayList의 인덱스 번호만 증가시키고 재귀 호출
		*/

		for (int i = 0; i < 4; i++) {// (1)
			int len = check(i, row, col);// (2)
			if (len != -1) {// (3)
				visit(i, row, col);
				solve(idx + 1, corenum + 1, sum + len);
				unvisit(i, row, col);
			}

			// (4)
			else if (len == -1 && i == 3)
				solve(idx + 1, corenum, sum);
		}
	}

	/*
	 * 전선을 연결할 수 있는지 check하는 method
	 * 전선을 연결할 수 있다면 그 길이를 반환
	 * 전선을 연결할 수 없다면 -1을 반환
	 * dir = 1 : 상
	 * dir = 2 : 하
	 * dir = 3 : 좌
	 * dir = 4 : 우
	 * x = 행, y = 열
	*/
	public static int check(int dir, int x, int y) {
		switch (dir) {
		case 0:
			for (int i = 1; i < x; i++)
				if (CELL[i][y] == 2 || CELL[i][y] == 1)
					return -1;
			return x - 1;

		case 1:
			for (int i = x + 1; i <= N; i++)
				if (CELL[i][y] == 2 || CELL[i][y] == 1)
					return -1;
			return N - x;

		case 2:
			for (int i = 1; i < y; i++)
				if (CELL[x][i] == 2 || CELL[x][i] == 1)
					return -1;
			return y - 1;

		case 3:
			for (int i = y + 1; i <= N; i++)
				if (CELL[x][i] == 2 || CELL[x][i] == 1)
					return -1;
			return N - y;
		}

		return -1;
	}

	/*
	 * 방문한 곳은 2로 표시하는 method
	 * dir = 1 : 상
	 * dir = 2 : 하
	 * dir = 3 : 좌
	 * dir = 4 : 우
	 * x = 행, y = 열
	*/
	public static void visit(int dir, int x, int y) {
		switch (dir) {
		case 0: 
			for (int i = 1; i <= x; i++)
				CELL[i][y] = 2;
			break;

		case 1: 
			for (int i = x; i <= N; i++)
				CELL[i][y] = 2;
			break;

		case 2:
			for (int i = 1; i <= y; i++)
				CELL[x][i] = 2;
			break;

		case 3:
			for (int i = y; i <= N; i++)
				CELL[x][i] = 2;
			break;

		}
	}

	/*
	 * 방문했다고 표시한 곳을 다시 0으로 바꿔주는 method
	 * dir = 1 : 상
	 * dir = 2 : 하
	 * dir = 3 : 좌
	 * dir = 4 : 우
	 * x = 행, y = 열
	 * 
	*/
	public static void unvisit(int dir, int x, int y) {
		switch (dir) {
		case 0:
			for (int i = 1; i < x; i++)
				CELL[i][y] = 0;
			break;

		case 1:
			for (int i = x + 1; i <= N; i++)
				CELL[i][y] = 0;
			break;

		case 2:
			for (int i = 1; i < y; i++)
				CELL[x][i] = 0;
			break;

		case 3:
			for (int i = y + 1; i <= N; i++)
				CELL[x][i] = 0;
			break;

		}
	}
}