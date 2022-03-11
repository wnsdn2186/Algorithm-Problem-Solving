package SWEA;

import java.io.FileInputStream;
import java.util.Scanner;

public class SWEA_P1213_String {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1213_input.txt"));
		Scanner sc = new Scanner(System.in);
		int T;

		for (int TC = 1; TC <= 10; TC++) {
			T = sc.nextInt();

			String Token = sc.next();
			String Str = sc.next();
			String[] SplitByToken = Str.split(Token);
			int Sum = 0;
			
			for(String s : SplitByToken)
				Sum += s.length();
			
			System.out.println("#" + TC + " " + ((Str.length() - Sum) / Token.length()));
		}
	}
}
