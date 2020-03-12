package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import controller.Controller;
import dao.MovieDao;
import dao.SeatDao;
import dao.UserDao;
import data.Database;
import data.Session;
import vo.MovieVO;
import vo.ReserveSeatVO;
import vo.ReserveVO;
import vo.SeatVO;
import vo.UserVO;

public class MovieService {

	private static MovieService instance;

	public MovieService() {
	}

	public static MovieService getInstance() {
		if (instance == null) {
			instance = new MovieService();
		}
		return instance;
	}

	Database database = Database.getInstance();
	UserDao userDao = UserDao.getInstance();

	// 영화 목록 //영현
	public void movieList() {
		MovieDao movieDao = MovieDao.getInstance();

		ArrayList<MovieVO> movieList = movieDao.selectMovieList();

		System.out.println("---------------------------------");
		System.out.println("번호\t영화이름\t감독\t줄거리\t배우\t개봉일\t시청나이");
		System.out.println("---------------------------------");
		for (int i = movieList.size() - 1; 0 <= i; i--) {
			MovieVO movie = movieList.get(i);
			System.out.println(movie.getMovieNum() + "\t" + movie.getMovieName() + "\t" + movie.getDirector() + "\t"
					+ movie.getPlot() + "\t" + movie.getActor() + "\t" + movie.getOpenMovieDate() + "\t"
					+ movie.getAge() + "\t");
		}
		System.out.println("---------------------------------");
	}

	// 영화 예약(영현)

	// 1번
	/*
	 * public class TheaterVO { 
	 * //상영관VO
	 * 
	 * private int theaterNum; //상영관 번호
	 * private String theaterName; //상영관 이름
	 */

	// 2.영화
	/*
	 * public class MovieVO { // 영화VO
	 * 
	 * private int movieNum; // 영화 번호 
	 * private String movieName; // 영화 이름 
	 * private String director; // 감독 
	 * private String plot; // 줄거리 
	 * private String actor; // 출연진 
	 * private int openMovieDate; // 개봉일 
	 * private String age; // 영화 관람 나이 
	 * private int theaterNum; // 상영관 번호 
	 * private String movieDate; // 상영 날짜 
	 * private int startMovieTime; // 상영 시작 시간 
	 * private int endMovieTime; // 상영 종료 시간
	 */

	// 3. 회원
	/*
	 * public class UserVO { // 회원VO
	 * 
	 * private String id; // 회원 아이디
	 * private String password; // 회원 비밀번호 
	 * private String name; // 회원 이름 
	 * private int birthdate; // 회원 생년월일 
	 * private boolean auth;// 권한
	 */

	// 4. 좌석
	/*
	 * public class SeatVO { //좌석VO
	 * 
	 * private int seatNum; // 좌석 번호 
	 * private int theaterNum; // 상영관 번호 
	 * private String realSeatNum; // 좌석 세부 번호 
	 * private int seatPrice; // 좌석별 가격
	 */

	// 5. 예매
	/*
	 * public class ReserveVO { // 예매VO
	 * 
	 * private int reserveNum; // 예매 번호 
	 * private int timeRound; // 회차 
	 * private String id;// 회원 아이디 
	 * private String reserveDate; // 예매 일자 
	 * private int price; // 예매 가격
	 */

	public void reserveMovie() {
		
		ReserveVO rVO = new ReserveVO();
		SeatDao seatDao = SeatDao.getInstance();
		Scanner s = new Scanner(System.in);

		UserVO user = Session.loginUser;

		int user_year = user.getBirthdate() / 10000;
		int year = Calendar.getInstance().get(Calendar.YEAR); // 현재 날짜의 연도 가져오기
		int month = Calendar.getInstance().get(Calendar.MONTH); // 현재 날짜의 월 가져오기
		int date = Calendar.getInstance().get(Calendar.DATE); // 현재 날짜의 일 가져오기

		if (year - user_year >= 19) {
			userDao.lookup_adult_movie();
		} else {
			userDao.lookup_minor_movie();
		}

		int input = 0;

			boolean check = false;
			boolean result = false;
			
			String movieName;
			String movieDate;
			String startMovieTime;
			
			do {
				
				System.out.println("-------------------------------------");
				System.out.print  ("예매할 영화의 제목을 입력 : ");
				movieName = s.nextLine();
				System.out.print  ("예매할 영화의 상영 날짜(YY/MM/DD)를 입력 : ");
				movieDate = s.nextLine();
				System.out.print  ("예매할 영화의 시작 시간(HH시 mm분)을 입력 : ");
				startMovieTime = s.nextLine();
				
				check = duplicate_check(movieName, movieDate, startMovieTime);
				
				if(!check) {
					System.out.println("입력하신 정보의 영화를 찾을 수 없습니다.");
					System.out.println("다시 입력해주세요.");
				}
				
			}while(check != true);
			
			int index = get_indexno_tb_movie_by_movieName_movieDate_startMovieTime(movieName, movieDate, startMovieTime);
			
			//상영관 번호(자동으로 들어감)
			int theaterPosition = database.tb_movie.get(index).getTheaterNum();
			
			seatDao.selectMovieTheater(theaterPosition); //영화 자리 보여주게 하려고 넣었던거
//			seatDao.theaterList_seat(); // 상영관 좌석 예약 배치도
			
			System.out.print("원하시는 좌석을 선택 : ");
			String seatPosition = s.nextLine();
			char seatPos_1;
			String seatPos_2;
			seatPos_1 = seatPosition.charAt(0);
			seatPos_2 = seatPosition.substring(1);

			int num = 0;

			if (seatPos_1 == 'A') {
				num = 0;
			} else if (seatPos_1 == 'B') {
				num = 10;
			} else if (seatPos_1 == 'C') {
				num = 20;
			} else if (seatPos_1 == 'D') {
				num = 30;
			}
			
			int menu = 0;
			
			System.out.println("----------------------------");
			System.out.println("1. 결제하기");
			System.out.println("2. 취소하기");
			System.out.println("----------------------------");
			System.out.print  ("입력 : ");
			
			menu = Integer.parseInt(s.nextLine());

			if (menu == 1) {
				 result = buy();
				}
			if (menu == 2) {
				
				}
			
			int num2 = num + Integer.parseInt(seatPos_2) - 1;

			if (database.tb_seat[theaterPosition][num2].getSeatUse() == false && result == false) {
				
				database.tb_seat[theaterPosition][num2].setSeatUse(true);
				database.tb_seat[theaterPosition][num2].setLookInfo("■");

				System.out.println();
				System.out.println("영화 표가 예매되었습니다!");

				ReserveVO reserve = new ReserveVO();

				if (year - user_year >= 19) {
					reserve.setPrice(database.tb_seat[theaterPosition][num2].getSeatPrice());
				} else if (9 <= year - user_year && year - user_year <= 18) {
					reserve.setPrice(database.tb_seat[theaterPosition][num2].getSeatPrice() * 0.7);
				}

				reserve.setId(Session.loginUser.getId());
				reserve.setMovieName(movieName);
				reserve.setReserveDate(year + "년 " + month + "월 " + date + "일");
				reserve.setSeatPosition(seatPosition);
				reserve.setTheaterPosition(theaterPosition);
				reserve.setStartMovieTime(startMovieTime);

				database.tb_reserve.add(reserve);

			} else if(database.tb_seat[theaterPosition][num2].getSeatUse() == true) {
				System.out.println("이미 예매된 좌석입니다.");
			}

	}
	
	//영화 예매할 때 입력받는 값 중복체크
	public boolean duplicate_check(String movieName, String movieDate, String startMovieTime) {
		
		boolean check = false;
		
		for(int i = 0; i < database.tb_movie.size(); i++) {
			MovieVO tb_movie = database.tb_movie.get(i);
			if(tb_movie.getMovieName().equals(movieName) && tb_movie.getMovieDate().equals(movieDate) && tb_movie.getStartMovieTime().equals(startMovieTime)) {
				check = true;
				break;
			}else {
				check = false;
			}		
		}		
		return check;		
	}
	
	// 인덱스 가져오기
	public int get_indexno_tb_movie_by_movieName_movieDate_startMovieTime(String movieName, String movieDate, String startMovieTime) {
		
		int indexno = -1;
		for(int i = 0; i < database.tb_movie.size(); i++) {
			MovieVO tb_movie = database.tb_movie.get(i);
			
			if(tb_movie.getMovieName().equals(movieName) && tb_movie.getMovieDate().equals(movieDate) 
					&& tb_movie.getStartMovieTime().equals(startMovieTime)) {
				indexno = i;
				break;
			}else {
				indexno = -1;
			}		
		}		
		return indexno;		
	}

	// 사용자가 고른 영화 인덱스 가져오기 - 재석
	public int find_indexno_tb_movie(String movieName, String movieDate, String startMovieTime) {

		int indexno = -1;

		for (int i = 0; i < database.tb_movie.size(); i++) {
			MovieVO movie_info = database.tb_movie.get(i);

			if (movieName.equals(movie_info.getMovieName()) && movieDate.equals(movie_info.getMovieDate())
					&& startMovieTime.equals(movie_info.getStartMovieTime())) {
				indexno = i;
			}
		}

		return indexno;
	}

	// 영화 예매 구입
		public boolean buy() {
			boolean check = false;
			AdminService adminService = AdminService.getInstance();
			UserService userService = UserService.getInstance();
			UserVO user = Session.loginUser;
			UserVO uVo = database.tb_user.get(adminService.tb_Index(user.getId()));

			// 성인요금, 청소년요금
			int adult_seat = database.tb_seat[0][0].getSeatPrice();
			double child_seat = database.tb_seat[0][0].getSeatPrice() * 0.7;

			int user_year = user.getBirthdate() / 10000;
			int year = Calendar.getInstance().get(Calendar.YEAR); // 현재 날짜의 연도 가져오기

			if (year - user_year >= 19) {
				if (uVo.getCash() < adult_seat) {
					System.out.println("돈이 부족합니다. 현금을 충전 해주세요!");
					check = true;
	 
				} else if (9 <= year - user_year && year - user_year <= 18) {
					if (uVo.getCash() < child_seat) {
						System.out.println("돈이 부족합니다. 현금을 충전 해주세요!");
						check = true;
						}
				}
				
			}
			
			int IndexNo = adminService.tb_Index(user.getId());
			UserVO db_id = database.tb_user.get(IndexNo);

		if (year - user_year >= 19) {
			if (db_id.getCash() - adult_seat >= 0) {
				db_id.setCash(db_id.getCash() - adult_seat);
				System.out.println("결제 되었습니다.");
				System.out.println(uVo.getName() + "님의 현재 잔액 : " + db_id.getCash());

			} 
		} else if (db_id.getCash() - child_seat >= 0) {
			if (9 <= year - user_year && year - user_year <= 18) {
				db_id.setCash(db_id.getCash() - child_seat);
				System.out.println("결제 되었습니다.");
				System.out.println(uVo.getName() + "님의 현재 잔액 : " + db_id.getCash());
				} 
			}
		return check;
		}
		//예매 취소(영현)
	public void reserveCancel() {
		int no = 1;

		System.out.println("—————————————————");
		for (int i = 0; i < database.tb_reserve.size(); i++) {

			ReserveVO tb_reserve = database.tb_reserve.get(i);

			System.out.println("[ " + no + " ]");
			System.out.println("예매 아이디 : " + tb_reserve.getId());
			System.out.println("예매 날짜 : " + tb_reserve.getReserveDate());
			System.out.println("예매 영화 이름 : " + tb_reserve.getMovieName());
			System.out.println("상영 시작 시간 : " + tb_reserve.getStartMovieTime());
			System.out.println("가격 : " + tb_reserve.getPrice());
			System.out.println("상영관 : " + tb_reserve.getTheaterPosition());
			System.out.println("좌석 위치 : " + tb_reserve.getSeatPosition());
			System.out.println("—————————————————");

			no++;
		}
			ReserveVO rVO = new ReserveVO();
			SeatDao seatDao = SeatDao.getInstance();
			
			Scanner s = new Scanner(System.in);

			UserVO user = Session.loginUser;
			
			System.out.println("취소할 영화 제목을 입력해주세요");
			String name = s.nextLine();
			
			System.out.println("영화 시작시간을 입력해주세요");
			String time = s.nextLine();
			
			System.out.println("자리를 입력해주세요");
			String replayseat = s.nextLine();
			
			int i = 0;
			
			for (i = 0; i < database.tb_reserve.size(); i++) { // 예약 테이블
				rVO = new ReserveVO();
				rVO = database.tb_reserve.get(i);
				
				if(name.equals(rVO.getMovieName()) && user.equals(rVO.getId()) 
						&& time.equals(rVO.getStartMovieTime()) && replayseat.equals(rVO.getSeatPosition())) {
					break;
				}
				
			}
			
			int theaterPosition = rVO.getTheaterPosition(); // 영화관 번호
			String seatPosition = rVO.getSeatPosition(); // 좌석 번호
			//String으로 받은 영화 좌석
			
			char seatPos_1;
			String seatPos_2;
			seatPos_1 = seatPosition.charAt(0);
			seatPos_2 = seatPosition.substring(1);

			int num = 0;

			if (seatPos_1 == 'A') {
				num = 0;
			} else if (seatPos_1 == 'B') {
				num = 10;
			} else if (seatPos_1 == 'C') {
				num = 20;
			} else if (seatPos_1 == 'D') {
				num = 30;
			}
			//좌석 숫자로 변환
			int num2 = num + Integer.parseInt(seatPos_2) - 1;
			
			SeatVO sVO = new SeatVO();
			sVO.setSeatNum(num2);
			sVO.setTheaterNum(theaterPosition);
			
			sVO.setSeatUse(false);// 빈네모로 바꿔줌
			sVO.setLookInfo("□");
			
			System.out.println("영화표가 취소 되었습니다.");
			
			database.tb_seat[theaterPosition][num2] = sVO;
			database.tb_reserve.remove(i-1);

			seatDao.reserveTheater_seat_look();

		}

		//영화 예매 조회
		public void lookup_reservation() {
			
			Scanner s = new Scanner(System.in);
			
			int no = 1;
			
			System.out.println("-----------------------------------");
			for(int i = 0; i < database.tb_reserve.size(); i++) {
				
				ReserveVO tb_reserve = database.tb_reserve.get(i);
				
				System.out.println("[ " + no + " ]");
				System.out.println("예매 아이디 : " + tb_reserve.getId());
				System.out.println("예매 날짜 : " + tb_reserve.getReserveDate());
				System.out.println("예매 영화 이름 : " + tb_reserve.getMovieName());
				System.out.println("가격 : " + tb_reserve.getPrice());
				System.out.println("상영관 : " + tb_reserve.getTheaterPosition());
				System.out.println("좌석 위치 : " + tb_reserve.getSeatPosition());
				System.out.println("-----------------------------------");
				
				no++;
			}
			
			System.out.println("0. 뒤로 가기");
			
			int input = 0;
			
			do {
				
				System.out.print  ("입력 : ");
				input = Integer.parseInt(s.nextLine());
				
				if(input != 0) {
					System.out.println("잘못된 번호를 입력하셨습니다.");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("다시 입력해주세요.");
				}
				
			}while(input != 0);
			
			System.out.println("잠시 후 메뉴로 이동합니다.");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
}
