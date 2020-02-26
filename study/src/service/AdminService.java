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
		//먼저 회원 리스트를 보여줌
		userList();
		
		System.out.print("수정하고 싶은 회원 아이디를 입력해주세요.");
		String id = s.nextLine();
		int indexNo = tb_Index(id);
		
		System.out.println(indexNo);
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
