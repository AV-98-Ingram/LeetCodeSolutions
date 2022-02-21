class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
	int blX = Math.max(ax1, bx1);
	int blY = Math.max(ay1, by1);
	int trX = Math.min(ax2, bx2);
	int trY = Math.min(ay2, by2);

	int x = trX - blX;
	int y = trY - blY;
	int overlap;
	
	if (x < 0 || y < 0)
	    overlap = 0;	
	else
	    overlap = x * y;

	return (ax2 - ax1) * (ay2 - ay1) + (bx2 - bx1) * (by2 - by1) - overlap;	
    }
}
