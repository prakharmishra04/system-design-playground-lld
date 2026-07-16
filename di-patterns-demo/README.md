# DI Patterns Demo - Factory Pattern with Spring & Guice

This project demonstrates the **Factory Pattern** and how it's abstracted by Dependency Injection frameworks like **Spring** and **Guice**.

## Project Structure

```
di-patterns-demo/
├── pom.xml                          # Maven configuration with Spring & Guice dependencies
├── src/main/java/com/prakhar/di/
│   ├── Transport.java               # Product interface
│   ├── Truck.java, Ship.java, Airplane.java  # Concrete products
│   ├── Logistics.java               # Creator interface
│   ├── RoadLogistics.java           # Spring implementation
│   ├── SeaLogistics.java            # Spring implementation
│   ├── AirLogistics.java            # Spring implementation
│   ├── RoadLogisticsGuice.java      # Guice implementation
│   ├── SeaLogisticsGuice.java       # Guice implementation
│   ├── AirLogisticsGuice.java       # Guice implementation
│   ├── LogisticsModule.java         # Guice Module (configuration)
│   ├── MainSpring.java              # Spring demo
│   └── MainGuice.java               # Guice demo
```

## Key Concepts

### 1. Manual Factory Pattern (from FactoryMethod.java)
```java
Logistics roadLogistics = new RoadLogistics();
roadLogistics.planDelivery();
```
**You create objects manually** - you decide when and where to instantiate classes.

### 2. Spring (Dependency Injection)
```java
@Component("roadLogistics")
public class RoadLogistics implements Logistics { }

// In main:
ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
Logistics roadLogistics = context.getBean("roadLogistics", Logistics.class);
roadLogistics.planDelivery();
```
**Spring creates objects for you** - mark classes with `@Component`, and Spring manages instantiation.

### 3. Guice (Dependency Injection)
```java
public class LogisticsModule extends AbstractModule {
    protected void configure() {
        bind(Logistics.class).annotatedWith(Named.named("road")).to(RoadLogisticsGuice.class);
    }
}

// In main:
Injector injector = Guice.createInjector(new LogisticsModule());
Logistics roadLogistics = injector.getInstance(Key.get(Logistics.class, Names.named("road")));
roadLogistics.planDelivery();
```
**Guice creates objects based on module configuration** - you define bindings, Guice instantiates.

## How to Build and Run

### Step 1: Navigate to Project Directory
```bash
cd di-patterns-demo
```
**Explanation:** Changes directory to the Maven project.

### Step 2: Clean and Build the Project
```bash
mvn clean install
```
**Explanation:** 
- `mvn clean` - Deletes the `target/` directory (removes old builds)
- `mvn install` - Compiles the Java files and creates JAR in `target/` directory

### Step 3: Download and Copy Dependencies
```bash
mvn dependency:copy-dependencies
```
**Explanation:** Copies all Maven dependencies (Spring, Guice, etc.) to `target/dependency/` folder. This is required because we need them in the classpath to run the demos.

### Step 4: Run Spring Demo
```bash
java -cp "target\classes;target\dependency\*" com.prakhar.di.MainSpring
```
**Explanation:**
- `java` - Java runtime
- `-cp` - Classpath flag (tells Java where to find classes and libraries)
- `"target\classes;target\dependency\*"` - Add compiled classes and all JAR files from dependencies
- `com.prakhar.di.MainSpring` - Fully qualified class name to execute

**Output:**
```
=== SPRING DEMO - Factory Pattern with DI ===

Road Transport (Spring):
🚚 Delivering by truck on land

Sea Transport (Spring):
⛴️  Delivering by ship on water

Air Transport (Spring):
✈️  Delivering by airplane in the sky

✅ Spring handled object creation for you!
```

### Step 5: Run Guice Demo
```bash
java -cp "target\classes;target\dependency\*" com.prakhar.di.MainGuice
```
**Explanation:**
- Same as Spring demo, but runs the Guice implementation

**Output:**
```
=== GUICE DEMO - Factory Pattern with DI ===

Road Transport (Guice):
🚚 Delivering by truck on land

Sea Transport (Guice):
⛴️  Delivering by ship on water

Air Transport (Guice):
✈️  Delivering by airplane in the sky

✅ Guice handled object creation for you!
```

### Quick Reference (One-Liner)
```bash
cd di-patterns-demo && mvn clean install && mvn dependency:copy-dependencies && java -cp "target\classes;target\dependency\*" com.prakhar.di.MainSpring
```
This builds and runs the Spring demo in one command.

## Comparison Table

| Aspect | Manual Factory | Spring | Guice |
|--------|---|---|---|
| Object Creation | Manual: `new RoadLogistics()` | Automatic via `@Component` | Automatic via Module |
| Configuration | Hard-coded in code | Annotations: `@Component`, `@Autowired` | Module: `bind().to()` |
| Flexibility | Low - hard to change implementations | High - swap implementations easily | High - swap via module |
| Boilerplate | More code needed | Less code with annotations | Less code with modules |
| Learning Curve | Simple, straightforward | Moderate | Moderate |
| Use Case | Learning, simple projects | Enterprise apps, Spring Boot | Lightweight projects, Android |

## Key Takeaways

1. **Manual Factory**: You control everything but write more code
2. **Spring**: Less code, annotations handle object creation
3. **Guice**: Lightweight, explicit configuration through modules

Both Spring and Guice **implement the Factory pattern internally** - you just use simpler abstractions!

## Next Steps for Practice

1. Add constructor injection: `@Autowired Transport transport`
2. Create custom qualifiers with `@Qualifier`
3. Use `@Scope` for singleton vs prototype beans
4. Add properties and see how DI frameworks inject dependencies
5. Create factory methods with `@Bean` in Spring or `@Provides` in Guice
