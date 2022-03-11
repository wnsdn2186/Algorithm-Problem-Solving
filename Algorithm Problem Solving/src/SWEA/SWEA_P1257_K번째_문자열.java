package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class SWEA_P1257_K번째_문자열 {
	static int Find;// 사전순으로 정렬했을 때 찾아야 하는 위치
	static int N;// 입력문자의 길이
	static String str;// 입력문자
	static TreeSet<String> Dick;// 사전순으로 정렬된 TreeSet

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1257_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		int T = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= T; TC++) {
			// 초기화
			Find = Integer.parseInt(br.readLine());
			str = br.readLine();
			N = str.length();
			Dick = new TreeSet<>();
			sb = new StringBuilder();
			int cnt = 0;

			for (int i = 1; i <= N; i++)// 한 글자에서 str의 길이만큼
				for (int j = 0; j <= N - i; j++)
					Dick.add(str.substring(j, j + i));// substring으로 분리한 다음 TreeSet에 삽입

			// TreeSet을 탐색
			for (String t : Dick) {
				if (cnt == Find - 1) {
					sb.append("#" + TC + " " + t);
					System.out.println(sb.toString());
				}
				cnt++;
			}
		}
		br.close();
	}
}