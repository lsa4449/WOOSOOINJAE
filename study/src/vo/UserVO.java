package vo;
     
public class UserVO {
	// 회원VO
  

	private String id; // 회원 아이디
	private String password; // 회원 비밀번호
	private String name; // 회원 이름
	private int birthdate; // 회원 생년월일
	private int cash; // 현금
	private boolean auth; // 권한

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(int birthdate) {
		this.birthdate = birthdate;
	}

	public boolean getAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	public int getCash() {
		return cash;
	}
	
	public void setCash(double d) {
		this.cash = (int) d;
	}

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", password=" + password + ", name=" + name + ", birthdate=" + birthdate
				+ ", cash=" + cash + ", auth=" + auth + "]";
	}
	
}
