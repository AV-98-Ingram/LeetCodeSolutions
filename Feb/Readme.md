**151-Reverse_words_in_a_string**
- For O(1) space solution: reverse the whole string then reverse each word.

**238-Product_of_array_except_self**
- Prefix product and suffix product
- Follow-up requires constant space usage: use only one variable to aggregate the suffix product

**297-Serialize_and_deserialize_binary_tree**
- serialize a binary tree level by level, using "()" to group each level

**449-Serialize_and_deserialize_BST**
- same as 297 but needs to remove all the tailing "null"s

**716-Max_stack**
- For the follow-up----make popMax() O(logN)
    - Use a double linked list to represent the stack
    - Use a TreeMap to map stack element values to nodes in the list    
    - To delete the max, it takes O(logN) to find the max value and
      then takes constant time to remove the corresponding node from
      the list

**1344-Angle_between_hands_of_a_clock**
- Be careful! The hour hand is not pointing at 1 at time "1:30" but at
  the middle of 1 and 2.

**easy ones**
- 443-String_compression
