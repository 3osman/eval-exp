package evalspeedmotion;

public class Trial {
    protected int block;
    protected int trial;
    protected String targetChange;
    protected int nonTargetsCount;
    
    protected Experiment experiment;
    
    private int number;
    private int visual;
    private int size;
    
    /**
     * Visual is the type of visual variable being tested in this trial. 
     * Size is the size of the grid.
     * 
     * @param number Trial number
     * @param visual O is direction, 1 is speed, 2 is both
     * @param size 4, 9, 16 or 25
     */
    public Trial(int number, int visual, int size) { 
        this.number = number;
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
        return "Trial: #" + number + " of type " + visual + " of size " + size + "";
    }
}