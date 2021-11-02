/* The "walker and runner" metaphor is very smart!  However, to find
   out where the cycle begins, we need to do something more.

   Suppose we found a cycle using the "walker and runner" approach:
   
   start----pos---------runner--walker----end, 

   where "end" connects to "pos" to form a cycle and "wakler - runner" in {1,2}.
   Let the distance walked through by walker be W.  Then the distance run by runner is 2W.
   Let "walker - pos" be X and "end - walker" be Y.  Consequently, "pos - start = W - X".

   If "walker - runner = 1", we have W + Y + X = 2W + 1 => Y - 1 = W -
   X. That is "pos - start = end - walker - 1".  Also note that it
   takes an extra step from "end" to "pos".  In such case, two walkers
   start from "start" and "walker->next->next" will eventually meet at
   "pos".

   Similarly, if "walker - runner = 2", we can derive "pos - start =
   end - walker - 2".  Also not forget the extra step from "end" to
   "pos".  We need to place the two walkers at "start" and
   "walker->next->next->next", respectively, in order for them to meet
   at "pos" eventually.

   ALTHOUGH, there is a theorem that needs to be proved "walker must
   meet with runner before re-visiting any node".
 */

#define Node struct ListNode
Node * findPos(Node * head, Node * walker);

struct ListNode *detectCycle(struct ListNode *head) {
  Node * walker = head;
  Node * runner = head; 
    
  while (runner != NULL) {
    if (runner->next == walker)
      return findPos(head, walker->next->next->next);
    if (runner->next != NULL) {
      if (runner->next->next == walker)
	return findPos(head, walker->next->next);
      runner = runner->next->next;
    } else
      return NULL;
    walker = walker->next;
  }
  return NULL;
}

Node * findPos(Node * head, Node * walker) {
  Node * meeter = head;

  while (walker != meeter) {
    walker = walker->next;
    meeter = meeter->next;
  }
  return walker;
}
