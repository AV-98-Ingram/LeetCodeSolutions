import java.util.*;

class Twitter {

    Map<Integer, LinkedList<int[]>> db = new HashMap<>(); // userID -> its own tweets in order

    Map<Integer, Collection<Integer>> rel = new HashMap<>();

    int time = 0;

    public Twitter() {

    }

    public void postTweet(int userId, int tweetId) {
	db.computeIfAbsent(userId, k -> new LinkedList<>()).addFirst(new int[] { time++, tweetId });
    }

    public List<Integer> getNewsFeed(int userId) {
	// list in pq must be non-empty:
	Queue<LinkedList<int[]>> pq = new PriorityQueue<>(
							  (a, b) -> (Integer.compare(b.getFirst()[0], a.getFirst()[0])));
	Collection<Integer> followings = rel.getOrDefault(userId, new LinkedList<>());

	followings.add(userId);
	for (Integer following : followings) {
	    LinkedList<int[]> hist = db.get(following);

	    if (hist != null)
		pq.add(new LinkedList<>(hist));
	}

	int count = 10;
	List<Integer> result = new LinkedList<>();

	while (count > 0 && !pq.isEmpty()) {
	    LinkedList<int[]> hist = pq.poll();

	    result.add(hist.removeFirst()[1]);
	    if (!hist.isEmpty())
		pq.add(hist);
	    count--;
	}
	return result;
    }

    public void follow(int followerId, int followeeId) {
	rel.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
	rel.computeIfPresent(followerId, (k, v) -> {v.remove(followeeId); return v;});
    }
}
