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
	Database database = Database.getInstance();

	// ------------------------ 회원 관리 메뉴 ------------------------
	// 관리자가 회원 직접 등록 - 일반 회원가입과는 다르게 등록할 때 권한도 설정 가능(재석)
	public void adduser() {
		Scanner s = new Scanner(System.in);

		System.out.println("[회원 등록]");
		System.out.print("아이디 : ");
		String id = s.nextLine();
		System.out.print("비밀번호 : ");
		String password = s.nextLine();
		System.out.print("이름 : ");
		String name = s.nextLine();
		System.out.print("생일 : ");
		int birthdate = Integer.parseInt(s.nextLine());
		int auth_input = 0;
		boolean auth = false;
		do {

			System.out.println("[권한]");
			System.out.println("1. 일반");
			System.out.println("2. 관리자");
			System.out.print("입력 : ");
			auth_input = Integer.parseInt(s.nextLine());

			if (auth_input == 1) {
				auth = false;
			}
			if (auth_input == 2) {
				auth = true;
			}

		} while (auth_input != 1 && auth_input != 2);

		UserVO user = new UserVO();

		user.setId(id);
		user.setPassword(password);
		user.setName(name);
		user.setBirthdate(birthdate);
		user.setAuth(auth);

		userDao.insertUser(user);

		System.out.println("새로운 회원이 등록되었습니다.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("잠시 후 메뉴로 이동합니다.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 회원 삭제 (재석)
	public void delete_user() {
		Scanner s = new Scanner(System.in);

		System.out.println("[회원 삭제]");
		System.out.println("---------------------------------");
		System.out.println("회원 번호\t아이디\t이름\t권한");
		for (int i = 0; i < database.tb_user.size(); i++) {
			UserVO user = database.tb_user.get(i);
			String user_auth = null;

			if (user.getAuth() == true) {
				user_auth = "관리자";
			}
			if (user.getAuth() == false) {
				user_auth = "일반";
			}
			System.out.println(i + 1 + "\t" + user.getId() + "\t" + user.getName() + "\t" + user_auth);
		}
		System.out.println("---------------------------------");

		int input = 0;
		UserVO user = null;

		do {
			System.out.print("삭제하고자 하는 회원의 번호를 입력 : ");

			input = Integer.parseInt(s.nextLine());

			try {
				user = database.tb_user.get(input - 1);
			} catch (IndexOutOfBoundsException e) {

			}

			if (user == null) {
				System.out.println("잘못된 번호를 입력하였습니다.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("다시 입력해주세요.");
			}
			if (user != null) {
				database.tb_user.remove(input - 1);
				System.out.println("선택하신 회원이 삭제되었습니다.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} while (user == null);

		System.out.println("잠시 후 메뉴로 돌아갑니다.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// 회원 권한 부여/제거 (재석)
	public void authorization() {
		Scanner s = new Scanner(System.in);

		System.out.println("[회원 권한 부여]");
		System.out.println("---------------------------------");
		System.out.println("회원 번호\t아이디\t이름\t권한");
		for (int i = 0; i < database.tb_user.size(); i++) {
			UserVO user = database.tb_user.get(i);
			String user_auth = null;

			if (user.getAuth() == true) {
				user_auth = "관리자";
			}
			if (user.getAuth() == false) {
				user_auth = "일반";
			}
			System.out.println(i + 1 + "\t" + user.getId() + "\t" + user.getName() + "\t" + user_auth);
		}
		System.out.println("---------------------------------");

		int input = 0;
		UserVO user = null;

		do {
			System.out.print("권한을 부여하고자 하는 회원의 번호를 입력 : ");

			input = Integer.parseInt(s.nextLine());

			try {
				user = database.tb_user.get(input - 1);
			} catch (IndexOutOfBoundsException e) {

			}

			if (user == null) {
				System.out.println("잘못된 번호를 입력하였습니다.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("다시 입력해주세요.");
			}
			if (user != null) {

				user = database.tb_user.get(input - 1);
				boolean userAuth = user.getAuth();

				if (userAuth == true) {
					user.setAuth(false);
				}
				if (userAuth == false) {
					user.setAuth(true);
				}

				System.out.println("선택하신 회원의 권한이 변경되었습니다.");

			}
		} while (user == null);

		System.out.println("잠시 후 메뉴로 돌아갑니다.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// 회원 목록 (재석)
	public void lookup_user() {
		Scanner s = new Scanner(System.in);

		System.out.println("[회원 목록]");
		System.out.println("---------------------------------");
		System.out.println("회원 번호\t아이디\t이름\t권한");
		for (int i = 0; i < database.tb_user.size(); i++) {
			UserVO user = database.tb_user.get(i);
			String user_auth = null;

			if (user.getAuth() == true) {
				user_auth = "관리자";
			}
			if (user.getAuth() == false) {
				user_auth = "일반";
			}
			System.out.println(i + 1 + "\t" + user.getId() + "\t" + user.getName() + "\t" + user_auth);
		}
		System.out.println("---------------------------------");
		System.out.println("0. 뒤로 가기");
		System.out.println("---------------------------------");
		System.out.print("입력 : ");
		int input = 0;

		do {

			input = Integer.parseInt(s.nextLine());

			if (input != 0) {
				System.out.println("메뉴에 있는 번호를 입력해주세요.");
			}

		} while (input != 0);

	}
	
	// 회원 정보 수정하는 메소드
	public void userEdit() {
		Scanner s = new Scanner(System.in);
		Database database = Database.getInstance();
		// 먼저 회원 리스트를 보여줌
		lookup_user();

		System.out.print("수정하고 싶은 회원 아이디를 입력해주세요.");
		String id = s.nextLine();

		int IndexNo = tb_Index(id);

		UserVO tb_id = database.tb_user.get(IndexNo);

		int menu = 0;

		while (menu != 1 && menu != 2 && menu != 3) {
			System.out.println("------------- 정보 -------------");
			System.out.println("1. 아   이   디 : " + tb_id.getId());
			System.out.println("2. 비 밀 번 호 : " + tb_id.getPassword());
			System.out.println("3. 생 년 월 일 : " + tb_id.getBirthdate());
			System.out.println("---------------------------------");
			System.out.print("수정하고자 하는 항목에 해당하는 번호 입력>");

			menu = Integer.parseInt(s.nextLine());

			if (menu == 1) {
				System.out.println("현재 아이디 : " + tb_id.getId());
				System.out.print("바꿀 아이디를 입력해주세요 > ");
				String input = s.nextLine();
				tb_id.setId(input);// 왜 데이터베이스가 영향을 주어 변경이 되는지 모르겠음 // UserVO tb_id =
									// database.tb_user.get(IndexNo);
			} else if (menu == 2) {
				System.out.println("현재 비밀번호 : " + tb_id.getPassword());
				System.out.println("바꿀 비밀번호를 입력해주세요 > ");
				String input = s.nextLine();
				tb_id.setPassword(input);
			} else if (menu == 3) {
				System.out.println("현재 생년월일 : " + tb_id.getBirthdate());
				System.out.println("바꿀 생년월일을 입력해주세요 > ");
				int input = Integer.parseInt(s.nextLine());
				tb_id.setBirthdate(input);
			} else {
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}
	}

	// ------------------------ 영화 관리 메뉴 ------------------------
	public void addmovie() { // 영화 등록 (재석) -- 수정해야함

		Scanner s = new Scanner(System.in);

		System.out.println("[영화 등록]");
		System.out.print("제목 : ");
		String movieName = s.nextLine();

		int ageinput = 0;
		String age = null;

		System.out.println("[관람 가능 나이]");
		System.out.println("1. 전체 이용가");
		System.out.println("2. 청소년 관람 불가");

		do {
			ageinput = Integer.parseInt(s.nextLine());

			if (ageinput == 1) {
				age = "전체 이용가";
			}
			if (ageinput == 2) {
				age = "청소년 관람 불가";
			}

			if (ageinput != 1 && ageinput != 2) {
				System.out.println("잘못된 번호를 입력하였습니다.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("다시 입력해주세요.");
			}
		} while (ageinput != 1 && ageinput != 2);

		System.out.print("감독 : ");
		String director = s.nextLine();
		System.out.print("배우 : ");
		String actor = s.nextLine();
		System.out.print("개봉일(200301) : ");
		int openMovieDate = Integer.parseInt(s.nextLine());
		System.out.print("줄거리 : ");
		String plot = s.nextLine();
		System.out.print("상영관(1 ~ 3) : ");
		int theaterNum = Integer.parseInt(s.nextLine());
		System.out.print("상영 날짜(YY/MM/DD) : ");
		String movieDate = s.nextLine();
		System.out.print("상영 시작 시간(HH시 mm분) : ");
		String startMovieTime = s.nextLine();
		System.out.print("상영 종료 시간(HH시 mm분) : ");
		String endMovieTime = s.nextLine();

		MovieVO movie = new MovieVO();

		movie.setMovieName(movieName);
		movie.setAge(age);
		movie.setDirector(director);
		movie.setActor(actor);
		movie.setOpenMovieDate(openMovieDate);
		movie.setPlot(plot);
		movie.setTheaterNum(theaterNum);
		movie.setMovieDate(movieDate);
		movie.setStartMovieTime(startMovieTime);
		movie.setEndMovieTime(endMovieTime);

		adminDao.insertMovie(movie);

		System.out.println("영화가 등록되었습니다.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("잠시 후 메뉴로 돌아갑니다.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// 영화 수정 // 영현 // 삭제만 만듬 // 이것도 만들기만 함 확인은 안해봄
	public void movieEdit() {
		Scanner s = new Scanner(System.in);
		MovieVO movieVO = new MovieVO();
		Database database = Database.getInstance();
		MovieService movieService = MovieService.getInstance();

		movieService.movieList();// 영화리스트를 보여주고 삭제할 번호 입력
		// 삭제
		System.out.print("삭제할 인덱스 번호를 입력하세요 >> ");
		int movieIndex = Integer.parseInt(s.nextLine());
		--movieIndex;// 1번부터 시작하게 할려고 해놨기 때문에 실제 인덱스 값은 -1임
		database.tb_movie.remove(movieIndex);// 아마 삭제 될듯
		for (int i = movieIndex; i < database.tb_movie.size(); i++) { // 영화번호 자동으로 앞으로 당기기
			movieVO = database.tb_movie.get(movieIndex);
			movieVO.setMovieNum(movieVO.getMovieNum() -1);
		}

		movieService.movieList();// 삭제되었는지 보여주기

	}

	public void lookup_moive() { // 영화 목록 (재석)

		Scanner s = new Scanner(System.in);

		int movie_no = 1;

		System.out.println("[영화 목록]");
		System.out.println("---------------------------------");
		for (int i = 0; i < database.tb_movie.size(); i++) {
			MovieVO movie = database.tb_movie.get(i);

			System.out.println("[ " + movie_no + " ]");
			System.out.println("제목 : " + movie.getMovieName());
			System.out.println("관람 나이 : " + movie.getAge());
			System.out.println("상영 날짜 : " + movie.getOpenMovieDate());
			System.out.println("상영 시간 : " + movie.getStartMovieTime());
			System.out.println("상영 종료 : " + movie.getEndMovieTime());

			movie_no++;
		}
		System.out.println("---------------------------------");
		System.out.println("0. 뒤로 가기");
		System.out.println("---------------------------------");
		System.out.print("입력 : ");
		int input = 0;

		do {

			input = Integer.parseInt(s.nextLine());

			if (input != 0) {
				System.out.println("메뉴에 있는 번호를 입력해주세요.");
			}

		} while (input != 0);

	}

	// 입력받은 아이디로 테이블 인덱스 번호를 리턴해주는 메소드 (재석)
	public int tb_Index(String id) {
		int indexno = 0;

		Database database = Database.getInstance();

		for (int i = 0; i < database.tb_user.size(); i++) {
			UserVO tb_id = database.tb_user.get(i);
			if (tb_id.getId().equals(id)) {
				indexno = i;
				break;
			}
		}
		return indexno;
	}


}
