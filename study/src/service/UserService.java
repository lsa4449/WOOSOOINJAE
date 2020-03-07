package service;

import java.util.HashMap;
import java.util.Scanner;

import controller.Controller;
import dao.UserDao;
import data.Database;
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
		
		System.out.print("생년원일(ex)202002) : ");
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
	public void login(){
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
		
		
		if(user == null){
			System.out.println("아이디 혹은 비밀번호를 잘못 입력하셨습니다.");
		}else {
			System.out.println("로그인 성공!");
			System.out.println(user.getName() + "님 환영합니다.");
			Session.loginUser = user;
//			if (user.getAuth()) { // admin
//				
//				AfterAdminLogin();
//			}else { // 일반 유저
//				
//			}
		}
	}
	
	//회원 정보 조회
	public void userInfoView() {
		
		UserVO user = null;
		
		System.out.println(user.getName() + "회원님의 아이디는 \t" + user.getId() +" 입니다.\n");
	    System.out.println(user.getName() +"회원님의 생년월일은 \t"+ user.getBirthdate()+" 입니다.\n");
	    System.out.println("───────────────────────────────────────────────────────");
   }
	
	
//	//테스트테스트
//	public void SortUser(String id) {
//		
//		Controller controller = new Controller();
//		
//		if(id.equals("admin")) {
//			controller.AfterAdminLogin();
//		}
//	}
	

}
