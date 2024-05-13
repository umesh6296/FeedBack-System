package com.example.feedback;

import java.util.ArrayList;

public class UserListDTO extends GenDTO {
    private ArrayList<UserEntity> list_entity ;

    public UserListDTO(boolean status, String msg, Exception ex, ArrayList<UserEntity> list_entity) {
        super(status, msg, ex);
        this.list_entity = list_entity;
    }

    @Override
    public String toString() {
        return "UserListDTO{" +
                "list_entity=" + list_entity +
                '}';
    }

    public ArrayList<UserEntity> getList_entity() {
        return list_entity;
    }
}
