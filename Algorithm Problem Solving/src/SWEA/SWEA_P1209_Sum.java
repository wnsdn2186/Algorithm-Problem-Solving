package SWEA;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

/*Sum*/
public class SWEA_P1209_Sum {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1209_input.txt"));
		Scanner sc = new Scanner(System.in);
		int T, num;
		int ROW_MAX;// 행 최대값
		int COL_MAX;// 열 최대값
		int DIAG_MAX;// 대각선 최대값
		int FINAL_MAX;// 최종 최대값
		int ROW_SUM, DIAG_SUM1, DIAG_SUM2;// 행 합, 열 합, 대각선 합
		int[] COL_SUM = new int[101];// 각 열의 합

		for (int test_case = 1; test_case <= 10; test_case++) {
			T = sc.nextInt();

			/*----------------초기화------------------*/
			ROW_MAX = Integer.MIN_VALUE;
			COL_MAX = Integer.MIN_VALUE;
			ROW_SUM = 0;
			DIAG_MAX = Integer.MIN_VALUE;
			FINAL_MAX = 0;
			ROW_SUM = 0;
			DIAG_SUM1 = 0;
			DIAG_SUM2 = 0;
			Arrays.fill(COL_SUM, 0);
			/*---------------------------------------*/

			for (int i = 1; i <= 100; i++) {
				for (int j = 1; j <= 100; j++) {
					num = sc.nextInt();

					ROW_SUM += num;// 현재 행의 합

					COL_SUM[j] += num;// 첫 열부터 마지막 열까지 계속 더함

					if (i == j)// 오른쪽 아래 대각선 합
						DIAG_SUM1 += num;

					if (i + j == 101)// 왼쪽 아래 대각선 합
						DIAG_SUM2 += num;
				}
				if (ROW_SUM > ROW_MAX)// 현재 행의 합과 행의 최대값 비교
					ROW_MAX = ROW_SUM;

				ROW_SUM = 0;
			}

			// 대각선 최대값 구하기
			DIAG_MAX = Math.max(DIAG_SUM1, DIAG_SUM2);

			// 각 열의 합 정렬 후 열의 최대값 구하기
			Arrays.sort(COL_SUM);
			COL_MAX = COL_SUM[100];

			// 최종 최대값 구하기
			FINAL_MAX = Math.max(ROW_MAX, Math.max(COL_MAX, DIAG_MAX));

			System.out.println("#" + T + " " + FINAL_MAX);
		}
	}
}
