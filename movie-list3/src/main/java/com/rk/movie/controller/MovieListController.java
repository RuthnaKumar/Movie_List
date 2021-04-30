package com.rk.movie.controller;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;


import com.rk.movie.dataaccess.MovieListRepository;
import com.rk.movie.model.Movie;


@Controller
@RequestMapping("/")
public class MovieListController {
	@Autowired
	private MovieListRepository movieListRepository;
	@RequestMapping(method=RequestMethod.GET,value="/movies/{actor}")
	public String getmovieListByActor(@PathVariable("actor") String name,Model model)
	{
		
		/*List<Movie> movielist = new ArrayList<Movie>();
		Movie movie = new Movie();
		movie.setActor("RK");
		movie.setDescription("Hero");
		movie.setName("VJ");
		movielist.add(movie);*/
		
		List<Movie>movielist=movieListRepository.findMoviesByActor(name);
		model.addAttribute("movies",movielist);
		return "movielist";
	}
     
	@RequestMapping(method=RequestMethod.POST,value="/movies",consumes="Application/json")
	public ResponseEntity<Object>addMovie(@RequestBody Movie movie)
	{
		movieListRepository.save(movie);
		return ResponseEntity.ok().build();
	}
}
