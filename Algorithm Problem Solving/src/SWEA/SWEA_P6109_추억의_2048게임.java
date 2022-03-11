package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_P6109_추억의_2048게임 {
	static int[][] board;
	static int Size;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P6109_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= T; TC++) {
			StringTokenizer sz = new StringTokenizer(br.readLine());
			Size = Integer.parseInt(sz.nextToken());// 배열크기 입력 받음
			String Dir = sz.nextToken();// 오른쪽, 왼쪽, 위, 아래중 방향을 입력 받음

			board = new int[Size][Size];// board 배열 초기화

			// board배열에 입력값 저장
			for (int i = 0; i < Size; i++) {
				sz = new StringTokenizer(br.readLine());
				for (int j = 0; j < Size; j++) {
					board[i][j] = Integer.parseInt(sz.nextToken());
				}
			}

			// #케이스번호 출력
			System.out.println("#" + TC);

			// 오른쪽이면 right() 호출
			if (Dir.equals("right"))
				right();

			// 왼쪽이면 left() 호출
			else if (Dir.equals("left"))
				left();

			// 위쪽이면 up() 호출
			else if (Dir.equals("up"))
				up();

			// 아래쪽이면 down() 호출
			else if (Dir.equals("down"))
				down();

			for(int i = 0; i < Size; i++)
				System.out.println(Arrays.toString(board[i]).replaceAll(",", "").replaceAll("\\[", "").replaceAll("\\]", ""));
		}
		br.close();
	}

	public static void right() {
		Stack<Integer> line = new Stack<Integer>();
		int x = 0;

		for (int i = 0; i < Size; i++) {
			for (int j = 0; j < Size; j++) {
				if (board[i][j] != 0) {
					line.push(board[i][j]);
					board[i][j] = 0;
				}
			}
			int y = Size - 1;

			while (!line.isEmpty()) {
				int m = line.pop();

				if (line.isEmpty())
					board[x][y] = m;

				else if (line.peek() == m) {
					m += line.pop();
					board[x][y] = m;
				}

				else if (line.peek() != m)
					board[x][y] = m;

				if (y - 1 >= 0)
					y--;
			}
			if (x + 1 < Size)
				x++;
		}
	}

	public static void left() {
		Queue<Integer> line = new LinkedList<Integer>();
		int x = 0;

		for (int i = 0; i < Size; i++) {
			for (int j = 0; j < Size; j++) {
				if (board[i][j] != 0) {
					line.add(board[i][j]);
					board[i][j] = 0;
				}
			}
			int y = 0;

			while (!line.isEmpty()) {
				int m = line.poll();

				if (line.isEmpty())
					board[x][y] = m;

				else if (line.peek() == m) {
					m += line.poll();
					board[x][y] = m;
				}

				else if (line.peek() != m)
					board[x][y] = m;

				if (y + 1 < Size)
					y++;
			}
			if (x + 1 < Size)
				x++;
		}
	}

	public static void up() {
		Queue<Integer> line = new LinkedList<Integer>();
		int y = 0;

		for (int i = 0; i < Size; i++) {
			for (int j = 0; j < Size; j++) {
				if (board[j][i] != 0) {
					line.add(board[j][i]);
					board[j][i] = 0;
				}
			}
			int x = 0;

			while (!line.isEmpty()) {
				int m = line.poll();

				if (line.isEmpty())
					board[x][y] = m;

				else if (line.peek() == m) {
					m += line.poll();
					board[x][y] = m;
				}

				else if (line.peek() != m)
					board[x][y] = m;

				if (x + 1 < Size)
					x++;
			}
			if (y + 1 < Size)
				y++;
		}
	}

	public static void down() {
		Stack<Integer> line = new Stack<Integer>();
		int y = 0;

		for (int i = 0; i < Size; i++) {
			for (int j = 0; j < Size; j++) {
				if (board[j][i] != 0) {
					line.push(board[j][i]);
					board[j][i] = 0;
				}
			}
			int x = Size - 1;

			while (!line.isEmpty()) {
				int m = line.pop();

				if (line.isEmpty())
					board[x][y] = m;

				else if (line.peek() == m) {
					m += line.pop();
					board[x][y] = m;
				}

				else if (line.peek() != m)
					board[x][y] = m;

				if (x - 1 >= 0)
					x--;
			}
			if (y + 1 < Size)
				y++;
		}
	}
}