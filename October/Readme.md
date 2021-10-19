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

- 84-Largest_rectangle_in_histogram.  Usng stack to keep track of
  donimators, smart.
  

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

**85-Maximal_rectangle**
- this problem can be reduced to the 84-Largest_rectangle_in_histogram problem.

**86-Partition_list**
- one mistake I made is that I didn't think it through how to update the "prev" pointer

**87-Scramble_string**
- don't miss chance of caching.

**89-Grey_code**
- look for the recursive pattern

**90-Subsets_II**
- similar to 78-Subsets but requires 1) sorting and 2) skipping duplication at each level

**91-Decode_ways**
- just need to figure out how to determine the invalid case

**95-Unique_binary_search_trees_II/96-Unique_binary_search_trees**
- learned properties of BST (binary search tree)
- for each sub-tree, we need to know the lower bound and the upper bound

**97-Interleaving_string**
- Let the two strings match the third one in turn.  Once one string reaches the end, the other must match the remaining.

**99-Recover_binary_search_tree**
- learned a new property of BST: inorder traversal of a BST yields a sorted sequence of values.

**105-Construct_binary_tree_from_preorder_and_inorder_traversal/106-Construct_binary_tree_from_inorder_and_postorder_traversal**
- observe patterns in the preorder and the inorder input and conclude an algorithm.
- 106 is the symmetric version of 105

**108-Convert_sorted_array_to_binary_search_tree**
- recursively split the array by half and construct the tree

**127-Word_ladder**
- the basic idea is to do Dijkstra's style BFS, i.e., initially making all nodes unvisited and removing visited nodes to avoid cycles; no cache is ever needed
- there two ways to achive it: 1) search on graph directly which is good for the cases of small edge numbers and long words; 2) search by manipulating characters in words, which is good for the cases of big edge numbers and shorter words.

**210-Course_schedule_II**
- I wrote a fix-point like solution which essentially is the BFS topological sorting but less concise.
- BFS topological sorting: https://en.wikipedia.org/wiki/Topological_sorting
- DFS topological sorting: marking nodes grey when visited but not fully enabled, marking nodes black once fully enabled. Reaching a grey node means a cycle.  Reaching a black node is fine but no need to search again. (Black node is not on stack while grey nodes are on stack).

**241-Different_ways_to_add_parentheses**
- recursively partition expressions: 

**282-Expression_add_operators**
- this one is a very good execise for coding
- no need to revisit but read my notes in the code is necessary

**380-Insert_dlete_get_random_set/381-Insert_delete_get_random_duplicates_allowed**
-when it comes to random, the probablity really matters.

**394-Decode_string**
- careful, careful, careful

**433-Minimum_genetic_mutation**
- same as 127-Word_ladder

**494-Target_sum**
- essentially like Ping Pong, remember Ping Pong?

**679-24_game**
- built a teeny-tiny Real number system based on numerator / denomenator

**Easys**
- 80-Remove_duplicates_from_sorted_array_II
- 82-Remove_duplicates_from_sorted_list_II
- 83-Remove_duplicates_from_sorted_list
- 88-Merge_sorted_array
- 92-Reverse_linked_list_II
- 93_Restore_ip_addresses
- 94-Binary_tree_inorder_traversal
- 100-Same_tree
- 102-Binary_tree_level_order_traversal
- 103-Binary_tree_zigzag_level_order_traversal
- 104-Maximum_depth_of_binary_tree
- 107-Binary_tree_level_order_traversal_II
- 206-Reverse_linked_list
- 554-Brick_wall