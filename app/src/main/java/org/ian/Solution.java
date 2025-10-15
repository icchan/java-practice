package org.ian;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This is the solution 
 */
public class Solution {
    private static final Logger log = LoggerFactory.getLogger(Solution.class);

    /**
     * Runs the solution
     */
    public void run() {
        log.info("ran solution");

        String json = """
            {"films":[{"name":"Your Name"},{"name":"Infinity Castle"},{"name":"Weathering with You"}]}""";
        ObjectMapper m = new ObjectMapper();
        try {
            Data d = m.readValue(json, Data.class);

            log.info(">" + d.films().size());
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

final record Data(List<Film> films){}

final record Film(String name){}