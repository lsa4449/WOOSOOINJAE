package vo;

public class TheaterVO {
	//상영관VO
	
	private int theaterNum; //상영관 번호
	private String theaterName; //상영관 이름

	public int getTheaterNum() {
		return theaterNum;
	}

	public void setTheaterNum(int theaterNum) {
		this.theaterNum = theaterNum;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	@Override
	public String toString() {
		return "TheaterVO [theaterNum=" + theaterNum + ", theaterName=" + theaterName + "]";
	}

}
