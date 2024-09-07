package com.example.bnspgradiva_todolist.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.bnspgradiva_todolist.model.ToDoContract.ToDoEntry.DONE;
import static com.example.bnspgradiva_todolist.model.ToDoContract.ToDoEntry.TABLE_NAME;
import static com.example.bnspgradiva_todolist.model.ToDoContract.ToDoEntry.TODO;

public class ToDoHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ToDo";

    public ToDoHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    private static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s " +
                    "(%s INTEGER PRIMARY KEY,"+
                    "%s TEXT, %s INTEGER)",
            TABLE_NAME, ToDoContract.ToDoEntry._ID, ToDoContract.ToDoEntry.TODO, ToDoContract.ToDoEntry.DONE);
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public long insertTodo(ToDoParcel toDoParcel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ToDoContract.ToDoEntry.TODO, toDoParcel.getToDo());
        values.put(ToDoContract.ToDoEntry.DONE, toDoParcel.isDone());

        return db.insert(TABLE_NAME, null, values);
    }

    public List<ToDoParcel> getAllTodos() {
        List<ToDoParcel> todos = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM todo", null);
        if (cursor.moveToFirst()) {
            do {
                String todo = cursor.getString(cursor.getColumnIndexOrThrow(TODO));
                boolean isDone;
                if (cursor.getInt(cursor.getColumnIndexOrThrow(DONE)) > 0) {
                    isDone = true;
                } else {
                    isDone = false;
                }
                todos.add(new ToDoParcel(todo, isDone));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return todos;

    }

    public List<ToDoParcel> getNotDone() {
        List<ToDoParcel> todos = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM todo WHERE done=0", null);
        if (cursor.moveToFirst()) {
            do {
                String todo = cursor.getString(cursor.getColumnIndexOrThrow(TODO));
                boolean isDone;
                if (cursor.getInt(cursor.getColumnIndexOrThrow(DONE)) > 0) {
                    isDone = true;
                } else {
                    isDone = false;
                }
                todos.add(new ToDoParcel(todo, isDone));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return todos;
    }

    public List<ToDoParcel> getDone() {
        List<ToDoParcel> todos = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM todo WHERE done>0", null);
        if (cursor.moveToFirst()) {
            do {
                String todo = cursor.getString(cursor.getColumnIndexOrThrow(TODO));
                boolean isDone;
                if (cursor.getInt(cursor.getColumnIndexOrThrow(DONE)) > 0) {
                    isDone = true;
                } else {
                    isDone = false;
                }
                todos.add(new ToDoParcel(todo, isDone));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return todos;
    }

    public void updateTodo(ToDoParcel toDoParcel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(ToDoContract.ToDoEntry.TODO, toDoParcel.getToDo());
        value.put(ToDoContract.ToDoEntry.DONE, toDoParcel.isDone());
        db.update(TABLE_NAME, value, ToDoContract.ToDoEntry.TODO + " = ?", new String[]{toDoParcel.getToDo()});
        db.close();

    }



}
