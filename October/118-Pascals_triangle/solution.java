class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascal = new ArrayList<>();
	List<Integer> first = new ArrayList<>();
	List<Integer> second = new ArrayList<>();
       
	first.add(1); second.add(1); second.add(1);
	if (numRows >= 1)
	    pascal.add(first);
	if (numRows >= 2)
	    pascal.add(second);
	for (int i = 2; i < numRows; i++) {
	    List<Integer> row = new ArrayList<>();

	    row.add(1);
	    for (int j = 0; j < i - 1; j++)
		row.add(pascal.get(i-1).get(j) + pascal.get(i-1).get(j+1));
	    row.add(1);
	    pascal.add(row);
	}
	return pascal;
    }
}
