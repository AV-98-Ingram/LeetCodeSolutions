class Solution {
    public int distributeCoins(TreeNode root) {
	return coinsNeed(root)[1];
    }

    // returning {coinsNeeded, numMoves}
    private int[] coinsNeed(TreeNode root) {
	if (root == null)
	    return new int[] {0, 0};

	int coins = root.val;	
	int[] l = coinsNeed(root.left);
	int[] r = coinsNeed(root.right);
	int coinsNeed = 1 - (coins - l[0] - r[0]);
	int moves = l[1] + r[1] + Math.abs(coinsNeed);
	    
	return new int[] {coinsNeed, moves};
    }
}
