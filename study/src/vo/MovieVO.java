package vo;



public class MovieVO {
	// 영화VO

	private int movieNum; // 영화 번호  
	private String movieName; // 영화 이름
	private String director; // 감독
	private String plot; // 줄거리
	private String actor; // 출연진
	private int openMovieDate; // 개봉일
	private int age; // 영화 관람 나이
	
	private static int staticmovieNum = 0; // 생성자 만들어서 생성자에서 ++ 되도록?

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	// 영화 번호 때문에 생성자를 만들어서 자동으로 늘어나도록
	/*public MovieVO() {
		staticmovieNum++;
		movieNum = staticmovieNum;
	}
	// 지웠을때 영화번호를 땡겨지게 하고 싶다. -> 다른 방법
	*/
	public int getMovieNum() {
		return movieNum;
	}

	public void setMovieNum(int movieNum) {
		this.movieNum = movieNum;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public int getOpenMovieDate() {
		return openMovieDate;
	}

	public void setOpenMovieDate(int openMovieDate) {
		this.openMovieDate = openMovieDate;
	}
	
	@Override
	public String toString() {
		return "MovieVO [movieNum=" + movieNum + ", movieName=" + movieName + ", director=" + director + ", plot="
				+ plot + ", actor=" + actor + ", openMovieDate=" + openMovieDate + ", age=" + age + "]";
	}



}
