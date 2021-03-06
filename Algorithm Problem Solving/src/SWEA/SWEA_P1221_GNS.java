package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_P1221_GNS {
	static String[] pattern = { "ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN" };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1221_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sz;
		int T = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= T; TC++) {
			sz = new StringTokenizer(br.readLine());
			sz.nextToken();// #테스트 케이스
			int N = Integer.parseInt(sz.nextToken());// 입력 사이즈

			int[] counting = new int[10];// 기수정렬을 위한 배열

			sz = new StringTokenizer(br.readLine());// 한 줄을 입력받아
			for (int i = 0; i < N; i++) {
				String str = sz.nextToken();
				counting[PatternNum(str)]++;//입력받은 문자열과 매치되는 counting 값을 1 증가
			}

			System.out.println("#" + TC + " ");

			for (int i = 0; i < counting.length; i++) {
				for (int j = 0; j < counting[i]; j++)
					System.out.print(pattern[i] + " ");
			}

			System.out.println("");
		}
		br.close();
	}

	// "ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"와 매치되는 숫자를 반환
	public static int PatternNum(String str) {
		switch (str) {
		case "ZRO":
			return 0;
		case "ONE":
			return 1;
		case "TWO":
			return 2;
		case "THR":
			return 3;
		case "FOR":
			return 4;
		case "FIV":
			return 5;
		case "SIX":
			return 6;
		case "SVN":
			return 7;
		case "EGT":
			return 8;
		case "NIN":
			return 9;
		}
		return -1;
	}
}