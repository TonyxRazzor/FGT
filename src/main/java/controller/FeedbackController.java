package controller;

import model.Feedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dao.FeedbackService;

@RestController
public class FeedbackController {

    private final FeedbackService feedbackService;
    private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/feedback-form")
    public String showFeedbackForm() {
        return "feedback-form";
    }

    @PostMapping("/submit-feedback")
    public ResponseEntity<String> submitFeedback(@RequestBody Feedback feedback) {
        try {
            feedbackService.saveFeedback(feedback);
            return ResponseEntity.ok("Feedback successfully submitted");
        } catch (Exception e) {
            logger.error("Error submitting feedback", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error submitting feedback");
        }
    }
}
