package com.Q_Five;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FiveBTest {

    @Test
    void findNodesWithOnlyTargetAsParent() {
        int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 6 }, { 2, 4 }, { 4, 6 }, { 4, 5 }, { 5, 7 } };
        int target = 4;

        List<Integer> uniqueParents = FiveB.findAffectedDevices(edges, target);

        assertNotNull(uniqueParents);
        assertEquals(2, uniqueParents.size());
        assertTrue(uniqueParents.contains(5));
        assertTrue(uniqueParents.contains(7));
    }

    @Test
    void findNodesWithOnlyTargetAsParent_NoNodesWithTargetAsParent_ShouldReturnEmptyList() {
        int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 6 }, { 2, 4 }, { 4, 6 }, { 5, 7 } }; // No parent 4 for node 5
        int target = 4;

        List<Integer> uniqueParents = FiveB.findAffectedDevices(edges, target);

        assertNotNull(uniqueParents);
        assertTrue(uniqueParents.isEmpty());
    }

    @Test
    void findNodesWithOnlyTargetAsParent_TargetNotInGraph_ShouldReturnEmptyList() {
        int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 6 }, { 2, 4 }, { 4, 6 }, { 4, 5 }, { 5, 7 } };
        int target = 8; // Target node 8 not present in the graph

        List<Integer> uniqueParents = FiveB.findAffectedDevices(edges, target);

        assertNotNull(uniqueParents);
        assertTrue(uniqueParents.isEmpty());
    }

    @Test
    void findNodesWithOnlyTargetAsParent_EmptyGraph_ShouldReturnEmptyList() {
        int[][] edges = {};
        int target = 4;

        List<Integer> uniqueParents = FiveB.findAffectedDevices(edges, target);

        assertNotNull(uniqueParents);
        assertTrue(uniqueParents.isEmpty());
    }
}
