package service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

import dao.UserDao;
import data.Database;
import data.Session;
import vo.MovieVO;
import vo.ReserveVO;
import vo.UserVO;

public class UserService {
	
	private static UserService instance;
	
	
	private UserService(){}
	
	public static UserService getInstance(){
		if(instance == null){
			instance = new UserService();
		}
		return instance;
	}
	
	
	UserDao userDao = UserDao.getInstance();
	Database database = Database.getInstance();
	AdminService adminService = AdminService.getInstance();
	
	//회원가입
	public void join(){
		Scanner s = new Scanner(System.in);
		
		String id = null;
		UserVO idCheck = null;
		do{
			System.out.print("아이디 : ");
			id = s.nextLine();
			
			HashMap<String, String> param = new HashMap<>();
			param.put("ID", id);
			idCheck = userDao.selectUser(param);
			if(idCheck != null){
				System.out.println("사용할 수 없는 아이디입니다.");
			}
		}while(idCheck != null);
		
		System.out.print("비밀번호 : ");
		String password = s.nextLine();
		
		System.out.print("이름 : ");
		String name = s.nextLine();
		
		System.out.print("생년원일(ex)20202002) : ");
		int birthdate = Integer.parseInt(s.nextLine());
		
		UserVO user = new UserVO();
		
		user.setId(id);
		user.setPassword(password);
		user.setName(name);
		user.setBirthdate(birthdate);
		
		userDao.insertUser(user);
		System.out.println("가입해주셔서 감사합니다.");
	}
	
	//로그인
	public int login(){
		Scanner s = new Scanner(System.in);
		
		
		System.out.print("아이디 : ");
		String id = s.nextLine();
		System.out.print("비밀번호 : ");
		String password = s.nextLine();
		
		HashMap<String, String> param = new HashMap<>();
		param.put("ID", id);
		param.put("PASSWORD", password);
		
		UserVO user = userDao.selectUser(param);
		
		//SortUser(id);
		
		int Check = 0;
		//관리자 : 1, 일반 : 2, 실패 : 3
		
		if(user == null){ //수정 (재석)
			Check = 3;
			System.out.println("아이디 혹은 비밀번호를 잘못 입력하셨습니다.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else {
			if(user.getAuth() == true) {
				Check = 1;
				System.out.println("관리자 계정으로 로그인하였습니다.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(user.getAuth() == false) {
				Check = 2;
				System.out.println("로그인에 성공하였습니다.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(user.getName() + "님 환영합니다.");
			}
			
			Session.loginUser = user;
			
		}
		return Check;
	}
	
	//회원 정보 조회 
	public void userInfoView() {
		
		UserVO userVo  = Session.loginUser;
		UserVO uVo = database.tb_user.get(adminService.tb_Index(userVo.getId()));
		
		System.out.println(userVo.getName() + "회원님의 아이디 : " + userVo.getId());
		System.out.println(userVo.getName() + "회원님의 비밀번호 : " + userVo.getPassword());		
	    System.out.println(userVo.getName() + "회원님의 생년월일 : " + userVo.getBirthdate());
	    System.out.println(userVo.getName() + "회원님의 소지 현금 : " + uVo.getCash());
   }
	
	//비밀 번호 수정
	public void userPwModify() {
		UserVO userVo  = Session.loginUser;
		Scanner in = new Scanner(System.in);
	    String input = "";
	    
	    System.out.print("변경할 비밀번호를 입력하세요 >>");
	    input = in.nextLine();
	  
		UserVO params = new UserVO();
		params.setId(userVo.getId());
		params.setPassword(input);
		
		if(userDao.userPwChange(params)) {
			 System.out.println("──────────────────");
	         System.out.println("변경에 성공하였습니다.");
	         System.out.println("──────────────────");
	      } else {
	         System.out.println("──────────────────");
	         System.out.println("변경에 실패하였습니다.");
	         System.out.println("──────────────────");
	      }
	}
	
	
	//이름 수정
	public void userNameModify() {
		UserVO userVo  = Session.loginUser;
		Scanner in = new Scanner(System.in);
	    String input = "";
	    
	    System.out.print("변경할 이름을 입력하세요 >>");
	    input = in.nextLine();
	    
		UserVO params = new UserVO();
		params.setId(userVo.getId());
		params.setName(input);
		
		if(userDao.userNameChange(params)) {
			 System.out.println("──────────────────");
	         System.out.println("변경에 성공하였습니다.");
	         System.out.println("──────────────────");
	      } else {
	         System.out.println("──────────────────");
	         System.out.println("변경에 실패하였습니다.");
	         System.out.println("──────────────────");
	      }
	}
		
	//생년월일 수정
	public void userBirthModify() {
		UserVO userVo  = Session.loginUser;
		Scanner in = new Scanner(System.in);
	    int input;
	    
	    System.out.print("변경할 생년월일을 입력하세요 >>");
	    input = Integer.parseInt(in.nextLine());
	    
		UserVO params = new UserVO();
		params.setId(userVo.getId());
		params.setBirthdate(input);
		
		if(userDao.userBirthChange(params)) {
			 System.out.println("──────────────────");
	         System.out.println("변경에 성공하였습니다.");
	         System.out.println("──────────────────");
	      } else {
	         System.out.println("──────────────────");
	         System.out.println("변경에 실패하였습니다.");
	         System.out.println("──────────────────");
	      }
	}
		
	//회원 탈퇴
	public void userInfoRemove() {
		
		UserVO userVo  = Session.loginUser;
		String id = userVo.getId();
		adminService.tb_Index(id);
		database.tb_user.remove(adminService.tb_Index(id));
		
		System.out.println("탈퇴 하였습니다....");
		
		
	}
	
	//영화 목록 조회 - 나이에 맞는 것만 조회(재석)
	public void lookup_movie() {
		Scanner s = new Scanner(System.in);
		
		UserVO user = Session.loginUser;

		int user_year = user.getBirthdate() / 10000;
		int year = Calendar.getInstance().get(Calendar.YEAR); //현재 날짜의 연도 가져오기
		
		int input = 0;
		
		if(year - user_year >= 19) {
			userDao.lookup_adult_movie();
			
			System.out.println("0. 뒤로 가기");
			System.out.println("---------------------------------");
			
			
			do {
				
				System.out.print  ("입력 : ");
				input = Integer.parseInt(s.nextLine());
				
				
				
				if(input != 0) {
					System.out.println("잘못된 번호를 입력하였습니다.");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("---------------------------------");
				}
				
			}while(input != 0);
			System.out.println("잠시 후 이동됩니다.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}else {
			userDao.lookup_minor_movie();
			
			System.out.println("0. 뒤로 가기");
			System.out.println("---------------------------------");
			
			do {
				
				System.out.print  ("입력 : ");
				input = Integer.parseInt(s.nextLine());
				
				if(input != 0) {
					System.out.println("잘못된 번호를 입력하였습니다.");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("---------------------------------");
				}
				
			}while(input != 0);
			System.out.println("잠시 후 이동됩니다.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	// 재석
	public int find_indexno_tb_movie(String movieName, String movieDate, String startMovieTime) {
		
		int indexno = -1;
		
		for(int i = 0; i < database.tb_movie.size(); i++) {
			MovieVO movie_info = database.tb_movie.get(i);
			
			if(movieName.equals(movie_info.getMovieName()) && movieDate.equals(movie_info.getMovieDate()) && startMovieTime.equals(movie_info.getStartMovieTime())) {
				indexno = i;
			}
		}
		
		return indexno;
	}
	
	public void reserveMovie() {
		
		//Session.loginUser;// 회원의 현재 로그인 계정
		
		// 관 고르기
		// 좌석 고르기(좌석별 금액), 영화 금액
		// 관람자의 보유 금액
		// 예약
		// 예약 취소
		
	}
	
	//현금 충전
		public void cash() {	
			
			UserVO user = Session.loginUser;
			Database database = Database.getInstance();
			UserVO uVo = database.tb_user.get(adminService.tb_Index(user.getId()));
			
			boolean userCash = cashAdd();
			if(userCash == true) {
				System.out.println("충전 실패!");

			} else {
			System.out.println("충전이 완료 되었습니다!");
			System.out.println("현재 잔액은 : " + uVo.getCash() + "입니다.");
		}
	}

		//현금 충전
		public boolean cashAdd() {
			
			boolean cashCheck = false;
			UserVO user = Session.loginUser;
			Database database = Database.getInstance();
			UserVO uVo = database.tb_user.get(adminService.tb_Index(user.getId()));
			
		      while(true){
		    	 Scanner s = new Scanner(System.in);
		         try{
		        	System.out.print("충전할 현금을 입력하세요 >>");
	      		int input = s.nextInt();
	      		if(input < 0){
	      			System.out.print("범위를 벗어났습니다. 다시 입력하세요! >>");
	      			cashCheck = true;
	 
	      		} else if(input > 1000000) {
	      			System.out.println("최대 100만원 까지 입니다.");
	     			cashCheck = true;
	     			      		
	      		} else {
	      			uVo.setCash(uVo.getCash() + input);
	      		}
	      		

		         } catch(Exception e){
		        	s = new Scanner(System.in);
		        	System.out.println("^^ \n그만큼의 현금이 있어보이진 않네요");
		        	System.out.println();
		            continue;
		         }
		         break;
		      }
			return cashCheck;
		}
		
	

		
}
