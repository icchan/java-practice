package org.ian;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class SolutionTest {

    @Test
    void noExceptionRun() {
        Solution solution = new Solution();

        // check it does not throw an exception
        assertDoesNotThrow(() -> solution.run());
    }

    @Test
    void easyFind() {
        Solution solution = new Solution();

        solution.addItem("ABC", new WorkItem("ABC", "ABC-1", "Task"));

        List<WorkItem> result = solution.find("key", "ABC");

        assertEquals(1, result.size());
    }

    @Test
    void notFound() {
        Solution solution = new Solution();

        solution.addItem("ABC", new WorkItem("ABC", "ABC-1", "Task"));

        List<WorkItem> result = solution.find("key", "XYZ");

        assertEquals(0, result.size());
    }

    @Test
    void findProject() {
        Solution solution = new Solution();

        solution.addItem("ABC", new WorkItem("ABC", "ABC-1", "Task"));

        List<WorkItem> result = solution.find("project", "ABC-1");

        assertEquals(1, result.size());
    }

    @Test
    void multiFind() {
        ComparisonNode keySearch = new ComparisonNode("key", "ABC");

        Solution solution = new Solution();
        solution.addItem("ABC", new WorkItem("ABC", "ABC-1", "Task"));

        ComparisonNode[] query = new ComparisonNode[1];
        query[0] = keySearch;
        List<WorkItem> result = solution.findAnd(query);

        assertEquals(1, result);

    }
    /**
     * key = ABC AND project = ABC-1
     * key = ABC OR project = ABC-1
     * 
     * key = ABC AND project = ABC-1 OR project = ABC-2
     * 
     * 
     */
}
