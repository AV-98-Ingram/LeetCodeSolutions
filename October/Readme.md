# Problems need re-visit

**try to solve them again after I have nearly forgotten the solution**

- 76-Minimum_window_substring.  One easy solution can be maintaining
  and aggregating a set of "states", each "state" consists of a start
  position where the first match of this state starts and a table
  storing the part remaining to match.  The algorithm will scan the
  string once, for each scanned letter 'c', all the states will be
  updated w.r.t 'c'.  For states that are completed, compare and set
  the min.

 The best solution is to maintain the window with two pointers "start"
 and "end". Using negative numbers to indicate duplicates, smart!


**77-Combinations**
- to get unordered combinations of k numbers in [1 .. n], we recursively "build" (on the fly) the tree
```
     1        2        3        4        ... n
   /   \    /   \    /   \
  2 ... n  3 ... n  4 ... n    ...
 /       \ 
```
and for each path of length >= k, we take the prefix of length k.


**78-Subsets**
- a direct use of 77-Combinations
- foreach k in [0 .. n], get unordered combinations of k of the input numbers