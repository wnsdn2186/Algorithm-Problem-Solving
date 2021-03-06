package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_P1861_정사각형_방 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1861_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= T; TC++) {
			int N = Integer.parseInt(br.readLine());
			int MAX = 0;
			int start = 0;

			int[][] rooms = new int[N + 1][N + 1];

			for (int i = 1; i <= N; i++) {
				StringTokenizer sz = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++)
					rooms[i][j] = Integer.parseInt(sz.nextToken());
			}
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					int move = solve(rooms, i, j);
					
					if(move > MAX) {
						MAX = move;
						start = rooms[i][j];
					}
					
					else if(move == MAX && rooms[i][j] < start)
						start = rooms[i][j];
				}					
			}
			
			
			System.out.print("#" + TC + " " + start + " " + (MAX + 1));
			System.out.println("");
		}
		br.close();
	}

	public static int solve(int[][] rooms, int i, int j) {
		int[] dx = { -1, 0, 1, 0 };
		int[] dy = { 0, 1, 0, -1 };
		boolean[][] visited = new boolean[rooms.length][rooms.length];

		Stack<Integer> X = new Stack<Integer>();
		Stack<Integer> Y = new Stack<Integer>();

		int curX = i;
		int curY = j;
		int cnt = 0;

		X.push(i);
		Y.push(j);
		visited[i][j] = true;

		while (true) {
			curX = X.pop();
			curY = Y.pop();
			visited[curX][curY] = true;

			for (int k = 0; k < 4; k++) {
				if (curX + dx[k] >= 1 && curX + dx[k] <= (rooms.length - 1) && curY + dy[k] >= 1
						&& curY + dy[k] <= (rooms.length - 1))
					if (rooms[curX + dx[k]][curY + dy[k]] - rooms[curX][curY] == 1
							&& visited[curX + dx[k]][curY + dy[k]] == false) {
						X.push(curX + dx[k]);
						Y.push(curY + dy[k]);
						cnt++;
					}
			}

			if (X.isEmpty() && Y.isEmpty())
				break;
		}

		return cnt;
	}
}