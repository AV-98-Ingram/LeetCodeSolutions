**727-Minimum_window_subsequence**
-- brutal force is easy
-- the keep-track-of-the-states approach can be optimized, see comments in the code

**745-Prefix_and_suffix_search**
-- use two prefix-trees: one for prefix and one for suffix
-- to get the last set bit of a BitSet, just get its length().

**777-Swap_adjacent_in_LR_string**
-- The key observation is that L is left mover and R is right mover and L
   and R cannot across each other.

**792-Number_of_matching_subsequences**
-- The best solution is optimized to only iterate the long matching string once.
-- The subsequences are managed in a bucket.

**833-Find_and_replace_in_string**
-- wrote a simple one that benefits parallelization

**843-Guess_the_word**
-- my idea: first pick any word w and suppose w matches n characters
   with the secret.  Then the secret word must be a word that matches
   w with n characters.  So we only need to look at them. Let the set
   of words matches w by n be W.  We then iteratively select a word in
   W to be the new w and repeat.  During the iteration, we keep n
   non-decreasing.
-- In addition, 1) any word that matches w by m such that m > n can be
   excluded.  Similarly, 2) any word that matches w by l such that l <
   n and is not in W can be excluded.  Note that W may change as n
   increases.  So a word not in an old W may be in a new W. Thus point
   2) here is not senseless.   
-- finally, this is the hint got from a Leetcode user: we can sort the
   given word list so that the first and the last words differ the
   most.  This could help us to improve the first pick of w.

**853-Car_fleet**
-- take a way message: 1) when it comes to multiply, be careful with overflow
                       2) when it comes to an (in)-equation involving divisions and precision matters,
		       	  see if we can transform the (in)-equation to only involve multiplications.
			  e.g., a/b > c ==> a > b*c

**easy ones**
-- 690-Employee_importance
-- 735-Asteroid_collision