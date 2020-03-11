package dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;

import data.Database;
import data.Session;
import vo.MovieVO;
import vo.UserVO;
   
public class UserDao {

		private static UserDao instance; // 객체 공유 -> 똑같은 값 -> 메모리 낭비가 덜 된다. 
		
		private UserDao(){}
		
		public static UserDao getInstance(){
			if(instance == null){
				instance = new UserDao();
			}
			return instance;
		}
		
		Database database = Database.getInstance();
		
		public void insertUser(UserVO user){
			database.tb_user.add(user);
		}

		public UserVO selectUser(HashMap<String, String> param) {
			UserVO rtnUser = null;
			for(int i = 0; i < database.tb_user.size(); i++) {
				UserVO user = database.tb_user.get(i);
				boolean flag = true;
				for(String key : param.keySet()){
					String value = param.get(key);
					if(key.equals("ID")){
						if(!user.getId().equals(value)) flag = false;
					}else if(key.equals("PASSWORD")){
						if(!user.getPassword().equals(value)) flag = false;
					}else if(key.equals("NAME")){
						if(!user.getName().equals(value)) flag = false;
					}
				}
				if(flag) rtnUser = user;
			}
			return rtnUser;
		}

		public ArrayList<UserVO> selectUserList() {
			return database.tb_user;
		}
		
		//영화 조회(성인) (재석)
		public void lookup_adult_movie() {
			
			int movie_no = 1;
			
			System.out.println("[영화 목록]");
			System.out.println("---------------------------------");
			for(int i = 0; i < database.tb_movie.size(); i++) {
				MovieVO movie = database.tb_movie.get(i);
				System.out.println("[ " + movie_no + " ]");
				System.out.println("제목 : " + movie.getMovieName() + "( " + movie.getAge() + " )");
				System.out.println("감독 : " + movie.getDirector());
				System.out.println("배우 : " + movie.getActor());
				System.out.println("개봉일 : " + movie.getOpenMovieDate());
				System.out.println("상영 날짜 : " + movie.getMovieDate());
				System.out.println("상영 시작 시간 : " + movie.getStartMovieTime());
				System.out.println("상영 종료 시간 : " + movie.getEndMovieTime());
				System.out.println("줄거리 : " + movie.getPlot());
				
				movie_no++;
			}
			System.out.println("---------------------------------");
		}
		
		//영화 조회(미성년자) (재석)
		public void lookup_minor_movie() {

			int movie_no = 1;
			
			System.out.println("[영화 목록]");
			System.out.println("---------------------------------");
			for(int i = 0; i < database.tb_movie.size(); i++) {
				MovieVO movie = database.tb_movie.get(i);
				
				if(movie.getAge().equals("전체 이용가")) {
					System.out.println("[ " + movie_no + " ]");
					System.out.println("제목 : " + movie.getMovieName() + "( " + movie.getAge() + " )");
					System.out.println("감독 : " + movie.getDirector());
					System.out.println("배우 : " + movie.getActor());
					System.out.println("개봉일 : " + movie.getOpenMovieDate());
					System.out.println("상영 날짜 : " + movie.getMovieDate());
					System.out.println("상영 시작 시간 : " + movie.getStartMovieTime());
					System.out.println("상영 종료 시간 : " + movie.getEndMovieTime());
					System.out.println("줄거리 : " + movie.getPlot());
					
					movie_no++;
				}
			}
			System.out.println("---------------------------------");
		}
		
		//회원 비밀번호 수정
		  public boolean userPwChange(UserVO params){
		      String id = params.getId();
		      for(UserVO uVo : database.tb_user){
		         if(uVo.getId().equals(id)){
		        	 uVo.setPassword(params.getPassword());
		            return true;
		         }
		      }
		      return false;
		   }
		  
		//회원 이름 수정
		  public boolean userNameChange(UserVO params){
		      String id = params.getId();
		      for(UserVO uVo : database.tb_user){
		         if(uVo.getId().equals(id)){
		        	 uVo.setName(params.getName());
		            return true;
		         }
		      }
		      return false;
		   }
		  
		  //회원 생년월일 수정
		  public boolean userBirthChange(UserVO params){
		      String id = params.getId();
		      for(UserVO uVo : database.tb_user){
		         if(uVo.getId().equals(id)){
		        	 uVo.setBirthdate(params.getBirthdate());
		            return true;
		         }
		      }
		      return false;
		   }
}