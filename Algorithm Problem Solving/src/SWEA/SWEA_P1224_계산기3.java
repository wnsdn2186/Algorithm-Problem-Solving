package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA_P1224_계산기3 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1224_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int TC = 1; TC <= 10; TC++) {
			int T = Integer.parseInt(br.readLine());
			
			String str = br.readLine();
			String postfix = InfixToPostfix(str);//Infix를 Postfix로 바꾸는 Method 호출
			
			System.out.println("#" + TC + " " + calculator(postfix));//결과로 받은 Postfix를 계산하는 Calculator Method 호출
		}
		
		br.close();
	}

	public static int priority(char c) {//연산자 우선순위 결정
		switch (c) {
		case '+':
		case '-':
			return 1;

		case '*':
			return 2;
		}

		return 0;
	}

	public static String InfixToPostfix(String str) {
		Stack<Character> stack = new Stack<Character>();
		String postfix = "";

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			if (c == '(')//여는 괄호이면 stack에 push
				stack.push(c);
			
			else if (Character.isDigit(c))//숫자면 postfix에 붙이기
				postfix += c;
			
			else if (c == '*' || c == '+') {//연산자 *, + 일 때 우선순위를 비교해 stack에 있는 것이 우선순위가 같거나 높으면 pop함
				if(priority(stack.peek()) >= priority(c))
					postfix += stack.pop();
				
				stack.push(c);//입력받은 것은 stack에 push
			} 
			
			else if (c == ')') {//닫는 괄호이면
				while (stack.peek() != '(')//stack의 top이 '('일 때까지  
					postfix += stack.pop();//pop한 뒤에 postfix에 추가함
				
				stack.pop();//현재 stack top에는 '('가 있으므로 pop해줌
			}
		}
		
		while (!stack.isEmpty())//stack에 남아있는 것들 postfix에 붙여줌
			postfix += stack.pop();
		
		stack.clear();
	
		return postfix;//return
	}

	public static int calculator(String str) {
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			if (Character.isDigit(c))//숫자면 stack에 그대로 push
				stack.push(c - '0');

			else if (c == '*')//'*'이면 pop을 2번 해서 곱하기
				stack.push(stack.pop() * stack.pop());

			else if (c == '+')//'+'이면 pop을 2번 해서 더하기
				stack.push(stack.pop() + stack.pop());
		}

		return stack.pop();//return
	}
}
