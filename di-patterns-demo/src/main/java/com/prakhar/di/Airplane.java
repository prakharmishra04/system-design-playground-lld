package com.prakhar.di;

public class Airplane implements Transport {
    @Override
    public void deliver() {
        System.out.println("✈️  Delivering by airplane in the sky");
    }
}
