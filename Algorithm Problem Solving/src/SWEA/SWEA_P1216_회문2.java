package SWEA;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_P1216_회문2 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1216_input.txt"));
		Scanner sc = new Scanner(System.in);

		for (int TC = 1; TC <= 10; TC++) {
			int T = sc.nextInt();

			int[][] array = new int[100][100];
			int length = 100;
			boolean flag = true;

			for (int i = 0; i < 100; i++) {
				String s = sc.next();
				for (int j = 0; j < 100; j++)
					array[i][j] = s.charAt(j);
			}

			while (flag) {
				// 가로 탐색
				for (int i = 0; i < 100; i++) {
					for (int j = 0; j < 100 - length + 1; j++) {// T만큼 탐색할 수 있는 만큼만 증가
						int check = 0;
						for (int k = 0; k < length / 2; k++) {
							if (array[i][j + k] != array[i][j + length - k - 1])// 대응하는 값이 다르면
								check++;// 증가
						}
						if (check == 0)// 0 : 전부 같을 때 | !0 : 다른 게 있을 때
							flag = false;
					}
				}

				// 세로 탐색
				for (int i = 0; i < 100 - length + 1; i++) {// T만큼 탐색할 수 있는 만큼만 증가
					for (int j = 0; j < 100; j++) {
						int check = 0;
						for (int k = 0; k < length / 2; k++) {
							if (array[i + k][j] != array[i + length - k - 1][j])
								check++;
						}
						if (check == 0)
							flag = false;
					}
				}
				
				length--;
			}

			System.out.println("#" + TC + " " + (length + 1));
		}
	}
}
