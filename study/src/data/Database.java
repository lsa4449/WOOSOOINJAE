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
		movie.setDirector("감독");
		movie.setPlot("줄거리");
		movie.setActor("배우");
		movie.setOpenMovieDate(20200120);
		movie.setAge("청소년 관람 불가");
		tb_movie.add(movie);
		 
		movie = new MovieVO();
		movie.setMovieNum(indexno++); // 영화번호 1
		movie.setMovieName("조선 명탐정 어쩌구");
		movie.setDirector("감독");
		movie.setPlot("줄거리");
		movie.setActor("이선균");
		movie.setOpenMovieDate(20200120);
		movie.setAge("청소년 관람 불가");
		tb_movie.add(movie);
	}
	
	//비밀번호 수정
	  public boolean userPwChange(UserVO params){
	      String id = params.getId();
	      for(UserVO uVo : tb_user){
	         if(uVo.getId().equals(id)){
	        	 uVo.setPassword(params.getPassword());
	            return true;
	         }
	      }
	      return false;
	   }
}
