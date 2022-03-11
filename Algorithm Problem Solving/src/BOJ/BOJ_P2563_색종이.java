package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_P2563_색종이 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./BOJ_INPUT/BOJ_P2563_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sz;
		int T = Integer.parseInt(br.readLine());

		int[][] map = new int[101][101];
		int sum = 0;

		for (int i = 0; i < T; i++) {
			sz = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(sz.nextToken());
			int y = Integer.parseInt(sz.nextToken());

			for (int j = x; j < x + 10; j++)
				for (int k = y; k < y + 10; k++) {
					if (map[j][k] == 0) {
						sum++;
						map[j][k]++;
					}
				}
		}

		System.out.println(sum);
	}
}
