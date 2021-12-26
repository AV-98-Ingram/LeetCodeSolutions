class Solution {
    /*
      Idea: each shifting group is identified by an array a[n], where
      a[i] is the difference between the i-th letter and the (i+1)-th
      letter.
     */
    
    class Group {
	int a[];
	
	Group(int[] a){
	    this.a = a;
	}

	public boolean equals(Object o) {
	    if (o instanceof Group) {
		Group other = (Group) o;
		
		return Arrays.equals(a, other.a);
	    }
	    return false;
	}
	
	public int hashCode() {
	    return Arrays.hashCode(a);
	}
    }
    
    public List<List<String>> groupStrings(String[] strings) {
        Map<Group, List<String>> map = new HashMap<>();

	for (String s : strings) {
	    final int len = s.length();
	    int[] a = new int[len-1];

	    for (int i = 0; i < len-1; i++) {
		char x = s.charAt(i);
		char y = s.charAt(i+1);
		
		a[i] = x > y ? x - y : (x + 26) - y;
	    }
	    map.computeIfAbsent(new Group(a), k->new LinkedList<>()).add(s);
	}

	List<List<String>> result = new LinkedList<>();

	result.addAll(map.values());
	return result;
    }
}
