package data;

import java.util.ArrayList;

import vo.MovieVO;
import vo.SeatVO;
import vo.ReserveVO;
import vo.TheaterVO;
import vo.UserVO;

public class Database {

	private static Database instance;

	private Database() {
	}

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	public ArrayList<UserVO> tb_user = new ArrayList<>();

	{
		UserVO admin = new UserVO();
		admin.setId("admin");
		admin.setPassword("admin");
		admin.setName("관리자");
		admin.setBirthdate(20200101);
		admin.setCash(9999999);
		admin.setAuth(true);
		tb_user.add(admin);
	}
	
	{

		UserVO user = new UserVO();
		user.setId("sua");
		user.setPassword("1234");
		user.setName("이수아");
		user.setBirthdate(19960714);
		user.setCash(0);
		user.setAuth(false);
		tb_user.add(user);

	}
	
	public ArrayList<MovieVO> tb_movie = new ArrayList<MovieVO>(); //영화
	
	{
	/*	private int movieNum; // 영화 번호 // 생성자 만들어서 생성자에서 ++ 되도록?
		private String movieName; // 영화 이름
		private String director; // 감독
		private String plot; // 줄거리
		private String actor; // 출연진
		private int openMovieDate; // 개봉일
		private int age; // 영화 관람 나이
	*/
		
		int indexno = 1; // 이렇게 줘서 값을 인덱스 값을 커지게 하자
		
		MovieVO movie = new MovieVO();
		movie.setMovieNum(indexno++); // 영화번호
		movie.setMovieName("히트맨");
		movie.setDirector("감독");
		movie.setPlot("줄거리");
		movie.setActor("배우");
		movie.setOpenMovieDate(20200120);
		movie.setAge("청소년 관람 불가");
		tb_movie.add(movie);
		 
		movie = new MovieVO();
		movie.setMovieNum(indexno++); // 영화번호 1
		movie.setMovieName("조선_명탐정_어쩌구");
		movie.setDirector("감독");
		movie.setPlot("줄거리");
		movie.setActor("이선균");
		movie.setOpenMovieDate(20200120);
		movie.setAge("청소년 관람 불가");
		tb_movie.add(movie);
	}
	
	String[][] seat = new String[4][10];
	
	//예약 정보
	public ArrayList<ReserveVO> tb_reserve = new ArrayList<ReserveVO>();
	
	
		
	
	
	
	// 1. 영화관VO
	public TheaterVO[] tb_theator = new TheaterVO[3];
	{
		TheaterVO theator = new TheaterVO();
		
		theator.setTheaterNum(0); // 0번 관
		theator.setTheaterName("대덕");
		tb_theator[0] = theator;
		
		theator = new TheaterVO();
		theator.setTheaterNum(1);
		theator.setTheaterName("인재");
		tb_theator[1] = theator;
		
		theator = new TheaterVO();
		theator.setTheaterNum(2);
		theator.setTheaterName("개발원");
		tb_theator[2] = theator;
	}
	
	// 4. 좌석
	public SeatVO[][] tb_seat = new SeatVO[tb_theator.length][40];// 앞에 상영관, 

	{
		SeatVO seat = new SeatVO();
		for (int i = 0; i < tb_theator.length; i++) {// 상화관 번호
			
			seat.setTheaterNum(i);
			for (int j = 0; j < tb_seat[i].length; j++) {// 좌석
				seat = new SeatVO();
				if (j < 10 || j > 30) {
					seat.setSeatNum(j);// 좌석 번호 // 좀 별루인 좌석
					seat.setSeatPrice(9000); // 좌석 가격 // 성인 기준 가격
				} else {
					seat.setSeatNum(j);// 좌석 번호 // 10 ~ 30 // 좋은 좌석
					seat.setSeatPrice(11000); // 좌석 가격 // 성인 기준 가격
				}
				seat.setSeatUse(false);
				tb_seat[i][j] = seat;
			}

		}

	}
	
	
	
	
	
	
	  
}
