package com.example.lifegameapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {

    private ArrayList<Integer> squares = new ArrayList<>();

    public void setOnCellListener(OnCellListener onCellListener) {
        this.onCellListener = onCellListener;
    }

    private OnCellListener onCellListener;

    public void setSquares(ArrayList<Integer> squares){
        this.squares = squares;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.image_rect,
                parent,
                false
        );
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageHolder viewHolder, int position) {

        Integer intImage = squares.get(position);
        viewHolder.imageView.setImageResource(intImage);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCellListener.onCellClick(v);
            }
        });


    }

    @Override
    public int getItemCount() {
        return squares.size();
    }

    static class ImageHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageRect);
        }
    }

    interface OnCellListener{
        void onCellClick(View view);
    }
}
