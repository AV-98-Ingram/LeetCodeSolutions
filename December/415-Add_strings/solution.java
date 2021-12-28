class Solution {
    public String addStrings(String num1, String num2) {
        final int len1 = num1.length();
	final int len2 = num2.length();

	return add(num1, len1-1, num2, len2-1, 0);
    }

    private String add(String num1, int pos1, String num2, int pos2, int carry) {
	if (pos1 < 0 && pos2 < 0)
	    return carry == 1 ? "1" : "";
	
	int dig1 = 0, dig2 = 0;
	
	if (pos1 < num1.length() && pos1 >= 0)
	    dig1 = num1.charAt(pos1) - '0';
	if (pos2 < num2.length() && pos2 >= 0)
	    dig2 = num2.charAt(pos2) - '0';

	int sum = dig1 + dig2 + carry;
	
	carry = sum >= 10 ? 1 : 0;
	if (sum >= 10)
	    sum -= 10;

	String prefix = add(num1, pos1-1, num2, pos2-1, carry);

	return prefix + sum;
    }
}
