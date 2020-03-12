package controller;

import java.util.Scanner;

import dao.SeatDao;
import dao.UserDao;
import data.Database;
import data.Session;
import service.AdminService;
import service.MovieService;
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
	AdminService adminService = AdminService.getInstance();
	MovieService movieService = MovieService.getInstance();


	private void showBanner(String str) { 
		System.out.println();
	
		System.out.println("██████╗  █████╗ ███████╗██████╗ ███████╗ ██████╗ ██╗  ██╗\r\n" + 
				"██╔══██╗██╔══██╗██╔════╝██╔══██╗██╔════╝██╔═══██╗██║ ██╔╝\r\n" + 
				"██║  ██║███████║█████╗  ██║  ██║█████╗  ██║   ██║█████╔╝ \r\n" + 
				"██║  ██║██╔══██║██╔══╝  ██║  ██║██╔══╝  ██║   ██║██╔═██╗ \r\n" + 
				"██████╔╝██║  ██║███████╗██████╔╝███████╗╚██████╔╝██║  ██╗\r\n" + 
				"╚═════╝ ╚═╝  ╚═╝╚══════╝╚═════╝ ╚══════╝ ╚═════╝ ╚═╝  ╚═╝\r\n");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("    ██████╗██╗███╗    ██╗███████╗███╗   ███╗  █████╗      \r\n" + 
				"    ██╔════╝██║████╗  ██║██╔════╝████╗ ████║██╔══██╗     \r\n" + 
				"    ██║     ██║██╔██╗ ██║█████╗  ██╔████╔██║███████║     \r\n" + 
				"    ██║     ██║██║╚██╗██║██╔══╝  ██║╚██╔╝██║██╔══██║     \r\n" + 
				"    ╚██████╗██║██║ ╚████║███████╗██║ ╚═╝ ██║██║  ██║     \r\n" + 
				"     ╚═════╝╚═╝╚═╝  ╚═══╝╚══════╝╚═╝     ╚═╝╚═╝  ╚═╝     ");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("─────────────────────────────────────────────────────────");
		System.out.println("\t\t영화가 보고 싶은 날엔 대덕 시네마 ♪\t\t ");
		System.out.println("\t\t" + str + "\t" );
		System.out.println("─────────────────────────────────────────────────────────");
	}
	
	private void start() {
		Scanner s = new Scanner(System.in);
		
		int menu;
		
		do{
			showBanner("사용할 메뉴를 선택해주세요 ▼");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("0. 프로그램 종료");
			System.out.println("─────────────────────────────────────────────────────────");
			System.out.print  ("입력 : ");
			
			
			menu = Integer.parseInt(s.nextLine());
			
			if(menu == 1) {
				userService.join();
			}
			if(menu == 2) { 
				int result = userService.login();
				
				if(result == 1) { //관리자 메뉴 (재석)
					AfterAdminLogin();
				}
				if(result == 2) { //일반 메뉴 (재석)
					AfterLogin();
				}
			}
			if(menu != 0 && menu > 2){
				System.out.println("잘못 입력하였습니다! 다시 입력해주세요!");
			}
			
		}while(menu != 0);
		
		showBanner("프로그램이 종료됩니다.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	//----------------------------------- 관리자 --------------------------------------------
	
	//관리자 로그인 메뉴 (재석)
	public void AfterAdminLogin() {
		Enter(50);
		Scanner s = new Scanner(System.in);
		
		int menu;
		
		do{
			showBanner("사용할 메뉴를 선택해주세요 ▼");
			System.out.println("1. 회원 관리");
			System.out.println("2. 영화 관리");
			System.out.println("0. 로그 아웃");
			System.out.println("─────────────────────────────────────");
			System.out.print  ("입력 : ");
			
			menu = Integer.parseInt(s.nextLine());
			
			if(menu == 1) { //회원 관리 (재석)
				user_management();
			}
			if(menu == 2) { //영화 관리 (재석)
				movie_management();
			}
			else if(menu != 0 && menu > 2 && menu < 0){
				System.out.println("잘못 입력하였습니다! 다시 입력해주세요!");
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
			System.out.println("3. 회원 권한 부여/제거");
			System.out.println("4. 회원 목록 조회");
			System.out.println("5. 회원 정보 수정");
			System.out.println("0. 뒤로 가기");
			System.out.println("---------------------------------");
			System.out.print  ("입력 : ");
			
			input = Integer.parseInt(s.nextLine());
			
			if(input == 1) { //회원 등록 (일반 회원가입과는 다르게 등록 시에 권한 부여도 가능) (재석)
				adminService.adduser();
			}
			if(input == 2) { //회원 삭제 (재석)
				adminService.delete_user();
			}
			if(input == 3) { //회원 권한 부여/제거 (재석)
				adminService.authorization();
			}
			if(input == 4) { //회원 목록 조회 (재석)
				adminService.lookup_user();
			}
			if(input == 5) { //회원 정보 수정 (재석)
				adminService.userEdit();
			}
			else if(input != 0 && input > 5 && input < 0){
				System.out.println("잘못 입력하였습니다! 다시 입력해주세요!");
			}
			
		}while(input != 0);
		
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
			
			if(input == 1) { //영화 등록 (재석)
				adminService.addmovie();
			}
			if(input == 2) { //영화 삭제인데 영현이가 만든거 넣으면 될듯!
				adminService.movieEdit();
			}
			if(input == 3) { //영화 목록 조회 (재석)
				adminService.lookup_moive();
			}
			else if(input != 0 && input > 3 && input < 0){
				System.out.println("잘못 입력하였습니다! 다시 입력해주세요!");
			}
			
		}while(input != 0);
		
	}
	
	
	//----------------------------------- 사용자 --------------------------------------------
	
	//사용자 로그인 후 첫 화면
	private void AfterLogin() {
		Enter(50);
		Scanner s = new Scanner(System.in);
		
		int menu;
		
		do{
			System.out.println("-------------- 메뉴 -------------- ");
			System.out.println("1. 영화 목록 조회 및 예매");
			System.out.println("2. 예매 영화 조회");
			System.out.println("3. MY Page");
			System.out.println("4. 예매 취소");
			System.out.println("0. 로그 아웃");
			System.out.println("---------------------------------");
			System.out.print  ("입력 : ");
			
			menu = Integer.parseInt(s.nextLine());
			
			if(menu == 1) { //영화 목록 조회 - 자신의 나이에 맞는 영화만 조회되게 함 (재석)
				movieService.reserveMovie();
			}
			if(menu == 2) {
				movieService.lookup_reservation();
			}
			if(menu == 3) {
				MypageView();
			}
			if(menu == 4) {
				movieService.reserveCancel();
			}
			else if(menu != 0 && menu > 4 && menu < 0){
				System.out.println("잘못 입력하였습니다! 다시 입력해주세요!");
			}
			
		}while(menu != 0);
		
		System.out.println("로그 아웃 되었습니다.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("잠시 후 로그인 화면으로 이동합니다.");
	}
	
	//My page 이동
	public void MypageView() { //회원 관리
		Scanner s = new Scanner(System.in);
		
		int input = 0;
		
		do {
			
			System.out.println("-------------- 메뉴 -------------- ");
			System.out.println("1. 회원 조회");
			System.out.println("2. 회원 수정");
			System.out.println("3. 회원 탈퇴");
			System.out.println("4. 현금 충전");
			System.out.println("0. 뒤로 가기");
			System.out.println("---------------------------------");
			System.out.print  ("입력 : ");
			
			input = Integer.parseInt(s.nextLine());
			
			if(input == 1) { //회원 조회
				userService.userInfoView();
			}
			if(input == 2) { //회원 수정
				MypageModify();
			}
			if(input == 3) { //회원 탈퇴
				userRemove();
			}
			if(input == 4) { //현금 충전
				userService.cash();
			}
			else if(input != 0 && input > 4 && input < 0){
				System.out.println("잘못 입력하였습니다! 다시 입력해주세요!");
			}
			
		}while(input != 0);
		
	}
	
	//My page 이동
		public void MypageModify() { //회원 수정
			Scanner s = new Scanner(System.in);
			
			int input = 0;
			 
			do {
				
				System.out.println("-------------- 메뉴 -------------- ");
				System.out.println("1. 비밀번호 수정");
				System.out.println("2. 이름 수정");
				System.out.println("3. 생년월일 수정");
				System.out.println("0. 뒤로 가기");
				System.out.println("---------------------------------");
				System.out.print  ("입력 : ");
				
				input = Integer.parseInt(s.nextLine());
				
				if(input == 1) { //비밀번호 수정
					userService.userPwModify();
				}
				if(input == 2) { //이름 수정
					userService.userNameModify();
				}
				if(input == 3) { //생년월일 수정
					userService.userBirthModify();
				}
				else if(input != 0 && input > 3 && input < 0){
					System.out.println("잘못 입력하였습니다! 다시 입력해주세요!");
				}
				
							
			}while(input != 0);
		}

		public void userRemove() { //회원 탈퇴
			Scanner s = new Scanner(System.in);
			
			int input = 0;
			 
			do {
				
				System.out.println("-------------- 메뉴 -------------- ");
				System.out.println("1. 진짜 탈퇴 하시겠습니까?(철회 불가능)");
				System.out.println("2. 뒤로 가기");
				System.out.println("---------------------------------");
				System.out.print  ("입력 : ");
				
				input = Integer.parseInt(s.nextLine());
				
				if(input == 1) { //진짜 탈퇴
					userService.userInfoRemove();
					start();
					
				} 
				else if(input != 1 && input != 2){
					System.out.println("잘못 입력하였습니다! 다시 입력해주세요!");
				}
							
			}while(input != 2);
		}

}
