class Solution {
    public String customSortString(String order, String s) {
	int i = 1;
	int alphabet[] = new int[26];
	
	for (char c : order.toCharArray())
	    alphabet[c - 'a'] = i++;
	
	ArrayList<Character> sArr = new ArrayList<>(s.length());

	for (char c : s.toCharArray())
	    sArr.add(c);

	Collections.sort(sArr,
		    (a,b)->(Integer.compare(alphabet[a-'a'], alphabet[b-'a'])));
	return new String(sArr.toArray());
    }
}
