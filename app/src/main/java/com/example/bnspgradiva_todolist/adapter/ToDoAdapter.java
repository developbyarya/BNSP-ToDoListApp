package com.example.bnspgradiva_todolist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bnspgradiva_todolist.R;
import com.example.bnspgradiva_todolist.model.ToDoHelper;
import com.example.bnspgradiva_todolist.model.ToDoParcel;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
    public List<ToDoParcel> todos;
    private boolean onBind = false;

    @NonNull
    @Override
    public ToDoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_layout, parent, false);
        return new ViewHolder(view, onBind);
    }

    public ToDoAdapter(List<ToDoParcel> todoList) {
        this.todos = todoList;
    }
    @Override
    public void onBindViewHolder(@NonNull ToDoAdapter.ViewHolder holder, int position) {
        ToDoParcel todo = todos.get(position);
        holder.textView.setText(todo.getToDo());
        holder.checkBox.setChecked(todo.isDone());

        holder.checkBox.setOnCheckedChangeListener((bView, isCheck) -> {

            todo.setDone(isCheck);
            ToDoHelper toDoHelper = new ToDoHelper(bView.getContext());
            toDoHelper.updateTodo(todo);
            notifyItemChanged(position);
            if (dataChangeListener != null) {
                dataChangeListener.onDataChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        public final CheckBox checkBox;
        private final boolean onBind;

        public ViewHolder(@NonNull View itemView, boolean onBind) {
            super(itemView);
            this.onBind = onBind;

            textView = (TextView) itemView.findViewById(R.id.txt_todo_title);
            checkBox = (android.widget.CheckBox) itemView.findViewById(R.id.cb_done);


        }





    }

    public void updateTaskList(List<ToDoParcel> newTodos) {
        todos.clear();
        todos.addAll(newTodos);
        notifyDataSetChanged();
    }

    public interface OnDataChangeListener {
        void onDataChanged();
    }
    private OnDataChangeListener dataChangeListener;

    public void setOnDataChangeListener(OnDataChangeListener listener) {
        this.dataChangeListener = listener;
    }
}
