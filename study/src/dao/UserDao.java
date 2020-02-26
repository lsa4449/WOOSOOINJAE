package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import data.Database;
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
		
		
}