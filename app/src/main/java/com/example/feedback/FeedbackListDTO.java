package com.example.feedback;

import java.util.ArrayList;

public class FeedbackListDTO extends GenDTO {
    private ArrayList<FeedbackEntity> list_entity ;

    public FeedbackListDTO(boolean status, String msg, Exception ex, ArrayList<FeedbackEntity> list_entity) {
        super(status, msg, ex);
        this.list_entity = list_entity;
    }

    @Override
    public String toString() {
        return "FeedbackListDTO{" +
                "list_entity=" + list_entity +
                '}';
    }

    public ArrayList<FeedbackEntity> getList_entity() {
        return list_entity;
    }
}
