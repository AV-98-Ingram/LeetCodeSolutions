The KMP algorithm is to match a pattern `w` in a string `s`.  The key
idea of KMP is to find the optimal position to re-start the match once
the current match fails.

A key observation is that when matching `s[j]` to `w[i]` fails, if
`w[k .. i-1]` is a prefix of `w` for the least such `k` (maximize the
prefix) and `k > 0` (true suffix), we can restart the match from `s[j - (i-k)]`.

So we need to figure out for each prefix of `w` ending at index `i`,
i.e., `w[0 .. i]`, its max true suffix that is a prefix of `w`.  Let's
call such max true suffix, a *good* suffix of `w[0 .. i]`.

Let `dp[i]` be the max such index that `w[0 .. dp[i]]` is a true
suffix of `w[0 .. i]`.

```
dp[0] = -1

dp[i] = dp[i-1] + 1,  if w[dp[i-1] + 1] = w[i], i.e., w[i] extends the true suffix from w[0 .. i-1]

        dp[dp[i-1]] + 1, elif dp[i-1] >= 0 and w[dp[dp[i-1]] + 1] = w[i]

        -1, otherwise,   no such true suffix of w[0 .. i]
```

The second case for computing `dp[i]` above is the
trickiest.  It enables reusing previous results.  It corresponds to
the case where we know that `w[0 .. dp[i-1]]` is a true suffix of
`w[0 .. i-1]` but `w[i]` discontinues this true suffix.  So we need to find
the *good* suffix for `w[0 .. i]`.  If the *good* suffix of `w[0 .. i]` exists,
it must also be the *good* suffix of `w[0 .. dp[i-1]+1]`, which has been computed.
Visually,

```

    w[0     ..      i-1] w[i]
           |<-   x   ->|        // x is the *good* suffix of w[0 .. i-1]. x repeats w[0 .. dp[i-1]].  

     |<-   x   ->|
    w[0         dp[i-1]]
     

    The *good* suffix of w[0 .. i] must also be a *good* suffix of w[0 .. dp[i-1]+1], which has been previously computed.

```

To utilize the information computed in `dp` to quickly restart after a
match fails, we convert it to another array `T`. `T[i]` represents the
number of steps to go back to re-start the match.

```

T[0] = -1               // if match fails at index 0, go back -1 step, i.e., re-start match from the next index

T[i] = T[dp[i]],        if w[i] extends the *good* suffix in w[0 .. i-1], i.e., dp[i] > dp[i-1]

       dp[i-1] + 1      otherwise

```

Let us explain the transition to `T[i]`.  The first case corresponds
to the case `w[i]` expends the *good* suffix `ws` of `w[0 .. i-1]`.
Note that in this case, the restart index is not `i - |ws|` as `w[i]`
is a part of `ws`.  In fact, the restart scheme to handle match
failure of `w[i]` is same as handle match failure of `w[dp[i]]`, which
is a previous computation.

The second case corresponds to the case `w[i]` does not extend the
*good* suffix (if exists) of `w[0 .. i-1]`. If such case, it is good
to restart from index `i - |ws|`, where `ws` is the *good* suffix of
`w[0 .. i-1]`.  Note that the formula is correct even if the *good*
suffix of `w[0 .. i-1]` does not exist.

