import java.util.*;

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        ArrayList<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] flight : flights) {
            graph[flight[0]].add(new int[]{flight[1], flight[2]});
        }

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{src, 0});

        int stops = 0;

        while (!q.isEmpty() && stops <= k) {

            int size = q.size();
            int[] temp = dist.clone();

            while (size-- > 0) {

                int[] curr = q.poll();
                int node = curr[0];
                int cost = curr[1];

                for (int[] edge : graph[node]) {

                    int next = edge[0];
                    int price = edge[1];

                    if (cost + price < temp[next]) {
                        temp[next] = cost + price;
                        q.offer(new int[]{next, temp[next]});
                    }
                }
            }

            dist = temp;
            stops++;
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}