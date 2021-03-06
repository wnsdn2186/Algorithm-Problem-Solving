package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_P1217_거듭_제곱 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1217_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int TC = 1; TC <= 10; TC++) {
			int T = Integer.parseInt(br.readLine());
			StringTokenizer sz = new StringTokenizer(br.readLine());
			
			System.out.println("#" + TC + " " + POW(Integer.parseInt(sz.nextToken()), Integer.parseInt(sz.nextToken())));
		}
	}
	
	public static int POW(int a, int b) {
		
		if(b == 0)
			return 1;
		
		else
			return POW(a, --b) * a;
	}
}
