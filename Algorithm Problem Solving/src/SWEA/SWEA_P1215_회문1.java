package SWEA;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_P1215_회문1 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1215_input.txt"));
		Scanner sc = new Scanner(System.in);
		int T;

		for (int TC = 1; TC <= 10; TC++) {
			T = sc.nextInt();

			int cnt = 0;
			int[][] array = new int[10][10];

			for (int i = 0; i < 8; i++) {
				String s = sc.next();
				for (int j = 0; j < 8; j++)
					array[i][j] = s.charAt(j);
			}

			// 가로 탐색
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8 - T + 1; j++) {// T만큼 탐색할 수 있는 만큼만 증가
					int check = 0;
					for (int k = 0; k < T / 2; k++) {
						if (array[i][j + k] != array[i][j + T - k - 1])// 대응하는 값이 다르면
							check++;// 증가
					}
					if (check == 0)// 0 : 전부 같을 때 | !0 : 다른 게 있을 때
						cnt++;
				}
			}

			// 세로 탐색
			for (int i = 0; i < 8 - T + 1; i++) {// T만큼 탐색할 수 있는 만큼만 증가
				for (int j = 0; j < 8; j++) {
					int check = 0;
					for (int k = 0; k < T / 2; k++) {
						if (array[i + k][j] != array[i + T - k - 1][j])// 대응하는 값이 다르면
							check++;// 증가
					}
					if (check == 0)// 0 : 전부 같을 때 | !0 : 다른 게 있을 때
						cnt++;
				}
			}

			System.out.println("#" + TC + " " + cnt);
		}
	}
}
