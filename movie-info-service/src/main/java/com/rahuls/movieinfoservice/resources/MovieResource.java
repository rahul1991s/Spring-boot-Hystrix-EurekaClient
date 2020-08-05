package com.rahuls.movieinfoservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rahuls.movieinfoservice.models.Movie;
import com.rahuls.movieinfoservice.models.MovieSummary;

/**
 * @author Rahul S
 *
 */
@RestController
@RequestMapping("/movies")
public class MovieResource {

	@Value("${api.key}")
	private String apiKey;

	@Autowired
	private RestTemplate restTemplate;

    @Autowired
    WebClient.Builder webClientBuilder;

	@HystrixCommand(fallbackMethod = "getFallBackMovieInfo")
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		MovieSummary movieSummary = restTemplate.getForObject(
				"https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + "c48e79ad2c381518f1621d21e2d7a9e6",
				MovieSummary.class);
		return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
	}

	/*
	Alternative WebClient way
	Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/"+ rating.getMovieId())
	.retrieve().bodyToMono(Movie.class).block();
	*/
	
	public Movie getFallBackMovieInfo(@PathVariable("movieId") String movieId) {
		return new Movie("NA", "No Movie", "Service is Down");

	}
}
