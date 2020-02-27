package vo;

public class MovieReviewVO {
 
	private int reviewNum; // 영화 리뷰 번호
	private int movieNum; // 영화 번호
	private String id; // 회원 아이디
	private String reviewContents; // 리뷰 내용
	private String reviewDate; // 리뷰 날짜
	private String reviewRating; // 리뷰 평점

	public int getReviewNum() {
		return reviewNum;
	}

	public void setReviewNum(int reviewNum) {
		this.reviewNum = reviewNum;
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

	public String getReviewContents() {
		return reviewContents;
	}

	public void setReviewContents(String reviewContents) {
		this.reviewContents = reviewContents;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(String reviewRating) {
		this.reviewRating = reviewRating;
	}

	@Override
	public String toString() {
		return "MovieReviewVO [reviewNum=" + reviewNum + ", movieNum=" + movieNum + ", id=" + id + ", reviewContents="
				+ reviewContents + ", reviewDate=" + reviewDate + ", reviewRating=" + reviewRating + "]";
	}

}
