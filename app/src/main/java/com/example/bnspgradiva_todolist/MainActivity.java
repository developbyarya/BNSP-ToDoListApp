package com.example.bnspgradiva_todolist;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bnspgradiva_todolist.adapter.ToDoAdapter;
import com.example.bnspgradiva_todolist.model.ToDoHelper;
import com.example.bnspgradiva_todolist.model.ToDoParcel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ToDoAdapter.OnDataChangeListener {

    private RecyclerView recyclerViewNotDone;
    private RecyclerView recyclerViewDone;
    private ToDoAdapter toDoAdapter;
    private List<ToDoParcel> todos;
    private ToDoHelper toDoHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewNotDone = findViewById(R.id.rv_todo_ongoing);
        recyclerViewNotDone.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDone = findViewById(R.id.rv_todo_done);
        recyclerViewDone.setLayoutManager(new LinearLayoutManager(this));
        toDoHelper = new ToDoHelper(this);

        load_todos();


//        FAB Click Listener
        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddTodo.class);
            intent.putExtra("edit_mode", false);
            startActivity(intent);
        });

    }

    private void load_todos() {

        todos = toDoHelper.getNotDone();
        ToDoAdapter toDoAdapterNotDone = new ToDoAdapter(todos);
        recyclerViewNotDone.setAdapter(toDoAdapterNotDone);
        toDoAdapterNotDone.setOnDataChangeListener(this);

        List<ToDoParcel> todosDone = toDoHelper.getDone();
        ToDoAdapter toDoAdapterDone = new ToDoAdapter(todosDone);
        recyclerViewDone.setAdapter(toDoAdapterDone);
        toDoAdapterDone.setOnDataChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        load_todos();
    }

    @Override
    public void onDataChanged() {
        // Re-fetch data from the database and update UI
        load_todos();
    }



}