package BOJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BOJ_P2669_직사각형_네개의_합집합의_면적_구하기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./BOJ_INPUT/BOJ_P2669_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[][] array = new int[100][100];
		int cnt = 0;

		for (int i = 0; i < 4; i++) {
			String[] s = br.readLine().split(" ");
			for (int j = Integer.parseInt(s[0]); j < Integer.parseInt(s[2]); j++) {
				for (int k = Integer.parseInt(s[1]); k < Integer.parseInt(s[3]); k++) {
					if (array[j][k] == 0) {
						array[j][k]++;
						cnt++;
					}
				}
			}
		}

		System.out.println(cnt);
	}
}
