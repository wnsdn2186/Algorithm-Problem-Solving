package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_P2564_경비원 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./BOJ_INPUT/BOJ_P2564_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer sz = new StringTokenizer(br.readLine());
		int row = Integer.parseInt(sz.nextToken());
		int col = Integer.parseInt(sz.nextToken());

		// 1 : 북
		// 2 : 남
		// 3 : 서
		// 4 : 동

		int T = Integer.parseInt(br.readLine());
		int[] dir = new int[T];
		int[] loc = new int[T];

		for (int i = 0; i < T + 1; i++) {
			sz = new StringTokenizer(br.readLine());
			dir[i] = Integer.parseInt(sz.nextToken());
			loc[i] = Integer.parseInt(sz.nextToken());
		}

		for(int i = 0; i < T; i++) {
			switch(dir[i]) {
			case 1:
				
			}
		}
	}
}
