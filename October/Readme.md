# Problems need re-visit

**try to solve them again after I have nearly forgotten the solution**

- 76-Minimum_window_substring.  One easy solution can be maintaining
  and aggregating a set of "states", each "state" consists of a start
  position where the first match of this state starts and a table
  storing the part remaining to match.  The algorithm will scan the
  string once, for each scanned letter 'c', all the states will be
  updated w.r.t 'c'.  For states that are completed, compare and set
  the min.  The best solution is to maintain the window with two
  pointers "start" and "end". Using negative numbers to indicate
  duplicates, smart!

- 1982-Find_array_given_subset_sums.  I used the approach of find all
  unique subsets from an unsorted, may-duplicate arrays but it seems
  is too slow.


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

**79-Word_search**
- simple DFS solution turns out the best. Using a map to only look at the letters in the given words is slow on Leetcode. Dumb Leetcode.

**81-Search_in_rotated_sorted_array_II**
- Similar to 33-Search_in_rotated_sorted_array but may contain duplicates, i.e.,
```
          /
  start _/   _ end
            /
           /
```
- It seems that one can no longer tell if a segment of the  array is across the "plummet" by testing "arr[start] > arr[end]" as we do in Problem 33.  In fact, we can do a easy trim preprocess to make the array be
```
              /
  new_start  /   new_end
                /
               /	       
```
then it becomes same as problem 33.
  

**90-Subsets_II**
- similar to 78-Subsets but requires 1) sorting and 2) skipping duplication at each level


** Easys **
- 80-Remove_duplicates_from_sorted_array_II
- 82-Remove_duplicates_from_sorted_list_II
- 83-Remove_duplicates_from_sorted_list