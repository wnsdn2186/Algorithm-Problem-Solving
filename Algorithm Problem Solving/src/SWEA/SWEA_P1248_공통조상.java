package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Node {
	int data;// 현재 노드의 데이터
	int lchild;// 왼쪽 자식
	int rchild;// 오른쪽 자식
	int parent;// 부모
}

public class SWEA_P1248_공통조상 {
	static int V, E;// 정점의 개수와 간선의 개수
	static Node[] list;// 이진트리를 배열로 표현하기 위한 변수
	static Stack<Integer> A_parent;// A의 부모를 저장하는 Stack
	static int CommonParent;// 공동 부모
	static int ChildCnt;// 공동 부모의 자식 개수

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1248_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sz;
		int T = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= T; TC++) {
			sz = new StringTokenizer(br.readLine());
			V = Integer.parseInt(sz.nextToken());// 정점의 개수
			E = Integer.parseInt(sz.nextToken());// 간선의 개수
			int A = Integer.parseInt(sz.nextToken());// 조상을 찾아야 하는 정점 1
			int B = Integer.parseInt(sz.nextToken());// 조상을 찾아야 하는 정점 2

			// 변수 초기화
			A_parent = new Stack<>();
			CommonParent = 0;
			ChildCnt = 0;

			// 배열 초기화
			list = new Node[V + 1];
			for (int i = 0; i <= V; i++)
				list[i] = new Node();

			// 입력 받기
			sz = new StringTokenizer(br.readLine());
			for (int i = 1; i <= E; i++) {
				int parent = Integer.parseInt(sz.nextToken());// 부모 입력받음
				int child = Integer.parseInt(sz.nextToken());// 자식 입력받음

				if (list[parent].lchild == 0) {// 부모에 왼쪽 자식이 없으면
					list[parent].lchild = child;// 왼쪽 자식에 먼저 넣고
					list[child].parent = parent;// 자식의 부모를 지정
				}

				else if (list[parent].rchild == 0) {// 부모에 오른쪽 자식이 없으면
					list[parent].rchild = child;// 오른쪽 자식에 넣고
					list[child].parent = parent;// 자식의 부모를 지정
				}
			}

			FindAncestorA(A);// A의 parent를 모두 찾는 method
			FindAncestorB(B);// A가 찾아놓은 parent중에서 B와 일치하는 parent를 찾는 method
			CountingChild(CommonParent);// 공통 부모의 자식 노드 개수를 찾는 method
			System.out.println("#" + TC + " " + CommonParent + " " + (ChildCnt + 1));// 공통 부모 자신도 포함해야 하기 때문에 +1을 해줌
			A_parent.clear();
		}
		br.close();
	}

	// A의 parent를 모두 찾는 method
	public static void FindAncestorA(int A) {
		if (list[A].parent == 1) {// 만약 A의 부모가 1이면 트리의 시작까지 올라갔다는 이야기니까
			A_parent.push(1);// A의 부모를 모아놓은 Stack에 1을 추가하고
			return;// 종료
		}

		A_parent.push(list[A].parent);// A의 부모가 1이 아니면 부모가 더 있다는 이야기이므로 Stack에 현재 부모를 넣고
		FindAncestorA(list[A].parent);// 현재 부모에서 다시 부모를 찾기 위해 재귀호출
	}

	public static void FindAncestorB(int B) {
		if (A_parent.contains(list[B].parent)) {// B의 부모를 트리 아래쪽에서부터 찾는데 가장 먼저 찾아지는 것이 제일 가까운 공통 부모임
			CommonParent = list[B].parent;
			return;
		}

		FindAncestorB(list[B].parent);// 현재 부모에서 다시 부모를 찾기 위해 재귀호출
	}

	public static void CountingChild(int node) {
		if (list[node].lchild != 0) {// 현재 노드에 왼쪽 자식이 있다면
			ChildCnt++;// 카운트 증가시키고
			CountingChild(list[node].lchild);// 왼쪽자식을 기준으로 다시 재귀호출
		}

		if (list[node].rchild != 0) {// 오른쪽 자식도 마찬가지
			ChildCnt++;
			CountingChild(list[node].rchild);
		}
	}
}
