package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.FeedbackDAO;
import model.Feedback;

import java.sql.SQLException; // Импорт SQLException

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackDAO feedbackDAO;

    @GetMapping("/feedback-form")
    public String showFeedbackForm(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "feedback-form";
    }

    @PostMapping("/submit-feedback")
    public String submitFeedback(@RequestParam String name,
                                 @RequestParam String email,
                                 @RequestParam String message) {
        try {
            Feedback feedback = new Feedback(name, email, message);
            feedbackDAO.saveFeedback(feedback);
            return "redirect:/feedback-success"; // редирект на страницу с подтверждением
        } catch (SQLException e) {
            e.printStackTrace(); // Вывести трассировку стека в консоль
            return "error"; // Обработка ошибки
        }
    }

    @GetMapping("/feedback-success")
    public String showFeedbackSuccess() {
        return "feedback-success";
    }
}
