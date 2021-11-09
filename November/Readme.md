**worth to revisit**


**146-LRU_cache**
- use a double linked list and a map from keys to list nodes
- double link is necessary as we need to connect "prev" and "next" of a just-accessed node and move the node to tail.

**460-LFU_Cache**
- My fastest solution maintains two maps.  A map from keys to nodes and a map from counters to node lists.
- details can be found on the comment of my solution.

**588-In_memory_file_system**
- not hard but quite a lot coding

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
- 167-Two_sum_II
- 604-Design_compressed_string_iterator
- 1304-Find_N_unique_integers_sum_up_to_zero
- 1448-Count_good_nodes_in_binary_tree