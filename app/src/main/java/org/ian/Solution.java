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
    private Map<String, Set<Car>> carsByCategory = new HashMap<>();

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
            return this.carsByColor.getOrDefault(condition.value(), new HashSet<>());
        } else if (condition.field() == Field.CATEGORY) {
            return this.carsByCategory.getOrDefault(condition.value(), new HashSet<>());
        } else {
            return Set.of();
        }
    }

    public Set<Car> findByQuery(QueryNode query) {
        // if condition, then return findByCondition
        if (query instanceof Condition)
            return findByCondition((Condition) query);

        // its an operation
        // get the left side
        Operation current = (Operation) query;
        Set<Car> left = findByQuery(current.left());
        Set<Car> right = findByQuery(current.right());

        // assume OR only here
        if (current.op() == Operator.OR) {
            left.addAll(right);
        } else {
            // AND
            left.retainAll(right);
        }

        return left;
    }

}

// Data format here

record Car(String id, String color, String category) {
}

enum Field {
    ID, COLOR, CATEGORY
}

enum Operator {
    OR, AND
}

// Querying here

interface QueryNode {
}

record Condition(Field field, String value) implements QueryNode {
}

record Operation(QueryNode left, QueryNode right, Operator op) implements QueryNode {
}
