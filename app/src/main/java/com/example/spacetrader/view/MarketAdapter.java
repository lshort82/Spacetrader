package com.example.spacetrader.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MarketViewHolder> {

        /** a copy of the list of students in the model */
        List<Item> inventory = new ArrayList<Item>();
        List<Integer> quantity  = new ArrayList<Integer>();
        List<Integer> price  = new ArrayList<Integer>();
        /** a listener for a touch event on the student */
        private OnItemClickListener listener;

        @NonNull
        @Override
        public MarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

            // hook up to the view for a single student in the system
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item, parent, false);

            return new MarketViewHolder(itemView);
        }


        @Override
        public void onBindViewHolder(@NonNull MarketViewHolder holder, int position) {

            //bind the student data for one student
            holder.name.setText(inventory.get(position).getName());
            holder.quant.setText("Quantity:" + quantity.get(position).toString());
            holder.price.setText("Price:" + price.get(position).toString());

        }

        @Override
        public int getItemCount() {
            return inventory.size();
        }

        public void setLists(List<Item> items, List<Integer>quantitys, List<Integer> prices) {
            inventory = items;
            quantity = quantitys;
            price = prices;
            notifyDataSetChanged();
        }


        /**
         * This is a holder for the widgets associated with a single entry in the list of students
         */
        class MarketViewHolder extends RecyclerView.ViewHolder {
            private TextView name;
            private TextView quant;
            private TextView price;



            public MarketViewHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.text_item_name);
                quant= itemView.findViewById(R.id.text_item_quantity);
                price = itemView.findViewById(R.id.text_item_price);

                itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        int position = getAdapterPosition();

                        if (listener != null && position != RecyclerView.NO_POSITION) {
                            listener.onItemClicked(inventory.get(position),quantity.get(position));
                        }
                    }
                });

            }
        }

        public interface OnItemClickListener {
            void onItemClicked(Item item,int quant);
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
    }
