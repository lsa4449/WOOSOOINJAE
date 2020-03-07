package service;

import java.util.ArrayList;
import java.util.Scanner;

import dao.AdminDao;
import dao.MovieDao;
import dao.UserDao;
import data.Database;
import vo.MovieVO;
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
	
	AdminDao adminDao = AdminDao.getInstance();
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
	public void userEdit() {
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
				tb_id.setId(input);// 왜 데이터베이스가 영향을 주어 변경이 되는지 모르겠음 // UserVO tb_id = database.tb_user.get(IndexNo);
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
	
	//영화 등록하는 메소드(미완성 -- 영화 번호 어떻게 할지 몰라서 아직 안했음) //번호  추가함(영현).확인은 안해봄 ;뒤에// 바꾼 부분 표시해 놓겠음
			public void Enrollment() {
				MovieVO movieVO = new MovieVO();
				
				MovieDao movieDao = MovieDao.getInstance();//여기
				ArrayList<MovieVO> movieList = movieDao.selectMovieList();//여기
				
				Scanner s = new Scanner(System.in);
				
				
				System.out.println("[영화 등록]");
				System.out.println("영화 번호" + movieList.size()+1);//여기
				System.out.print  ("제목 : ");
				String movieName = s.nextLine();
				System.out.print  ("감독 : ");
				String director = s.nextLine();
				System.out.print  ("줄거리 : ");
				String plot = s.nextLine();
				System.out.print  ("배우 : ");
				String actor = s.nextLine();
				System.out.print  ("영화 개봉일 : ");
				int openMovieDate = Integer.parseInt(s.nextLine());
				System.out.println("영화 관람 나이");
				int age = Integer.parseInt(s.nextLine());
				
				movieVO.setMovieNum(movieList.size()+1);//여기
				movieVO.setMovieName(movieName);
				movieVO.setDirector(director);
				movieVO.setPlot(plot);
				movieVO.setActor(actor);
				movieVO.setOpenMovieDate(openMovieDate);
				movieVO.setAge(age);
			}
	
	//영화 수정 // 영현 // 삭제만 만듬 // 이것도 만들기만 함 확인은 안해봄
	public void movieEdit() {
		Scanner s = new Scanner(System.in);
		MovieVO movieVO = new MovieVO();
		Database database = Database.getInstance();
		MovieService movieService = new MovieService();
		
		
		
		movieService.movieList();// 영화리스트를 보여주고 삭제할 번호 입력
		// 삭제
		System.out.print("삭제할 인덱스 번호를 입력하세요 >> ");
		int movieIndex = Integer.parseInt(s.nextLine());
		--movieIndex;// 1번부터 시작하게 할려고 해놨기 때문에 실제 인덱스 값은 -1임
		database.tb_movie.remove(movieIndex);// 아마 삭제 될듯 
		for (int i = movieIndex; i < database.tb_movie.size(); i++) { // 영화번호  자동으로 앞으로 당기기
			movieVO = database.tb_movie.get(movieIndex);
			movieVO.setMovieNum(movieVO.getMovieNum()-1);
		}
		
		
		
		movieService.movieList();// 삭제되었는지 보여주기
		
		
		
		
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
