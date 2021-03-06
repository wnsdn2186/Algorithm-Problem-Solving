package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pos {
	int x;
	int y;

	Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class SWEA_P1249_보급로 {
	static int N, MIN;
	static int[][] map, weight;
	static boolean[][] visited;
	// 상하좌우에 해당하는 방향
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1249_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= T; TC++) {
			N = Integer.parseInt(br.readLine());

			init();// 초기화

			// 입력
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = s.charAt(j) - '0';
				}
			}

			BFS();
			System.out.println("#" + TC + " " + MIN);
		}
		br.close();
	}

	public static void BFS() {
		Queue<Pos> queue = new LinkedList<Pos>();

		// queue에 (0,0) 추가
		queue.add(new Pos(0, 0));
		visited[0][0] = true;

		while (!queue.isEmpty()) {
			Pos P = queue.poll();

			if (P.x == N - 1 && P.y == N - 1) {
				if (MIN > weight[P.x][P.y])
					MIN = weight[P.x][P.y];
				continue;
			}

			if (MIN <= weight[P.x][P.y])
				continue;

			for (int i = 0; i < 4; ++i) {
				int nx = P.x + dx[i];
				int ny = P.y + dy[i];

				// 이동하려는 방향이 map범위 안이라면
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				if (!visited[nx][ny] || weight[P.x][P.y] + map[nx][ny] < weight[nx][ny]) {
					visited[nx][ny] = true;
					weight[nx][ny] = weight[P.x][P.y] + map[nx][ny];
					queue.add(new Pos(nx, ny));
				}
			}
		}
	}

	public static void init() {
		map = new int[N][N];
		weight = new int[N][N];
		visited = new boolean[N][N];
		MIN = Integer.MAX_VALUE;
	}
}