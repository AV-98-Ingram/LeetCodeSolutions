/*
  If there is a solution, then \sum(0, n, \lambda int t; gas[t] - cost[t]) >= 0.

  If we know there is a solution and the solution is unique, we can
  then scan the array X just once, where X[i] = gas[i] - cost[i].
  When the scan first reaches a non-negative X[i], we assume i to be
  the start candidate.  Then we continue the scan and sums up all
  following X elements.  If the sum becomes negative at j (j > i),
  then we know that no site between 0 and j (inclusive) is suitable
  for being the start. We have to continue the scan, hunt for the next
  candidate, and repeat.  As there must be a unique solution, we will
  find one before the scan ends.
  
 */
int canCompleteCircuit(int* gas, int gasSize, int* cost, int costSize) {
  int n = gasSize;
  int sum = 0;

  for (int i = 0; i < n; i++)
    sum += gas[i] - cost[i];
  if (sum < 0)
    return -1;
  
  int start = -1;

  sum = 0;
  for (int i = 0; i < n; i++) {
    if (start == -1) {
      if (gas[i] - cost[i] >= 0) {
	start = i;
	sum = gas[i] - cost[i];
      }
    } else
      sum += gas[i] - cost[i];
    if (sum < 0)
      start = -1;
  }
  return start;
}
