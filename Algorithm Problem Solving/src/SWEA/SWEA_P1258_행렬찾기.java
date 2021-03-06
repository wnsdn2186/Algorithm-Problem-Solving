package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

private class Pos implements Comparable<Pos> {
	int row;
	int col;
	int mult;

	Pos(int row, int col, int mult) {
		this.row = row;
		this.col = col;
		this.mult = mult;
	}

	@Override
	public int compareTo(Pos o) {
		if (this.mult < o.mult)
			return -1;
		
		// 행 X 열을 했을 때 값이 같으면 교체해준다.
		// Collections.sort를 특정 기준으로 정렬하기 위함
		else if (this.mult == o.mult) {
			if (this.row < o.row) {
				int tempRow = this.row;
				int tempCol = this.col;
				this.row = o.row;
				this.col = o.col;
				o.row = tempRow;
				o.col = tempCol;
			}
			return 0;
		} 
		else 
			return 1;
	}
}

public class SWEA_P1258_행렬찾기 {
	static int N;// 2차원 배열의 크기
	static int map[][];// 입력받을 2차원 배열
	static boolean visited[][];// 방문한 곳을 나타내는 배열
	static int cnt;
	static ArrayList<Pos> result;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1258_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sz;
		int T = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= T; TC++) {
			N = Integer.parseInt(br.readLine());

			init();

			// 2차원 배열에 값 저장
			for (int i = 0; i < N; i++) {
				sz = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++)
					map[i][j] = Integer.parseInt(sz.nextToken());
			}

			// 배열의 처음부터 끝을 살펴보며 0보다 크고 방문하지 않은 곳은
			// solve() method 호출
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > 0 && visited[i][j] == false)
						solve(i, j);
				}
			}

			System.out.print("#" + TC + " " + cnt + " ");
			Collections.sort(result);
			for (Pos p : result) {
				System.out.print(p.row + " " + p.col + " ");
			}
			System.out.println("");
		}
		br.close();
	}

	//초기화
	public static void init() {
		map = new int[N][N];
		visited = new boolean[N][N];
		cnt = 0;
		result = new ArrayList<>();
	}

	public static void solve(int x, int y) {//x와 y는 행렬의 시작점
		int nx = x;
		int ny = y;

		// 우선 행의 끝을 찾아주고
		while (true) {
			if (ny >= N)
				break;

			else if (map[nx][ny] == 0)
				break;

			ny++;
		}
		ny -= 1;

		// 열의 끝을 찾아준다.
		while (true) {
			if (nx >= N)
				break;

			else if (map[nx][ny] == 0)
				break;

			nx++;
		}
		nx -= 1;

		// 그럼 시작점(x, y)와 마지막 지점 (nx, ny)를 통해 행렬의 크기를 구할 수 있다.
		int row = Math.abs(nx - x) + 1;
		int col = Math.abs(ny - y) + 1;

		// 그리고 방문한 곳을 표시해준다.
		for (int i = x; i <= nx; i++)
			for (int j = y; j <= ny; j++)
				visited[i][j] = true;

		// 결과는 result에 추가하고
		result.add(new Pos(row, col, row * col));
		
		// 개수를 늘려준다.
		cnt++;
	}
}