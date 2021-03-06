package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class SWEA_P4047_영준이의_카드_카운팅 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P4047_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		// S, D, H, C
		for (int TC = 1; TC <= T; TC++) {
			int[] S = new int[14];//S의 개수를 저장하는 배열
			int[] D = new int[14];//D의 개수를 저장하는 배열
			int[] H = new int[14];//H의 개수를 저장하는 배열
			int[] C = new int[14];//C의 개수를 저장하는 배열
			int[] sum = new int[4];//S, D, H, C의 합을 저장하는 배열
			int flag = 0;//ERROR를 나타내는 flag

			String s = br.readLine();
			
			if(s.length() == 1 || s.length() == 2)//입력받은 길이가 1 또는 2이면 continue
				continue;
			
			System.out.print("#" + TC + " ");
			
			for (int i = 0; i < s.length() / 3; i++) {//3의 배수로 나아가야 하기 때문에 s.length / 3
				char c = s.charAt(i * 3);//3 * i는 S, D, H, C 중 하나 
				int num = (s.charAt(i * 3 + 1) - '0') * 10 + (s.charAt(i * 3 + 2) - '0');//*10을 해주고 더해야 2자리수까지 커버 가능 

				//해당하는 카드의 배열값을 1 증가시키고, 만약 그 배열값이 1보다 크면 중복이라는 말이므로 ERROR를 출력하고 flag = 1로 설정. ERROR가 아니면 sum의 값 +1
				switch (c) {
				case 'S':
					S[num] += 1;
					if (S[num] > 1) {
						System.out.println("ERROR");
						flag = 1;
					} else
						sum[0]++;
					break;

				case 'D':
					D[num] += 1;
					if (D[num] > 1) {
						System.out.println("ERROR");
						flag = 1;
					} else
						sum[1]++;
					break;

				case 'H':
					H[num] += 1;
					if (H[num] > 1) {
						System.out.println("ERROR");
						flag = 1;
					} else
						sum[2]++;
					break;

				case 'C':
					C[num] += 1;
					if (C[num] > 1) {
						System.out.println("ERROR");
						flag = 1;
					} else
						sum[3]++;
					break;
				}

				if (flag == 1)
					break;
			}
			if (flag == 0) {//ERROR가 안 났을 때
				for (int i = 0; i < 4; i++)
					System.out.print((13 - sum[i]) + " ");//13에서 개수를 빼서 출력
				System.out.println("");
			}

		}
		br.close();
	}
}