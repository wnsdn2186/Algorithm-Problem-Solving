package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_P4008_숫자_만들기 {
	static int N;
	static int[] op;
	static int[] num;
	static int max;
	static int min;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P4008_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sz;
		int T = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= T; TC++) {
			N = Integer.parseInt(br.readLine());
			op = new int[4];
			num = new int[N];

			sz = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++)
				op[i] = Integer.parseInt(sz.nextToken());

			sz = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				num[i] = Integer.parseInt(sz.nextToken());

			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;

			DFS(1, num[0]);
			System.out.println("#" + TC + " " + (max - min));
		}
		br.close();
	}

	public static void DFS(int idx, int result) {
		if (idx >= N) {
			max = max > result ? max : result;
			min = min < result ? min : result;
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (op[i] > 0) {
				op[i]--;

				if (i == 0)
					DFS(idx + 1, result + num[idx]);

				else if (i == 1)
					DFS(idx + 1, result - num[idx]);

				else if (i == 2)
					DFS(idx + 1, result * num[idx]);

				else if (i == 3)
					DFS(idx + 1, result / num[idx]);

				op[i]++;
			}
		}
	}
}