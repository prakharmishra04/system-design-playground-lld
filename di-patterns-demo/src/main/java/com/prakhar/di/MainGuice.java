package com.prakhar.di;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;

// Guice Main Demo
public class MainGuice {
    public static void main(String[] args) {
        System.out.println("=== GUICE DEMO - Factory Pattern with DI ===\n");

        // Guice creates an Injector and manages all bindings
        Injector injector = Guice.createInjector(new LogisticsModule());

        // Guice automatically injects the beans based on module configuration
        Logistics roadLogistics = injector.getInstance(
                com.google.inject.Key.get(Logistics.class, Names.named("road")));
        System.out.println("Road Transport (Guice):");
        roadLogistics.planDelivery();

        System.out.println();

        Logistics seaLogistics = injector.getInstance(
                com.google.inject.Key.get(Logistics.class, Names.named("sea")));
        System.out.println("Sea Transport (Guice):");
        seaLogistics.planDelivery();

        System.out.println();

        Logistics airLogistics = injector.getInstance(
                com.google.inject.Key.get(Logistics.class, Names.named("air")));
        System.out.println("Air Transport (Guice):");
        airLogistics.planDelivery();

        System.out.println("\n✅ Guice handled object creation for you!");
    }
}
