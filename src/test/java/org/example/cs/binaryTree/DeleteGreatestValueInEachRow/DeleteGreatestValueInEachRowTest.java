package org.example.cs.binaryTree.DeleteGreatestValueInEachRow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteGreatestValueInEachRowTest {
    @Test
    public void testExample1() {
        DeleteGreatestValueInEachRow solver = new DeleteGreatestValueInEachRow();
        int[][] grid = {{1,2,4},{3,3,1}};
        assertEquals(8, solver.deleteGreatestValue(grid));
    }

    @Test
    public void testSingleRow() {
        DeleteGreatestValueInEachRow solver = new DeleteGreatestValueInEachRow();
        int[][] grid = {{5, 1, 9}};
        assertEquals(15, solver.deleteGreatestValue(grid));
    }

    @Test
    public void testSingleColumn() {
        DeleteGreatestValueInEachRow solver = new DeleteGreatestValueInEachRow();
        int[][] grid = {{2},{7},{4}};
        assertEquals(7, solver.deleteGreatestValue(grid));
    }

    @Test
    public void testAllSame() {
        DeleteGreatestValueInEachRow solver = new DeleteGreatestValueInEachRow();
        int[][] grid = {{3,3,3},{3,3,3}};
        assertEquals(9, solver.deleteGreatestValue(grid));
    }
}
