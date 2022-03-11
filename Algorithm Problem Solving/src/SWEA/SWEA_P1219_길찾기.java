package SWEA;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_P1219_길찾기 {
	static int[][] G;
	static boolean[] visited;
	static int flag;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1219_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sz;

		for (int TC = 1; TC <= 10; TC++) {
			sz = new StringTokenizer(br.readLine());

			TC = Integer.parseInt(sz.nextToken());//TestCase 번호
			int T = Integer.parseInt(sz.nextToken());//TestCase 수

			init();//Adjacency List, visited, flag 초기화

			String[] str = br.readLine().split(" ");

			for (int i = 0; i < T * 2; i += 2)
				G[Integer.parseInt(str[i])][Integer.parseInt(str[i + 1])] = 1;//연결된 Vertex는 1
			
			DFS(0);//DFS 실행
			
			if(visited[99])//도착지점이 True이면
				flag = 1;//flag = 1
			
			System.out.println("#" + TC + " " + flag);//출력

		}
		
		br.close();
	}

	public static void DFS(int V) {
		visited[V] = true;//방문한 노드는 True

		for(int i = 1; i < G.length; i++) {
			if(G[V][i] == 1 && !visited[i]) {//연결된 Vertex확인 후 방문하지 않았으면
				DFS(i);//DFS호출
			}
		}
	}

	public static void init() {
		G = new int[100][100];
		visited = new boolean[100];
		flag = 0;
	}
}
