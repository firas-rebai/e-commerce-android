package com.example.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import model.book;

public class AdapterBookCard extends RecyclerView.Adapter<AdapterBookCard.BookViewHolder> {

    Context context;
    ArrayList<book> bookArrayList;
    private OnItemClicked onClick;


    public AdapterBookCard(Context context, ArrayList<book> bookArrayList) {
        this.context = context;
        this.bookArrayList = bookArrayList;

    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.book_cardview, parent, false);
        return new BookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        book book = bookArrayList.get(position);
        holder.title.setText(book.getTitle());
        holder.price.setText(String.valueOf(book.getPrice()));
        holder.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    public interface OnItemClicked {
        void onItemClick(int position);
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, price;
        RelativeLayout background;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            background = itemView.findViewById(R.id.background_color);
            title = itemView.findViewById(R.id.book_title);
            price = itemView.findViewById(R.id.bookcard_price);
        }

        @Override
        public void onClick(View v) {
        }
    }

    public void setOnClick(OnItemClicked onClick) {
        this.onClick = onClick;
    }
}
