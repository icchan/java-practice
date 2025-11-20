package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DesignValidators {
    record Design(int width, int height, List<Page> pages) {
    }

    record Page(String id, List<Element> elements) {
    }

    record Element(String id, int width, int height) {
    }

    interface DesignUpdater {
        /**
         * Replaces the entire design with the provided design.
         * 
         * @return true if successful, false if the design was not replaced.
         */
        boolean replaceWith(Design newDesign);

        /**
         * Adds the provided page to the existing design
         * 
         * @return true if successful, false if the design was not updated.
         */
        boolean addPage(Page newPage);
    }

    static class DesignUpdaterImpl implements DesignUpdater {
        public Design currentDesign = new Design(100, 100, List.of());

        @Override
        public boolean replaceWith(Design newDesign) {

            Set<String> elementIds = new HashSet<>();

            // validate element size
            for (Page page : newDesign.pages) {
                if (!validatePage(page, elementIds, currentDesign.height, currentDesign.width))
                    return false;
            }

            currentDesign = newDesign;
            return true;
        }

        @Override
        public boolean addPage(Page newPage) {
            var currentPages = new ArrayList<>(currentDesign.pages());

            Set<String> elementIds = new HashSet<>();
            // create a set of current element ids
            for (Page page : currentDesign.pages) {
                for (Element e : page.elements) {
                    elementIds.add(e.id);
                }
            }

            if (!validatePage(newPage, elementIds, currentDesign.height, currentDesign.width))
                return false;

            currentPages.add(newPage);

            currentDesign = new Design(currentDesign.width(), currentDesign.height(), currentPages);
            return true;
        }
    }

    private static boolean validatePage(Page page, Set<String> elementIds, int maxheight, int maxwidth) {
        // for the new page, check its elements
        for (Element element : page.elements) {
            if (element.height > maxheight || element.width > maxwidth)
                return false;

            // ensure no duplicate element IDs
            if (!elementIds.add(element.id)) {
                return false;
            }
        }
        return true;
    }

}