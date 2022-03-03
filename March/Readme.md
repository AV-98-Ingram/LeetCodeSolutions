**254-Factor_combinations**
- ostensibly easy but avoiding duplication is non-trivial
- To avoid duplication, enforce an order during recursive computation, e.g., factors shall go ascending or descending order

**355-Design_twitter**
- easy
- If use a real link list, I can avoid copy the whole history of every followee at "getNewsFeed".

**708-Insert_into_a_sorted_circular_linked_list**
- Iterate the circular list and try to find the following 4 nodes:
    - max:  a node must be max if `node.val > node.next.val`
    - min:  a node must be min if its previous node is max
    - node n such that `n.val == insertVal`, this is a good place to insert
    - node n such that `n.val < insertVal < n.next.val`, this is a good place to insert

**easy ones**
- 198-House_robber