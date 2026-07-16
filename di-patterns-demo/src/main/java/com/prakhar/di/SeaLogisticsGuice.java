package com.prakhar.di;

// Guice Implementation - SeaLogistics
public class SeaLogisticsGuice implements Logistics {
    private Transport transport;

    public SeaLogisticsGuice() {
        this.transport = new Ship();
    }

    @Override
    public void planDelivery() {
        transport.deliver();
    }
}
