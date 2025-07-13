package com.automobiles.bikegarage.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.automobiles.bikegarage.R;
import com.automobiles.bikegarage.models.Service;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    private List<Service> serviceList;

    public ServiceAdapter(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Service service = serviceList.get(position);
        holder.customerNameTextView.setText(service.getCustomerName());
        holder.vehicleNumberTextView.setText(service.getVehicleNumber());
        holder.statusTextView.setText(service.getStatus());

        // Set a click listener to handle item clicks
        holder.itemView.setOnClickListener(v -> {
            // TODO: Handle the click event to show service details
        });
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    // Method to update the service list and notify the adapter
    public void updateServiceList(List<Service> newServiceList) {
        this.serviceList.clear();
        this.serviceList.addAll(newServiceList);
        notifyDataSetChanged();
    }

    static class ServiceViewHolder extends RecyclerView.ViewHolder {
        TextView customerNameTextView;
        TextView vehicleNumberTextView;
        TextView statusTextView;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            customerNameTextView = itemView.findViewById(R.id.text_customer_name);
            vehicleNumberTextView = itemView.findViewById(R.id.text_vehicle_number);
            statusTextView = itemView.findViewById(R.id.text_status);
        }
    }
}
