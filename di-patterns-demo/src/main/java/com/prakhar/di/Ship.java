package com.prakhar.di;

public class Ship implements Transport {
    @Override
    public void deliver() {
        System.out.println("⛴️  Delivering by ship on water");
    }
}
