# Topics
- KMP algorithm

# Problems need re-visit

**try to solve them  again after I have nearly forgotten the solution**

- 1235-maximum_profit_in_job_scheduling (hard)
- 834-Sum_of_distances_in_tree (hard)
- 41-First_missing_positive (hard)
- 43-Multiply_strings
-- This one is not hard but takes a good amount of engineering effort. And this one is good to show off pointer techniques.



# A Quick Index to the Solved Problems 

**4-Median_of_two_sorted_arrays (hard)**
- based on comparing medians of the two arrays 
- getting rid of nearly half of the smaller array each iteration.
- reduce by half, median

**5-Longest_Palindromic_Substring**
- maintaining a window (left, right)
- slide (to left and right) and enlarging the window from the center

**6-Zigzag**
- figure out the pattern by hand then coding

**7-Reverse_integer**
- extract digits and use of stack
- ALWAYS be careful with 32-bit integer out-of-bound

**8-String_to_integer**
- algorithm: simply follow the problem description
- ALWAYS be careful with 32-bit integer out-of-bound

**9-Palindrome_number**
- extract digits and test if it is palindrome

**10-Regular_expression_matching (hard)**
-  pattern is a nondeterministic finite automaton
-  pattern matching is to travel the automaton
-  saving visited states so that each state is visited once
-  a state is a pair (string position, machine state)
-  time complexity is the size of the space space 
-  be careful with machine construction (e.g. I made mistake in dealing with "a*")

**11-Container_with_most_water**
- maintain a window (left, right), which initially is (left_most_bar, right_most_bar)
- selectively shrink the window by moving towards inner from the shorter bar

**12-Integer_to_roman**
- extract digits from an integer and use of List

**15-3Sum** 
- dividing numbers to non-negative and negative groups
- finding the one in one group that can cancel the sum of two numbers in the other group
- (0, 0, 0) needs special handling as all numbers are from the same group
- this approach is also O(n^2) but "two pointer" is better in space cost

**16-3Sum_closest**
- in order to have a O(n^2) solution, this problem must use the "two pointer" approach mentioned in 15-3Sum

**18-4Sum**
- Just use the "two pointers" approach. Time complexity O(n^3).

**23-merge_k_sorted_list**
- didn't realize what I did there is in fact a PriorityQueue

**24-Swap_nodes_in_pairs**
- easy but be careful with prevprev, prev and curr node update

**25-Reverse_nodes_in_k_Group(hard)**
- not hard, but need to be careful
- remember how to reverse a linked list

**28-strStr**
- easy IF not using KMP

**29-Divide_two_integers**
- not hard but need to do binary subtraction to compute quotient
- bit shift operation is needed

**30-Substring_with_concatenation_of_all_words(hard)**
- a brutal force solution timeouts but passes with a tiny optimization
- no need to look for better solutions I guess

**31-Next_permutation**
- find the pattern in lexicographical order

**32-Longest_valid_parentheses(hard)**
- not hard, play around with stack

**33-Search_in_rotated_sorted_array**
- the given condition that the input array has unique elements is critical
- binary search by testing on the ascending half (at least one ascending half)

**36-Valid_sudoku/37-Sudoku_solver** 
- maintain each "region" separately, where a "region" is either a row, a column or a 3X3 sub-box.
- use book-keeping function to relate row index and column index with corresponding regions.

**38-Count_and_say**
- recursive computation. For each recursion, I let it re-use the group information from last recursion and build group information incrementally.
- maybe straightforward solution is faster since each group is really small so that saving group information does not really help.

**39-Combination_sum**
- simple DP
- be careful when caching lists.  Making copies of those lists so that lists in cache will not be touched by rest of the algorithm.

**40-Combination_sum**
- instead of using set to remove duplicates, my algorithm avoids duplicates at computing by trying out all possible repetitions for each unique number

**42-Trapping_rain_water(hard)**
- to compute the water size, instead of compute it directly, compute by subtracting the inner "upheaval" sizes from the dent size.
- keep looking for the max dent among the rest of the sorted heights

**44-Wildcard_matching**
- simple NFA solution

**45-Jump_game_II**
- looks like a DP problem but in fact simpler
- when a problem is obviously a DP one, think twice if DP is needed

**47-Permutation_II**
- Compare to 46-Permutation, this solution needs to make sure that at each position equivalent numbers do not appear more than once.

**48-Rotate_image**
- With a drawed picture, it is easier to figure out the algorithm.

**274-H_Index**
- "For each number i, how many publications having at least i citations?"
- Answering the question above takes O(n) with saving previous results.

**331-Verify_preorder_serialization_of_a_binary_tree**
- recursively visit a binary tree
- the input is invalid if input ends before visiting done or visiting is done before the input ends
- using Java's string split method could make coding much easier

# Easy ones:
13-Roman_to_integer

14-Longest_common_prefix

17-Letter_combinations_of_a_phone_number

19-Remove_nth_node_from_end_of_list

20-Valid_parentheses

21-Merge_two_sorted_list

22-Generate_parentheses

26-Remove_duplicates_from_sorted_array

27-Remove_element

34-Find_first_and_last_position_of_element_in_sorted_array

35-Search_insert_position

46-Permutation

49-Group_anagrams

153-Find_minimum_in_rotated_sorted_array
