** Needs revisit
- 301-Remove_invalid_parentheses: I hate this problem. Need to practise more
- 407-Trapping_rain_water_II: my BFS is too slow. See my comment in
  solution2.java, which is a solution I got from the LC discussion
  board. It is amazing to me though it seems it not fast comparing to
  other solutions.
  And a take away mesaage:
    when dealing with irregular shape (e.g., obstacles or irregular
    boundaries) in matrix problems, think about BFS.

**173-Binary_search_tree_iterator**
- each node is push on stack once and poped once so armotized operation time complexity is O(n)
- use a stack to keep track of ancestors

**215-Kth_largest_element_in_an_array**
- use a Tree-multi-Set to keep k largest then scan the array once.
- a Tree-multi-Set is a TreeSet whose comparator is simply: a < b ? -1 : 1.

**227-Basic_calculator_II**
- subtle but not hard

**236-Lowest_common_ancestor_of_a_binary_tree**
- not hard but think carefully before coding

**300-Longest_increasing_subsequence**
- I came up a O(n*m) DP approach but the best solution given is O(nlogn).
- the best solution is given by soluion2.java including a proof sketch
  on its non-obvious correctness.

**314-Binary_tree_vertical_order_traversal**
- Using PriorityQueue to sort by column and row numbers seems was ok
  but it in fact is NOT due to non-deterministic order when both colum
  and row are identical.
- BFS is easy and fast

**317-Shortest_distance_from_all_buildings**
- nothing fancy but just BFS with little optimization. See my comments
  in the code.

**329-Longest_increasing_path_in_a_matrix**
- I used DFS with memorization of the longest path reaching a node.
- LC gives a solution that first sorts the nodes by dependency using
  Onion Peeling algorithm (i.e., iteratively removing leaves), then
  the answer immediately becomes how many (peeling) levels are there.

**354-Russian_doll_envelopes**
- Sort the envelopes in groups by width, then sort heights within each
  group.  Finally perform DFS on groups.

**523-Continuous_subarray_sum**
- I only figured out a brutal force O(n^2) solution
- the optimal solution takes O(n): it saves modulo-k results of prefix
  sums.  If two prefix sums at positions i and j (i < j),
  respectively, have identical modulo result, it means that the sum of
  the numbers between i and j is a multiple of k.

**560-Subarray_sum_equals_k**
- be careful with the case of k == 0

**632-Smallest_range_covering_elements_from_k_lists**
- As the problem gives a set of order-list of numbers, I quickly
  thought of merge-sort.  Following the idea, I merge sorted all the
  numbers, each of which is attched with an ID representing the list
  it comes from.  Then perform a window slide and match algorithm to
  find the smallest window.

**636-Exclusive_time_of_functions**
- similar to the solution of the Stack-Min problem

**659-Split_array_into_consecutive_subsequences**
- greedy
- "given list of numbers are ordered" is one of the keys
- follow the intuitive match process and make use of the ordered property of the given list.

**670-Maximum_swap**
- be careful with the cases where there are more than one maximum
  digits on the right for swap.

**727-Minimum_window_subsequence**
- brutal force is easy
- the keep-track-of-the-states approach can be optimized, see comments in the code

**745-Prefix_and_suffix_search**
- use two prefix-trees: one for prefix and one for suffix
- to get the last set bit of a BitSet, just get its length().

**777-Swap_adjacent_in_LR_string**
- The key observation is that L is left mover and R is right mover and L
   and R cannot across each other.

**792-Number_of_matching_subsequences**
- The best solution is optimized to only iterate the long matching string once.
- The subsequences are managed in a bucket.

**827-Making_a_large_island**
- nothing but Union-find

**833-Find_and_replace_in_string**
- wrote a simple one that benefits parallelization

**843-Guess_the_word**
- my idea: first pick any word w and suppose w matches n characters
   with the secret.  Then the secret word must be a word that matches
   w with n characters.  So we only need to look at them. Let the set
   of words matches w by n be W.  We then iteratively select a word in
   W to be the new w and repeat.  During the iteration, we keep n
   non-decreasing.
- In addition, 1) any word that matches w by m such that m > n can be
   excluded.  Similarly, 2) any word that matches w by l such that l <
   n and is not in W can be excluded.  Note that W may change as n
   increases.  So a word not in an old W may be in a new W. Thus point
   2) here is not senseless.   
- finally, this is the hint got from a Leetcode user: we can sort the
   given word list so that the first and the last words differ the
   most.  This could help us to improve the first pick of w.

**853-Car_fleet**
- take a way message: 1) when it comes to multiply, be careful with overflow
                       2) when it comes to an (in)-equation involving divisions and precision matters,
		       	  see if we can transform the (in)-equation to only involve multiplications.
			  e.g., a/b > c ==> a > b*c

**871-Minimum_number_of_refueling_stops**
- DP is an overkill for this gas station problem
- in fact, the best solution is much simpler.  As long as the gas
tank is never empty, it doesn't matter we refuel at which PAST gas
station.  For example, we keep track of past gas stations in gas_queue
and our tank is good enough for travel to the n-th station but not
enough to the (n+1)-th station.  We only need to pick the largest
refuel among the gas_queue, increment the number of stops, and
continue our trip.

**940-Distinct_subsequences_II**
- I should know how to use DP to deal with subsequences now.

**954-Array_of_doubled_pairs**
- easy but need to be CAREFUL with 0 as 0 * 2 = 0

**1048-Longest_string_chain**
- sorting words into length-ordered collections in an PriorityQueue then repeatedly
looking for the longest chain in this PriorityQueue

**1218-Longest_arithmetic_subsequence_of_given_difference**
- Maintaining a map during the scan where keys are the expecting next
   number 'n'.  The map maps each of such 'n' to the MAX length
   of the subsequence that ends with 'n'.  For example,
   given
   [1,2,3, ...} and the diff is -2, the map after scanning '3' is {-1 -> 2, 0 -> 2, 1 -> 2}.
- When there are multiple subsequences that are expecting the same next 'n', the map keeps the length of the longest one.

**1254-Number_of_closed_islands**
- easy one but I need to be careful that when two islands merge, either one can turn the other from closed to non-closed.

**1277-Count_square_submatrices_with_all_ones**
- Brutal force shall be easy.  I added a little optimization: Let dp[i][j]
  be the known largest square edge of the square whose left top is
  [i][j].  Once, for example, we computed that dp[i][j] == n, we could
  simply fill out dp[i .. i+n-1][j .. j+n-1].  Then for any cell "(r,
  c)", if dp[r][c] has been computed, we could start to compute the
  next largest square edge from the value of dp[r][c].  But we always
  have to fille out dp[r .. r + n - 1][c .. c + n -1] once longer edge
  is discovered.
  - Why my solution has to fill out dp cells of any new largest square
  just dicovered?  After I read the real DP solution, we realized that
  it is because of my "square identifier" choice.  I choose left top
  as the identifier while the real DP solution chooses right bottom.
  What's the difference?  When to compute the largest sqaure for the
  left top identifier (i, j), other (maybe sub-) squares identified by
  (i + k1, j + k2) for any positive k1 & k2 are unknown.  However, if
  one is trying to compute the largest square for the right bottom
  identifier (i, j), other (maybe sub-) squares identified by (i - k1,
  j - k2) for any positive k1 & k2 are KNOWN!.

**1284-Minimum_number_of_flips_to_convert_binary_matrix_to_zero_matrix**
- simple model checking

**1293-Shortest_path_in_a_grid_with_obstacles_elimination**
- The simple BFS solution is good enough to memorize for a real interview.
- However, see my comments in solution2.java, I think there is a
  non-trivial theorem in this problem for the BFS solution to be
  correct.
- I also came up a fixed-point + DP solution that does not require the
theorem to be true.

**1406-Stone_game_III**
- reminds me of the Ping-Pong defeat of mine.
- solution.java is the brutal force one, solution2.java is the DP solution
I came up that computes player's advantage in reverse order, and
in solution3.java I realized that it only needs to compute one generic
player's advantage at his/her turn at a position.

**1368-Minimum_cost_to_make_at_least_one_valid_path_in_a_grid**
- create connected sub-graphs then topological BFS

**1423-Maximum_points_you_can_obtain_from_cards**
- there is a word-trap in the description:  "player can take cards
from head or tail each round" in fact is saying that "player take i
cards from head and j cards from tail such that i +j = k".  Then the
problem is a fixed size window moving problem that can be solved by
simply moving the window k times.

**1499-Max_value_of_equation**
- The formula `y_i + y_j - (x_j - x_i)` can be converted to `(y_i -
  x_i) + (y_j + x+j)`, which consists of two independent parts.  Each
  part associates to a unique coordinate as i < j.  So we can fix the
  part `(y_j + x_j)` and look for the largest `(y_i -x_i)` by scanning
  each `(x_j, y_j)` while maintaining all previous `(y_i - x_i)` using
  a PriorityQueue.

**1509-Minimum-difference_between_largest_and_smallest_value_in_three_moves**
- Window moving: First to sort the array.  Then try to change numbers
   at two ends to the numbers in middle so that they become more
   close.

**1610-Maximum_number_of_visible_points**
- using atan2(y, x) to get the radian between the vector (x, y) and (+infi, 0)
- convert radian to degree angle
- sorting all the angles between a vector and (+infi, 0) then moving
  the given "angle" window to maximize the observed points.  
- However, one problem is that, for example, there are two vectors whose
  angles between (+infi, 0) are 135 and -135, respectively.  It seems
  that one needs a window of at least size 270 to see both points but
  in fact the angle between these two vectors are only 90. 
```
   vector 1
     \  |
      \	|
       \|
--------|----------
       /|
      / |         
     /  |
  vector 2
```
- The solution is to, in addition, add a new vector for vector 2 as if
  it has 135 + 90 degrees.  As the observer's view angle is less than
  360, it is not possible for both the new vector and vector 2 to be
  observed at the same time. So there will be no duplication in a fixed
  view.

**1834-Single_threaded_CPU**
- I used two PriorityQueues, one sorts by task starting time and the
  other holds all tasks that can be launched at current time sorted by
  their processing time.  Once the CPU executed one task, we update
  the current time and move more tasks from the first PriorityQueue to
  the second one.
- I feel like a topological BFS will do as well.  The to find the next
  nodes, it probably will also need to maintain a "current time"
  variable.

**1937-Maximum_number_of_points_with_cost**
- At first I came up a straightforward DP solution which takes O(m * n * n) time.
- Then by reading some LC discussion, I was inspired by that there are
  sub-problems can also be solved using DP.
  
- The key idea to use DP to solve the sub-problem is similar to the
  idea in <<1499-Max_value_of_equation>>:
```
  For each row, fix a column j, we'd like to know how is the best
  result before j comparing to j.  Suppose k, 0 <= k <= j, is the
  column on the same row that maximizes

     diff = result[k] - result[j] - (j - k)
     
  If we rearrange the formula:

     diff = (result[k] + k) - (result[j] + j)

  We only need to compare result[j] + j with the maximal result[k] + k. When scanning the columns by j, the maximal
  result[k] + k can be obtained along the scan.

  Similarly, we also need to know how it the best result after j
  comparing to j.  This can be achived using the asymmeric method.
```
Using DP also to solve sub-problems helps to improve the algorithm to
O(m * n) time.
- Take away messages are 1) when a DP soluton seems still to slow,
  think about using DP to solve sub-problems; 2) for problems to
  obtain the optimal value of some formula like `y_i +/- y_j +/- |x_i
  - x_j|`, there might be ways to re-arrange the formula to convert
  the problem to obtain the optimal value of some formula depending on
  ONLY a single cell.  This would reduce the time complexity from O(m*n) to O(n).

**easy ones**
- 162-Find_peak_element
- 199-Binary_tree_right_side_view
- 249-Group_shifted_strings
- 339-Nested_list_weight_sum
- 347-Top_k_frequent_elements
- 408-Valid_word_abbreviation
- 415-Add_strings
- 426-Convert_binary_search_tree_to_sorted_doubly_linked_list
- 498-Diagonal_traverse
- 528-Random_pick_with_weight
- 543-Diameter_of_binary_tree
- 680-Valid_palindrome_II
- 690-Employee_importance
- 735-Asteroid_collision
- 766-Toeplitz_matrix
- 791-Custom_sort_string
- 900-RLE_iterator
- 1110-Delete_nodes_and_return_forest
- 1525-Number_of_good_ways_to_split_a_string 
- 2007-Find_original_array_from_doubled_array
- 2013-Detect_squares
- 2034-Stock_price_fluctuation