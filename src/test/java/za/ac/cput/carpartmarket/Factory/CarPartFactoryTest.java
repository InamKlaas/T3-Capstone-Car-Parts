package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.CarPart;

import static org.junit.jupiter.api.Assertions.*;

 class CarPartFactoryTest {

     private static CarPart carPart = CarPartFactory.createCarPart("73637372L", "side Mirror", "white Toyota Corolla", 150.00, 10, "Toyota Corolla", 1001L, "2001L");

     @Test
     public void createCarPart() {
         assertNotNull(carPart);
         System.out.println(carPart.toString());
     }

     @Test
     public void createCarPartWithNullDescription() {
         assertNotNull(carPart);
         System.out.println(carPart.toString());
     }
 }