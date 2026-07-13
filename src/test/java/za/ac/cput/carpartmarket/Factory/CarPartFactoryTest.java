package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

 class CarPartFactoryTest {


     @Test
     void createCarPart() {

         CarPart carPart = CarPartFactory.createCarPart(
                 "Brake Pad",
                 "High performance brake pad",
                 "Toyota Corolla",
                 1001L
         );

         assertNotNull(carPart);

         System.out.println(carPart);
     }

     @Test
     void createCarPartWithNullPartName() {

         CarPart carPart = CarPartFactory.createCarPart(
                 "",
                 "High performance brake pad",
                 "Toyota Corolla",
                 1001L
         );

         assertNull(carPart);
     }
 }