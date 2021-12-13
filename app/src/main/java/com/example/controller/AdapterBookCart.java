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

public class AdapterBookCart extends RecyclerView.Adapter<AdapterBookCart.CartViewHolder> {

    Context context;
    ArrayList<book> bookArrayList;
    private OnItemClicked onClick;


    public AdapterBookCart(Context context, ArrayList<book> bookArrayList) {
        this.context = context;
        this.bookArrayList = bookArrayList;

    }

    @NonNull
    @Override
    public AdapterBookCart.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cart_bookview, parent, false);
        return new AdapterBookCart.CartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBookCart.CartViewHolder holder, int position) {
        book book = bookArrayList.get(holder.getAdapterPosition());
        holder.title.setText(book.getTitle());
        holder.price.setText(String.valueOf(book.getPrice()));
        holder.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(holder.getAdapterPosition());

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

    public static class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, price;
        RelativeLayout background;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            background = itemView.findViewById(R.id.cart_background);
            title = itemView.findViewById(R.id.cart_title);
            price = itemView.findViewById(R.id.cart_price);
        }

        @Override
        public void onClick(View v) {
        }
    }

    public void setOnClick(OnItemClicked onClick) {
        this.onClick = onClick;
    }


}
