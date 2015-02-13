package evalspeedmotion;

import java.sql.Time;

public class Trial {
    protected int block;
    protected int trial;
    protected int visual;
    protected int size;
    
    protected int hit;
    protected Time duration;
    
    protected Experiment experiment;
    
    /**
     * Visual is the type of visual variable being tested in this trial. 
     * Size is the size of the grid.
     * 
     * @param block The block number
     * @param trial The trial number
     * @param visual O is direction, 1 is speed, 2 is both
     * @param size 4, 9, 16 or 25
     */
    public Trial(int block, int trial, int visual, int size) { 
        this.block = block;
        this.trial = trial;
        this.visual = visual;
        this.size = size;
    }

    public void displayInstructions() {
        // ...
    }
    
    public void hideInstructions() {
        //experiment.getCanvas().removeShapes(experiment.getInstructions());
    }
    
    public void start() {
        // ...
        // install the graphical listener and the user input listener
        // call experiment.trialCompleted(); when appropriate
    }
   
    public void stop() { 
    }
    
    @Override
    public String toString() {
        return "Trial: #" + trial + " of type " + visual + " of size " + size + "";
    }
}