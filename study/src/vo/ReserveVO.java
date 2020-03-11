package vo;

public class ReserveVO {
	// 예매VO

	private int reserveNum; // 예매 번호 // 안쓸거야
	private int timeRound; // 회차
	private String id;// 회원 아이디
	private String reserveDate; // 예매 일자
	private int price; // 예매 가격
	private String movieName;
	private String seatPosition;// String으로 받은 좌석
	private int theaterPosition;
	private String startMovieTime;

	public String getStartMovieTime() {
		return startMovieTime;
	}

	public void setStartMovieTime(String startMovieTime) {
		this.startMovieTime = startMovieTime;
	}

	public String getSeatPosition() {
		return seatPosition;
	}

	public void setSeatPosition(String seatPosition) {
		this.seatPosition = seatPosition;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getReserveNum() {
		return reserveNum;
	}

	public void setReserveNum(int reserveNum) {
		this.reserveNum = reserveNum;
	}

	public int getTimeRound() {
		return timeRound;
	}

	public void setTimeRound(int timeRound) {
		this.timeRound = timeRound;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(double d) {
		this.price = (int) d;
	}

	public int getTheaterPosition() {
		return theaterPosition;
	}

	public void setTheaterPosition(int theaterPosition) {
		this.theaterPosition = theaterPosition;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ReserveVO [reserveNum=" + reserveNum + ", timeRound=" + timeRound + ", id=" + id + ", reserveDate="
				+ reserveDate + ", price=" + price + ", movieName=" + movieName + ", seatPosition=" + seatPosition
				+ ", theaterPosition=" + theaterPosition + "]";
	}

}
