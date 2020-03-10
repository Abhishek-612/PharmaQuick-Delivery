package com.example.pharmaquickdelivery.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmaquickdelivery.Order;
import com.example.pharmaquickdelivery.OrderAdapter;
import com.example.pharmaquickdelivery.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    OrderAdapter adapter;

    ArrayList<Order> orderList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        orderList = new ArrayList<>();

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        orderList.add(new Order("1200","abhi","my address","9876543"));
        orderList.add(new Order("1201","qwer","my address1","9876543"));
        orderList.add(new Order("1202","tyui","my address2","9876543"));
        orderList.add(new Order("1203","ghjk","my address3","9876543"));

        adapter = new OrderAdapter(getContext(),orderList);
        recyclerView.setAdapter(adapter);

        return root;
    }
}