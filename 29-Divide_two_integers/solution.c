#include "stdint.h"

#define MIN (-2147483648)
#define MAX (2147483647)
#define i32 int32_t

i32 to_positive(i32 num, i32 * tail) {
  i32 ret;
  
  if (num < 0) {
    if (num == MIN) {
      ret = 2147483647;
      *tail = 1;
    } else
      ret = -num;
  } else
    return num;
  return ret;
}

i32 safe_incr(i32 num, i32 * tail) {
  if (num == MAX) {
    *tail = 1;
    return num;
  }
  return num + 1;
}

i32 bin_pos_div(i32 dd, i32 ds, i32 * rem) {
  i32 dd_half = dd >> 1; // dd_half = dd/2;
  i32 quo = 0;
  
  if (dd_half <= ds) {
    while (dd >= ds) {
      dd -= ds;
      quo++;
    } 
    *rem = dd;
  } else {
    i32 my_rem = dd - dd_half - dd_half;
    
    quo = bin_pos_div(dd_half, ds, rem);
    quo += quo;
    my_rem += *rem + *rem;
    while (my_rem >= ds) {
      my_rem -= ds;
      quo++;
    }
    *rem = my_rem;
  }
  return quo;
}

int divide(int dividend, int divisor){
  i32 dd_tail = 0;  // dd_tail is 1 iff dividend == -2^31
  i32 pos_dd, pos_ds;
  char pos = (dividend < 0 && divisor < 0) ||
    (dividend > 0 && divisor > 0);

  if (divisor == 0)
    return MAX;
  if (divisor == MIN)
    return dividend == MIN ? 1 : 0;
  if (dividend == 0)
    return 0;

  pos_ds = to_positive(divisor, &dd_tail); // this dd_tail will be discarded
  pos_dd = to_positive(dividend, &dd_tail);

  i32 quo = 0, quo_tail = 0; 

  if (pos_ds > 1) {
    int rem;

    quo = bin_pos_div(pos_dd, pos_ds, &rem);
    pos_dd = rem;
  } else { // optimize for case pos_ds == 1:
    quo = pos_dd;
    pos_dd = 0;
  }
  // At this point, we have pos_dd < pos_ds.
  // Then if pos_dd + dd_tail == pos_ds, quo++;
  // otherwise, no-op;
  if (pos_dd + dd_tail == pos_ds) 
    quo = safe_incr(quo, &quo_tail); // here quo may go overflow
  return pos ? quo : -quo-quo_tail;
}

#include "stdlib.h"
#include "stdio.h"

int main(int argc, char * argv[]) {
  int dd = atoi(argv[1]);
  int ds = atoi(argv[2]);
  
  printf("%d\n", divide(dd, ds));
}
