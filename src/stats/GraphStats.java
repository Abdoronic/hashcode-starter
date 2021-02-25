package stats;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class GraphStats {

	static ArrayList<Integer>[] adj;
	static boolean[] visited;
	static int[] col;

	static boolean isTree(int u, int p) {
		visited[u] = true;
		for (int v : adj[u]) {
			if (v == p)
				continue;
			if (visited[v])
				return false;
			if (!isTree(v, u))
				return false;
		}
		return true;
	}

	static boolean isBipartite(int u, int d) {
		col[u] = d;
		for (int v : adj[u]) {

			if (col[v] == col[u])
				return false;
			if (col[v] == -1 && !isBipartite(v, d ^ 1))
				return false;
		}
		return true;
	}

	public static void analyzeGraph(ArrayList<Integer>[] in) throws FileNotFoundException {
		adj = in;
		int n = adj.length;
		visited = new boolean[n];
		col = new int[n];
		Arrays.fill(col, -1);
		int treeCnt = 0, bipartiteCnt = 0, cc = 0;
		for (int i = 0; i < n; i++)
			if (!visited[i]) {
				cc++;
				boolean isTree = isTree(i, -1);
				if (isTree)
					treeCnt++;
				else {
					boolean isBipartite = isBipartite(i, -1);
					if (isBipartite)
						bipartiteCnt++;
				}
			}
		System.out.println("Number of NODES = "+n);
		System.out.println("Number of CONNECTED COMPONENTS: = " + cc);
		System.out.println("Number of TREES: = " + treeCnt);
		System.out.println("Number of BIPARTITE COMPONENTS: = " + bipartiteCnt);
		printDegrees(adj);
	}

	public static void printDegrees(ArrayList<Integer>[] adj) throws FileNotFoundException {
		PrintWriter out = new PrintWriter("degrees.txt");
		int minDegree = Integer.MAX_VALUE, maxDegree = 0, n = adj.length;
		int[] cnt = new int[n];
		Integer[] indices = new Integer[n];
		for (int i = 0; i < n; i++) {
			int deg = adj[i].size();
			out.println(deg);
			cnt[deg]++;
			indices[i] = i;
			minDegree = Math.min(minDegree, deg);
			maxDegree = Math.max(maxDegree, deg);
		}
		Arrays.sort(indices, Comparator.comparingInt(i -> -cnt[i]));
		int considerCnt = Math.min(n, 7);

		System.out.println("Min Degree =  " + minDegree);
		System.out.println("Max Degree =  " + maxDegree);
		System.out.println("Most frequent Degrees are: ");
		for (int i = 0; i < considerCnt; i++) {
			int deg = indices[i];
			if (cnt[deg] == 0)
				break;
			System.out.printf("%d Nodes has degree %d\n", cnt[deg], deg);
		}
		out.close();
	}
}
