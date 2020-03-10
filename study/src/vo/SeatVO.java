package vo;

public class SeatVO {
	// 좌석VO

	private int seatNum; // 좌석 번호
	private int theaterNum; // 상영관 번호
	private String realSeatNum; // 좌석 세부 번호
	private int seatPrice; // 좌석별 가격
	private boolean seatUse; // 좌석 예매여부
	private String lookInfo; // 빈네모, 꽉찬 네모

	public String getLookInfo() {
		return lookInfo;
	}

	public void setLookInfo(String lookInfo) {
		this.lookInfo = lookInfo;
	}

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

	public boolean getSeatUse() {
		return seatUse;
	}

	public void setSeatUse(boolean seatUse) {
		this.seatUse = seatUse;
	}

	@Override
	public String toString() {
		return "SeatVO [seatNum=" + seatNum + ", theaterNum=" + theaterNum + ", realSeatNum=" + realSeatNum
				+ ", seatPrice=" + seatPrice + ", seatUse=" + seatUse + "]";
		
	}



}
