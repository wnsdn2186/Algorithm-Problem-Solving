package SWEA;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

/*View*/
public class SWEA_P1206_View {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1206_input.txt"));
		Scanner sc = new Scanner(System.in);
		int Case, sum = 0, check = 0;
		int[] array;
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			Case = sc.nextInt();
			array = new int[Case + 1];

			for (int i = 1; i <= Case; i++) 
				array[i] = sc.nextInt();//array[]에 각 세대의 높이 저장
			
			for(int j = 3; j <= Case - 2; j++) {
				/*
				 * 1. 현재 세대를 기준으로 왼쪽 2세대의 높이 중 최대높이 구하기
				 * 2. 현재 세대를 기준으로 오른쪽 2세대의 높이 중 최대높이 구하기
				 * 3. 왼쪽 2세대 중에서 구한 최대높이와 오른쪽 2세대 중에서 구한 최대높이를 다시 비교해 최대높이 구하기
				 * 4. 현재 세대 높이 - 구한 최대 높이
				 * 5. 양수면 sum에 저장 음수면 패스*/
				check = array[j] - Math.max(Math.max(array[j - 1], array[j - 2]), Math.max(array[j + 1], array[j + 2]));
				if(check > 0)
					sum += check;
			}
			
			System.out.println("#" + test_case + " " + sum);//조망권이 확보된 세대 출력
			sum = 0;//sum 초기화
			Arrays.fill(array, 0);//array[] 0으로 초기화
		}
	}
}
