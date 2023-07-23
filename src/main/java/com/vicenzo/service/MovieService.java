package com.vicenzo.service;

import com.vicenzo.entity.Movie;
import com.vicenzo.repository.MovieRepository;
import com.vicenzo.util.JsonResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MovieService {

    private final MovieRepository repository;


    public ResponseEntity<?> findAllMovies() {
        try {
            List<Movie> movies = repository.findAll();
            if (CollectionUtils.isEmpty(movies)) {
                return ResponseEntity.ok(JsonResponseUtil.getJsonResponse("success",
                        "No movies found", null));
            } else {
                return ResponseEntity.ok(JsonResponseUtil.getJsonResponse("success",
                        "All movies", movies));
            }
        } catch (Exception e) {
            log.error("Error in findAllMovies() :", e);
            return new ResponseEntity<>(JsonResponseUtil.getJsonResponse("failed",
                    "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> findMovieByImdbId(String imdbId) {
        try {
            Optional<Movie> movie = repository.findMovieByImdbId(imdbId);
            return movie.map(value -> ResponseEntity.ok(JsonResponseUtil.getJsonResponse("success",
                    "Movie found", value))).orElseGet(() -> ResponseEntity.ok(JsonResponseUtil.getJsonResponse("success",
                    "No movie exist with this imdbId", null)));
        } catch (Exception e) {
            log.error("Error in findMovieByImdbId() :", e);
            return new ResponseEntity<>(JsonResponseUtil.getJsonResponse("failed",
                    "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
