**94-Binary_tree_inorder_traversal_loop_impl**
- Keep push left child iteratively onto the stack until no more left
  child.  At this time, the top on stack node shall be popped and
  "visited".  If the popped node has no right child, keep popping the
  stack then. Otherwise, push the right node onto the stack and we
  just reached the same state as the beginning (i.e., to start to
  traverse a sub-tree).
- Sanitization:
    - Left child iterative exploration (push to stack) only happens
      when a single new node is pushed onto the stack, so we will
      never explore the left child of a node twice.
    - Each root node of a sub-tree is popped before its right child
      being pushed, so we will never visit a node twice.

**145-Binary_tree_postorder_traversal_loop_impl**
- The iterative algorithm for postorder traversal is bit different
  from inorder traversal: the root node of a sub-tree on stack shall
  be popped when all its children visited.  Thus instead of putting
  bare tree nodes on stack, we put node wrappers on stack. Each node
  wrapper includes the node as well as an iterator of its children.
- The rest of the algorithm goes in a similar way to inorder
  traversal.

**254-Factor_combinations**
- ostensibly easy but avoiding duplication is non-trivial
- To avoid duplication, enforce an order during recursive computation, e.g., factors shall go ascending or descending order

**355-Design_twitter**
- easy
- If use a real link list, I can avoid copy the whole history of every followee at "getNewsFeed".

**373-Find_k_pairs_with_smallest_sums**
- A relatively good soluion is easy: starting from `(0,0)`, which is
  guaranteed to be minimal. Then add `(0,1)` and `(1,0)` into the heap.
  Keep selecting the min `(i,j)` from the heap and add `(i+1, j)` and `(i, j+1)`
  back to the heap.
- But LC sets time and memory restriction too tight so the solution
  has to be optimal in order to be accepted.  The idea is to first
  pick `(0, 0), (1, 0), ..., (k-1, 0)` into the heap.  For any pair
  `(i, j)` in the overall `k` minimal pairs, `0 <= i < k`.  Thus, next
  we only need to try to explore different `j`s for the `k` pairs
  currently in heap:  
    - For each minimal pair `(i, j)` in the heap, we poll it out and
      add `(i, j+1)` back to the heap.
  

**708-Insert_into_a_sorted_circular_linked_list**
- Iterate the circular list and try to find the following 4 nodes:
    - max:  a node must be max if `node.val > node.next.val`
    - min:  a node must be min if its previous node is max
    - node n such that `n.val == insertVal`, this is a good place to insert
    - node n such that `n.val < insertVal < n.next.val`, this is a good place to insert

**1982-Find_array_given_subset_sums**
- This one is really hard.
- First if we sort all the sums, we can get two candidates for a number `x` in the array:
  `x = sums[len-1] - sum[len-2] or -x = sums[len-1] - sum[len-2]`.  This is not so obvious.
  Suppose there are `n` positive numbers in the array `a`.

```
  n = 0 -> sums[len-1] = 0 (empty set) and sums[len-2] = max(a)
  
  n = 1 -> sums[len-1] = max(a) and sums[len-2] = max(0, max(a) + second_max(a))
           in this case, sum[len-1] - sums[len-2] = max(a) or second_max(a).

  n > 1 -> sums[len-1] = sum(nonnegatives(a)) and
           sums[len-2] = max(sum(nonnegatives(a)) + max(negatives(a)),
	                     sum(nonnegatives(a)) - min(nonnegatives(a)))
           in this case, sum[len-1] - sums[len-2] = max(negatives(a) or min(nonnegatives(a))
```

- with a known `x`, we can divide all the sums in two groups:
    - 1. `{0, a, b, c, ..., a + b, a + c, ..., a + b + c + ... }`
    - 2. `{x, a + x, b + x, ..., a + b + x, ..., a + b + c + ... + x}`
- Finding another number `y` in the array from the first group is a recursive problem.
- Dividing all sums into two groups follows such an algorithm:
    - If `x` is positive, starting from the smallest `sum`, if `sum +
      x` in `sums`, `sum` goes to the first group, and `sum + x` goes
      to the second group.  Otherwise, `sum` goes to the second group.
      We know that `sum-x` is either non-existed or processed already as `sum` is the
      smallest unprocessed one.      
    - Vice versa for `x` is negative.


**easy ones**
- 158-Read_n_characters_given_read4_II
- 198-House_robber
- 508-Most_frequent_subtree_sum
- 814-Binary_tree_pruning
- 1166-Design_file_system