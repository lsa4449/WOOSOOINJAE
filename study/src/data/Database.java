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
	
	{

		UserVO user2 = new UserVO();
		user2.setId("js123");
		user2.setPassword("1234");
		user2.setName("김재석");
		user2.setBirthdate(19950414);
		user2.setCash(0);
		user2.setAuth(false);
		tb_user.add(user2);

	}
	
	{

		UserVO user3 = new UserVO();
		user3.setId("yh123");
		user3.setPassword("1234");
		user3.setName("전영현");
		user3.setBirthdate(19950221);
		user3.setCash(0);
		user3.setAuth(false);
		tb_user.add(user3);

	}
	
	{

		UserVO user4 = new UserVO();
		user4.setId("dn123");
		user4.setPassword("1234");
		user4.setName("김덕년");
		user4.setBirthdate(19950317);
		user4.setCash(0);
		user4.setAuth(false);
		tb_user.add(user4);

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
		movie.setMovieNum(indexno++);
		movie.setMovieName("히트맨");
		movie.setDirector("감독");
		movie.setPlot("줄거리");
		movie.setActor("배우");
		movie.setOpenMovieDate(200301);
		movie.setTheaterNum(0);
		movie.setStartMovieTime("02시 30분");
		movie.setEndMovieTime("04시 50분");
		movie.setAge("청소년 관람 불가");
		movie.setMovieDate("20/02/29");
		tb_movie.add(movie);
		 
		movie = new MovieVO();
		movie.setMovieNum(indexno++);
		movie.setMovieName("조선_명탐정_어쩌구");
		movie.setDirector("감독");
		movie.setPlot("줄거리");
		movie.setActor("이선균");
		movie.setOpenMovieDate(20200120);
		movie.setTheaterNum(1);
		movie.setStartMovieTime("03시 00분");
		movie.setEndMovieTime("05시 00분");
		movie.setAge("청소년 관람 불가");
		movie.setMovieDate("20/02/29");
		tb_movie.add(movie);
		
		movie = new MovieVO();
		movie.setMovieNum(indexno++);
		movie.setMovieName("너의 이름은");
		movie.setDirector("일본인");
		movie.setPlot("몸이 바뀜");
		movie.setActor("배우배우");
		movie.setOpenMovieDate(20200121);
		movie.setTheaterNum(2);
		movie.setStartMovieTime("14시 20분");
		movie.setEndMovieTime("17시 50분");
		movie.setAge("전체 이용가");
		movie.setMovieDate("20/03/01");
		tb_movie.add(movie);
	}
	
	String[][] seat = new String[4][10];
	
	//예약 정보
	public ArrayList<ReserveVO> tb_reserve = new ArrayList<ReserveVO>();
	
	
		
	
	
	
	// 1. 영화관VO(영현)
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
	
	// 4. 좌석(영현)
	public SeatVO[][] tb_seat = new SeatVO[tb_theator.length][40];// 앞에 상영관, 

	{
		SeatVO seat = new SeatVO();
		for (int i = 0; i < tb_theator.length; i++) {// 상영관 번호
			
			seat.setTheaterNum(i);
			for (int j = 0; j < tb_seat[i].length; j++) {// 좌석
				seat = new SeatVO();
				if (j < 10 || j > 30) {
					seat.setSeatNum(j);// 좌석 번호 // 좀 별루인 좌석
					seat.setSeatPrice(9000); // 좌석 가격 // 성인 기준 가격
				} else {
					seat.setSeatNum(j);// 좌석 번호 // 10 ~ 30 // 좋은 좌석
					seat.setSeatPrice(12000); // 좌석 가격 // 성인 기준 가격
				}
				seat.setSeatUse(false);
				tb_seat[i][j] = seat;
			}

		}

	}
	
	//4. 좌석 (이렇게 했으면 더 편했을 듯)
//	public SeatVO[][] tb_Seat = new SeatVO[4][10];

	
	
	
	  
}
