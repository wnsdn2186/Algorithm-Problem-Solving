package BOJ;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_P2309_일곱_난쟁이 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./BOJ_INPUT/BOJ_P2309_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] tall = new int[9];
		ArrayList<Integer> result = new ArrayList<>();
		int sum = 0;
		int flag = 0;

		for (int i = 0; i < 9; i++) {
			tall[i] = Integer.parseInt(br.readLine());
			sum += tall[i];
		}

		for (int i = 0; i < 9; i++) {
			for (int j = i + 1; j < 9; j++) {
				if (sum - tall[i] - tall[j] == 100) {
					for (int k = 0; k < 9; k++)
						if (k != i && k != j)
							result.add(tall[k]);
					flag = 1;
					break;
				}
			}
			if(flag == 1)
				break;
		}
		
		Collections.sort(result);
		for(int i = 0; i < result.size(); i++)
			System.out.println(result.get(i));
	}
}