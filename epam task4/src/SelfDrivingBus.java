/*
 * Treeland is a country with  cities and  roads. There is exactly one path between any two cities.

The ruler of Treeland wants to implement a self-driving bus system and asks tree-loving Alex to plan the bus routes. Alex decides that each route must contain a subset of connected cities; a subset of cities is connected if the following two conditions are true:

There is a path between every pair of cities which belongs to the subset.
Every city in the path must belong to the subset.
 Help Alex to find number of connected segments in Treeland.
 */
import java.io.*;
import java.util.*;


public class SelfDrivingBus {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static PrintWriter pw;

    static int[][] lca;
    static int[] depth;
    static int[][] minRMQ;
    static int[][] maxRMQ;

    static final int DEPTH = 19;
    static LinkedList<Integer>[] edges;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int qq = Integer.MAX_VALUE;
        for(int casenum = 1; casenum <= qq; casenum++)    {
            int n = readInt();
            lca = new int[n][DEPTH];
            minRMQ = new int[n][DEPTH];
            maxRMQ = new int[n][DEPTH];
            depth = new int[n];
            edges = new LinkedList[n];
            for(int i = 0; i < n; i++) {
                edges[i] = new LinkedList<Integer>();
            }
            for(int i = 1; i < n; i++) {
                Arrays.fill(lca[i], -1);
                int x = readInt()-1;
                int y = readInt()-1;
                edges[x].add(y);
                edges[y].add(x);
            }
            LinkedList<Integer> q = new LinkedList<Integer>();
            q.add(0);
            while(!q.isEmpty()) {
                int curr = q.removeFirst();
                for(int out: edges[curr]) {
                    if(lca[out][0] == -1) {
                        depth[out] = 1 + depth[curr];
                        lca[out][0] = curr;
                        minRMQ[out][0] = Math.min(out, curr);
                        maxRMQ[out][0] = Math.max(out, curr);
                        q.add(out);
                    }
                }
            }
            updateLCA();
            int[] minL = new int[n-1];
            int[] maxR = new int[n-1];
            for(int i = 0; i < n-1; i++) {
                int[] vals = getLCA(i, i+1);
                minL[i] = vals[0];
                maxR[i] = vals[1];
            }
            int ret = n;
            for(int i = 0; i < n-1; i++) {
                int l = minL[i];
                int r = maxR[i];
                if(l >= i && r <= i+1) {
                    ret++;
                }
                for(int j = i+1; j < n-1; j++) {
                    l = Math.min(l, minL[j]);
                    r = Math.max(r, maxR[j]);
                    if(l < i) break;
                    if(l >= i && r <= j+1) {
                        ret++;
                    }
                }
            }
            pw.println(ret);
        }
        exitImmediately();
    }

    public static int[] getLCA(int a, int b) {
        if(depth[a] > depth[b]) return getLCA(b, a);
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        while(depth[b] > depth[a]) {
            for(int d = 18; d >= 0; d--) {
                if(depth[b] - (1<<d) >= depth[a]) {
                    min = Math.min(min, minRMQ[b][d]);
                    max = Math.max(max, maxRMQ[b][d]);
                    b = lca[b][d];
                }
            }
        }
        for(int d = 18; d >= 0; d--) {
            if(lca[a][d] != lca[b][d]) {
                min = Math.min(min, minRMQ[a][d]);
                min = Math.min(min, minRMQ[b][d]);
                
                max = Math.max(max, maxRMQ[a][d]);
                max = Math.max(max, maxRMQ[b][d]);
                a = lca[a][d];
                b = lca[b][d];
            }
        }
        if(a != b) {
            min = Math.min(min, minRMQ[a][0]);
            min = Math.min(min, minRMQ[b][0]);
            
            max = Math.max(max, maxRMQ[a][0]);
            max = Math.max(max, maxRMQ[b][0]);
        }
        return new int[]{min, max};
    }

    public static void updateLCA() { 
        for(int depth = 1; depth < DEPTH; depth++) {
            for(int i = 0; i < lca.length; i++) {
                int parent = lca[i][depth-1];
                lca[i][depth] = lca[parent][depth-1];
                minRMQ[i][depth] = Math.min(minRMQ[i][depth-1], minRMQ[parent][depth-1]);
                maxRMQ[i][depth] = Math.max(maxRMQ[i][depth-1], maxRMQ[parent][depth-1]);
            }
        }
    }

    private static void exitImmediately() {
        pw.close();
        System.exit(0);
    }


    private static int readInt() throws IOException {
        return Integer.parseInt(nextToken());
    }



    private static String nextToken() throws IOException  {
        while(st == null || !st.hasMoreTokens())  {
            if(!br.ready()) {
                exitImmediately();
            }
            st = new StringTokenizer(br.readLine().trim());
        }
        return st.nextToken();
    }
}