package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class SWEA_P1233_사칙연산_유효성_검사 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1233_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int TC = 1; TC <= 10; TC++) {
			int flag = 1;
			int T = Integer.parseInt(br.readLine());

			for (int i = 0; i < T; i++) {
				String[] s = br.readLine().split(" ");

				if (!Character.isDigit(s[1].charAt(0)) && s.length == 2)//연산자가 들어왔는데 리프노드라면
					flag = 0;//flag를 0으로 설정
			
				else
					continue;//for문을 다 돌아야 다음 케이스로 넘어갈 수 있기 때문에 continue;
			}

			System.out.println("#" + TC + " " + flag);//출력
		}
		br.close();
	}
}
