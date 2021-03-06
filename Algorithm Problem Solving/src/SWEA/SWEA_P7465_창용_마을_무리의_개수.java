package SWEA;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_P7465_창용_마을_무리의_개수 {
	static int N;
	static int M;
	static int[] root;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P7465_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sz;
		int T = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= T; TC++) {
			sz = new StringTokenizer(br.readLine());
			N = Integer.parseInt(sz.nextToken());
			M = Integer.parseInt(sz.nextToken());

			root = new int[N + 1];
			for (int i = 1; i <= N; i++)
				root[i] = i;

			for (int i = 0; i < M; i++) {
				sz = new StringTokenizer(br.readLine());
				union(Integer.parseInt(sz.nextToken()), Integer.parseInt(sz.nextToken()));
			}

			int[] count = new int[N + 1];
			int cnt = 0;

			for (int i = 1; i <= N; i++) {
				if (count[find(i)] == 0) {
					cnt++;
					count[find(i)]++;
				}
			}

			System.out.println("#" + TC + " " + cnt);
		}
		br.close();
	}

	public static int find(int x) {
		if (root[x] == x)
			return x;

		else
			return find(root[x]);
	}

	public static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA == rootB)
			return;

		root[rootA] = rootB;
	}
}