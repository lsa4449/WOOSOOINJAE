package dao;

import data.Database;

public class SeatDao {
	
	public static SeatDao Instance;
	
	private SeatDao() {}
	
	public static SeatDao getInstance() {
		if(Instance == null) {
			Instance = new SeatDao();
		}
		
		return Instance;
	}

	public void theaterList_seat() {
		
		Database database = Database.getInstance();

		for (int i = 0; i < database.tb_theator.length; i++) {

			System.out.println();
			System.out.println(database.tb_theator[i].getTheaterName());
			System.out.print("  ");
			for (int t = 0; t < 10; t++) {
				System.out.print((t + 1) + " ");
			}

			char ch = 'A';
			for (int j = 0; j < database.tb_seat[i].length; j++) {
				if (j % 10 == 0) {
					System.out.println();
					System.out.print(ch++ + " ");
				}

				if (database.tb_seat[i][j].getSeatUse()) {
					System.out.print("■" + " ");
				} else {
					System.out.print("□" + " ");
				}

			}
			System.out.println();
			System.out.println();

		}
	}
}
