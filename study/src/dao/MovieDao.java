package dao;

import java.util.ArrayList;

import data.Database;
import vo.MovieVO;


public class MovieDao {
	
	private static MovieDao instance; // 객체 공유 -> 똑같은 값 -> 메모리 낭비가 덜 된다. 
	
	private MovieDao(){}
	
	public static MovieDao getInstance(){
		if(instance == null){
			instance = new MovieDao();
		}
		return instance;
	}
	
	Database database = Database.getInstance();
	
	public ArrayList<MovieVO> selectMovieList() {
		return database.tb_movie;
	}
	
	
}
