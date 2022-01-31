import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public int maxProfit(int[] inventory, int orders) {
	Queue<Integer> balls = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
	long sell = 0;

	for (int ball : inventory)
	    balls.add(ball);
	while (!balls.isEmpty() && orders > 0) {
	    int ball = balls.poll();
	    int n = 1;

	    while (!balls.isEmpty() && ball == balls.peek()) {
		balls.poll();
		n++;
	    }

	    int lowerBall = balls.isEmpty() ? 0 : balls.peek();
	    int ballSell = Math.min(orders, (ball - lowerBall) * n);
	    int perBallSell = ballSell / n;
	    int remaining = ballSell % n;

	    sell += bulkSell(ball, perBallSell) * n;
	    sell += ((long) remaining * (long) (ball - perBallSell));
	    orders -= ballSell;

	    // add balls back to queue
	    int remainingBalls = ball - perBallSell - 1;

	    if (remainingBalls > 0)
		for (int i = 0; i < remaining; i++)
		    balls.add(remainingBalls);
	    if (remainingBalls >= 0)
		for (int i = remaining; i < n; i++)
		    balls.add(remainingBalls + 1);
	}
	return (int) (sell % (long) 1000000007);
    }

    private long bulkSell(int max, int sells) {
	int min = max - sells + 1;
	long result;

	if ((sells & 1) == 0)
	    result = (long) (max + min) * (long) (sells >> 1);
	else
	    result = (long) (max + min) * (long) (sells >> 1) + max - (sells >> 1);
	return result;
    }

    public static void main(String[] args) {
	new Solution().maxProfit(new int[] { 2, 5 }, 4);
    }
}
