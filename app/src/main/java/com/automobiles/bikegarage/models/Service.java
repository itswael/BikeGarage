package com.automobiles.bikegarage.models;

public class Service {
    private String customerName;
    private String vehicleNumber;
    private String status;
    private String lastServiceDate; // to store the last service date
    private String nextServiceDate; // to store the next service date
    private int kilometersDriven; // to track the kilometers driven since the last service
    private int serviceInterval; // to specify how many kilometers can be driven before the next service

    // Constructor
    public Service(String customerName, String vehicleNumber, String status,
                   String lastServiceDate, String nextServiceDate,
                   int kilometersDriven, int serviceInterval) {
        this.customerName = customerName;
        this.vehicleNumber = vehicleNumber;
        this.status = status;
        this.lastServiceDate = lastServiceDate;
        this.nextServiceDate = nextServiceDate;
        this.kilometersDriven = kilometersDriven;
        this.serviceInterval = serviceInterval;
    }

    // Getters
    public String getCustomerName() {
        return customerName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getStatus() {
        return status;
    }

    public String getLastServiceDate() {
        return lastServiceDate;
    }

    public String getNextServiceDate() {
        return nextServiceDate;
    }

    public int getKilometersDriven() {
        return kilometersDriven;
    }

    public int getServiceInterval() {
        return serviceInterval;
    }

    // Setters
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLastServiceDate(String lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

    public void setNextServiceDate(String nextServiceDate) {
        this.nextServiceDate = nextServiceDate;
    }

    public void setKilometersDriven(int kilometersDriven) {
        this.kilometersDriven = kilometersDriven;
    }

    public void setServiceInterval(int serviceInterval) {
        this.serviceInterval = serviceInterval;
    }
}
