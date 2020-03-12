package dao;

import data.Database;
import data.Session;
import vo.ReserveVO;

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
	
	public void reserveTheater_seat_look() { // 로그인 유저가 선택한 영화의 관 보여주기
		Database database = Database.getInstance();
		String id = Session.loginUser.getId();
		ReserveVO reserveVO = new ReserveVO();
		
		for (int i = 0; i < database.tb_reserve.size(); i++) {
			 reserveVO = new ReserveVO();
			reserveVO = database.tb_reserve.get(i);
			if(Session.loginUser.getId().equals(reserveVO.getId())) {
				break;
			}
		}
		
		reserveVO.getTheaterPosition();
		

//		for (int i = 0; i < database.tb_theator.length; i++) 

			System.out.println(database.tb_theator[reserveVO.getTheaterPosition()].getTheaterName());
			System.out.print("  ");
			for (int t = 0; t < 10; t++) {
				System.out.print((t + 1) + " ");
			}

			char ch = 'A';
			for (int j = 0; j < database.tb_seat[reserveVO.getTheaterPosition()].length; j++) {
				if (j % 10 == 0) {
					System.out.println();
					System.out.print(ch++ + " ");
				}

				if (database.tb_seat[reserveVO.getTheaterPosition()][j].getSeatUse()) {
					System.out.print("■" + " ");
				} else {
					System.out.print("□" + " ");
				}

			}
			System.out.println();
			System.out.println();

	}
	
	
	public void selectMovieTheater(int theaterPosition) {
		Database database = Database.getInstance();
		
		System.out.println();
		System.out.print(database.tb_theator[theaterPosition].getTheaterName());
		char ch = 'A';
		for (int j = 0; j < database.tb_seat[theaterPosition].length; j++) {
			if (j % 10 == 0) {
				System.out.println();
				System.out.print(ch++ + " ");
			}

			if (database.tb_seat[theaterPosition][j].getSeatUse()) {
				System.out.print("■" + " ");
			} else {
				System.out.print("□" + " ");
			}

		}
			System.out.println();
			System.out.println();
		}

	

}
