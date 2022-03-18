package com.example.interactive_programing_assignment;

import static java.lang.Integer.parseInt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import databaseModule.Db;
import student_model.Product;


public class MainActivity extends AppCompatActivity {
    private EditText product_name, buying, selling;
    private Button btn_add, btn_view;
    private TextView error;
    private Db database;
    private Uri uri;
    private ImageView chooser;
    private Product product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new Db(MainActivity.this);
        btn_add = findViewById(R.id.add);
        btn_view = findViewById(R.id.view);
        product_name = findViewById(R.id.product_name);
        selling = findViewById(R.id.selling);
        buying = findViewById(R.id.buying);
        error=findViewById(R.id.img_error);
        chooser=findViewById(R.id.chooser);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputdata();

            }


        });


        chooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery=new Intent();
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                startActivityForResult(gallery,4);
            }
        });




        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, View_recodes.class);
                startActivity(intent);
            }
        });

    }

    public void inputdata() {
        String productn = product_name.getText().toString().toUpperCase();
        String  buying_price = buying.getText().toString();
        String selling_price =selling.getText().toString();
        product = new Product(productn, buying_price, selling_price, uri);
        if (!productn.isEmpty()) {
            if (!buying.getText().toString().isEmpty()) {
                if (!selling.getText().toString().isEmpty()) {

                    if(uri!=null){
                        boolean insert = database.post(product);
                        if (insert == true) {
                            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                            reset();
                        } else Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        error.setError("please provide image");
                    }
                } else selling.setError("this is required");
            } else buying.setError("this is required");
        } else product_name.setError("this is required");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==4 && data!=null){
            uri=data.getData();
            chooser.setImageURI(uri);
        }
    }

    public void reset() {
        product_name.setText("");
        selling.setText("");
        buying.setText("");
    }
}