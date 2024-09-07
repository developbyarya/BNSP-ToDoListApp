package com.example.bnspgradiva_todolist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bnspgradiva_todolist.model.ToDoHelper;
import com.example.bnspgradiva_todolist.model.ToDoParcel;

public class AddTodo extends AppCompatActivity {
    private EditText edt_todo;
    private Button add_button;
    private boolean edit_mode;
    private ToDoHelper toDoHelper;

    private final int STATUS_CODE_SUCCESS = 100;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        toDoHelper = new ToDoHelper(this);

        edit_mode = getIntent().getBooleanExtra("edit_mode", false);

        edt_todo = findViewById(R.id.edt_todo);
        add_button = findViewById(R.id.btn_add);

        add_button.setOnClickListener(add_modify_click);

    }

    private View.OnClickListener add_modify_click = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            String todo = edt_todo.getText().toString();
            if (todo.isEmpty()) {
                Toast.makeText(AddTodo.this, "Jangan ada yang kosong", Toast.LENGTH_SHORT).show();
                return;
            }
            ToDoParcel toDoParcel = new ToDoParcel(todo, false);

            toDoHelper.insertTodo(toDoParcel);

            finish();

        }
    };
}