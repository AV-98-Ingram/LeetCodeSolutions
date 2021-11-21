import java.util.Arrays;
import java.util.Random;

/*
  Idea: put all numbers in a bag, then equally and randomly take one out.

  Why each permutation will have equal chance to be presented? 

  Suppose there are n numbers, then there are n! permutations.  So we
  prove that each permutation has 1/n! chance to be presented by the
  approach.

  The proof is easy:
  The first number x to be taken from the bag has chance 1/n;
  the second number y has chance 1/(n-1);
  the third number z has chance 1/(n-1);
  ...
  So the permutation x y z ...  has chance 1/n * 1/(n-1) * 1/(n-2) * ... = 1/(n!) to be presented.
  

*/
class Solution {

	int[] nums;
	Random rand;

	public Solution(int[] nums) {
		this.nums = nums;
		this.rand = new Random();
	}

	public int[] reset() {
		return nums;
	}

	public int[] shuffle() {
		int[] shuffled = Arrays.copyOf(nums, nums.length);

		for (int i = 0; i < nums.length; i++) {
			int pick = Math.abs(rand.nextInt()) % (nums.length - i);
			int picked = shuffled[pick + i];

			shuffled[pick + i] = shuffled[i];
			shuffled[i] = picked;
		}
		return shuffled;
	}
}
