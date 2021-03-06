package SWEA;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_P1220_Magnetic {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1220_input.txt"));
		Scanner sc = new Scanner(System.in);
		int T, cnt = 0, pre = 0;
		int[][] array;

		for (int TC = 1; TC <= 10; TC++) {
			T = sc.nextInt();

			array = new int[100][100];
			cnt = 0;

			for (int i = 0; i < 100; i++)
				for (int j = 0; j < 100; j++)
					array[i][j] = sc.nextInt();// 2차원 배열 array에 값 저장

			for (int i = 0; i < 100; i++) {
				pre = array[0][i];// 현재값을 열의 첫 번째로 지정
				for (int j = 1; j < 100; j++) {
					if (array[j][i] == 0)// 열에서 0이 나오면 for문 반복
						continue;

					if (pre == 1 && array[j][i] == 2)// 이전 열의 값이 1이고, 현재 열의 값이 2이면 cnt++
						cnt++;

					pre = array[j][i];// 현재 열의 값이 1인 경우 pre에 저장(최대한 S극과 가까운 N극을 찾기 위함)
				}
			}

			System.out.println("#" + TC + " " + cnt);
		}
	}
}
