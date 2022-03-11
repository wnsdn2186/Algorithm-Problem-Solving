package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 행과 열을 하나의 객체로 관리하는 Class
class Matrix {
	int row;
	int col;

	// 생성자
	Matrix(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

public class SWEA_P1260_화학물질2 {
	static int N;// 2차원 배열의 크기
	static int map[][];// 입력받을 2차원 배열
	static boolean visited[][];// 방문한 곳을 나타내는 배열
	static ArrayList<Matrix> matrix;// 찾았던 배열의 (행, 열)쌍을 저장하는 변수
	static Queue<Matrix> sorted_matrix;// matrix를 순서대로 정렬한 결과를 저장하는 변수

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1260_input.txt"));
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

			// 배열을 찾아서 matrix에 (행, 열) 쌍 저장
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					/*
					 * 만약 배열의 원소가 0보다 크고 방문하지 않은 곳이라면 행렬의 첫 원소라는 의미이므로 FindMatrix method를 이용해 행렬의
					 * 마지막 원소를 찾는다.
					 */
					if (map[i][j] > 0 && visited[i][j] == false)
						FindMatrix(i, j);
				}
			}

			// matrix에 저장된 (행, 열) 쌍을 정렬
			for (Matrix i : matrix) {
				DFS(i);

				/*
				 * 결과를 저장하는 Queue의 size가 N이랑 같으면 정렬이 완료됐다는 의미이므로 break
				 */
				if (sorted_matrix.size() == matrix.size())
					break;

				// size랑 다르면 정렬이 덜 됐다는 것이므로 Queue를 clear하고 다시 반복
				else
					sorted_matrix.clear();
			}

			System.out.println("#" + TC + " " + FindSum());
		}
		br.close();
	}

	// 변수 초기화
	public static void init() {
		map = new int[N][N];
		visited = new boolean[N][N];
		matrix = new ArrayList<>();
		sorted_matrix = new LinkedList<>();
	}

	// 행렬을 찾는 method
	public static void FindMatrix(int x, int y) {// (x, y)는 행렬의 첫 번째 원소
		// nx, ny에 시작점을 저장
		int nx = x;
		int ny = y;

		// 시작점으로부터 밑으로 계속 내려가면서 행렬의 끝을 찾는다.
		while (true) {
			if (ny >= N)
				break;

			else if (map[nx][ny] == 0)
				break;

			ny++;
		}
		ny -= 1; // ny++;로 인해 1이 더 증가했으므로 빼준다.

		// 아래쪽의 끝을 찾았으면 오른쪽으로 계속 가면서 행렬의 오른쪽 끝을 찾는다.
		while (true) {
			if (nx >= N)
				break;

			else if (map[nx][ny] == 0)
				break;

			nx++;
		}
		nx -= 1;// nx++;로 인해 1이 더 증가했으므로 빼준다.

		// 행렬의 마지막 원소를 구했다면 시작점(x, y)과 끝점(nx, ny)을 이용해 행렬의 행과 열의 크기를 구할 수 있다.
		int row = Math.abs(nx - x) + 1;
		int col = Math.abs(ny - y) + 1;

		// 이미 확인한 행렬의 원소라면 방문하지 않기 위해서 표시를 해준다.
		for (int i = x; i <= nx; i++)
			for (int j = y; j <= ny; j++)
				visited[i][j] = true;

		// 결과를 저장한다.
		matrix.add(new Matrix(row, col));
	}

	// 연쇄행렬을 찾는 method
	public static void DFS(Matrix i) {
		// 매개변수로 들어온 i를 일단 저장한다.
		sorted_matrix.add(i);

		/*
		 * i를 제외하고 모든 (행, 열)쌍을 살펴보면서 만약 i의 열과 j의 행이 같다면 연쇄행렬이므로 다시 j를 매개변수로 넘겨 재귀호출
		 */
		for (Matrix j : matrix) {
			if (i.col == j.row)
				DFS(j);
		}
	}

	// 연쇄행렬 최소 곱셈을 구하는 method
	public static int FindSum() {
		int n = sorted_matrix.size();
		int[] D = new int[2 * n + 1];
		int[][] DP = new int[n + 1][n + 1];

		// i의 행, i의 열, j의 열 방식으로 저장
		for (int i = 0; i < n; i++) {
			Matrix m = sorted_matrix.poll();
			D[i] = m.row;
			D[i + 1] = m.col;
		}

		// Matrix Chain Multiplication 알고리즘
		for (int len = 2; len <= n; len++) {
			for (int i = 1; i <= n - len + 1; i++) {
				int j = i + len - 1;
				DP[i][j] = Integer.MAX_VALUE;
				for (int k = i; k < j; k++) {
					int weight = DP[i][k] + DP[k + 1][j] + D[i - 1] * D[k] * D[j];
					DP[i][j] = Math.min(DP[i][j], weight);
				}
			}
		}

		return DP[1][n];
	}
}