package com.vicenzo.controller;

import com.vicenzo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("reviews/api/v1")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReviewController {
    
    private final ReviewService reviewService;

    @PostMapping()
    public <T> ResponseEntity<?> createReview(@RequestBody Map<String, T> payload) {
        String reviewBody = (String) payload.get("reviewBody");
        String imdbId = (String) payload.get("imdbId");
        return reviewService.createReview(reviewBody, imdbId);
    }
}
