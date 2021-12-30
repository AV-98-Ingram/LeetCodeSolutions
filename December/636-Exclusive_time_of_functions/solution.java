class Solution {
    /*
      Idea: using a stack. 

      Similar to Stack-Min problem. We save execution time information
      also in the stack.

      The top 2 entries have the following patterns:

      [time, log, ...]: the "time" of other functions after the
      function of the "log" starts.

      [log, ...]: no other function ever executed since the function
      of the "log" starts.
      
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        LinkedList<int[]> stack = new LinkedList<>();
	int times[] = new int[n];
	
        for (String log : logs) {
            int[] result = parse(log); // result: [id, time, 0/1], 0 for start, 1 for end
            
            if (result[2] == 0) {
                stack.addFirst(result);
            } else {
		int top[] = stack.removeFirst();
		int otherTime = 0;
		int execTime;
		
		if (top[0] == -1) {
		    otherTime = top[1];
		    top = stack.removeFirst();
		}
		execTime = result[1] - top[1] + 1;
		times[result[0]] += execTime - otherTime;
		if (!stack.isEmpty()) {
		    top = stack.getFirst();
		    if (top[0] == -1) {
			stack.removeFirst();
			execTime += top[1];
		    }
		    stack.addFirst(new int[]{-1, execTime});
		}
            }
        }
	return times;
    }

    private int[] parse(String log) {
	int id = 0;
	int start;
	int time = 0;
	int i = 0;
	final int len = log.length();
	
	while (log.charAt(i) >= '0' && log.charAt(i) <= '9')
	    id = id * 10 + (log.charAt(i++) - '0');
	i++;
	if (log.charAt(i) == 's') {
	    start = 0;
	    i += 5;
	} else {
	    start = 1;
	    i += 3;
	}
	i++;
	while (i < len && log.charAt(i) >= '0' && log.charAt(i) <= '9')
	    time = time * 10 + (log.charAt(i++) - '0');
	return new int[]{id, time, start};
    }
}
