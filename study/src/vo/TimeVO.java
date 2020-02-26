package vo;

public class TimeVO {
	// 시간VO

	private int timeRound; // 회차
	private int movieNum; // 영화 번호
	private int theaterNum; // 상영관 번호
	private String movieDate; // 상영 날짜
	private int startMovieTime; // 상영 시작 시간
	private int endMovieTime; // 상영 종료 시간

	public int getTimeRound() {
		return timeRound;
	}

	public void setTimeRound(int timeRound) {
		this.timeRound = timeRound;
	}

	public int getMovieNum() {
		return movieNum;
	}

	public void setMovieNum(int movieNum) {
		this.movieNum = movieNum;
	}

	public int getTheaterNum() {
		return theaterNum;
	}

	public void setTheaterNum(int theaterNum) {
		this.theaterNum = theaterNum;
	}

	public String getMovieDate() {
		return movieDate;
	}

	public void setMovieDate(String movieDate) {
		this.movieDate = movieDate;
	}

	public int getStartMovieTime() {
		return startMovieTime;
	}

	public void setStartMovieTime(int startMovieTime) {
		this.startMovieTime = startMovieTime;
	}

	public int getEndMovieTime() {
		return endMovieTime;
	}

	public void setEndMovieTime(int endMovieTime) {
		this.endMovieTime = endMovieTime;
	}

	@Override
	public String toString() {
		return "TimeVO [timeRound=" + timeRound + ", movieNum=" + movieNum + ", theaterNum=" + theaterNum
				+ ", movieDate=" + movieDate + ", startMovieTime=" + startMovieTime + ", endMovieTime=" + endMovieTime
				+ "]";
	}

}
