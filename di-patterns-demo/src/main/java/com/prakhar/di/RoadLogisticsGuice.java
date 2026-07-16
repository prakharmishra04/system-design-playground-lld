package com.prakhar.di;

// Guice Implementation - RoadLogistics
public class RoadLogisticsGuice implements Logistics {
    private Transport transport;

    public RoadLogisticsGuice() {
        this.transport = new Truck();
    }

    @Override
    public void planDelivery() {
        transport.deliver();
    }
}
