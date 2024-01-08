package com.example.lifegameapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ArrayList<Integer> squares = new ArrayList<>();
//                (Arrays.asList(
//            R.drawable.red_square_svgrepo_com,R.drawable.red_square_svgrepo_com,
//                R.drawable.red_square_svgrepo_com,R.drawable.red_square_svgrepo_com,
//                R.drawable.red_square_svgrepo_com,R.drawable.red_square_svgrepo_com,
//                R.drawable.red_square_svgrepo_com,R.drawable.red_square_svgrepo_com,
//                R.drawable.red_square_svgrepo_com,R.drawable.red_square_svgrepo_com
//        ));

        for (int i = 0; i < 1000; i++) {
            squares.add(R.drawable.red_square_svgrepo_com);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycleView);

        ImageAdapter imageAdapter = new ImageAdapter();
        recyclerView.setAdapter(imageAdapter);
        imageAdapter.setSquares(squares);

        int numberOfColumns = ColumnCount.calculateNoOfColumns(this, 20);
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

    }
}