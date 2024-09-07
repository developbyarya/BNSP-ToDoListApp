package com.example.bnspgradiva_todolist.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ToDoParcel implements Parcelable {
    private String toDo;
    private boolean done;
    private int id;

    public ToDoParcel(int id, String toDo, boolean done) {
        this.id = id;
        this.toDo = toDo;
        this.done = done;
    }

    protected ToDoParcel(Parcel in) {
        toDo = in.readString();
        done = in.readByte() != 0;
    }

    public static final Creator<ToDoParcel> CREATOR = new Creator<ToDoParcel>() {
        @Override
        public ToDoParcel createFromParcel(Parcel in) {
            return new ToDoParcel(in);
        }

        @Override
        public ToDoParcel[] newArray(int size) {
            return new ToDoParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(toDo);
        parcel.writeByte((byte) (done ? 1 : 0));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }
}
