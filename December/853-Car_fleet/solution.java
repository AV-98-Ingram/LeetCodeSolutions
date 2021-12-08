import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

class Solution {

    class Car {
	int pos, spd;

	Car(int pos, int spd) {
	    this.pos = pos;
	    this.spd = spd;
	}
    }

    public int carFleet(int target, int[] position, int[] speed) {
	ArrayList<Car> cars = new ArrayList<>();

	for (int i = 0; i < speed.length; i++)
	    cars.add(new Car(position[i], speed[i]));

	Collections.sort(cars, (i, j) -> Integer.compare(i.pos, j.pos));

	LinkedList<Car> fleets = new LinkedList<>();

	fleets.add(cars.remove(cars.size() - 1));
	while (!cars.isEmpty()) {
	    Car car1 = cars.remove(cars.size() - 1);
	    Car car2 = fleets.get(0);

	    if (!meet(car1, car2, target))
		fleets.addFirst(car1);
	}
	return fleets.size();
    }

    // car1 chases car2
    private boolean meet(Car car1, Car car2, int target) {
	if (car1.spd <= car2.spd)
	    return false;

	return ((long) (target - car2.pos)
		* (long) car1.spd) >= (long) (target - car1.pos)
	    * (long) car2.spd;
    }

    public static void main(String[] args) {
	System.out.println(new Solution().carFleet(660732,
						   new int[] { 620831, 145366, 229113, 144305, 382893, 514856,
							       171642, 87230, 409014, 65613 },
						   new int[] { 327716, 69772, 667805, 856849, 78755, 606982,
							       696937, 207697, 275337, 290550 }));
    }
}

