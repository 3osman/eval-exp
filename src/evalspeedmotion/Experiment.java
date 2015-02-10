package evalspeedmotion;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class Experiment {
    // input file (design): "experiment.csv"
    protected File designFile = null;
    // output file: logs
    protected PrintWriter pwLog = null;
    
    protected ArrayList<Trial> allTrials = new ArrayList<Trial>();
    protected int currentTrial = 0;
    
    private int participant;
    private int block;
    private int trial;
    
    public Experiment(int participant, int block, int trial) {
        this.participant = participant;
        this.block = block;
        this.trial = trial;
        // …
        loadTrials();
        //initLog();
        //nextTrial();
    }
    
    public void loadTrials() {
        allTrials.clear();
        // read the design file and keep only the trials to run
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("resources/speedmotion.csv").getFile());                        
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            // ...
            System.out.println(line);
            while(line != null) {
                String[] parts = line.split(",");
                
                if(!parts[0].equals("Participant")) {
                    if(Integer.parseInt(parts[0]) == participant) {
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
                            
                            allTrials.add(new Trial(trialNumber, visual, size));
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
    
    public void trialCompleted() {
        Trial trial = allTrials.get(currentTrial);
        trial.stop();
        log(trial);
        currentTrial++;
        nextTrial();
    }
    
    public void log(Trial trial) {}
    
    public void stop() {
        // display a "thank you" message
    }
    public void nextTrial() {
        if(currentTrial >= allTrials.size()) {
            stop();
        }
        Trial trial = allTrials.get(currentTrial);
        trial.displayInstructions();
    }
    
    public void initLog() {
        String logFileName = "log_S" + participant + "_" + (new Date()).toString() + ".csv";
        File logFile = new File(logFileName);
        try {
            pwLog = new PrintWriter(logFile);
            String header = "Block\t"
                +"Trial\t"
                +"TargetChange\t"
                +"NonTargetsCount\t"
                +"Duration\t"
                +"Hit\n";
            pwLog.print(header);
            pwLog.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
