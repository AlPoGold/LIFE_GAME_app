package com.example.lifegameapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lifegameapp.model.Board;
import com.example.lifegameapp.model.Tuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button startBtn;
    private Button nextgenbtn;
    private TextView tvCountGen;
    private Button clearBoard;
    private Board board;
    int countGen = 0;
    int[] positions =  new int[400];


    HashMap<Integer, Tuple> coordPos;

    ArrayList<Integer> newCoordinates = new ArrayList<>();
//    ArrayList<Integer> squares = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        initViews();
//        initSquares();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Integer> squares = new ArrayList<>();
        for (int i = 0; i < 400; i++) {
            squares.add(R.drawable.red_square_svgrepo_com);
        }

        recyclerView = findViewById(R.id.recycleView);
        startBtn = findViewById(R.id.btnStart);
        nextgenbtn = findViewById(R.id.nextGenBtn);
        tvCountGen = findViewById(R.id.tvCountGeneration);
        clearBoard = findViewById(R.id.clearBoardBtn);


        String countGenString = getString(R.string.gen_count, countGen);
        tvCountGen.setText(countGenString);


        board = new Board();
        positions = board.getPositions();
        coordPos = board.getMapCoordinates();

        ArrayList<Tuple> initPosition = new ArrayList<>();

        ImageAdapter imageAdapter = new ImageAdapter();
        recyclerView.setAdapter(imageAdapter);
        imageAdapter.setSquares(squares);

        int numberOfColumns = ColumnCount.calculateNoOfColumns(this, 20);
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));


        imageAdapter.setOnCellListener(new ImageAdapter.OnCellListener() {
            @Override
            public void onCellClick(View view) {
                ImageView img = view.findViewById(R.id.imageRect);
                img.setImageResource(R.drawable.green_square_svgrepo_com);

                int position = recyclerView.getChildAdapterPosition(view);
                Tuple tuple = coordPos.get(position);
                initPosition.add(tuple);
                Log.d("POSITION", String.valueOf(position));

            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                board.initWorld(initPosition);
                board.printBoard();
            }
        });

        nextgenbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                board.updateWorld();
                newCoordinates.clear();
                countGen++;
                String countGenString = getString(R.string.gen_count, countGen);
                tvCountGen.setText(countGenString);

                ArrayList<Tuple> aliveCells = board.getAliveCells();
                Log.d("alive_cells", aliveCells.toString());

                for (Tuple tuple: aliveCells
                     ) {
                    for(Integer key :coordPos.keySet()){

                        if(Objects.equals(coordPos.get(key), tuple)) {
                            newCoordinates.add(key);
                        }
                    }
                }

                updateRecycleView();

            }
        });

        clearBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                board.clearBoard();
                newCoordinates.clear();
                updateRecycleView();
                countGen = 0;
                String countGenString = getString(R.string.gen_count, countGen);
                tvCountGen.setText(countGenString);
            }
        });

    }

    private void updateRecycleView() {


        int count = recyclerView.getChildCount();
        for (int i = 0; i < count; i++) {
            RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(i);


            boolean isAlive = false;
            for (int j = 0; j < newCoordinates.size(); j++) {
                if(i==newCoordinates.get(j)) isAlive = true;

            }
            if(holder!=null){
                ImageView img = holder.itemView.findViewById(R.id.imageRect);

                if(isAlive) {
                    img.setImageResource(R.drawable.green_square_svgrepo_com);
                } else img.setImageResource(R.drawable.red_square_svgrepo_com);
            }

        }
    }

    private void initSquares() {
        ArrayList<Integer> squares = new ArrayList<>();
//                (Arrays.asList(
//            R.drawable.red_square_svgrepo_com,R.drawable.red_square_svgrepo_com,
//                R.drawable.red_square_svgrepo_com,R.drawable.red_square_svgrepo_com,
//                R.drawable.red_square_svgrepo_com,R.drawable.red_square_svgrepo_com,
//                R.drawable.red_square_svgrepo_com,R.drawable.red_square_svgrepo_com,
//                R.drawable.red_square_svgrepo_com,R.drawable.red_square_svgrepo_com
//        ));

        for (int i = 0; i < 400; i++) {
            squares.add(R.drawable.red_square_svgrepo_com);
        }
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recycleView);
        startBtn = findViewById(R.id.btnStart);
        nextgenbtn = findViewById(R.id.nextGenBtn);
    }

    private Tuple getCoordinates(int position) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if(positions[i]==position){
                    x = i;
                    y = j;
                }
            }

        }
        Tuple tuple = new Tuple(x, y);
        return tuple;
    }
}