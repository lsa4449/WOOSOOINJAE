package vo;

public class ReviewVO {
	// 리뷰 VO

	private int movieReviewNum; // 영화 리뷰 번호
	private int movieNum; // 리뷰 번호
	private String id; // 아이디
	private String reviewContent; // 리뷰 내용
	private int reviewDate; // 리뷰 작성 날짜
	private int reviewRate; // 리뷰 평점

	public int getMovieReviewNum() {
		return movieReviewNum;
	}

	public void setMovieReviewNum(int movieReviewNum) {
		this.movieReviewNum = movieReviewNum;
	}

	public int getMovieNum() {
		return movieNum;
	}

	public void setMovieNum(int movieNum) {
		this.movieNum = movieNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public int getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(int reviewDate) {
		this.reviewDate = reviewDate;
	}

	public int getReviewRate() {
		return reviewRate;
	}

	public void setReviewRate(int reviewRate) {
		this.reviewRate = reviewRate;
	}

	@Override
	public String toString() {
		return "ReviewVO [movieReviewNum=" + movieReviewNum + ", movieNum=" + movieNum + ", id=" + id
				+ ", reviewContent=" + reviewContent + ", reviewDate=" + reviewDate + ", reviewRate=" + reviewRate
				+ "]";
	}

}
