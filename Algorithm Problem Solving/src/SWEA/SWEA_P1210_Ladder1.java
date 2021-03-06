package SWEA;
import java.io.FileInputStream;
import java.util.Scanner;

/*Ladder1*/
public class SWEA_P1210_Ladder1 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1210_input.txt"));
		Scanner sc = new Scanner(System.in);
		int T;
		int start = 1, floor = 98;
		int[][] ladder = new int[100][100];
		boolean[][] visit;

		for (int test_case = 1; test_case <= 10; test_case++) {
			T = sc.nextInt();
			
			visit = new boolean[100][100];
			floor = 98;

			for (int i = 0; i < 100; i++)
				for (int j = 0; j < 100; j++)
					ladder[i][j] = sc.nextInt();

			for (int k = 0; k < 100; k++)
				if(ladder[floor + 1][k] == 2)
					start = k;
				
			while (true) {
				if(floor == 0)//첫 줄에 도착했을 때
					break;
				
				else if (start - 1 > 0 && start + 1 < 100 && ladder[floor][start - 1] == 0 && ladder[floor][start + 1] == 0) {//좌우 길이 없고 벽이 아닐 때
					visit[floor][start] = true;
					floor--;
				}

				else if (start - 1 > 0 && ladder[floor][start - 1] == 1 && visit[floor][start - 1] == false) {//왼쪽 길이 있을 때																				
					visit[floor][start] = true;
					start--;
				}
				
				else if (start + 1 < 100 && ladder[floor][start + 1] == 1 && visit[floor][start + 1] == false) {//오른쪽 길이 있을 때
					visit[floor][start] = true;
					start++;
				}
				
				else if(start - 1 < 0 || start + 1 >= 100) {//왼쪽 또는 오른쪽이 벽일 때
					visit[floor][start] = true;
					floor--;
				}
				
				else if(visit[floor][start - 1] == false || visit[floor][start + 1] == false) {//왼쪽이나 오른쪽 길을 지나왔을 때
					visit[floor][start] = true;
					floor--;
				}
			}
			
			System.out.println("#" + T + " " + start);//출력
		}
	}
}
