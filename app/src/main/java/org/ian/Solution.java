package org.ian;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.ArrayList;

/**
 * This is the solution 
 */
public class Solution {
    private static final Logger log = LoggerFactory.getLogger(Solution.class);

// Your challenge is to write a function generateTiles which takes an image as an input and returns all tiles for the corresponding input image. 

// Simplifications for the interview:
// We are not dealing with real-world images.
// No data is loaded from disk.
// Each pixel is represented as an integer value.
// Images can be represented using arrays or any suitable data structure.
// Each tile is 4×4 pixels in size.
// Each image is 16×16 pixels in size.

    //static final int tileSize = 4;

    public record Tile (int[][] pixels) {
    }

    /**
     * Runs the solution
     */
    public List<Tile> generateTiles(int[][] data, int tileSize) {
        log.debug("*** ran solution ***");
        if (data == null || data.length == 0) return null;

        // how many times
         List<Tile> tiles = new ArrayList<Tile>();

        // output
        int startRow = 0;
        int startCol = 0;

        while (startRow < data.length) {
            while (startCol < data[startRow].length) {
                // handle one tile
                int[][] newTile = new int[tileSize][tileSize];
                for (int rowPos=0; rowPos<tileSize; rowPos++) {
                    for (int colPos=0; colPos<tileSize; colPos++){
                        newTile[rowPos][colPos] = data[startRow+rowPos][startCol+colPos];
                    }
                }

                // save the tile
                Tile tileObj = new Tile(newTile);
                tiles.add(tileObj);

                printTile(tileObj);

                startCol = startCol+tileSize;
            }
            startRow = startRow+tileSize;
            startCol = 0;
        }

        log.info(" tiles: {}", tiles.size());
        return tiles;
    }

    void printTile(Tile tile) {
        for (int r=0; r<tile.pixels.length; r++) {
            for (int c=0; c< tile.pixels[r].length; c++) {
                System.out.print(tile.pixels[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
