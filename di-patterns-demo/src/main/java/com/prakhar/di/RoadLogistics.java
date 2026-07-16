package com.prakhar.di;

import org.springframework.stereotype.Component;

// Spring Implementation - RoadLogistics with @Component annotation
@Component("roadLogistics")
public class RoadLogistics implements Logistics {
    private Transport transport;

    // Constructor Injection by Spring
    public RoadLogistics() {
        this.transport = new Truck();
    }

    @Override
    public void planDelivery() {
        transport.deliver();
    }
}
