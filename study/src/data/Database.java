package data;

import java.util.ArrayList;

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
}
