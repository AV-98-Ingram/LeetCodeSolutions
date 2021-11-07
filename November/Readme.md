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

**easy ones**
- 143-Reorder_list
- 144-Binary_tree_preorder_traversal
- 145-Binary_tree_postorder_traversal
- 167-Two_sum_II
- 604-Design_compressed_string_iterator
- 1304-Find_N_unique_integers_sum_up_to_zero
- 1448-Count_good_nodes_in_binary_tree