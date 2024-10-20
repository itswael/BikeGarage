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
import com.automobiles.bikegarage.services.FirebaseService;

import java.util.ArrayList;
import java.util.List;

public class ServiceFragment extends Fragment {

    private RecyclerView recyclerView;
    private ServiceAdapter serviceAdapter;
    private List<Service> serviceList;
    private FirebaseService firebaseService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_services);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        serviceList = new ArrayList<>();
        serviceAdapter = new ServiceAdapter(serviceList);
        recyclerView.setAdapter(serviceAdapter);

        firebaseService = new FirebaseService();
        retrieveServices(); // Fetch services from Firebase

        return view;
    }

    private void retrieveServices() {
        firebaseService.retrieveServices(serviceAdapter); // Fetch data and update the adapter
    }
}
