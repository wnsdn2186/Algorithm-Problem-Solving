package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_P1267_작업순서 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1267_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sz;

		for (int TC = 1; TC <= 10; TC++) {
			sz = new StringTokenizer(br.readLine());

			int V = Integer.parseInt(sz.nextToken());// Vertex 개수
			int E = Integer.parseInt(sz.nextToken());// Edge 개수

			int[][] G = new int[V + 1][V + 1];//인접행렬 선언
			int[] InDegree = new int[V + 1];//진입차수 선언
			
			Queue<Integer> queue = new LinkedList<Integer>();//Queue선언

			String[] str = br.readLine().split(" ");//한 줄 입력

			for (int i = 0; i < E * 2; i += 2) {
				G[Integer.parseInt(str[i])][Integer.parseInt(str[i + 1])] = 1;// 연결된 Vertex는 1
				InDegree[Integer.parseInt(str[i + 1])]++;//간선을 받는 Vertex는 진입차수 + 1
			}

			for (int j = 1; j < G.length; j++)//진입차수가 0인 Vertex는 Queue에 Add
				if (InDegree[j] == 0)
					queue.add(j);

			System.out.print("#" + TC + " ");

			while (!queue.isEmpty()) {//Queue가 빌 때까지 반복
				int node = queue.poll();//Queue에서 하나 꺼내서 출력
				System.out.print(node + " ");

				for (int k = 1; k <= V; k++) {
					if (G[node][k] == 1) {//Q에서 나가는 Edge가 있다면
						InDegree[k]--;//InDegree값 -1
						if (InDegree[k] == 0)//InDegree값이 0이면 들어오는 간선이 없다는 의미이므로
							queue.add(k);// Queue에 추가
					}
				}
			}
			
			System.out.println("");
		}
		br.close();
	}
}
