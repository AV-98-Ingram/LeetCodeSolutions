**207-Course_schedule**
- topological BFS

**212-Word_search_II**
- using a prefix tree and backtrack BFS
- optimization: delete the path to a found word in the prefix tree IFF
  no other word is sharing the path

**348-Design_tic_tac_toe**
- Simple solution: look at 8 neighbors each step, worst case O(n) complexity per move
- Advanced solution: we only need to count how many moves each play
  has placed on each row, column and the two (cross) diagonals.  Yes,
  there are only two diagonals have maximum length n.  For each move by
  one player, we can update the count of each row, column or either of
  the diagonal in a constant time.

**370-Range_addition**
- I immediately thought about break up the problem to multiple
  independent tasks for easy parallelization.
- The LC solution is amazing.  For a range update `[start, end, incr]`,
  we mark on the result array a that
```
a[start] = incr;
a[end+1] = -incr;
```
meaning that `a[start ...]` will increase by `incr` and `a[end+1 ...]`
will decrease by `incr`.  They together simply mean that the range
a[start .. end] will increase by `incr`.  We just need to do such mark
for each "update" record and scan the result array once at final.

**472-Concatenated_words**
- Build a Trie from shortest word to longest word.  During the build
  of each word, testing if a word segment is already in thee Trie.  
  For example, to build "catdogcat" with "cat", "dog" and
  "catdog" already in Trie:
```
  1. We would first match "cat" in "catdogcat" then we face a branch:
   1.1. try to find if "dogcat" is a word (or a word concatenation), i.e., (almost) repeat step 1 recursively.
   1.2. continue to match until we saw "catdog" and repeat step 1.

```
- We could optimize the solution above by caching words.

**642-Design_search_autocomplete_system**
- The key idea is to use Trie.

**696-Count_binary_substrings**
- This one is quite hard. The base of the idea is to sum up elements
  with '0' being counted as -1 and '1' as 1. Once the sum of the substring
  s[i .. j] is zero, we know the array has same amount of '0's
  and '1's.  
- The hard part is that the problem only counts substrings where '0's
  and '1's are consecutive.  For example, "0101" has two '0's and two
  '1's but they are not consecutive so it is not valid. Another
  example is "0011", which is valid.  So how to take use of this
  restriction to optimize the solution ?  The key observation is that
  we only need to look at a substring when '0's and '1's in it become
  non-consecutive:
```
Let i be the index of the head of a scanning substring and j the exclusive tail.

During the scan, if we reach, for example, such a state "001110",
where i refers to the first '0' and j refers to the third '0'. We know
'0's are no longer consecutive so we look at the substring "00111".

We know the "sum" of this substring is -1 + (-1) + 1 + 1 + 1 = 1 and
its length is 5.  So the longest valid substring in it has length 5 -
1 = 4.  A valid string of length n will contribute n / 2 valid
substrings to our final result.

Afterwards, we'd like to continue the scan from an updated state where
no duplicated work is ever needed.  In this example, the next state to
resume the scan is
   0 0 1 1 1 0
       |     |
       i     j
So we need to update i, j, and "sum":
 - i shall then refer to the first '1' in "001110" because all the
   possibilities involving the first two '0's have been counted.   
 - j shall stay the same because we are next going to explore
   substrings starting from those '1's.
 - "sum" shall be updated to the sum of the elements in the substring
   s[i .. j-1], where i and j hold the updated value. 
```

**767-Reorganize_string**
- Try to place all the characters in the input string in following way:
-- 1. Sorting the characters in the input	by the number of their appearances.
-- 2. Suppose the character c of the maximum appearances appears n times.
      Also suppose the string has length m.  By placing c first, we can create n separated spaces:
```
c ... c ... c ...   (dots denotes the separated space)
```
-- 3. To make sure there is no two c's are adjacent to each other, we
   need to put other characters into the first n-1 separated
   spaces. So if (m - n) < n - 1. We can do nothing but return "".
-- 4. Instead, once (m - n) >= n - 1, we can always find at least one
   valid result.  This could be achieved by place the rest of the
   characters in a round-robin style in the n separated spaces.   
   For any other character that has multiple appearances, we can
   separate them because their number of appearances is no greater
   than n.


**828-Count_unique_characters_of_all_substrings_of_a_given_string**
- O(n^2) solution is easy see solution.java and solution2.java
- O(n) solution is hard ass. See comments in solution3.java

**easy ones**
- 387-First_unique_character_in_a_string
