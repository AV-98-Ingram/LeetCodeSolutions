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

**297-Serialize_and_deserialize_binary_tree**
- serialize a binary tree level by level, using "()" to group each level

**449-Serialize_and_deserialize_BST**
- same as 297 but needs to remove all the tailing "null"s

**716-Max_stack**
- For the follow-up----make popMax() O(logN)
    - Use a double linked list to represent the stack
    - Use a TreeMap to map stack element values to nodes in the list    
    - To delete the max, it takes O(logN) to find the max value and
      then takes constant time to remove the corresponding node from
      the list

**979-Distribute_coins_in_binary_tree**
- Recursively compute the number coins needed for both left and right
  child and at the same time accumulating moves needed.
- A big NOTE is that the coins needed by a child node could be a
  negative number meaning that the child node in fact has extra coins.

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

**1578-Minimum_time_to_make_rope_colorful**
- window sliding and greedy:
    - sliding the window and maintaining the invariant that the window
      `[left, right]` covers consecutive balloons (cells) of same
      color.      
    - once a window becomes maximal, we only keep the one in the
      windown with the max cost and remove the rest.  This requires us
      to keep track of max during window sliding

**easy ones**
- 443-String_compression
- 545-Boundary_of_binary_tree 