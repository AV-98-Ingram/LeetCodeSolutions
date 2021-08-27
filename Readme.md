# A Quick Index to the Solved Problems 

** 4-Median_of_two_sorted_arrays (hard) **
- based on comparing medians of the two arrays 
- getting rid of nearly half of the smaller array each iteration.
- reduce by half, median

** 5-Longest_Palindromic_Substring **
- maintaining a window (left, right)
- slide (to left and right) and enlarging the window from the center

** 6-Zigzag **
- figure out the pattern by hand then coding

** 7-Reverse_integer **
- extract digits and use of stack
- ALWAYS be careful with 32-bit integer out-of-bound

** 8-String_to_integer **
- algorithm: simply follow the problem description
- ALWAYS be careful with 32-bit integer out-of-bound

** 9-Palindrome_number **
- extract digits and test if it is palindrome

** 10-Regular_expression_matching (hard) **
-  pattern is a nondeterministic finite automaton
-  pattern matching is to travel the automaton
-  saving visited states so that each state is visited once
-  a state is a pair (string position, machine state)
-  time complexity is the size of the space space 
-  be careful with machine construction (e.g. I made mistake in dealing with "a*")

** 11-Container_with_most_water **
- maintain a window (left, right), which initially is (left_most_bar, right_most_bar)
- selectively shrink the window by moving towards inner from the shorter bar

** 12-Integer_to_roman **
- extract digits from an integer and use of List

** 274-H_Index ** 
- "For each number i, how many publications having at least i citations?"
- Answering the question above takes O(n) with saving previous results.

** 331-Verify_preorder_serialization_of_a_binary_tree **
- recursively visit a binary tree
- the input is invalid if input ends before visiting done or visiting is done before the input ends
- using Java's string split method could make coding much easier