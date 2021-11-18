class Solution {
    static String ones[] = new String[] { "Zero", "One", "Two", "Three", "Four",
					  "Five", "Six", "Seven", "Eight", "Nine" };

    static String tens[] = new String[] { "Ten", "Eleven", "Twelve", "Thirteen",
					  "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
					  "Nineteen" };

    static String tenties[] = new String[] { "Twenty", "Thirty", "Forty",
					     "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };

    static final int billion = 1000000000;
    static final int million = 1000000;

    public String numberToWords(int num) {
	String result = "";
	int n;

	// process billions:
	n = num / billion;
	if (n > 0)
	    result += process(n, false) + " Billion";
	// process millions:
	num = num - n * billion;
	n = num / million;
	if (n > 0)
	    result += process(n, result.length() > 0) + " Million";
	// process thousands:
	num = num - n * million;
	n = num / 1000;
	if (n > 0)
	    result += process(n, result.length() > 0) + " Thousand";
	// process the rest:
	num = num - n * 1000;
	result += process(num, result.length() > 0);
	if (result.length() == 0)
	    result += "Zero";
	return result;
    }

    // pre-cond: 0 < num < 1000
    private String process(int num, boolean addSpaceBefore) {
	int n = num / 100;
	String result = "";

	if (n > 0)
	    result += process(n, addSpaceBefore) + " Hundred";
	num = num - n * 100;
	n = num / 10;
	addSpaceBefore = addSpaceBefore || result.length() > 0;
	if (n > 1) {
	    result += (addSpaceBefore ? " " : "") + tenties[n - 2];
	    num = num - n * 10;
	    if (num > 0)
		result += process(num, true);
	} else if (n > 0) {
	    result += (addSpaceBefore ? " " : "") + tens[num - 10];
	} else if (num > 0) {
	    result += (addSpaceBefore ? " " : "") + ones[num];
	}
	return result;
    }

    public static void main(String[] args) {
	new Solution().numberToWords(1234);
    }
}
