package com.example.spaceTrader.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spaceTrader.R;
import com.example.spaceTrader.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MarketViewHolder> {

        /** a copy of the list of students in the model */
        private List<Item> inventory = new ArrayList<>();
        private List<Integer> quantity  = new ArrayList<>();
        private List<Integer> price  = new ArrayList<>();
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
            String quantityString = "Quantity:" + quantity.get(position).toString();
            holder.quantity.setText(quantityString);
            String priceString = "Price:" + price.get(position).toString();
            holder.price.setText(priceString);

        }

        @Override
        public int getItemCount() {
            return inventory.size();
        }

        public void setLists(List<Item> items, List<Integer> quantities, List<Integer> prices) {
            inventory = items;
            quantity = quantities;
            price = prices;
            notifyDataSetChanged();
        }


        /**
         * This is a holder for the widgets associated with a single entry in the list of students
         */
        class MarketViewHolder extends RecyclerView.ViewHolder {
            private final TextView name;
            private final TextView quantity;
            private final TextView price;



            public MarketViewHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.text_item_name);
                quantity = itemView.findViewById(R.id.text_item_quantity);
                price = itemView.findViewById(R.id.text_item_price);

                itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        int position = getAdapterPosition();

                        if (listener != null && position != RecyclerView.NO_POSITION) {
                            listener.onItemClicked(inventory.get(position), MarketAdapter.this.quantity.get(position));
                        }
                    }
                });

            }
        }

        public interface OnItemClickListener {
            void onItemClicked(Item item, int quantity);
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
    }
