import java.util.LinkedList;
import java.util.List;

class Solution {
    // a linear solution based on a non-trivial math theorem    
    public String optimalDivision(int[] nums) {
	if (nums.length > 1)
	    return process(nums, nums[0]);
	else
	    return nums[0] + "";
    }

    private String process(int[] nums, int nu) {
	LinkedList<List<Integer>> groups = new LinkedList<>();
	List<Integer> firstGroup = new LinkedList<>();

	firstGroup.add(nums[1]);
	groups.add(firstGroup);
	for (int i = 2; i < nums.length; i++) {
	    int num = nums[i];

	    if (num > 1) {
		groups.getLast().add(num);
	    } else {
		List<Integer> newGroup = new LinkedList<>();

		newGroup.add(num);
	    }
	}

	StringBuffer result = new StringBuffer();

	result.append(nu + "/");
	for (List<Integer> group : groups) {
	    if (group.size() > 1)
		result.append("(");
	    for (int n : group)
		result.append(n + "/");
	    result.deleteCharAt(result.lastIndexOf("/"));
	    if (group.size() > 1)
		result.append(")");
	}
	return result.toString();
    }

    public static void main(String[] args) {
	new Solution().optimalDivision(new int[] { 1000, 100, 10, 2 });
    }
}
