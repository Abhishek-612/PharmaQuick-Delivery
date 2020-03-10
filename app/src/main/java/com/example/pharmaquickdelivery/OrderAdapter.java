package com.example.pharmaquickdelivery;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
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
        final Order order = orderList.get(position);
        holder.orderId.setText("Order ID: "+order.getOrderId());
        holder.name.setText("Name: "+order.getName());
        holder.custAddr.setText("Address: "+order.getCustomerAddress());
        holder.custContact.setText("Contact: "+order.getCustomerContact());

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "selected", Toast.LENGTH_SHORT).show();

                FragmentTransaction ft =  ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                OrderBottomSheetFragment orderBottomSheetFragment = OrderBottomSheetFragment.newInstance();

                Bundle bundle = new Bundle();
                bundle.putSerializable("order_obj", order);
                orderBottomSheetFragment.setArguments(bundle);

                orderBottomSheetFragment.show(((FragmentActivity)context).getSupportFragmentManager(), "");
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder{

        TextView orderId,name,custAddr,custContact;
        Button accept;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            orderId = itemView.findViewById(R.id.order_no);
            name = itemView.findViewById(R.id.order_name);
            custAddr = itemView.findViewById(R.id.order_address);
            custContact = itemView.findViewById(R.id.order_contact);
            accept = itemView.findViewById(R.id.accept_order);

        }

    }

}
