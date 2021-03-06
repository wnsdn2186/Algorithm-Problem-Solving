package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA_P1234_비밀번호 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1234_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<>();

		for (int TC = 1; TC <= 10; TC++) {
			String[] s = br.readLine().split(" ");
			int T = Integer.parseInt(s[0]);//처음 비밀번호의 길이

			for (int i = 0; i < T; i++) {
				int N = s[1].charAt(i) - '0';

				if (stack.isEmpty())//Stack이 비었으면 Push
					stack.push(N);

				else {//Stack이 비어있지 않을 때
					if (stack.peek() == N)//Stack top이랑 입력값이 같으면
						stack.pop();//Stack Pop

					else//아니면 Stack에 Push
						stack.push(N);
				}
			}

			StringBuilder PWD = new StringBuilder();
			while (!stack.isEmpty())//Stack에 있는 값 String으로 만들고
				PWD.append(stack.pop());

			System.out.println("#" + TC + " " + PWD.reverse());//reverse해서 출력
			stack.clear();
		}
		
		br.close();
	}
}
