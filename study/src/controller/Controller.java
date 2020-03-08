package controller;

import java.util.Scanner;

import dao.UserDao;
import data.Database;
import data.MovieList;
import data.Session;
import service.AdminService;
import service.UserService;
import vo.MovieVO;
import vo.UserVO;

public class Controller {
	
	public static void Enter(int input) {
		for(int i = 0; i <= input; i++) {
			System.out.println();
		}
	}

	public static void main(String[] args) {
		
		/*
		 * 조 소개 > 주제 소개 > 주제 선정 배경 > 프로그램 구조 > 시연
		 * 발표자 1명, ppt 및 시연 1명
		 * 
		 * Controller : 메뉴 선택
		 * Service : 메뉴 기능 수행
		 * Dao : 데이터베이스 접속
		 * VO : 데이터를 담는 클래스
		 * 
		 * 회원가입	로그인	회원목록
		 * 
		 * 정보입력	정보입력	정보출력
		 * 
		 * DB저장	DB조회	DB조회
		 * 
		 * 데이터베이스
		 * 
		 */
		
		new Controller().start();
		
	}
	
	UserService userService = UserService.getInstance();
	AdminService adminService = new AdminService();

	private void start() {
		Enter(50);
		Scanner s = new Scanner(System.in);
		
		int menu;
		
		do{
			System.out.println("-------------- 메뉴 -------------- ");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("0. 프로그램 종료");
			System.out.println("---------------------------------");
			System.out.print  ("입력 : ");
			
			menu = Integer.parseInt(s.nextLine());
			
			if(menu == 1) {
				userService.join();
			}
			if(menu == 2) {
				int result = userService.login();
				
				if(result == 1) {
					AfterAdminLogin();
				}
				if(result == 2) {
					AfterLogin();
				}
				if(result == 3) {
					userService.login();
				}
			}
		}while(menu != 0);
		System.out.println("잠시 후 프로그램이 종료됩니다.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	//----------------------------------- 관리자 --------------------------------------------
	
	//관리자 로그인 후 첫 화면
	public void AfterAdminLogin() {
		Enter(50);
		Scanner s = new Scanner(System.in);
		
		int menu;
		
		do{
			System.out.println("-------------- 메뉴 -------------- ");
			System.out.println("1. 회원 관리");
			System.out.println("2. 영화 관리");
			System.out.println("0. 로그 아웃");
			System.out.println("---------------------------------");
			System.out.print  ("메뉴에 해당하는 번호 입력>");
			
			menu = Integer.parseInt(s.nextLine());
			
			if(menu == 1) {
				user_management();
			}
			if(menu == 2) {
				movie_management();
			}
		}while(menu != 0);
		
		System.out.println("로그 아웃 되었습니다.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("잠시 후 로그인 화면으로 이동합니다.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void user_management() { //회원 관리 (재석)
		Scanner s = new Scanner(System.in);
		
		int input = 0;
		
		do {
			
			System.out.println("-------------- 메뉴 -------------- ");
			System.out.println("1. 회원 등록");
			System.out.println("2. 회원 삭제");
			System.out.println("3. 회원 권한 부여");
			System.out.println("4. 회원 목록 조회");
			System.out.println("0. 뒤로 가기");
			System.out.println("---------------------------------");
			System.out.print  ("입력 : ");
			
			input = Integer.parseInt(s.nextLine());
			
			if(input == 1) { //회원 등록 (일반 회원가입과는 다르게 등록 시에 권한 부여도 가능)
				adminService.adduser();
			}
			if(input == 2) { //회원 삭제
				adminService.delete_user();
			}
			if(input == 3) {
				adminService.authorization();
			}
			if(input == 4) {
				adminService.lookup_user();
			}
			
		}while(input != 0);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void movie_management() { //영화 관리 (재석)
		Scanner s = new Scanner(System.in);
		
		int input = 0;
		
		do {
			
			System.out.println("-------------- 메뉴 -------------- ");
			System.out.println("1. 영화 등록");
			System.out.println("2. 영화 삭제");
			System.out.println("3. 영화 목록 조회");
			System.out.println("0. 뒤로 가기");
			System.out.println("---------------------------------");
			System.out.print  ("입력 : ");
			input = Integer.parseInt(s.nextLine());
			
			if(input == 1) {
				
			}
			if(input == 2) {
				
			}
			if(input == 3) {
				
			}
			
		}while(input != 0);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	//----------------------------------- 사용자 --------------------------------------------
	
	//사용자 로그인 후 첫 화면
	private void AfterLogin() {
		Enter(50);
		Scanner s = new Scanner(System.in);
		
		int menu;
		
		do{
			System.out.println("-------------- 메뉴 -------------- ");
			System.out.println("1. 영화 목록 조회");
			System.out.println("2. 예매 영화 조회");
			System.out.println("3. MY Page");
			System.out.println("0. 로그 아웃");
			System.out.println("---------------------------------");
			System.out.print  ("메뉴에 해당하는 번호 입력>");
			
			menu = Integer.parseInt(s.nextLine());
			
			if(menu == 1) {
				MovieAgeSelect();
			}
			if(menu == 2) {
				
			}
			if(menu == 3) {
				
			}
		}while(menu != 0);
		
		System.out.println("로그 아웃 되었습니다.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("잠시 후 로그인 화면으로 이동합니다.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//연령 별 영화 선택
	private void MovieAgeSelect() {
		Enter(50);
		Scanner s = new Scanner(System.in);
		
		MovieList movieList = new MovieList();
		
		Database database = Database.getInstance();
		
		
		int menu;
		
		do{
			System.out.println("-------------- 메뉴 -------------- ");
			System.out.println("1. 전체 이용가 영화");
			System.out.println("2. 청소년 관람불가 영화");
			System.out.println("0. 뒤로 가기");
			System.out.println("3. 데이터베이스 연결을 위해서 확인 작업");
			System.out.println("---------------------------------");
			System.out.print  ("메뉴에 해당하는 번호 입력>");
			
			menu = Integer.parseInt(s.nextLine());
			
			if(menu == 1) {
				movieList.MovieList_All();
			}
			if(menu == 2) {
				movieList.MovieList_adult();
			}
			if(menu == 3) {
				for (int i = 0; i < database.tb_movie.size(); i++) {
					MovieVO movieVO = database.tb_movie.get(i);
					System.out.println(movieVO.toString());
				}
			}
		}while(menu != 0);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
}
