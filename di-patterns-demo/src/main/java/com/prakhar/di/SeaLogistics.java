package com.prakhar.di;

import org.springframework.stereotype.Component;

// Spring Implementation - SeaLogistics with @Component annotation
@Component("seaLogistics")
public class SeaLogistics implements Logistics {
    private Transport transport;

    public SeaLogistics() {
        this.transport = new Ship();
    }

    @Override
    public void planDelivery() {
        transport.deliver();
    }
}
