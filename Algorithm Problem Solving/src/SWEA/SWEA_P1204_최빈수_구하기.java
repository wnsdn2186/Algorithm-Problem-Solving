package SWEA;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

/*최빈수 구하기*/
public class SWEA_P1204_최빈수_구하기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1204_input.txt"));
		Scanner sc = new Scanner(System.in);
		int T, Case;
		int[] array = new int[101];
		int max = 0, idx = 0;
		T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			Case = sc.nextInt();

			for (int i = 0; i < 1000; i++)
				array[sc.nextInt()]++;//array[]에 점수별 학생의 수 저장
			
			for (int j = 0; j < 101; j++)
				if (array[j] >= max && j >= idx) {//현재까지 점수 빈도와 비교 
					max = array[j];
					idx = j;
					//새롭게 지정
				}

			System.out.println("#" + Case + " " + idx);//최종 최빈수 출력
			max = idx = 0;//max, idx 값 초기화
			Arrays.fill(array, 0);//array[] 0으로 초기화
		}
	}
}
