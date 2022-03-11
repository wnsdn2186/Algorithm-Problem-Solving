package SWEA;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class SWEA_P1208_Flatten {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1208_input.txt"));
		Scanner sc = new Scanner(System.in);
		int Dump, Difference = 0;//Dump : 주어진 횟수,  Difference : 최고점 - 최저점
		int[] array = new int[101];

		for (int test_case = 1; test_case <= 10; test_case++) {
			Dump = sc.nextInt();//Dump 입력 받기

			for (int i = 1; i <= 100; i++)
				array[i] = sc.nextInt();//array[]에 각 높이 저장

			for (int j = 1; j <= Dump; j++) {
				Arrays.sort(array);//array[] 정렬
				/*정렬된 높이에서 최고높이는 -1, 최소높이는 +1 => Dump만큼 반복*/
				array[1]++;
				array[100]--;
			}

			Arrays.sort(array);//다시 정렬
			Difference = array[100] - array[1];//최종 높이차

			System.out.println("#" + test_case + " " + Difference);//출력
		}
	}

}
