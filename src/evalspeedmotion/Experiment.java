package evalspeedmotion;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class Experiment {
    // input file (design): "experiment.csv"
    //protected File designFile = null;
    // output file: logs
    protected PrintWriter pwLog = null;
    File logFile = null;
    
    protected ArrayList<Trial> allTrials = new ArrayList<Trial>();
    protected int currentTrial = 0;
    
    private String participant;
    private int block;
    
    public final static int BLOCKS = 4;
    public final static int TRIALS = 11;
    
    public Experiment(String participant, int block, int trial) {
        this.participant = participant;
        this.block = block;
        this.currentTrial = trial;
        // …
        loadTrials();
        //initLog();
    }
    
    public void loadTrials() {
        allTrials.clear();
        // read the design file and keep only the trials to run
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("resources/speedmotion.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = br.readLine();

            System.out.println(line);
            while(line != null) {
                String[] parts = line.split(",");
                
                if(!parts[0].equals("Participant")) {
                    if(Integer.parseInt(parts[0]) == Integer.parseInt(participant)) {
                        if(Integer.parseInt(parts[2]) == block) {
                            int trialNumber = Integer.parseInt(parts[3]);
                            int visual;
                            switch(parts[4]) {
                                case "W1":
                                    visual = 0;
                                    break;
                                case "W2":
                                    visual = 1;
                                    break;
                                case "W1W2":
                                    visual = 2;
                                    break;
                                default:
                                    continue;
                            }
                            
                            int size = Integer.parseInt(parts[5]);
                            
                            allTrials.add(new Trial(block, trialNumber, visual, size));
                        }
                    }
                }

                line = br.readLine();
            }
            System.out.println(allTrials.toString());
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    public void log(Trial trial) {
        String trialToPrint = this.block + "\t"
                + trial.trial + "\t"
                + trial.visual + "\t"
                + trial.size + "\t"
                + trial.duration + "\t"
                + trial.hit + "\n";
        pwLog.print(trialToPrint);
        pwLog.flush();
    }
    
    public void trialCompleted() {
        Trial trial = allTrials.get(currentTrial);
       // log(trial);
        currentTrial++;
    }
    
    public void blockCompleted() {
        currentTrial = 0;
        block++;
        if(block <= BLOCKS) {
            loadTrials();
        }
    }
    
    public void initLog() {
        String logFileName = "log_S" + participant + "_" + (new Date()).toString() + ".csv";
        logFile = new File(logFileName);
        try {
            pwLog = new PrintWriter(logFile);
            String header = "Block\t"
                +"Trial\t"
                +"A\t"
                +"B\t"
                +"Duration\t"
                +"Hit\n";
            pwLog.print(header);
            pwLog.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public String getParticipant() {
        return participant;
    }
    
    public int getBlock() {
        return block;
    }
    
    public int getTrial() {
        return currentTrial;
    }
    
    public Trial getCurrentTrial() {
        return allTrials.get(currentTrial);
    }
    
    public ArrayList<Trial> getAllTrials() {
        return allTrials;
    }
}
