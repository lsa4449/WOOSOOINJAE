package vo;

public class SeatVO {
	//좌석VO
	
	private int seatNum; // 좌석 번호
	private int theaterNum; // 상영관 번호
	private String realSeatNum; // 좌석 세부 번호
	private int seatPrice; // 좌석별 가격
	

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	public int getTheaterNum() {
		return theaterNum;
	}

	public void setTheaterNum(int theaterNum) {
		this.theaterNum = theaterNum;
	}

	public String getRealSeatNum() {
		return realSeatNum;
	}

	public void setRealSeatNum(String realSeatNum) {
		this.realSeatNum = realSeatNum;
	}

	public int getSeatPrice() {
		return seatPrice;
	}

	public void setSeatPrice(int seatPrice) {
		this.seatPrice = seatPrice;
	}

	@Override
	public String toString() {
		return "SeatVO [seatNum=" + seatNum + ", theaterNum=" + theaterNum + ", realSeatNum=" + realSeatNum
				+ ", seatPrice=" + seatPrice + "]";
	}

}
