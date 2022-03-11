package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_P1238_Contact {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1238_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int TC = 1; TC <= 10; TC++) {
			StringTokenizer sz = new StringTokenizer(br.readLine());
			int[][] G = new int[101][101];

			int Edge = Integer.parseInt(sz.nextToken());//간선의 수 
			int Start = Integer.parseInt(sz.nextToken());//시작점

			String[] s = br.readLine().split(" ");

			for (int i = 0; i < Edge; i += 2)
				G[Integer.parseInt(s[i])][Integer.parseInt(s[i + 1])] = 1;

			System.out.println("#" + TC + " " + solve(G, Start));

		}
		br.close();
	}

	public static int solve(int[][] G, int Start) {
		Queue<Integer> queue = new LinkedList<>();
		int[] visited = new int[G.length];
		int cur = Start;
		int max = 0;

		queue.add(Start);//시작점 queue에 add
		visited[Start] = 1;//시작점의 visited = 1

		while (!queue.isEmpty()) {//queue가 빌 때까지 반복
			cur = queue.poll();//queue에서 하나 꺼내고

			for (int i = 1; i < G.length; i++) {
				if (G[cur][i] == 1 && visited[i] == 0) {//cur에 연결된 노드가 있다면
					queue.add(i);//queue에 추가하고
					visited[i] = visited[cur] + 1;//레벨을 부여(시작점은 1, 그 이후로는 2, 3, 4...)
				}
			}

			max = visited[cur];//최대 레벨의 값
		}

		for (int j = G.length - 1; j >= 1; j--)
			if (visited[j] == max)//visited를 뒤에서부터 보면서 max를 가지는 지점을 확인
				return j;

		return -1;
	}
}
