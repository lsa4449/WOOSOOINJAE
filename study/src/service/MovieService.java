package service;

import java.util.ArrayList;
import java.util.Scanner;

import dao.MovieDao;
import data.Database;
import vo.MovieVO;

public class MovieService {
	
		
		//영화 목록 //영현
		public void movieList(){
			MovieDao movieDao = MovieDao.getInstance();
			
			ArrayList<MovieVO> movieList = movieDao.selectMovieList();
			
			System.out.println("---------------------------------");
			System.out.println("번호\t영화이름\t감독\t줄거리\t배우\t개봉일\t시청나이");
			System.out.println("---------------------------------");
			for(int i = movieList.size() - 1; 0 <= i; i--) {
				MovieVO movie = movieList.get(i);
				System.out.println(movie.getMovieNum() + "\t" + movie.getMovieName() + "\t" + movie.getDirector() + "\t" + movie.getPlot() + "\t"+ movie.getActor() + "\t"+ movie.getOpenMovieDate() + "\t"+ movie.getAge() + "\t");
			}
			System.out.println("---------------------------------");
		}
		
		// 영화 삭제
		
}
