package com.prakhar.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// Spring Main Demo
public class MainSpring {
    public static void main(String[] args) {
        System.out.println("=== SPRING DEMO - Factory Pattern with DI ===\n");

        // Spring creates the ApplicationContext and manages all @Component beans
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        // Spring automatically injects the beans
        Logistics roadLogistics = context.getBean("roadLogistics", Logistics.class);
        System.out.println("Road Transport (Spring):");
        roadLogistics.planDelivery();

        System.out.println();

        Logistics seaLogistics = context.getBean("seaLogistics", Logistics.class);
        System.out.println("Sea Transport (Spring):");
        seaLogistics.planDelivery();

        System.out.println();

        Logistics airLogistics = context.getBean("airLogistics", Logistics.class);
        System.out.println("Air Transport (Spring):");
        airLogistics.planDelivery();

        System.out.println("\n✅ Spring handled object creation for you!");
    }
}
