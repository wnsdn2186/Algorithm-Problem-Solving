package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//수나사와 암나사의 굵기를 저장하는 class
class Screw {
	int male;
	int female;

	Screw(int male, int female) {
		this.male = male;
		this.female = female;
	}
}

public class SWEA_P1259_금속막대 {
	static int N;// 나사 쌍의 개수
	static ArrayList<Screw> screw;// 쌍을 저장할 ArrayList
	static Queue<Screw> result;// 결과를 저장할 Queue

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1259_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer sz;
		StringBuilder sb;

		int T = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= T; TC++) {
			sb = new StringBuilder();

			//입력 및 초기화
			N = Integer.parseInt(br.readLine());
			screw = new ArrayList<Screw>();
			result = new LinkedList<Screw>();

			sz = new StringTokenizer(br.readLine());

			//쌍 입력받고 저장
			for (int i = 0; i < N; i++) {
				int male = Integer.parseInt(sz.nextToken());
				int female = Integer.parseInt(sz.nextToken());
				screw.add(new Screw(male, female));
			}

			//쌍의 처음부터 solve() method 호출
			for (Screw i : screw) {
				solve(i);
				if (result.size() == N)//결과를 저장하는 Queue의 size가 N이랑 같으면
					break;//정렬이 완료됐다는 의미이므로 break
				else
					result.clear();
			}

			//출력
			sb.append("#" + TC + " ");
			while (!result.isEmpty()) {
				Screw i = result.poll();
				sb.append(i.male + " " + i.female + " ");
			}
			bw.write(String.valueOf(sb + "\n"));
		}
		br.close();
		bw.flush();
		bw.close();
	}

	public static void solve(Screw i) {
		result.add(i);//일단 결과를 저장하는 곳에 추가하고

		for (Screw j : screw) {
			if (i.female == j.male)//i의 암나사와 같은 굵기를 가지는 수나사가 있으면
				solve(j);//재귀호출
		}
	}
}