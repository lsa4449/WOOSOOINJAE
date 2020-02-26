package service;

import java.util.ArrayList;
import java.util.Scanner;

import dao.UserDao;
import data.Database;
import vo.UserVO;

public class AdminService {
	private static AdminService instance;

	public AdminService() {
	}

	public static AdminService getInstance() {
		if (instance == null) {
			instance = new AdminService();
		}
		return instance;
	}
	
	UserDao userDao = UserDao.getInstance();
	
	
	//회원목록
	public void userList(){
		ArrayList<UserVO> userList = userDao.selectUserList();
		
		System.out.println("---------------------------------");
		System.out.println("번호\t아이디\t이름");
		System.out.println("---------------------------------");
		for(int i = userList.size() - 1; 0 <= i; i--) {
			UserVO user = userList.get(i);
			System.out.println(i + 1 + "\t" + user.getId() + "\t" + user.getName());
		}
		System.out.println("---------------------------------");
	}
	
	//회원 정보 수정하는 메소드
	public void UserEdit() {
		Scanner s = new Scanner(System.in);
		Database database = Database.getInstance();
		//먼저 회원 리스트를 보여줌
		userList();
		
		System.out.print("수정하고 싶은 회원 아이디를 입력해주세요.");
		String id = s.nextLine();
		
		int IndexNo = tb_Index(id);
		
		UserVO tb_id = database.tb_user.get(IndexNo);
		
		int menu = 0;
		
		
		while(menu != 1 && menu != 2 && menu != 3) {
			System.out.println("------------- 정보 -------------");
			System.out.println("1. 아   이   디 : " + tb_id.getId());
			System.out.println("2. 비 밀 번 호 : " + tb_id.getPassword());
			System.out.println("3. 생 년 월 일 : " + tb_id.getBirthdate());
			System.out.println("---------------------------------");
			System.out.print  ("수정하고자 하는 항목에 해당하는 번호 입력>");
			
			menu = Integer.parseInt(s.nextLine());
			
			if(menu == 1) {
				System.out.println("현재 아이디 : " + tb_id.getId());
				System.out.print  ("바꿀 아이디를 입력해주세요 > ");
				String input = s.nextLine();
				tb_id.setId(input);
			}else if(menu == 2) {
				System.out.println("현재 비밀번호 : " + tb_id.getPassword());
				System.out.println("바꿀 비밀번호를 입력해주세요 > ");
				String input = s.nextLine();
				tb_id.setPassword(input);
			}else if(menu == 3) {
				System.out.println("현재 생년월일 : " + tb_id.getBirthdate());
				System.out.println("바꿀 생년월일을 입력해주세요 > ");
				int input = Integer.parseInt(s.nextLine());
				tb_id.setBirthdate(input);
			}else {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
			
		}
		

		
	}
	
	//입력받은 아이디로 테이블 인덱스 번호를 리턴해주는 메소드
	public int tb_Index(String id) {
		int indexno = 0;
		
		Database database = new Database();
		
		for(int i = 0; i < database.tb_user.size(); i++) {
			UserVO tb_id = database.tb_user.get(i);
			if(tb_id.getId().equals(id)) {
				indexno = i;
				break;
			}
		}
		return indexno;
	}
}
