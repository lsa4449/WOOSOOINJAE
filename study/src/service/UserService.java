package service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

import dao.UserDao;
import data.Session;
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
			
//			if (user.getAuth()) { // admin
//				
//				AfterAdminLogin();
//			}else { // 일반 유저
//				
//			}
		}
		return Check;
	}
	
	//회원 정보 조회 
	public void userInfoView() {
		
		UserVO user = null;
		
		System.out.println(user.getName() + "회원님의 아이디 : " + user.getId());
		System.out.println(user.getName() + "회원님의 비밀번호 : " + user.getPassword());		
	    System.out.println(user.getName() +"회원님의 생년월일 : " + user.getBirthdate());
	    System.out.println("───────────────────────────────────────────────────────");
   }
	
	//비밀 번호 수정
	public void userPwModify() {
		UserVO param = new UserVO();
		
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
	
	

}
