import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/* simply apply the "two pointers" approach.
   Be careful that using equals() to compare Integers.
   
   Time Complexity O(n^3)
 */
class Solution { 
	private static class Quadruple {
		Integer[] quadruple = new Integer[4];

		Quadruple(int a, int b, int c, int d) {
			quadruple[0] = a;
			quadruple[1] = b;
			quadruple[2] = c;
			quadruple[3] = d;
			Arrays.sort(quadruple);
		}

		@Override
		public int hashCode() {
			return Arrays.hashCode(quadruple);
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Quadruple) {
				Quadruple other = (Quadruple) obj;

				return quadruple[0].equals(other.quadruple[0])
						&& quadruple[1].equals(other.quadruple[1])
						&& quadruple[2].equals(other.quadruple[2])
						&& quadruple[3].equals(other.quadruple[3]);
			}
			return false;
		}
	}

	public static List<List<Integer>> fourSum(int[] nums, int target) {
		int numsSize = nums.length;
		Set<Quadruple> seen = new HashSet<>();

		Arrays.sort(nums);
		for (int i = 0; i < numsSize - 3; i++)
			for (int j = i + 1; j < numsSize - 2; j++) {
				int left = j + 1, right = numsSize - 1;
				int ij = nums[i] + nums[j];

				while (left < right) {
					int lr = nums[left] + nums[right];

					if (lr + ij == target) {
						seen.add(new Quadruple(nums[i], nums[j], nums[left],
								nums[right]));
						left++;
						right--;
					} else if (lr + ij < target)
						left++;
					else
						right--;
				}
			}

		List<List<Integer>> ret = new LinkedList<>();

		for (Quadruple q : seen) {
			ret.add(Arrays.asList(q.quadruple));
		}
		return ret;
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 387, 421, 435, 439, 387, 421, 435, 439 };
		for (List<Integer> l : fourSum(nums, 1682))
			System.out.println(l);
	}
}
