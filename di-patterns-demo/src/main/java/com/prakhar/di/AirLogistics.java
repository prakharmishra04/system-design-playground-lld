package com.prakhar.di;

import org.springframework.stereotype.Component;

// Spring Implementation - AirLogistics with @Component annotation
@Component("airLogistics")
public class AirLogistics implements Logistics {
    private Transport transport;

    public AirLogistics() {
        this.transport = new Airplane();
    }

    @Override
    public void planDelivery() {
        transport.deliver();
    }
}
