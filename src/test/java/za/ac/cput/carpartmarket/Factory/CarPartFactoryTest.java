package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.CarPart;

import static org.junit.jupiter.api.Assertions.*;

 class CarPartFactoryTest {

     private static CarPart carPart = CarPartFactory.createCarPart("73637372L", "side Mirror", "white Toyota Corolla", 150.00, 10, "Toyota Corolla", 1001L, "2001L", "Approved", "IMG", "Used");

     @Test
     public void createCarPart() {
         assertNotNull(carPart);
         assertEquals("Used", carPart.getPartCondition());
         System.out.println(carPart);
     }

     @Test
     public void createCarPartWithNullDescription() {
         CarPart invalid = CarPartFactory.createCarPart(
                 "73637373L", "side Mirror", null, 150.00, 10,
                 "Toyota Corolla", 1001L, "2001L", "Approved", "IMG", "Used"
         );
         assertNull(invalid);
     }

     @Test
     void createCarPartWithInvalidCondition() {
         CarPart invalid = CarPartFactory.createCarPart(
                 "73637374L", "side Mirror", "white Toyota Corolla", 150.00, 10,
                 "Toyota Corolla", 1001L, "2001L", "Approved", "IMG", "Broken");
         assertNull(invalid);
     }

     @Test
     void createCarPartWithNullCondition() {
         CarPart invalid = CarPartFactory.createCarPart(
                 "73637375L", "side Mirror", "white Toyota Corolla", 150.00, 10,
                 "Toyota Corolla", 1001L, "2001L", "Approved", "IMG", null);
         assertNull(invalid);
     }
 }