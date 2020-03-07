package data;

import java.util.ArrayList;

import vo.MovieVO;
import vo.UserVO;

public class Database {

	private static Database instance;

	public Database() {
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
		admin.setAuth(true);
		tb_user.add(admin);
	}
	
	{

		UserVO user = new UserVO();
		user.setId("sua");
		user.setPassword("1234");
		user.setName("이수아");
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
		movie.setDirector("코미디, 액션");
		movie.setPlot("최원섭");
		movie.setActor("누구지?");
		movie.setOpenMovieDate(20200120);
		movie.setAge(19);
		tb_movie.add(movie);
		
		movie = new MovieVO();
		movie.setMovieNum(indexno++); // 영화번호 1
		movie.setMovieName("조선 명탐정 어쩌구");
		movie.setDirector("코미디, 사극");
		movie.setPlot("이선균 아는 배우");
		movie.setActor("이선균");
		movie.setOpenMovieDate(20200120);
		movie.setAge(19);
		tb_movie.add(movie);
	}
}
