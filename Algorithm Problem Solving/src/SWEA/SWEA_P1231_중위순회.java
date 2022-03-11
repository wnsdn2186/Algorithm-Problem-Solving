package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class SWEA_P1231_중위순회 {
 
    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("./SWEA_INPUT/SWEA_P1231_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        for (int TC = 1; TC <= 10; TC++) {
            int T = Integer.parseInt(br.readLine());
            char[] Tree = new char[T + 1];
 
            for (int i = 1; i <= T; i++) {
                StringTokenizer sz = new StringTokenizer(br.readLine());
                Tree[Integer.parseInt(sz.nextToken())] = sz.nextToken().charAt(0);
            }
 
            System.out.print("#" + TC + " ");
            PrintInOrder(Tree, 1);
            System.out.println("");
        }
 
        br.close();
    }
 
    public static void PrintInOrder(char[] Tree, int node) {
        if (Tree[node] != '0') {
            if (node * 2 < Tree.length)
                PrintInOrder(Tree, node * 2);
            System.out.print(Tree[node]);
            if (node * 2 + 1 < Tree.length)
                PrintInOrder(Tree, node * 2 + 1);
        }
    }
}