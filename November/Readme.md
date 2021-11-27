**worth to revisit**


**146-LRU_cache**
- use a double linked list and a map from keys to list nodes
- double link is necessary as we need to connect "prev" and "next" of a just-accessed node and move the node to tail.

**155-Min_stack**
- Whenever do a push, also push the current minimum value on the stack.  So pop will remove two entries at a time as well.  Entries are group in two in the stack.  Each such pair at place i contains the min through i to the bottom of the stack.

**169-Majority_element**
- remember Boyer-Moore voting algorithm

**239-Sliding_window_maximum**
- I was thinking of performing a constant number of operations per window sliding but could not figure out one.
- Then I was trying to come up with a simple solution with little
  optimization, which requires to traverse a queue per window sliding
  in some non-constant steps.  Soon later I realized that this is still O(n) complexity.
- see comments in the code for details  
- take away message: sometimes performing non-constant operations per step for n steps in total doesn't make the algorithm not O(n).

**253-Meeting_rooms_II**
- I made a similar mistake as what I made for 239. I.e., did not fully
  think about the most obvious (burtal force) solution. If I think
  about how sorted meetings shall be organized, I should be able to
  come up with the priority queue solution.
- Though I made a graph based solution, it is slow.  

**269-Alien_dictionary**
- my original idea: build graph and constraint solving.  It did make
  me sweaty in non-recursive DFS cycle detection.  For non-recursive
  DFS, a node that is visited before and is also on the stack
  indicates the existence of a cycle.

  Remember: For non-recursive DFS, visied nodes on stack form the path to the current node

- official solution: build graph and topological BFS sorting.  It turns out not faster than my original idea.

**295-Find_median_from_data_stream**
- I used ArrayList to maintain all the data.  I thought as a "list",
  it would take O(log(n)) time using binary search to insert a new
  data into ArrayList.  But I was wrong, inserting a data in ArrayList
  involves elements shifting as they are maintained in arrays under
  the hood.

**299-Bulls_and_cows**
- The problem is easy.
- Take-away message: when processing a stream of data, if the current input causes a
 counter to increasing, increase the counter then; if the current
 input causes the same counter to decrease, albeit there is a hard
 lower bound 0, you could allow it to be decreased to negative and
 count on future inputs to add it back to be non-negative.

**384-Shuffle_an_array**
- classic random shuffle problem

**388-Longest_absolute_file_path**
- I thought of building a tree then compute the longest path. A better idea is to do it on the fly.

**410-Split_array_largest_sum**
- hard-ass DP but I figured it out. see code comment for detailed explanation.

**418-Sentence_screen_fitting**
- simple solution timeouts. Then an OK optimization is accepted.

**460-LFU_Cache**
- My fastest solution maintains two maps.  A map from keys to nodes and a map from counters to node lists.
- details can be found on the comment of my solution.

**465-Optimal_account_balancing**
- the fundamental part that I should master: elaborate all possible
  groups of a list of numbers (i.e., all possible groups of size 1, of
  size 2, ... of size #numbers. Worst case complexity is O(n!)).
- My own theory of group matching is not proved but accepted by LC.
  And it seems faster than the simpler solution, i.e., recursively
  try-out all possible matches.  I think the possible supremacy of my
  solution is that in general case the complexity is O((n/2)!) +
  O((n/2)!) as numbers are divided into positives and negatives.

**588-In_memory_file_system**
- not hard but quite a lot coding

**668-Kth_smallest_number_in_multiplication_table**
- I only figured out a brutal-force solution.
- I didn't realize that each row in the table is sorted and to find the number of position k is similar to merge sort all rows.

**1188-Design_bounded_blocking_queue**
- simple exercise of wait() and notify()

**1239-Maximum_length_of_a_concatenated_string_with_unique_characters**
- I tried to find advanced solution but failed
- It turns out that combining 1) simple backtrack and 2) BitSet representation of strings with unique characters is the best solution

**1289-Minimum_falling_path_sum_II**
- see comments for detailed description
- I shall not over-simplify the algorithm

**1647-Minimum_deletions_to_make_character_frequencies_unique**
- sorts the original frequencies list and decreases duplicated frequencies to 0 from the greatest to the smallest
- re-wrote quick-sort. Note that pivot can change after partition.

**1684-Count_the_number_of_consistent_strings**
- easy. use bit operation for strings consist of unique characters

**1987-Number_of_unique_good_subsequences**
- I didn't think of this as a DP problem until I saw people saying it is one. Then I figured out the DP pattern.
-
```
Let g(n) be the set of good subseqs out of the n-length prefix of the
input.  Suppose the (n+1)-th binary is '1'. Also suppose the last 1 we
saw in the n-length prefix is at x-th place.  That is,

  1 ... 1 0 0 ... 0 1
        x        (n+1)

For all the good subseqs in g(x-1), adding the (n+1)-th '1' to them
only results in duplicates made by adding the x-th '1' to them.  On
the other hand, for any good subseq that are in g(i), x <= i <= n, but
not g(x-1), adding the (n+1)-th '1' to it results in a new good
subseq.

Thus, the number of newly added good subseqs by the (n+1)-th '1' can
be represented as "delta = |g(n)| - |g(x-1)|".  Consequently,
"|g(n+1)| = |g(n)| + delta".  Vice versa for the case where the
(n+1)-th binary is '0'.
```

**easy ones**
- 143-Reorder_list
- 144-Binary_tree_preorder_traversal
- 145-Binary_tree_postorder_traversal
- 157-Read_n_characters_given_read4
- 167-Two_sum_II
- 273-Integer_to_english_words
- 284-Peeking_iterator
- 359-Logger_rate_limiter
- 366-Find_leaves_of_binary_tree
- 604-Design_compressed_string_iterator
- 622-Design_circular_queue
- 641-Design_circular_deque
- 678-Valid_parenthesis_string
- 981-Time_based_key_value_store
- 1146-Snapshot_array
- 1304-Find_N_unique_integers_sum_up_to_zero
- 1448-Count_good_nodes_in_binary_tree
- 1756-Design_most_recently_used_queue