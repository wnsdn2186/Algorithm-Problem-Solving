package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_P2605_줄_세우기 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./BOJ_INPUT/BOJ_P2605_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringTokenizer sz = new StringTokenizer(br.readLine());
		
		int[] line = new int[T];
		
		for(int i = 0; i < T; i++) {
			line[i] = i + 1;
			int change = Integer.parseInt(sz.nextToken());
			if(change != 0) {
				int temp = line[i];
				for(int j = i; j > i - change; j--) 
					line[j] = line[j - 1];
				line[i - change] = temp;
			}
		}
		
		for(int i = 0; i < T; i++)
			System.out.print(line[i] + " ");
	}
}
