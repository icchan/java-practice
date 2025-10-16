package org.ian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the solution
 */
public class Solution {
    private static final Logger log = LoggerFactory.getLogger(Solution.class);

    Map<String, WorkItem> items = new HashMap<>();

    public void addItem(String key, WorkItem item) {
        items.put(key, item);
    }

    public List<WorkItem> find(String field, String value) {
        List<WorkItem> results = new ArrayList<>();

        for (Entry<String, WorkItem> entry : this.items.entrySet()) {
            if (field == "key") {
                if (entry.getKey() == value) {
                    results.add(entry.getValue());
                }
            } else if (field == "project") {
                if (entry.getValue().project() == value) {
                    results.add(entry.getValue());
                }
            } else if (field == "type") {
                if (entry.getValue().type() == value) {
                    results.add(entry.getValue());
                }
            }
        }

        return results;
    }

    public List<WorkItem> findAnd(ComparisonNode[] comparisons) {
        // get items for first comparion
        // iterate the comparisons
        // AND for each, only keep items in both lists
        // OR for each, merge lists

        List<WorkItem> results = new ArrayList<>();

        for (ComparisonNode node : comparisons) {

            List<WorkItem> comparisonResult = new ArrayList<>();

            for (Entry<String, WorkItem> entry : this.items.entrySet()) {
                if (node.getKey() == "key") {
                    if (entry.getKey() == node.getValue()) {
                        comparisonResult.add(entry.getValue());
                    }
                } else if (node.getKey() == "project") {
                    if (entry.getValue().project() == node.getValue()) {
                        comparisonResult.add(entry.getValue());
                    }
                } else if (node.getKey() == "type") {
                    if (entry.getValue().type() == node.getValue()) {
                        comparisonResult.add(entry.getValue());
                    }
                }
            }

        }
        // keep only items in all the result lists.

        return null;
    }

    /**
     * Runs the solution
     */
    public void run() {
        log.info("ran solution");

    }
}

// TODO move to a new file
record WorkItem(String key, String project, String type) {
}

interface Node {
}

class ComparisonNode implements Node {
    private String key;
    private String value;

    public ComparisonNode(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }
}

class OperationNode implements Node {
    String operator; // OR or AND
    Node left;
    Node right;

    public OperationNode(String operator, Node left, Node right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }
}
