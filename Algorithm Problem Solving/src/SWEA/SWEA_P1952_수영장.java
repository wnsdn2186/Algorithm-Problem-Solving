package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_P1952_수영장 {
	static int[] fee = new int[4]; 
	static int[] days = new int[13];
	static int result;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1952_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sz;
		int T = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= T; TC++) {
			sz = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++)
				fee[i] = Integer.parseInt(sz.nextToken());

			sz = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++)
				days[i] = Integer.parseInt(sz.nextToken());

			result = fee[3];
			DFS(1, 0);
			System.out.println("#" + TC + " " + result);
		}
		br.close();
	}

	public static void DFS(int month, int sum) {
		if (month == 13) {
			result = result > sum ? sum : result;
			return;
		}

		if (days[month] == 0)
			DFS(month + 1, sum);

		if (days[month] > 0)
			DFS(month + 1, sum + days[month] * fee[0]);

		DFS(month + 1, sum + fee[1]);

		if (month <= 10)
			DFS(month + 3, sum + fee[2]);
	}
}