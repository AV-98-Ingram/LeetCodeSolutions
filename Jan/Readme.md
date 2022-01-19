**Revisit**
- 992-Subarrays_with_k_different_integers

    - I got that this is a window sliding problem.  I think I also got
      the intuition right: move the right pointer of the window step
      by step; in each step, if there are k unique numbers, we count the
      number of good subarrays ending at the right pointer.

      But I did not figure out an O(n) algorithm.  The following is
      what I got from LC:

     Maining three pointers, left `l`, right `r` and middle `m`, which
     are all initially 0.  Enlarge the window by moving `r` step by
     step.  In each step, if there are k unique numbers, we move `m`
     to the place such that the window `[m, r]` is the minimum window
     containing k unique numbers.  Then, we can add the good
     sub-arrays ending at `r`, i.e., `m - l + 1`.  In each step, once
     there are k+1 unique numbers, we know how to shrink the window,
     i.e., move `l` and `m` to `m+1`.  For example, the input is
     "1,2,3,1,2,4" and k = 3.
     
```
   1   2   3   1   2   4
   |
 l,m,r

   1   2   3   1   2   4
   |       |
 l,m       r                 // m stays, and we count good subarrays ending at r: (m-l) + 1 = 1

   1   2   3   1   2   4
   |   |       |
   l   m       r             // m moves, and we count good subarrays ending at r: (m-l) + 1 = 2, i.e., [1 2 3 1] & [2 3 1]

   1   2   3   1   2   4
   |       |       |
   l       m       r         // m moves, and we count good subarrays ending at r: (m-l) + 1 = 3, i.e., [1 2 3 1 2], [2 3 1 2], & [3 1 2]

   1   2   3   1   2   4
   |       |           |
   l       m           r     // now there are 4 unique numbers, we need to update l and m to shink the window

   1   2   3   1   2   4
               |       |
              l,m      r     // note that there are again k unique numbers, we need to count the sun-arrays (m - l) + 1 = 1   
```

- 730-Count_different_palindromic_subsequences
    - this is one is a hard-ass
    
    - the main idea is to recursively to `count(s, start, end)` with
      memorization, where `count(s, start, end)` means the number of
      palindromic subsequences in `s[start .. end]`
      
```
  count(s, start, end) =
    s[start] != s[end] -> count(s, start+1, end) + count(s, start, end-1) - count(s, start+1, end-1)
  | s[start] == s[end] ->
                          s[start+1 .. end-1] contains no s[start] -> count(s, start+1, end-1) * 2 + 2
                        | s[start+1 .. end-1] contains one s[start] -> count(s, start+1, end-1) * 2 + 1
			| s[start+1 .. end-1] contains multiple s[start] -> count(s, start+1, end-1) * 2 - count(s, innerStart+1, innerEnd-1),
			  where innerStart and innerEnd are the first and the last s[start] appearances in s[start+1 .. end-1].
```

    - some explanation:    
        - For a string "a x .. y b" where first and last differ,
	  itself cannot form a palindrome so we won't count itself.
	  Therefore, we recursively look at the prefix "a x...y" as
	  well as the suffix "x ...y b".  But when count palindrome
	  subseqs in "a x...y" and "x...y b", we count "x...y"
	  twice. So we subtract the count of "x .. y".	  
        - For a string "a x...y a" where first and last are identical,
          itself is a palindrome.  So any palindrome subseq `s` in "x
          ... y" will count, as well as `"a s a"`.  That's `count("x
          ... y") * 2`.  In addition, if 'a' is not in "x .. y", we
          have both "aa" and "a".  If there are at least 2 appearances
          of 'a' in "x ... y", suppose "x ... y" has the form "x ... a
          S a .. y" where the two 'a's are the first and last 'a' in
          it and "S" is a substring.  Then for any palindrome subseq
          `s` in "S", we will count `"a s a"` twice by `count("x ... y")
          * 2`. Thats way we eventually subtract ``count(S)`.



**192-Word_frequency (bash script problem)**
- solution: `tr -s ' ' '\n' < words.txt | sort | uniq -c | sort -r | awk '{print $2 " " $1}'`
    - `tr -s ' ' '\n'`: transforms all white spaces ' ' to new line '\n' and SQUEEZE multiple consecutive '\n's into just one.
    - `sort` : sort lines in lexicographical order; the option `-r` means sort in reverse order
    - `uniq -c` : report or filter out repeated lines in a file.  If identical lines are not consecutive, they are not considered unique.
                 The option `-c` places the number of repeats in front of each unique line.		 
    - `awk 'pattern{action}'`:  performs an `action` on a text matched the `pattern`.  A missing pattern means always match.
                                Each line by default is separated by white spaces. One can use `$1, $2,...` to refer to the
				first, second,... separated fields.
                                One can also assign regex to a special variable FS using the option `-v FS=regex` to specify
				the separator.

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

**428-Serialize_and_deserialize_n-ary_Tree**
- pure engineering effort, easy

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
    - 1. Sorting the characters in the input    by the number of their appearances.
    - 2. Suppose the character c of the maximum appearances appears n times.
      Also suppose the string has length m.  By placing c first, we can create n separated spaces:
      `c ... c ... c ...   (dots denotes the separated space)`
    - 3. To make sure there is no two c's are adjacent to each other, we
         need to put other characters into the first n-1 separated
         spaces. So if (m - n) < n - 1. We can do nothing but return "".
   - 4. Instead, once (m - n) >= n - 1, we can always find at least one
        valid result.  This could be achieved by place the rest of the
        characters in a round-robin style in the n separated spaces.   
        For any other character that has multiple appearances, we can
        separate them because their number of appearances is no greater
        than n.

**828-Count_unique_characters_of_all_substrings_of_a_given_string**
- O(n^2) solution is easy see solution.java and solution2.java
- O(n) solution is hard ass. See comments in solution3.java

**829-Consecutive_numbers_sum**
- hard-ass math problem. See my comment in code

**863-All_nodes_distance_K_in_binary_tree**
- My solution seems OK. Here is the description:
  
  For each target node t, any descendant of t that is in k distance
  to t can be easily found by traversing the tree rooted by t.

  For non-descendants of t, we do the same traversal on the trees that
  are rooted by ancestors of t.  Suppose the distance between an
  ancestor R of t and t itself is n.  The traversal is to find nodes
  under R that is in (k-n) distance to R.  Note that, if t is in the
  left child of a, we only need to traverse the right child of a, and vice verse.


**873-Length_of_longest_fibonacci_subsequence**
- a DP problem.  Let `dp[i][j]` be the max length of fibo-subseq that
  ends with `arr[i]` and `arr[j]`.  Then we compute for all these
  cells by

```
foreach (arr[i], arr[j]) where i > j
  next := arr[i] + arr[j];
  if (next = arr[k])
     dp[j][k] = max(dp[i][j] + 1, dp[j][k]);
```

- correctness justification: `dp[i][j]` is always computed before
  `dp[j][next]` so long as `i < j < next`. So it is not possible for
  the case that `dp[j][next] = dp[i][j]+1` goes before `dp[i][j]`
  being updated.  
- This DP idea comes from my first intuitive thought. Roughly, scan
  `arr` one by one and maintain a state.  The state comprises `arr[i]
  + arr[j] -> fibo subseqs whose next are arr[i] + arr[j]` pairs.  For
  each newly scanned `arr[k]`, update the state by processing `(arr[i] + arr[k])` for each already scanned `arr[i]`.

**907-Sum_of_subarray_minimums**
- There are three key ideas in this problem
    - 1. The same basic idea as Problem 828: counting each number for its appearances in sub-arrays where it is the min.
    - 2. The different part is that there is no "easy" way to find the left and right bounds for each array element a[i].
         To find the bounds of each element, we need to scan the array twice: left -> right and left <- right:
        - For each element a[i], its left bound is a[i-1] if a[i-1] < a[i] or recursively,
	  the left bound of a[i-1], the left bound of the left bound of a[i-1], ....
	  Vice versa for the right bound.

    - 3. For a subarray that has multiple min numbers, only one min
      number shall be counted.  So we could choose to always count the
      last (right-most) min.  To achieve this, we define left and
      right bounds of a[i] by
         - left bound:  the max k among [0, i) such that a[k] < a[i], or -1 if no such k.
         - right bound: the min k among (i, len) such that a[k] <= a[i], or len if no such k.  
    So for a[i], in any sub-array where it counts, the last element in the
    sub-array is strictly less than a[i]. So we a[i] is the last min that
    will be counted if there are multiple mins.

**909-Snakes_and_ladders**
- simply do BFS

**926-Flip_string_to_monotone_increasing**
- My solution:
  
  First, we could ignore the prefix of the given string that are all '0's.
  Similarly, we could ignore the suffix of the given string that are all '1's.
  So lets say the segment we need to look into is s[start .. end].

  Suppose we know how many '1's are there in s[start .. end], denoted
  `Ones(start, end)`. This can be done by scan the string once.

  Then we look at s[end], if it is '1', we can simply keep it as it is
  then continue to look at s[start .. end-1] with the number of '1's
  decrements.   If s[end] is '0', we have to compare the two choices:
    1. Keeping '0' and changing all '1's in s[start .. end] to '0'.  It takes `Ones(start, end)` flips.
    2. Flip it to '1' and continue to look at s[start .. end-1].

  Choice 1 is a constant step and choice 2 takes recursion. Total time complexity is O(n).

- LC gives a DP solution using prefix sums, which essentially is
  similar to mine.  Prefix sum of the prefix s[0 .. i] tells you how
  many ones are there.  With the prefix sums information, the DP is
  simple.

**1041-Robot_bounded_in_circle**
- I thought the ONLY condition for circling is that each repetition
  of the instructions turns a non-zero (non-360) degree in a
  direction.  
- By running tests, I realized the second condition: each repetition
  of the instructions turns no degree but goes back to (0, 0).

**1111-Maximum_nesting_depth_of_two_valid_parentheses_strings**
- Simple idea: We call a substring `s` in the given string a
  "parenthesis group", if `s` does NOT enclosed by any parenthesis in
  the given string.  Then for each "parenthesis group", we count the
  depth `D` in the first scan, and extract a pair of parenthesis of
  depth less than or equal to `D/2` to another string in the second
  scan.

**1135-Connecting_cities_with_minimum_cost**
- Use greedy algorithm to always pick shortest paths among the rest; and 
- Use Union-Find to connect cities after picking shortest paths.

**1229-Meeting_scheduler**
- Sorting `slots1` and match each slot `s1` in `slots1` in order with
  "may-overlap" slot `s2` in `slots2` in order.  The slots in `slots2`
  "may-overlap" with `s1` are visually like the following
  
```
s1:     [        slot1         ]
      [ s2_1 ] [ s2_2 ] ...  [s2_n]
```

- Formally, suppose all slots in `slots2` are sorted.  Let `start(s)`
  be the start time and `end(s)` be the end time of a slot `s`, resp.
    - `s2_1` is the one that `start(s2_1) < start(s1)` if exists;
    - `s2_2, ..., s2_{n-1}` are the ones that are FULLY overlapped with `s1`;
    - `s2_n` is the one that `start(s2_n) < end(s1) && end(s2_n) >= end(s1)` if exists    
- To quickly find all such "may-overlap" slots for each `s1`, we put
  all slots in `slots2` in a BSTree. 
  

**1473-Paint_house_III**
- backtrack + state caching, passed through slow
- DP solution takes O(m * n * target) complexity but faster
    - Basically, dp[i][t][c] is the min cost of painting houses[0 .. i] in t groups and houses[i] has color c.
    - The transition is not hard:
    ```
    dp[i][t][c] = Min(dp[i-1][t][c], Min(dp[i-1][t-1][0 .. c, c .. n-1]))
    ```
    - of course the transition is twisted with the fact that some houses already painted
    - we also need to save the minimum color in `mins[i][t]` such that `dp[i][t][mins[i][t]] = Min(dp[i][t][*])`
      otherwise, the algorithm goes O(m * n^2 * target)
    - in fact, we save the minimum and second minimum in case the minimum choice conflicts with the color of the right neighbor
- Last but not the least, I really need to be careful with TreeSet.
  WHENEVER USING TreeSet, think about duplication problem.

**1606-Find_servers_that_handled_most_number_of_requests**
- This one is a good problem.  Brutal force is easy but optimized one is hard.
- I was trying to
    - maintaining servers in a Heap by there free time so that I can
      quickly get available ones but it takes O(n) to pick the nearest
      one to `i%k` among the availables      
    - or, maintaining tasks in a Heap so that I can assign a future
      task to a server which is not free for the current
      task. However, this is wrong.
      
- the optimized solution is learned from LC.  Basically, it separates
  servers into two groups: Busies and Availables.  Busies are
  maintained in a Heap of pairs, each pair comprises the free time and
  the ID of the server.  Availables are maintained in a Heap of server
  IDs.  Initially, all servers are in Availables.  To handle job i,
  one first put all Busy servers that are available for job i back to
  Availables.  This is quick as Busies are in a Heap.  Then one can
  quickly find the nearest one to i%k in Availables.  Finally the
  server is removed from Availables, updated with new free time w.r.t
  job i, and added to Busies.

  Moving servers from Busies to Availables w.r.t job `i` takes
  amortized O(logk).  Because if the worst case happens that all K
  servers are in Busies and are available to job `i`, it takes
  O(klogk) this time but there will be at most 1 server in Busies for
  the next job.
- Take away message: when I need to select an element from a
  collection with respect to 2 different properties of elements, think
  about separate the collection into two group.


**1740-Find_distance_in_a_binary_tree**
- Another problem of "finding two nodes under a tree", typically,
  just consider the two cases:
    - one node is the ancestor of another node
    - there is an LCA of the two nodes.
- This one has a simple special case: return 0 immediatelt if `p == q`

**easy ones**
- 387-First_unique_character_in_a_string
- 937-Reorder_data_in_log_files
- 994-Rotting_oranges
- 1732-Find_the_highest_altitude