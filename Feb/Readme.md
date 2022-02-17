**123-Best_time_to_buy_and_sell_stock_III**
- the optimal algorithm optimizes for exact 2 transactions:
    - Bi-directional `DP` with `DP[i]` meaning that the max profit after 1 transaction and day i.
    - state machine.    

**151-Reverse_words_in_a_string**
- For O(1) space solution: reverse the whole string then reverse each word.

**188-Best_time_to_buy_and_sell_stock_IV**
- Another practice for `DP[i] = Max/Min(DP[x] - a[y])` for some `x,y`
  that are in some range defined by `i`.  One can always think about
  it as if it is `DP[x] + (-a[y])` and use DP technique to cache
  Max/Min of `-a[y]` so that no need to iterate to find out the
  Max/Min.
- This problem is quite difficult to find the DP pattern I think:
    - DP[i][j][0]: the max money in hand at day j with at most i transactions and 0 stock in hand
    - DP[i][j][1]: the max money in hand at day j with at most i transactions and a stock in hand

**238-Product_of_array_except_self**
- Prefix product and suffix product
- Follow-up requires constant space usage: use only one variable to aggregate the suffix product


**285-Inorder_successor_in_bst**
- There are two cases:
    - If the give node `p` has right child, then return the next inorder node of its right child.
    - Otherwise, recursively compute:
        - If a node's left child contains `p` but not successor of `p` the node is the successor.

**297-Serialize_and_deserialize_binary_tree**
- serialize a binary tree level by level, using "()" to group each level

**322-Coin_change**
- A DP problem: `DP[i]` is the min number of coins to make up the amount `i`
- Maybe backtrack + cache could be better? An hypothesis is that not
  every `amount` can be made up by the coins and backtrack approach only
  computes for `amount`s that can be made up.

**340-Longest_substring_with_at_most_k_distinct_characters**
- simple window sliding problem

**351-Android_unlock_patterns**
- DFS and only to explore paths starting from (0,0) (other corner
  cells are the same), (0,1) (other non-corner edge cells are the
  same), and (1,1) (center).

**449-Serialize_and_deserialize_BST**
- same as 297 but needs to remove all the tailing "null"s

**476-Number_complement**
- finds out the highest bit of 1 (suppose the bit is the n-th bit
  counting from the rightmost), creates a mask of n bits of 1s, and
  the result is then `num ^ mask`.

**716-Max_stack**
- For the follow-up----make popMax() O(logN)
    - Use a double linked list to represent the stack
    - Use a TreeMap to map stack element values to nodes in the list    
    - To delete the max, it takes O(logN) to find the max value and
      then takes constant time to remove the corresponding node from
      the list

**968-Binary_tree_cameras**
- I used the greedy approach:
    - Compute from bottom-up using DFS
    - If a node is a leaf, leave it there as UN-monitored.
    - If a non-leaf node has at least one child UN-monitored, the node has to install a camera
    - If a non-leaf node has all its children monitored,
        - if one of its child has an installed camera, mark the node monitored
	- otherwise, leave it there as UN-monitored

**979-Distribute_coins_in_binary_tree**
- Recursively compute the number coins needed for both left and right
  child and at the same time accumulating moves needed.
- A big NOTE is that the coins needed by a child node could be a
  negative number meaning that the child node in fact has extra coins.

**1015-Smallest_integer_divisible_by_k**
- Need to know a bit math here: let `n` be a number where all digits
  are `1`s.  If `n % k == 0` we are done.  Otherwise,  `n` becomes `n * 10 + 1` and the remainder becomes
  `(n % k) * 10 + 1`.  So we only need to test if `((n % k) * 10 + 1) % k == 0` for `n * 10 + 1`.
- Repeatedly doing this until we go back to a seen remainder.  

**1275-Find_winner_on_a_tic_tac_toe_game**
- Remember the design tic-tac-toe? count each row, column and diagnal separately.

**1344-Angle_between_hands_of_a_clock**
- Be careful! The hour hand is not pointing at 1 at time "1:30" but at
  the middle of 1 and 2.

**1386-Cinema_seat_allocation**
- bitwise representation for seats
- instead of iterating each row for computing the total capacity, iterating the
  rows containing reserved seats only! Because rows without reserved seat can fit 2 groups for sure!

**1405-Longest_happy_string**
- Greedy algo:
    - sort the given letters by their maximum appearances in descending order    
    - always try to place the letter with max remaining appearance if
      possible because we want to place it as many as possible    
        - if it is not possible to place the letter of the max
          appearance, place one letter of the second max.	
        - if it is possible, see if we can greedily place two letters
          of the max appearance:	
	    - if the max appearance is greater than the second max by
              at least two, we can place two letters of the max
              appearance	      
	    - the intuition behind this is that we want to place the
              letter of max appearance as many as possible.  But if
              the max appearance does not greater than the second max
              by at least two, the current letter of max may no longer
              hold the max remaining appearance after being placed one
              letter.  In such case, we can only place one letter for
              the current letter of max apperance.

**1463-Cherry_pick_II**
- obvious DP but be careful with all kinds of constraints:
    - left and right bots have no need to make their paths cross    
    - at each row i, the max column the left bot can reach is (0 +
      i). Vice versa for the right bot.

**1578-Minimum_time_to_make_rope_colorful**
- window sliding and greedy:
    - sliding the window and maintaining the invariant that the window
      `[left, right]` covers consecutive balloons (cells) of same
      color.      
    - once a window becomes maximal, we only keep the one in the
      windown with the max cost and remove the rest.  This requires us
      to keep track of max during window sliding

**easy ones**
- 341-Flatten_nested_list_iterator
- 443-String_compression
- 545-Boundary_of_binary_tree
- 609-Find_duplicate_file_in_system