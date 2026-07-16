// Factory Method Pattern - Demonstrates object creation without specifying concrete classes

// Product interface
interface Transport {
    void deliver();
}

// Concrete Products
class Truck implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering by truck on land");
    }
}

class Ship implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering by ship on water");
    }
}

class Airplane implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering by airplane in the sky");
    }
}

// Creator abstract class
abstract class Logistics {
    abstract Transport createTransport();
    
    public void planDelivery() {
        Transport transport = createTransport();
        transport.deliver();
    }
}

// Concrete Creators
class RoadLogistics extends Logistics {
    @Override
    Transport createTransport() {
        return new Truck();
    }
}

class SeaLogistics extends Logistics {
    @Override
    Transport createTransport() {
        return new Ship();
    }
}

class AirLogistics extends Logistics {
    @Override
    Transport createTransport() {
        return new Airplane();
    }
}

// Main class to demonstrate Factory Method Pattern
public class FactoryMethod {
    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern Demo ===\n");
        
        // Using Road Logistics
        Logistics roadLogistics = new RoadLogistics();
        System.out.println("Road Transport:");
        roadLogistics.planDelivery();
        
        System.out.println();
        
        // Using Sea Logistics
        Logistics seaLogistics = new SeaLogistics();
        System.out.println("Sea Transport:");
        seaLogistics.planDelivery();
        
        System.out.println();
        
        // Using Air Logistics
        Logistics airLogistics = new AirLogistics();
        System.out.println("Air Transport:");
        airLogistics.planDelivery();
    }
}