package service;

import java.util.ArrayList;

import dao.UserDao;
import vo.UserVO;

public class AdminService {
	private static AdminService instance;

	private AdminService() {
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
}
