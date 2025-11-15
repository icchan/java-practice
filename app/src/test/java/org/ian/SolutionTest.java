package org.ian;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class SolutionTest {

    private Solution sampleData1() {
        Solution solution = new Solution();

        solution.addCar(new Car("001", "red", "compact"));
        solution.addCar(new Car("002", "blue", "compact"));
        solution.addCar(new Car("003", "green", "sedan"));
        solution.addCar(new Car("004", "red", "suv"));
        solution.addCar(new Car("005", "black", "coupe"));
        solution.addCar(new Car("006", "black", "coupe"));
        return solution;
    }

    /**
     * Find a car by id
     */
    @Test
    void findItem() {
        Solution data = this.sampleData1();

        assertDoesNotThrow(() -> {
            Set<Car> result = data.findByCondition(new Condition(Field.ID, "001"));
            assertEquals(1, result.size());
            assertEquals("red", result.iterator().next().color());
        });
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

        for (Car car : result) {
            assertEquals("red", car.color());
        }
    }

    @Test
    void findItemByOr() {
        Solution data = this.sampleData1();

        // return blue or suv (expected 2 or 4)
        Operation query = new Operation(
                new Condition(Field.COLOR, "blue"),
                new Condition(Field.CATEGORY, "suv"),
                Operator.OR);

        Set<Car> result = data.findByQuery(query);
        assertEquals(2, result.size());

        for (Car car : result) {
            System.out.println(car.id());
        }
    }

    @Test
    void findItemByAnd() {
        Solution data = this.sampleData1();

        // return red and compact (expected 1)
        Operation query = new Operation(
                new Condition(Field.COLOR, "red"),
                new Condition(Field.CATEGORY, "compact"),
                Operator.AND);

        Set<Car> result = data.findByQuery(query);
        assertEquals(1, result.size());

        for (Car car : result) {
            System.out.println(car.id());
        }
    }

    @Test
    void findItemByNestedQuery() {
        //
    }
}
