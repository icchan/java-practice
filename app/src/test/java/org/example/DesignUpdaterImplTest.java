package org.example;

import org.example.DesignValidators.Design;
import org.example.DesignValidators.DesignUpdaterImpl;
import org.example.DesignValidators.Element;
import org.example.DesignValidators.Page;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DesignUpdaterImplTest {
    @Test
    public void designUpdateShouldReplaceDesignIfSuccessful() {
        var updater = new DesignUpdaterImpl();
        var newDesign = new Design(50, 50, List.of(new Page("page-id", List.of())));
        var updated = updater.replaceWith(newDesign);

        // did replace design
        assertTrue(updated);
        assertEquals(newDesign, updater.currentDesign);
    }

    @Test
    public void designUpdateShouldNotReplaceWhenNewDesignHasElementsLargerThanDesign() {
        var updater = new DesignUpdaterImpl();
        var element = new Element("element", 50, 50);
        var elementLargerThanDesign = new Element("element-larger-than-design", 200, 100);
        var newDesign = new Design(100, 100, List.of(new Page("page-id", List.of(
                element, elementLargerThanDesign
        ))));
        var updated = updater.replaceWith(newDesign); // this should not update

        // did not replace design
        assertFalse(updated);
        assertNotEquals(newDesign, updater.currentDesign);
    }
}