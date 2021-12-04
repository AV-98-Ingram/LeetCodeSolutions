import java.util.LinkedList;

class Solution {
	public int[] asteroidCollision(int[] asteroids) {
		LinkedList<Integer> afterExplosion = new LinkedList<>();

		for (int i = 0; i < asteroids.length;) {
			int curr;

			if (afterExplosion.isEmpty()) {
				afterExplosion.add(asteroids[i]);
				i++;
				continue;
			}
			curr = afterExplosion.getLast();
			if (!(curr > 0 && asteroids[i] < 0)) {
				curr = asteroids[i];
				afterExplosion.add(curr);
				i++;

			} else if (curr > -asteroids[i]) {
				// asteroids[i] explodes
				i++;
			} else if (curr == -asteroids[i]) {
				// both explode
				afterExplosion.removeLast();
				i++;
			} else {
				// curr explodes
				afterExplosion.removeLast();
			}
		}
		int[] ret = new int[afterExplosion.size()];
		int i = 0;
		for (int astr : afterExplosion)
			ret[i++] = astr;
		return ret;
	}
}

