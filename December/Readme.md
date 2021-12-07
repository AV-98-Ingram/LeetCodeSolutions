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

**easy ones**
-- 690-Employee_importance
-- 735-Asteroid_collision