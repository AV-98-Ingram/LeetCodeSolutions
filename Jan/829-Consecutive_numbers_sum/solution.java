class Solution {

    /*
      This is more like a mathematical problem.  The solution comes
      from my observations.

      If there is a odd number, for example, three of consecutive
      numbers whose sum is our target n, it must be like the
      following: 
       
      (x-1) + x + (x+1) = n  ==>  3x = n.

      In other words, for any odd number m, if m divides n and (n/m) >
      (m/2), there is a group of consecutive numbers ... (n/m - 1),
      (n/m), (n/m +1) ... whose sum is n.  Note that once (n/m) <=
      (m/2), no need to look at any m' that is greater than m.
      
      For the case of a even number m of consecutive numbers summing
      up to n, I come up with an unproved (but worked) theorem:

      If ((n int_div m) + 0.5) * m == n, there is a group of m
      consecutive numbers  
      (n/m - m/2 + 1), ..., (n/m), (n/m+1), ... (n/m + m/2)
      summing up to n.

      For example, let n = 10 and m = 4.  We have (10 int_div 4) = 2
      and (2.5 * 4 == 10).  Thus, we can come up with 4 consecutive
      numbers: 1 2 3 4.

      Once (n/m) < (m/2), no need to look at any m' > m.
     */
    public int consecutiveNumbersSum(int n) {
	int result = 0;
	
	for (int i = 1; i <= n; i++) {
	    if ((i & 1) == 0) {
		//even:
		int m = n / i;
		double m5 = m + 0.5;
		
		if (m - (i >> 1) < 0)
		    break;
		if (m5 * (double)i != (double)n)
		    continue;
		result++;
	    } else {
		// odd:
		if (n / i - i / 2 <= 0)
		    break;
		if (n % i != 0)
		    continue;
		result++;
	    }	    
	}
	return result;
    }
}
