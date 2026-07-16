package com.prakhar.di;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

// Guice Module - Configures how to create objects
public class LogisticsModule extends AbstractModule {

    @Override
    protected void configure() {
        // Bind interfaces to implementations
        bind(Logistics.class)
                .annotatedWith(Names.named("road"))
                .to(RoadLogisticsGuice.class);

        bind(Logistics.class)
                .annotatedWith(Names.named("sea"))
                .to(SeaLogisticsGuice.class);

        bind(Logistics.class)
                .annotatedWith(Names.named("air"))
                .to(AirLogisticsGuice.class);
    }
}
