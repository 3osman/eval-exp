package evalspeedmotion;

import java.sql.Time;

public class Trial {

    protected int block;
    protected int trial;
    protected int visual;
    protected int size;

    protected boolean hit;
    protected double duration;

    protected Experiment experiment;

    /**
     * Visual is the type of visual variable being tested in this trial. Size is
     * the size of the grid.
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

    public String getInstructions() {
        switch (visual) {
            case 0:
                return "when you find the circle moving counterclockwise.";
            case 1:
                return "when you find the fastest circle.";
            case 2:
                return "when you find the fastest circle moving counterclockwise.";
        }

        return "";
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getTrial() {
        return trial;
    }

    public void setTrial(int trial) {
        this.trial = trial;
    }

    public boolean getHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    public int getVisual() {
        return visual;
    }

    public void setVisual(int visual) {
        this.visual = visual;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Trial: #" + trial + " of type " + visual + " of size " + size + "";
    }
}
