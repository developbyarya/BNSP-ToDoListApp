package com.example.bnspgradiva_todolist.model;

import android.provider.BaseColumns;

public final class ToDoContract {
    private ToDoContract(){}

    public static class ToDoEntry implements BaseColumns {
        public static final String TABLE_NAME = "todo";
        public static final String TODO = "todo";
        public static final String DONE = "done";
    }

}
