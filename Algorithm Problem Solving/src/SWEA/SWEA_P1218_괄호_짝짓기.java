package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA_P1218_괄호_짝짓기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1218_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int TC = 1; TC <= 10; TC++) {
			int T = Integer.parseInt(br.readLine());
			int flag = 0;
			Stack<Character> stack = new Stack<>();

			String s = br.readLine();

			for (int i = 0; i < T; i++) {
				if (stack.empty())
					stack.push(s.charAt(i));

				else if (stack.peek() - s.charAt(i) == -1 || stack.peek() - s.charAt(i) == -2)
					stack.pop();

				else
					stack.push(s.charAt(i));
			}

			if (stack.empty())
				flag = 1;

			System.out.println("#" + TC + " " + flag);
		}
	}
}
