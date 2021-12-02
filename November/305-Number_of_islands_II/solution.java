import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

	class Island {
		final int id;
		private Island root = null;

		Island(int id) {
			this.id = id;
		}

		void merge(Island island) {
			getRep().root = island;
		}

		Island getRep() {
			Island thisRep = this;

			while (thisRep.root != null)
				thisRep = thisRep.root;
			return thisRep;
		}
	}

	class Cell {
		final int r, c;

		Cell(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public int hashCode() {
			return r * 10000 + c;
		}

		public boolean equals(Object obj) {
			if (obj instanceof Cell) {
				Cell other = (Cell) obj;
				return r == other.r && c == other.c;
			}
			return false;
		}
	}

	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		List<Integer> ans = new ArrayList<>();
		Map<Cell, Island> islands = new HashMap<>();
		int nislands = 0;
		int idCounter = 0;

		for (int[] op : positions) {
			Cell cell = new Cell(op[0], op[1]);

			if (islands.containsKey(cell)) {
				ans.add(nislands);
				continue;
			}

			Cell l = new Cell(op[0], op[1] - 1);
			Cell r = new Cell(op[0], op[1] + 1);
			Cell t = new Cell(op[0] - 1, op[1]);
			Cell b = new Cell(op[0] + 1, op[1]);
			Island neibs[] = new Island[] { islands.get(l), islands.get(r),
					islands.get(t), islands.get(b) };
			Island myland = null;
			int i = 0;

			while (myland == null && i < 4)
				myland = neibs[i++];
			for (; i < 4; i++)
				if (neibs[i] != null
						&& neibs[i].getRep().id != myland.getRep().id) {
					neibs[i].merge(myland);
					nislands--;
				}
			if (myland == null) {
				ans.add(++nislands);
				myland = new Island(idCounter++);
			} else
				ans.add(nislands);
			islands.put(cell, myland);
		}
		return ans;
	}

	public static void main(String[] args) {
		new Solution().numIslands2(3, 3, new int[][] { { 0, 0 }, { 2, 2 },
				{ 0, 1 }, { 1, 0 }, { 2, 1 }, { 1, 2 }, { 1, 1 } });
	}
}
