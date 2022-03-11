package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_P2578_빙고 {
	static int[][] Bingo;
	static int Bingoline = 0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./BOJ_INPUT/BOJ_P2578_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Bingo = new int[25][25];
		int cnt = 0;
		int flag = 0;

		for (int i = 0; i < 5; i++) {
			StringTokenizer sz = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++)
				Bingo[i][j] = Integer.parseInt(sz.nextToken());
		}

		for (int i = 0; i < 5; i++) {
			StringTokenizer sz = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				Delete(Integer.parseInt(sz.nextToken()));
				++cnt;
				
		
					Check();
				
				if (Bingoline >= 3) {
					flag = 1;
					break;
				}	
				
				else
					Bingoline = 0;
			}
			if(flag == 1)
				break;
		}

		System.out.println(cnt);
	}

	public static void Delete(int num) {
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				if (Bingo[i][j] == num) {
					Bingo[i][j] = 0;
					break;
				}
	}

	public static void Check() {
		String[] horizon = new String[5];
		String[] vertical = new String[5];
		String[] diagonal = new String[2];

		for (int i = 0; i < 5; i++) {
			horizon[i] = "";
			vertical[i] = "";
			if(i < 2)
				diagonal[i] = "";
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				horizon[i] += String.valueOf(Bingo[i][j]);
				vertical[i] += String.valueOf(Bingo[j][i]);
			}
		}

		diagonal[0] = String.valueOf(Bingo[0][0]) + String.valueOf(Bingo[1][1]) + String.valueOf(Bingo[2][2])
				+ String.valueOf(Bingo[3][3]) + String.valueOf(Bingo[4][4]);
		diagonal[1] = String.valueOf(Bingo[0][4]) + String.valueOf(Bingo[1][3]) + String.valueOf(Bingo[2][2])
				+ String.valueOf(Bingo[3][1]) + String.valueOf(Bingo[4][0]);

		for (int i = 0; i < 5; i++) {
			if (horizon[i].equals("00000"))
				Bingoline++;

			if (vertical[i].equals("00000"))
				Bingoline++;

			if (i < 2 && diagonal[i].equals("00000"))
				Bingoline++;
		}
	}
}
