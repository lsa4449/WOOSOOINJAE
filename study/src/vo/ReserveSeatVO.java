package vo;

public class ReserveSeatVO {

		private int reserveSeatNum;// 예매 좌석 번호
		private int seatNum; // 좌석 번호
		private int reserveNum; // 예매 번호
		
		@Override
		public String toString() {
			return "ReserveSeatVO [reserveSeatNum=" + reserveSeatNum + ", seatNum=" + seatNum + ", reserveNum="
					+ reserveNum + "]";
		}
		
		public int getReserveSeatNum() {
			return reserveSeatNum;
		}
		public void setReserveSeatNum(int reserveSeatNum) {
			this.reserveSeatNum = reserveSeatNum;
		}
		public int getSeatNum() {
			return seatNum;
		}
		public void setSeatNum(int seatNum) {
			this.seatNum = seatNum;
		}
		public int getReserveNum() {
			return reserveNum;
		}
		public void setReserveNum(int reserveNum) {
			this.reserveNum = reserveNum;
		}
		
		
		
}
