package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {//노드 클래스
	float data;
	int lchild;
	int rchild;
	char op;
}

public class SWEA_P1232_사칙연산 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1232_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int TC = 1; TC <= 10; TC++) {
			int T = Integer.parseInt(br.readLine());

			Node[] Tree = new Node[T + 1];//Node타입의 배열 선언

			for (int i = 0; i <= T; i++)
				Tree[i] = new Node();//각 Node 생성

			for (int i = 0; i < T; i++) {
				StringTokenizer sz = new StringTokenizer(br.readLine());

				int idx = Integer.parseInt(sz.nextToken());//인덱스 번호
				String s = sz.nextToken();

				if (!Character.isDigit(s.charAt(0))) {//만약 연산자가 들어오면
					Tree[idx].op = s.charAt(0);//연산자를 넣고
					Tree[idx].lchild = Integer.parseInt(sz.nextToken());
					Tree[idx].rchild = Integer.parseInt(sz.nextToken());
					//왼쪽, 오른쪽 자식도 넣음
				}

				else
					Tree[idx].data = Float.parseFloat(s);//연산자가 아닌 숫자가 들어오면 data에 저장
			}

			System.out.println("#" + TC + " " + (int) solve(Tree, 1));
		}
		br.close();
	}

	public static float result;

	public static float solve(Node[] Tree, int node) {
		if (Tree[node].op != '0' && Tree[node].op == '+')
			result = solve(Tree, Tree[node].lchild) + solve(Tree, Tree[node].rchild);

		else if (Tree[node].op != '0' && Tree[node].op == '-')
			result = solve(Tree, Tree[node].lchild) - solve(Tree, Tree[node].rchild);

		else if (Tree[node].op != '0' && Tree[node].op == '*')
			result = solve(Tree, Tree[node].lchild) * solve(Tree, Tree[node].rchild);

		else if (Tree[node].op != '0' && Tree[node].op == '/')
			result = solve(Tree, Tree[node].lchild) / solve(Tree, Tree[node].rchild);	
		//현재 노드가 연산자이면 (왼쪽 자식 연산자 오른쪽 자식)의 형태로 재귀

		else//연산자가 아니면 result에 넣고
			result = Tree[node].data;

		return result;//return
	}
}
