**worth to revisit**


**146-LRU_cache**
- use a double linked list and a map from keys to list nodes
- double link is necessary as we need to connect "prev" and "next" of a just-accessed node and move the node to tail.

**460-LFU_Cache**
- My fastest solution maintains two maps.  A map from keys to nodes and a map from counters to node lists.
- details can be found on the comment of my solution.

**easy ones**
- 143-Reorder_list
- 144-Binary_tree_preorder_traversal
- 145-Binary_tree_postorder_traversal
- 167-Two_sum_II
- 604-Design_compressed_string_iterator
