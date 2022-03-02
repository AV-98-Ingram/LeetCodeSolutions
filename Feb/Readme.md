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


**311-Sparse_matrix_multiplication**
- sparse matrix representation saves non-zero columns in each row

**312-Burst_balloons**
- top-down divide and conquer with caching.
- To divide: If fix the `i`-th balloon to burst at LAST, it divides
  the balloons into two independent groups: before and after `i`.  The
  whole group of balloons can be "conquered" recursively in such a
  way.


**322-Coin_change**
- A DP problem: `DP[i]` is the min number of coins to make up the amount `i`
- Maybe backtrack + cache could be better? An hypothesis is that not
  every `amount` can be made up by the coins and backtrack approach only
  computes for `amount`s that can be made up.

**325-Maximum_size_subarray_sum_equals_k**
- prefix sum
- don't forget the left bound (i.e., prefix sum 0, index 0) for the case that the max subarray is a prefix of the given array.

**336-Palindrome_pairs**
- brutal force is easy
- optimization using a prefix tree (trie).

    - For each pair of words `x`, `y`, it is either `xy` or `yx` forms
      a palindrome.  That is saying it is either `x` and `reverse(y)`
      or `y` and `reverse(x)` share a prefix.
    - Save every reversed words in a trie. Then iterate each word to
      match agaist the trie to find out the minimal set of candidates
      for further check.      
    - Note that there are two cases: either the iterating word `w` is
      longer or the reversed word sharing a prefix with `w` is longer.      
          - The former case requires to know that if a trie node is the end
            of a reversed word.	    
          - The latter case requires to know that what are the owners
            (reversed words) of a trie node.      

**340-Longest_substring_with_at_most_k_distinct_characters**
- simple window sliding problem

**351-Android_unlock_patterns**
- DFS and only to explore paths starting from (0,0) (other corner
  cells are the same), (0,1) (other non-corner edge cells are the
  same), and (1,1) (center).

**420-Strong_password_checker**
- Tricky but interesting, see my comment in the code

**449-Serialize_and_deserialize_BST**
- same as 297 but needs to remove all the tailing "null"s

**476-Number_complement**
- finds out the highest bit of 1 (suppose the bit is the n-th bit
  counting from the rightmost), creates a mask of n bits of 1s, and
  the result is then `num ^ mask`.


**503-Next_greater_element_II**
- I came out a solution that takes O(nlogn) time:
    - scan the array in two rounds.  In the first round, try to find
      the "greater next" `n` for each previously visited but unmarked
      `m` such that `index(n) > index(m)`.  Mark `m` once its "greater
      next" has been found.

      In the second round, try to find the "greater next" `n` for each
      unmarked `m` (left from last round) such that `index(n) < index(m)`.
      Mark out `m` onces its "greater next" has been found.

      Test if `n` is the "greater next" for unmarked `m`s takes O(logn).

- LC provides O(n) solution.  The main idea is still scan the array
  twice.  It first scans the array in backwards: suppose the scan
  reaches `a[i]`, then for `a[j] <= a[i], i < j`, `a[j]` is not
  possible to be the "greater next" of the un-scanned numbers on the
  left of `i`. But for `a[j] > a[i], i < j`, it still is the "greater
  next" candidate for un-scanned numbers on the left of `i` that is no
  less than `a[i]`.  So we can use a stack to maintain these candidates.
  Vice versa for the second round of the scan.


**716-Max_stack**
- For the follow-up----make popMax() O(logN)
    - Use a double linked list to represent the stack
    - Use a TreeMap to map stack element values to nodes in the list    
    - To delete the max, it takes O(logN) to find the max value and
      then takes constant time to remove the corresponding node from
      the list

**796-Rotate_string**
- KMP revisit
- simply `(str + str).contains(goal)`. Concatenating two of the given string `str`.

**895-Maximum_frequency_stack**
- Use a map to keep track of positions of every value.
- On the order of values, we only need to preserve the order of values
  in their `i`-th repetition for every `i`.  
    - For example, if the stack has three `2`s and three `3`s and one
      `1`, we only care about the order between the 3rd `2` and the
      3rd `3`, the order between the 2nd `2` and the 2nd `3`, and the
      order among the 1st `1`, `2` and `3`.

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

**1007-Minimum_domino_rotations_for_equal_row**
- Two things need to be careful due to the fact that the top and
  bottom on a domino can be the same:  
    - 1. If it takes `n` rotations to make all tops the same, it DOES
         NOT MEANING that it must take `#dominos - n` rotations to
         make all bottoms the same.	 
    - 2. If the top and bottom of a domino are the same, be careful not to rotating such domino for nothing.

- Optimization: Although it seems that there are 6 possible numbers
  (i.e., 1 to 6) for top or bottom to be after rotation, there are in
  fact at most 2 among the 6, i.e., `{tops[0], bottoms[0]}`.  As for any
  number in [1,6] that does not equal to `tops[0]` or `bottoms[0]`, it
  won't work.

**1015-Smallest_integer_divisible_by_k**
- Need to know a bit math here: let `n` be a number where all digits
  are `1`s.  If `n % k == 0` we are done.  Otherwise,  `n` becomes `n * 10 + 1` and the remainder becomes
  `(n % k) * 10 + 1`.  So we only need to test if `((n % k) * 10 + 1) % k == 0` for `n * 10 + 1`.
- Repeatedly doing this until we go back to a seen remainder.  

**1275-Find_winner_on_a_tic_tac_toe_game**
- Remember the design tic-tac-toe? count each row, column and diagnal separately.

**1291-Sequential_digits**
- be careful with termination: last digit as well as the number of digits cannot go beyond 9

**1312-Minimum_insertion_steps_to_make_a_string_palindrome**

- Important! When it comes to palindrome-related DP, think about
  topdown DP that explore three cases for the string `s[l .. r]`:
    - `s[l+1 .. r]`
    - `s[l .. r-1]`
    - `s[l+1 .. r-1]`

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

**1488-Avoid_flood_in_the_city**
- For each pair (i, j) such that `rains[i]` and `rains[j]` pour in the
  same lake and i < j, find the first dry day in between (i, j) to dry
  the lake.
- Use heap to store dry days for log(n) search  

**1510-Stone_game_IV**
- top-down DP with cache

**1524-Number_of_sub-arrays_with_odd_sum**
- `DP[i]`: the number of odd sum subarrays ending at index `i`.
- transition is based on the idea that
    - If `nums[i+1]` is even, `DP[i+1] = DP[i]`.
    - If `nums[i+1]` is odd,  `DP[i+1] = 1 + (the number of even sum subarrays ending at index i)`.

**1578-Minimum_time_to_make_rope_colorful**
- window sliding and greedy:
    - sliding the window and maintaining the invariant that the window
      `[left, right]` covers consecutive balloons (cells) of same
      color.      
    - once a window becomes maximal, we only keep the one in the
      windown with the max cost and remove the rest.  This requires us
      to keep track of max during window sliding

**easy ones**
- 223-Rectangle_area
- 283-Move_zeroes
- 341-Flatten_nested_list_iterator
- 443-String_compression
- 545-Boundary_of_binary_tree
- 609-Find_duplicate_file_in_system
- 894-All_possible_full_binary_trees
- 1207-Unique_number_of_occurrences
- 1266-Minimum_time_visiting_all_points
- 1446-Consecutive_characters
- 2042-Check_if_numbers_are_ascending_in_a_sentence