package com.example.interactive_programing_assignment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import adapter.Myadapter;
import databaseModule.Db;
import student_model.Product;


public class View_recodes extends AppCompatActivity {
    private Db database;
    ArrayList<Product> products;
    FloatingActionButton back;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recodes);
        database = new Db(this);
        back = findViewById(R.id.back);
        products = database.fetch();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        adapter = new Myadapter(products, this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(View_recodes.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
