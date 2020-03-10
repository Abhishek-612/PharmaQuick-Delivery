package com.example.pharmaquickdelivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context context;
    ArrayList<Order> orderList;

    public OrderAdapter(Context context, ArrayList<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }


    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_card_layout,null);
        OrderViewHolder holder = new OrderViewHolder(view);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.orderId.setText("Order ID: "+order.getOrderId());
        holder.name.setText("Name: "+order.getName());
        holder.custAddr.setText("Address: "+order.getCustomerAddress());
        holder.custContact.setText("Contact: "+order.getCustomerContact());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder{

        TextView orderId,name,custAddr,custContact;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            orderId = itemView.findViewById(R.id.order_no);
            name = itemView.findViewById(R.id.order_name);
            custAddr = itemView.findViewById(R.id.order_address);
            custContact = itemView.findViewById(R.id.order_contact);
        }
    }

}
