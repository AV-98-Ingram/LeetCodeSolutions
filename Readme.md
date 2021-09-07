# Problems need re-visit

**try to solve them  again after I have nearly forgotten the solution**

- 1235-maximum_profit_in_job_scheduling (hard)
- 834-Sum_of_distances_in_tree (hard)



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

153-Find_minimum_in_rotated_sorted_array
