package com.example.feedback;

public class FeedbackEntity {
    String email,course,suggestion;
    float rating;

    public FeedbackEntity(String email, String course, String suggestion, float rating) {
        this.email = email;
        this.course = course;
        this.suggestion = suggestion;
        this.rating = rating;
    }

    public String getEmail() {
        return email;
    }

    public String getCourse() {
        return course;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public float getRating() {
        return rating;
    }
}
