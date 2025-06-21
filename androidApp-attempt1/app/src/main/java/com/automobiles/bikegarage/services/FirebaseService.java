package com.automobiles.bikegarage.services;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.automobiles.bikegarage.models.Service;

import java.util.HashMap;
import java.util.Map;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class FirebaseService {
    private DatabaseReference databaseReference;

    public FirebaseService() {
        databaseReference = FirebaseDatabase.getInstance().getReference("services");
    }

    // Method to add a service
    public void addService(Service service) {
        String serviceId = databaseReference.push().getKey();
        if (serviceId != null) {
            databaseReference.child(serviceId).setValue(service);
        }
    }

    // Method to retrieve services (this is a simple example)
    public void retrieveServices(final ServiceAdapter serviceAdapter) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Service> services = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Service service = snapshot.getValue(Service.class);
                    services.add(service);
                }
                // Notify the adapter with the new data
                serviceAdapter.updateServiceList(services);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }

    // Method to update a service
    public void updateService(String serviceId, Service service) {
        Map<String, Object> updates = new HashMap<>();
        updates.put("customerName", service.getCustomerName());
        updates.put("vehicleNumber", service.getVehicleNumber());
        updates.put("status", service.getStatus());
        updates.put("lastServiceDate", service.getLastServiceDate());
        updates.put("nextServiceDate", service.getNextServiceDate());
        updates.put("kilometersDriven", service.getKilometersDriven());
        updates.put("serviceInterval", service.getServiceInterval());

        databaseReference.child(serviceId).updateChildren(updates);
    }

    // Method to delete a service
    public void deleteService(String serviceId) {
        databaseReference.child(serviceId).removeValue();
    }
}
