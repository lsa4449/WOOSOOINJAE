package vo;

public class ReserveVO {
	// 예매VO

	private int reserveNum;
	private String reserveDate;
	private int price;
	private int time;
	private String id;

	public int getReserveNum() {
		return reserveNum;
	}

	public void setReserveNum(int reserveNum) {
		this.reserveNum = reserveNum;
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

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTime() { 
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ReserveVO [reserveNum=" + reserveNum + ", reserveDate=" + reserveDate + ", price=" + price + ", time="
				+ time + ", id=" + id + "]";
	}

}
