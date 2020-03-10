package service;

import java.util.ArrayList;
import java.util.Scanner;

import dao.MovieDao;
import data.Database;
import vo.MovieVO;

public class MovieService {
	
	private static MovieService instance;
	
	MovieService() {};
	
	static {
		instance = new MovieService();
	}
	
		
		//영화 목록 //영현
		public void movieList(){
			MovieDao movieDao = MovieDao.getInstance();
			
			ArrayList<MovieVO> movieList = movieDao.selectMovieList();
			
			System.out.println("---------------------------------");
			System.out.println("번호\t영화이름\t감독\t줄거리\t배우\t개봉일\t시청나이");
			System.out.println("---------------------------------");
			for(int i = movieList.size() - 1; 0 <= i; i--) {
				MovieVO movie = movieList.get(i);
				System.out.println(movie.getMovieNum() + "\t" + movie.getMovieName() + "\t" + movie.getDirector() + "\t" + movie.getPlot() + "\t"+ movie.getActor() + "\t"+ movie.getOpenMovieDate() + "\t"+ movie.getAge() + "\t");
			}
			System.out.println("---------------------------------");
		}
		
		// 영화 예약(영현)
		
		// 1번
/*		public class TheaterVO {
		//상영관VO
		
		private int theaterNum; //상영관 번호
		private String theaterName; //상영관 이름	
	*/

		// 2.영화
/*		public class MovieVO {
		// 영화VO

		private int movieNum; // 영화 번호  
		private String movieName; // 영화 이름
		private String director; // 감독
		private String plot; // 줄거리
		private String actor; // 출연진
		private int openMovieDate; // 개봉일
		private String age; // 영화 관람 나이
		private int theaterNum; // 상영관 번호
		private String movieDate; // 상영 날짜
		private int startMovieTime; // 상영 시작 시간
		private int endMovieTime; // 상영 종료 시간 
		*/
		
		//3. 회원
/*		public class UserVO {
			// 회원VO
		  
			private String id; // 회원 아이디
			private String password; // 회원 비밀번호
			private String name; // 회원 이름
			private int birthdate; // 회원 생년월일
			private boolean auth; // 권한
			*/
		

		// 4. 좌석
/*		public class SeatVO {
			//좌석VO
			
			private int seatNum; // 좌석 번호
			private int theaterNum; // 상영관 번호
			private String realSeatNum; // 좌석 세부 번호
			private int seatPrice; // 좌석별 가격
		*/

		// 5. 시간
/*		public class TimeVO {
			// 시간VO

			private int timeRound; // 회차
			private int movieNum; // 영화 번호
			private int theaterNum; // 상영관 번호
			private String movieDate; // 상영 날짜
			private int startMovieTime; // 상영 시작 시간
			private int endMovieTime; // 상영 종료 시간
 			*/

		// 6. 예매
/*		public class ReserveVO {
			// 예매VO

			private int reserveNum; // 예매 번호
			private int timeRound; // 회차
			private String id;// 회원 아이디
			private String reserveDate; // 예매 일자
			private int price; // 예매 가격
		*/		
		
		public void theaterList() {
			for (int i = 0; i < ; i++) {
				
				
			}
		}
		

		
}
