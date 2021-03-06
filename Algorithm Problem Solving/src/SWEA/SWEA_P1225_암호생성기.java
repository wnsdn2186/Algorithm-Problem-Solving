package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_P1225_암호생성기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1225_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> queue = new LinkedList<>();

		for (int TC = 1; TC <= 10; TC++) {
			int T = Integer.parseInt(br.readLine());
			String[] str = br.readLine().split(" ");

			for (int i = 0; i < 8; i++)
				queue.add(Integer.parseInt(str[i]));

			int cnt = 1;

			while (!queue.contains(0)) {//queue에 0이 포함될 때까지
				if (cnt == 6)//cnt가 6이면 다시 1로 감소
					cnt = 1;

				if (queue.peek() - cnt < 0) {//queue의 값에서 cnt를 뺐을 때 음수면 0으로 add
					queue.poll();
					queue.add(0);
				} else
					queue.add(queue.poll() - cnt++);//음수가 아니면 cnt를 빼고 add
			}

			System.out.print("#" + TC + " ");//출력
			while (!queue.isEmpty())
				System.out.print(queue.poll() + " ");

			System.out.println("");
			queue.clear();
		}

		br.close();
	}
}
