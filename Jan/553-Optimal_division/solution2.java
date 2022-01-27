import java.util.Arrays;

class Solution {

    // brutal force solution:
    public String optimalDivision(int[] nums) {
	double[] reals = new double[nums.length];

	for (int i = 0; i < nums.length; i++)
	    reals[i] = (double) nums[i];

	StringBuffer sb = new StringBuffer();

	maxOf(reals, 0, sb);
	return sb.toString();
    }

    private double maxOf(double[] nums, int since, StringBuffer sb) {
	if (since + 1 == nums.length) {
	    sb.append((int) nums[since]);
	    return nums[since];
	} else if (since + 2 == nums.length) {
	    sb.append((int) nums[since] + "/" + (int) nums[since + 1]);
	    return nums[since] / nums[since + 1];
	}

	StringBuffer sb1 = new StringBuffer();
	StringBuffer sb2 = new StringBuffer();

	double n = nums[since];
	double d = minOf(nums, since + 1, sb1);
	double opt1 = n / d; // "n / REST"
	double copy[] = Arrays.copyOfRange(nums, since + 1, nums.length);

	copy[0] = n / nums[since + 1];

	double opt2 = maxOf(copy, 0, sb2); // "n / d / REST"

	sb2.delete(0, sb2.indexOf("/") + 1);
	if (opt1 > opt2) {
	    sb.append((int) n + "/(" + sb1 + ")");
	    return opt1;
	} else {
	    sb.append((int) n + "/" + (int) nums[since + 1] + "/" + sb2);
	    return opt2;
	}
    }

    private double minOf(double[] nums, int since, StringBuffer sb) {
	if (since + 1 == nums.length) {
	    sb.append((int) nums[since]);
	    return nums[since];
	} else if (since + 2 == nums.length) {
	    sb.append((int) nums[since] + "/" + (int) nums[since + 1]);
	    return nums[since] / nums[since + 1];
	}

	StringBuffer sb1 = new StringBuffer();
	StringBuffer sb2 = new StringBuffer();

	double n = nums[since];
	double d = maxOf(nums, since + 1, sb1);
	double opt1 = n / d; // "n / REST"
	double copy[] = Arrays.copyOfRange(nums, since + 1, nums.length);

	copy[0] = n / nums[since + 1];

	double opt2 = minOf(copy, 0, sb2); // "n / d / REST"

	sb2.delete(0, sb2.indexOf("/") + 1);
	if (opt1 < opt2) {
	    sb.append((int) n + "/(" + sb1 + ")");
	    return opt1;
	} else {
	    sb.append((int) n + "/" + (int) nums[since + 1] + "/" + sb2);
	    return opt2;
	}
    }

    public static void main(String[] args) {
	new Solution().optimalDivision(new int[] { 1000, 100, 10, 2 });
    }
}
