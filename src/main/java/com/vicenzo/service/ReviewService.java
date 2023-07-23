package com.vicenzo.service;

import com.mongodb.client.result.UpdateResult;
import com.vicenzo.entity.Movie;
import com.vicenzo.entity.Review;
import com.vicenzo.repository.ReviewRepository;
import com.vicenzo.util.JsonResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReviewService {

    private final ReviewRepository reviewRepository;


    private final MongoTemplate mongoTemplate;

    public ResponseEntity<?> createReview(String reviewBody, String imdbId) {
        try {
            Review review = reviewRepository.insert(new Review(reviewBody));
            Query query = new Query(Criteria.where("imdbId").is(imdbId));
            Update update = new Update().push("reviewIds").value(review);

            FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true);
            // Set returnNew to true to return the updated document

            UpdateResult result = mongoTemplate.updateFirst(query, update, Movie.class);

            // Check if the movie was found and updated
            if (result.getModifiedCount() > 0) {
                // Movie found and updated
                return new ResponseEntity<>(JsonResponseUtil.getJsonResponse("success",
                        "Review added to movies",review), HttpStatus.CREATED);
            } else {
                // Movie with the given imdbId not found
                return new ResponseEntity<>(JsonResponseUtil.getJsonResponse("failure",
                        "No movie exist with this imdbId"), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(JsonResponseUtil.getJsonResponse("failure",
                    "Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
