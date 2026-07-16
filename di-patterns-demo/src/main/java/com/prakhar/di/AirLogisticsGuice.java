package com.prakhar.di;

// Guice Implementation - AirLogistics
public class AirLogisticsGuice implements Logistics {
    private Transport transport;

    public AirLogisticsGuice() {
        this.transport = new Airplane();
    }

    @Override
    public void planDelivery() {
        transport.deliver();
    }
}
