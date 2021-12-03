class Solution {
    Map<Integer, Employee> table = new HashMap<>();
    
    public int getImportance(List<Employee> employees, int id) {	
        for (Employee empe : employees)
	    table.put(empe.id, empe);

	Employee head = table.get(id);
	if (head != null)
	    return totalValue(head);
	return 0;
    }

    private int totalValue(Employee empe) {
	int val = empe.importance;

	for (int sub : empe.subordinates) {
	    Employee subee = table.get(sub);
        
	    val += subee == null ? 0 : totalValue(subee);
	}
	return val;
    }
}

