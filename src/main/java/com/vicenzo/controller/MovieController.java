package com.vicenzo.controller;

import com.vicenzo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movies/api/v1")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<?> getMovies() {
        return movieService.findAllMovies();
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<?> getSingleMovie(@PathVariable String imdbId){
        return movieService.findMovieByImdbId(imdbId);
    }
}
