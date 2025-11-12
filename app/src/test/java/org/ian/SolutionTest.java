package org.ian;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class SolutionTest {

    private Solution sampleData1() {
        Solution solution = new Solution();

        solution.addCar(new Car("001", "red", Category.COMPACT));
        solution.addCar(new Car("002", "blue", Category.COMPACT));
        solution.addCar(new Car("003", "green", Category.SEDAN));
        solution.addCar(new Car("004", "red", Category.SUV));
        solution.addCar(new Car("005", "black", Category.COUPE));
        solution.addCar(new Car("006", "black", Category.COUPE));
        return solution;
    }

    /**
     * Find a car by id
     */
    @Test
    void findItem() {
        Solution data = this.sampleData1();

        Set<Car> result = data.findByCondition(new Condition(Field.ID, "001"));
        assertEquals(1, result.size());
        assertEquals("red", result.iterator().next().color());
    }

    /**
     * Find a single car by condition
     */
    @Test
    void findItemByColor() {
        Solution data = this.sampleData1();

        Set<Car> result = data.findByCondition(new Condition(Field.COLOR, "blue"));
        assertEquals(1, result.size());
        assertEquals("002", result.iterator().next().id());
    }

    /**
     * Find multiple cars using a condition
     */
    @Test
    void findItemByColorMulti() {
        Solution data = this.sampleData1();

        Set<Car> result = data.findByCondition(new Condition(Field.COLOR, "red"));
        assertEquals(2, result.size());

        System.out.println("here");
        for (Car car : result) {
            assertEquals("red", car.color());
        }
    }

}
