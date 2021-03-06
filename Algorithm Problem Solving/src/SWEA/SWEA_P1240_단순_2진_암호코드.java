package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_P1240_단순_2진_암호코드 {
	static String[] CODE = { "0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011",
			"0110111", "0001011" };//0~9값을 가지는 코드

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1240_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= T; TC++) {
			StringTokenizer sz = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(sz.nextToken());
			int col = Integer.parseInt(sz.nextToken());

			String msg = null;
			String NON_CODE = null;

			for (int i = 1; i <= col; i++)
				NON_CODE += String.valueOf(0);//전부 0으로 채워진 CODE

			for (int i = 1; i <= row; i++) {
				String str = br.readLine();

				if (str.contains(NON_CODE))//전부 0으로 채워진 line이라면 continue
					continue;

				else {//그게 아니라면
					for (int j = col - 1; j >= 0; j--) {
						if (str.charAt(j) - '0' == 1) {
							msg = str.substring(j - 55, j);//코드 부분만 분리
							break;
						}
					}
				}
			}

			System.out.println("#" + TC + " " + solve(msg + "1"));//출력
		}

		br.close();
	}

	public static int solve(String str) {
		int[] num = new int[8];
		int idx = 0;

		for (int i = 0; i < str.length(); i += 7) {
			String s = str.substring(i, i + 7);
			for (int j = 0; j < 10; j++) {
				if (s.equals(CODE[j])) {//코드와 매치되는 패턴이 있다면
					num[idx++] = j;//num배열에 추가
					break;
				}
			}
		}

		int check = (num[0] + num[2] + num[4] + num[6]) * 3 + num[1] + num[3] + num[5] + num[7];//올바른 코드인지 확인

		if (check % 10 != 0)
			return 0;

		int result = 0;

		for (int j = 0; j < idx; j++)
			result += num[j];//올바른 코드라면 result에 더해주고

		return result;//return
	}
}