package com.example.to_do_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.to_do_application.Adapters.TaskAdapter;
import com.example.to_do_application.Adapters.TaskAdapter;
import com.example.to_do_application.CreateTask;
import com.example.to_do_application.Models.Task;
import com.example.to_do_application.Models.TaskDatabaseHelper;
import com.example.to_do_application.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TaskActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter,completedTaskAdapter;

    private Button newTask, completedTask;
    private List<Task> taskList, completedTaskList;

    private TaskDatabaseHelper taskDatabaseHelper;

    private LinearLayout NoTaskLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_main);


        newTask = findViewById(R.id.onGoing);
        completedTask = findViewById(R.id.completedTasks);


        taskDatabaseHelper = new TaskDatabaseHelper(this);

        NoTaskLayout = findViewById(R.id.noTasksBanner);
            // Initialize RecyclerView and its adapter
        recyclerView = findViewById(R.id.taskRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        displayNewTasks();

        newTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                displayNewTasks();

            }
        });

        completedTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayCompletedTasks();
            }
        });



        SharedPreferences sharedPreferences = getSharedPreferences("App", MODE_PRIVATE);

        TextView T = findViewById(R.id.taskUserText);
        String name = sharedPreferences.getString("userName", "User");

// Add show() to display the Toast
//        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();

        T.setText("Welcome " + name);


        FloatingActionButton createTask = (FloatingActionButton) findViewById(R.id.createFAB);

        createTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CreateTask.class));
            }
        });

    }

    public void updateNoTaskBannerVisibility() {
        if (taskList.size() != 0) {
            NoTaskLayout.setVisibility(View.INVISIBLE);
        } else {
            NoTaskLayout.setVisibility(View.VISIBLE);
        }
    }

    public void displayNewTasks(){
            taskList = new ArrayList<>();

//            SQL for new Tasks



         taskList = taskDatabaseHelper.getTasksByStatus(1);
         taskList.addAll(taskDatabaseHelper.getTasksByStatus(0));



            if(taskList.size() != 0) {
                NoTaskLayout.setVisibility(View.INVISIBLE);
                taskAdapter = new TaskAdapter(this, taskList,this);
                recyclerView.setAdapter(taskAdapter);
            }else{
                NoTaskLayout.setVisibility(View.VISIBLE);
                recyclerView.setAdapter(new TaskAdapter(this,new ArrayList<>(),this));

            }
    }

    public void displayCompletedTasks(){
        completedTaskList = new ArrayList<>();

        completedTaskList = taskDatabaseHelper.getTasksByStatus(2);
//        SQL for Completed

        if(completedTaskList.size() != 0) {
            NoTaskLayout.setVisibility(View.INVISIBLE);
            completedTaskAdapter = new TaskAdapter(this, completedTaskList,this);
            recyclerView.setAdapter(completedTaskAdapter);
        }else{
            NoTaskLayout.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(new TaskAdapter(this,new ArrayList<>(),this));
        }
    }

}
