package dao;

import java.util.ArrayList;
import java.util.HashMap;

import data.Database;
import vo.MovieVO;
import vo.UserVO;

public class AdminDao {
	
	private static AdminDao instance;
	
	private AdminDao(){}
	
	public static AdminDao getInstance(){
		if(instance == null){
			instance = new AdminDao();
		}
		return instance;
	}
	
	Database database = Database.getInstance();
	
	public void insertUser(UserVO admin){
		database.tb_user.add(admin);
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
	
	//영화 정보 저장 (재석)
	public void insertMovie(MovieVO movieVO) {
		database.tb_movie.add(movieVO);
	}

}
