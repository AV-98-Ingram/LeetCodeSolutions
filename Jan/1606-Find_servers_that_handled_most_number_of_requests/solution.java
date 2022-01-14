import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
	int handles[] = new int[k];
	int max = 0;
	List<Integer> results = new LinkedList<>();
	final int len = arrival.length;
	TreeSet<Integer> availables = new TreeSet<>();
	PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

	for (int i = 0; i < k; i++)
	    availables.add(i);
	for (int i = 0; i < len; i++) {
	    int arr = arrival[i];
	    int l = load[i];

	    while (!busyServers.isEmpty() && busyServers.peek()[0] <= arr)
		availables.add(busyServers.poll()[1]);
	    if (availables.isEmpty())
		continue;

	    Integer server = availables.ceiling(i % k);

	    if (server == null)
		server = availables.first();
	    availables.remove(server);
	    busyServers.add(new int[] { arr + l, server });
	    handles[server]++;
	    if (handles[server] > max) {
		max = handles[server];
		results.clear();
		results.add(server);
	    } else if (handles[server] == max) {
		results.add(server);
	    }
	}
	return results;
    }
}
