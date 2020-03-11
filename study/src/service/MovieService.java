package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

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

	private MovieService() {
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
	 * public class TheaterVO { //상영관VO
	 * 
	 * private int theaterNum; //상영관 번호 private String theaterName; //상영관 이름
	 */

	// 2.영화
	/*
	 * public class MovieVO { // 영화VO
	 * 
	 * private int movieNum; // 영화 번호 private String movieName; // 영화 이름 private
	 * String director; // 감독 private String plot; // 줄거리 private String actor; //
	 * 출연진 private int openMovieDate; // 개봉일 private String age; // 영화 관람 나이 private
	 * int theaterNum; // 상영관 번호 private String movieDate; // 상영 날짜 private int
	 * startMovieTime; // 상영 시작 시간 private int endMovieTime; // 상영 종료 시간
	 */

	// 3. 회원
	/*
	 * public class UserVO { // 회원VO
	 * 
	 * private String id; // 회원 아이디 private String password; // 회원 비밀번호 private
	 * String name; // 회원 이름 private int birthdate; // 회원 생년월일 private boolean auth;
	 * // 권한
	 */

	// 4. 좌석
	/*
	 * public class SeatVO { //좌석VO
	 * 
	 * private int seatNum; // 좌석 번호 private int theaterNum; // 상영관 번호 private
	 * String realSeatNum; // 좌석 세부 번호 private int seatPrice; // 좌석별 가격
	 */

	// 5. 예매
	/*
	 * public class ReserveVO { // 예매VO
	 * 
	 * private int reserveNum; // 예매 번호 private int timeRound; // 회차 private String
	 * id;// 회원 아이디 private String reserveDate; // 예매 일자 private int price; // 예매 가격
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

		System.out.print("영화 제목을 입력해주세요 >> ");
		rVO.setMovieName(s.nextLine());

		int input = 0;

		if (year - user_year >= 19) {
			userDao.lookup_adult_movie();
			System.out.print("예매할 영화의 제목을 입력 : ");
			String movieName = s.nextLine();
			System.out.print("예매할 영화의 상영 날짜(YY/MM/DD)를 입력 : ");
			String movieDate = s.nextLine();
			System.out.print("예매할 영화의 시작 시간(HH시 mm분)을 입력 : ");
			String startMovieTime = s.nextLine();

			seatDao.theaterList_seat(); // 상영관 좌석 예약 배치도
			System.out.print("원하시는 상영관을 입력 : ");
			String theaterPosition = s.nextLine();
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

			int menu;
			System.out.println("----------------------------");
			System.out.println("1. 결제하기");
			System.out.println("2. 취소하기");
			System.out.println("----------------------------");
			System.out.print  ("입력 : ");
			menu = Integer.parseInt(s.nextLine());

			if (menu == 1) {
				buy();
			}
			if (menu == 2) {

			}

			int num2 = num + Integer.parseInt(seatPos_2) - 1;

			if (database.tb_seat[seatPos_1][num2].getSeatUse() == false) {
				database.tb_seat[seatPos_1][num2].setSeatUse(true);

				System.out.println("예매되었습니다.");

				ReserveVO reserve = new ReserveVO();

				if (year - user_year >= 19) {
					reserve.setPrice(database.tb_seat[seatPos_1][num2].getSeatPrice());
				} else if (9 <= year - user_year && year - user_year <= 18) {
					reserve.setPrice(database.tb_seat[seatPos_1][num2].getSeatPrice() * 0.7);
				}

				reserve.setId(Session.loginUser.getId());
				reserve.setMovieName(movieName);
				reserve.setReserveDate(year + "년 " + month + "월 " + date + "일");
				reserve.setSeatPosition(seatPosition);

				database.tb_reserve.add(reserve);

			} else {
				System.out.println("이미 예매된 좌석입니다.");
			}

//				database.tb_seat[Integer.parseInt(theaterPosition)][num2] = database.tb_seat[Integer.parseInt(theaterPosition)][num2];

		}

	}

	// 재석
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
	public void buy() {
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
				userService.cash();
				return;
 
			} else if (9 <= year - user_year && year - user_year <= 18) {
				if (uVo.getCash() < child_seat) {
					System.out.println("돈이 부족합니다. 현금을 충전 해주세요!");
					userService.cash();
					return;
				}
			}
		}
		if (year - user_year >= 19) {
			uVo.getCash() -= adult_seat;
			System.out.println("결제 되었습니다.");
		} else if (9 <= year - user_year && year - user_year <= 18) {
			uVo.getCash() -= child_seat;
			System.out.println("결제 되었습니다.");

		}
	}

}
