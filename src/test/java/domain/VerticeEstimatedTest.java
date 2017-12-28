package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VerticeEstimatedTest {

    private VerticeEstimated verticeEst;

    public VerticeEstimatedTest() {
        verticeEst = new VerticeEstimated(3, 5, 120.00);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getDistToGoalWorks() {
        assertTrue(verticeEst.getDistToGoal() == Long.MAX_VALUE);
    }

    @Test
    public void distToGoalCorrectAfterSetting() {
        verticeEst.setDistToGoal(18);
        assertTrue(verticeEst.getDistToGoal() == 18);
    }

    @Test
    public void compareToLargerWorks() {
        VerticeEstimated newVertice = new VerticeEstimated(4, 5, 121.00);
        verticeEst.setDistToStart(3);
        newVertice.setDistToStart(4);
        verticeEst.setDistToGoal(10);
        newVertice.setDistToGoal(10);
        assertTrue(verticeEst.compareTo(newVertice) == -1);
    }

    @Test
    public void compareToSmallerWorks() {
        VerticeEstimated newVertice = new VerticeEstimated(4, 5, 121.00);
        verticeEst.setDistToStart(4);
        newVertice.setDistToStart(4);
        verticeEst.setDistToGoal(11);
        newVertice.setDistToGoal(10);
        assertTrue(verticeEst.compareTo(newVertice) == 1);
    }

    @Test
    public void compareToEqualWorks() {
        VerticeEstimated newVertice = new VerticeEstimated(4, 5, 121.00);
        verticeEst.setDistToStart(3);
        newVertice.setDistToStart(4);
        verticeEst.setDistToGoal(11);
        newVertice.setDistToGoal(10);
        assertTrue(verticeEst.compareTo(newVertice) == 0);
    }

}
