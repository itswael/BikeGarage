package com.automobiles.bikegarage.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.automobiles.bikegarage.R;
import com.automobiles.bikegarage.adapters.ServiceAdapter;
import com.automobiles.bikegarage.models.Service;

import java.util.ArrayList;
import java.util.List;

public class InProgressFragment extends Fragment {

    private RecyclerView recyclerView;
    private ServiceAdapter serviceAdapter;
    private List<Service> serviceList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_in_progress, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Sample data
        serviceList = new ArrayList<>();
        serviceList.add(new Service("John Doe", "Bike 123", "In Progress"));
        serviceList.add(new Service("Jane Smith", "Bike 456", "In Progress"));

        serviceAdapter = new ServiceAdapter(serviceList);
        recyclerView.setAdapter(serviceAdapter);

        return view;
    }
}
