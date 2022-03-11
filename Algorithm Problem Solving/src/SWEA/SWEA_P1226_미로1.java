package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA_P1226_미로1 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1226_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		for (int TC = 1; TC <= 10; TC++) {
			int T = Integer.parseInt(br.readLine());

			int[][] maze = new int[16][16];

			for (int i = 0; i < 16; i++) {//2차원 배열에 미로 저장
				String s = br.readLine();
				for (int j = 0; j < 16; j++) {
					maze[i][j] = s.charAt(j) - '0';
				}
			}

			System.out.println("#" + TC + " " + solve(maze));//solve() 호출
		}

		br.close();
	}

	public static int solve(int[][] maze) {
		Stack<Integer> X = new Stack<>();//X값을 저장하는 stack
		Stack<Integer> Y = new Stack<>();//Y값을 저장하는 stack
		int[] dx = { -1, 0, 1, 0 };//상, 우, 하, 좌에 따라 변하는 X값
		int[] dy = { 0, 1, 0, -1 };//상, 우, 하, 좌에 따라 변하는 Y값
		boolean[][] visited = new boolean[maze.length - 1][maze.length - 1];//방문한 곳을 나타내는 2차원 배열
		int flag = 0;

		X.push(1);//시작 X값
		Y.push(1);//시작 Y값

		while (true) {
			//현재 X, Y값을 Stack에서 pop
			int curX = X.pop();
			int curY = Y.pop();

			visited[curX][curY] = true;//현재 X, Y는 방문했다는 표시

			for (int k = 0; k < 4; k++) {//상, 하, 좌, 우를 살펴볼 거임
				//만약 살펴보다 도착지가 있으면 무한 loop 탈출
				if (maze[curX + dx[k]][curY + dy[k]] == 3) {
					flag = 1;
					break;
				}
				
				//도착지가 아니면 현재 위치에서 갈 수 있는 곳을 Stack에 push
				else if (maze[curX + dx[k]][curY + dy[k]] == 0 && visited[curX + dx[k]][curY + dy[k]] == false) {
					X.push(curX + dx[k]);
					Y.push(curY + dy[k]);
				}
			}

			if (flag == 1)//만약 flag = 1이면 도착지가 있다는 의미
				break;

			else if (X.isEmpty() && Y.isEmpty())//flag = 0이면서 stack이 비었으면 모든 길을 탐색했지만, 도착지를 찾지 못했다는 의미
				break;
		}

		return flag;
	}
}
