package org.ian;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the solution
 */
public class Solution {
    private static final Logger log = LoggerFactory.getLogger(Solution.class);

    // full list by ID
    private Map<String, Car> cars = new HashMap<>();
    // list by project
    private Map<String, Set<Car>> carsByColor = new HashMap<>();
    // list by type
    private Map<Category, Set<Car>> carsByCategory = new HashMap<>();

    public void addCar(Car car) {
        cars.put(car.id(), car); // store the car

        // store in color index
        Set<Car> colorSet = carsByColor.computeIfAbsent(car.color(), key -> new HashSet<>());
        colorSet.add(car);

        // store in category index
        Set<Car> categorySet = carsByCategory.computeIfAbsent(car.category(), key -> new HashSet<>());
        categorySet.add(car);

    }

    public Set<Car> findByCondition(Condition condition) {
        if (condition.field() == Field.ID) {
            if (this.cars.containsKey(condition.value())) {
                return Set.of(this.cars.get(condition.value()));
            } else {
                return Set.of();
            }
        } else if (condition.field() == Field.COLOR) {
            return this.carsByColor.get(condition.value());
        } else if (condition.field() == Field.CATEGORY) {
            Category searchCategory = Category.valueOf(condition.value());
            return this.carsByCategory.get(searchCategory);
        } else {
            return Set.of();
        }
    }

}

record Car(String id, String color, Category category) {
}

enum Field {
    ID, COLOR, CATEGORY
}

enum Category {
    COMPACT, SUV, COUPE, SEDAN
}

interface QueryNode {
}

record Condition(Field field, String value) implements QueryNode {
}

record Operation(QueryNode left, QueryNode right) implements QueryNode {
}
