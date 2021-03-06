package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class set {
	double loc;// 좌표
	int m;;// 질량
}

public class SWEA_P1245_균형점 {
	static int N = 0;
	static set[] list;

	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1245_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sz;
		int T = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= T; TC++) {
			N = Integer.parseInt(br.readLine());// 좌표의 개수
			list = new set[N];

			for (int i = 0; i < N; i++)
				list[i] = new set();

			sz = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++)
				list[i].loc = Double.parseDouble(sz.nextToken());// 좌표 저장

			for (int i = 0; i < N; i++)
				list[i].m = Integer.parseInt(sz.nextToken());// 질량 저장

			System.out.print("#" + TC + " ");
			for (int i = 0; i < N - 1; i++)
				FindBalalcePoint(i, list[i].loc, list[i + 1].loc);
			System.out.println("");
		}
	}

	// BinarySearch로 균형점 찾기
	public static void FindBalalcePoint(int idx, double left, double right) {
		double div = 0.0;
		double sum = 0.0;

		for (int cnt = 0; cnt < 100; cnt++) {
			div = (left + right) / 2.0;
			
			sum = 0.0;

			for (int i = 0; i <= idx; i++)
				sum += calc(i, div);

			for (int i = N - 1; i > idx; i--)
				sum -= calc(i, div);

			if (sum > 0.0)
				left = div;

			else if (sum < 0.0)
				right = div;
		}

		System.out.printf("%.10f ", div);
	}

	// F = G*m1*m2/(d*d) 계산
	static double calc(int idx, double div) {
		return list[idx].m / ((list[idx].loc - div) * (list[idx].loc - div));
	}
}