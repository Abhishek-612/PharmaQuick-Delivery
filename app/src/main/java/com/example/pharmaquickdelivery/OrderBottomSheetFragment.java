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
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class OrderBottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    TextView id, cName, cAddr, cCont, sName, sAddr, sCont, cost;
    Button accept;
    Order order;

    public static OrderBottomSheetFragment newInstance() {
        return new OrderBottomSheetFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        order = (Order) bundle.getSerializable("order_obj");
        return inflater.inflate(R.layout.order_bottom_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = view.findViewById(R.id.this_order_id);
        cName = view.findViewById(R.id.this_order_name);
        cAddr = view.findViewById(R.id.this_customer_addr);
        cCont = view.findViewById(R.id.this_customer_phone);
        sName = view.findViewById(R.id.this_store_name);
        sAddr = view.findViewById(R.id.this_store_addr);
        sCont = view.findViewById(R.id.this_store_contact);
        cost = view.findViewById(R.id.this_order_cost);
        accept = view.findViewById(R.id.final_accept);


        id.setText(order.getOrderId());
        cName.setText(order.getName());
        cAddr.setText(order.getCustomerAddress());
        cCont.setText(order.getCustomerContact());
        sName.setText(order.getStoreName());
        sAddr.setText(order.getStoreAddress());
        sCont.setText(order.getStoreContact());
        cost.setText(order.getCost()+"");

        accept.setOnClickListener(this);

    }

    @Override public void onClick(View view) {
        Toast.makeText(getContext(), cName.getText().toString()+" Order accepted", Toast.LENGTH_SHORT).show();
        dismiss();
    }

}
