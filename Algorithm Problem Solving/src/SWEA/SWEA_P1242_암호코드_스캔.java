package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_P1242_암호코드_스캔 {
	static int Hex[][] = { { 0, 0, 0, 0 }, // 0
			{ 0, 0, 0, 1 }, // 1
			{ 0, 0, 1, 0 }, // 2
			{ 0, 0, 1, 1 }, // 3
			{ 0, 1, 0, 0 }, // 4
			{ 0, 1, 0, 1 }, // 5
			{ 0, 1, 1, 0 }, // 6
			{ 0, 1, 1, 1 }, // 7
			{ 1, 0, 0, 0 }, // 8
			{ 1, 0, 0, 1 }, // 9
			{ 1, 0, 1, 0 }, // A
			{ 1, 0, 1, 1 }, // B
			{ 1, 1, 0, 0 }, // C
			{ 1, 1, 0, 1 }, // D
			{ 1, 1, 1, 0 }, // E
			{ 1, 1, 1, 1 } // F
	};

	static int[][][] CODE = new int[4][3][4];

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1242_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		init();

		for (int TC = 1; TC <= T; TC++) {
			StringTokenizer sz = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(sz.nextToken());
			int col = Integer.parseInt(sz.nextToken());
			int[][] Bincode = new int[row][col * 4];
			String preline = "";

			for (int i = 0; i < row; i++) {
				String line = br.readLine();

				if (line.split("0").length == 0 && line.contains(preline))//0으로 채워져 있거나 이전 line과 같다면 continue
					continue;
				
				else
					preline = line;//아니라면 preline 재설정

				//16진수를 2진수로 변환
				for (int j = 0; j < col; j++) {
					int c = line.charAt(j);

					if (c <= '9' && c >= '0')
						c = c - '0';
					else
						c = c - 'A' + 10;

					for (int k = 0; k < 4; k++)
						Bincode[i][j * 4 + k] = Hex[c][k];
				}
			}

			System.out.println("#" + TC + " " + Encode(Bincode, row, col));
		}
		br.close();
	}

	//0~9를 나타내는 코드의 비율 초기화
	public static void init() {
		CODE[1][0][0] = 0;
		CODE[1][1][0] = 1;
		CODE[0][1][1] = 2;
		CODE[3][0][0] = 3;
		CODE[0][2][1] = 4;
		CODE[1][2][0] = 5;
		CODE[0][0][3] = 6;
		CODE[2][0][1] = 7;
		CODE[1][0][2] = 8;
		CODE[0][0][1] = 9;
	}

	public static int Encode(int[][] Bincode, int row, int col) {
		int idx = 7;
		int[] code = new int[8];
		int result = 0;

		for (int i = 1; i < row; i++) {
			for (int j = col * 4 - 1; j >= 0; j--) {
				if (Bincode[i][j] == 1 && Bincode[i - 1][j] == 0) {//현재 지점과 이전 라인의 지점이 다른지 확인(중복제거)
					int x, y, z;
					x = y = z = 0;

					//끝에서 1의 개수 카운트
					while (Bincode[i][j] == 1) {
						z++;
						j--;
					}

					////끝에서 0의 개수 카운트
					while (Bincode[i][j] == 0) {
						y++;
						j--;
					}

					//끝에서 1의 개수 카운트
					while (Bincode[i][j] == 1) {
						x++;
						j--;
					}
					
					//끝에서 0의 개수 카운트
					while (Bincode[i][j] == 0 && j > 0) {
						j--;
					}

					j++;

					int min = Math.min(Math.min(x, y), z);//가장 작은 숫자로

					//나눠서 비율을 찾아냄
					x /= min;
					y /= min;
					z /= min;

					code[idx--] = CODE[x - 1][y - 1][z - 1];//code배열에 넣음

					if (idx == -1) {
						int sum = (code[0] + code[2] + code[4] + code[6]) * 3 + code[1] + code[3] + code[5] + code[7];

						if (sum % 10 == 0)
							for (int k = 0; k < code.length; k++)
								result += code[k];

						idx = 7;
					}
				}
			}
		}
		return result;
	}
}
