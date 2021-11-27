/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */

/**
  Idea: DFS.  Visited cells becoming walls.
 */
class Solution {

    class Pair {
	int car;
	int cdr;
	Pair(int car, int cdr) {
	    this.car = car;
	    this.cdr = cdr;
	}

	public int hashCode() {
	    return car * 31 + cdr;
	}

	public boolean equals(Object obj) {
	    if (obj instanceof Pair) {
		Pair other = (Pair) obj;

		return car == other.car && cdr == other.cdr;		
	    }
	    return false;
	}
    }
    
    Set<Pair> visited = new HashSet<>();
    static int[][] directions = new int[][] {{0,1}, {1,0}, {0,-1}, {-1, 0}};
    
    public void cleanRoom(Robot robot) {
	robot.clean();
	clean(rob, 0, 0, 0);
    }

    private void clean(Robot rob, int r, int c, int d) {
	rob.clean();
	visited.add(new Pair(r, c));
	
	Pair next = new Pair(r + directions[d][0], c + directions[d][1]);
	int turnRight = 0;
	
	for (int i = 0; i < 4; i++) {
	    if (!visited.contains(next) && rob.move()) {
		clean(rob, next.car, next.cdr, d);
		// backtrack:
		rob.turnRight();
		rob.turnRight();
		rob.move();
		rob.turnLeft();
		rob.turnLeft();
	    }
	    d = (d+1) & (3); // more efficient ver of (d+1) % 4
	    next.car = r + directions[d][0];
	    next.cdr = c + directions[d][1];
	    rob.turnRight();
	}	
    }

}
