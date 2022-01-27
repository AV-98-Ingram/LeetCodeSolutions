import java.util.Arrays;

class Solution {
    // this solution is readable but
    // the numerator and denomenator could easily go beyond "long"
    class Real {
	long nu;
	long de;
	String rep;

	Real(long nu) {
	    this.nu = nu;
	    this.de = 1;
	    this.rep = String.valueOf(nu);
	}

	Real(long nu, long de) {
	    this.nu = nu;
	    this.de = de;
	    this.rep = nu + "/" + de;
	}

	Real div(Real r) {
	    Real result = new Real(nu * r.de, de * r.nu);

	    String repOfR = r.rep;

	    if (r.de != 1)
		repOfR = "(" + repOfR + ")";
	    result.rep = this.rep + "/" + repOfR;
	    return result;
	}

	double doubleVal() {
	    return (double) nu / (double) de;
	}

	public String toString() {
	    return this.rep;
	}
    }

    public String optimalDivision(int[] nums) {
	Real[] reals = new Real[nums.length];

	for (int i = 0; i < nums.length; i++)
	    reals[i] = new Real(nums[i]);
	return maxOf(reals, 0).rep;
    }

    private Real maxOf(Real[] nums, int since) {
	if (since + 1 == nums.length) {
	    return nums[since];
	} else if (since + 2 == nums.length)
	    return nums[since].div(nums[since + 1]);

	Real n = nums[since];
	Real d = minOf(nums, since + 1);
	Real opt1 = n.div(d); // "n / REST"
	Real copy[] = Arrays.copyOf(nums, nums.length);

	copy[since + 1] = n.div(nums[since + 1]);

	Real opt2 = maxOf(copy, since + 1); // "n / d / REST"

	if (opt1.doubleVal() > opt2.doubleVal())
	    return opt1;
	return opt2;
    }

    private Real minOf(Real[] nums, int since) {
	if (since + 1 == nums.length) {
	    return nums[since];
	} else if (since + 2 == nums.length)
	    return nums[since].div(nums[since + 1]);

	Real n = nums[since];
	Real d = maxOf(nums, since + 1);
	Real opt1 = n.div(d); // "n / REST"
	Real copy[] = Arrays.copyOf(nums, nums.length);

	copy[since + 1] = n.div(nums[since + 1]);

	Real opt2 = minOf(copy, since + 1); // "n / d / REST"

	if (opt1.doubleVal() < opt2.doubleVal())
	    return opt1;
	return opt2;
    }

    public static void main(String[] args) {
	new Solution().optimalDivision(new int[] { 100, 60, 85, 98, 57, 54, 98 });
    }
}
