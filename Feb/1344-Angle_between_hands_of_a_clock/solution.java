class Solution {
    public double angleClock(int hour, int minutes) {
	double dm = (1.0 / 60.0) * minutes * 360.0;
	double exactHour = (minutes / 60.0) + hour;
	double dh = (1.0 / 12.0) * exactHour * 360.0;
	
	return Math.min(Math.abs(dh - dm), (360.0 - Math.max(dh, dm)) + Math.min(dh, dm));
    }
}
