import java.util.*;
import java.util.concurrent.LinkedTransferQueue;

public class Permutations {

    public static String shuffle(String str) {
        List<Character> chars = new ArrayList<Character>();
        for(char c : str.toCharArray()){
            chars.add(c);
        }
        StringBuilder output = new StringBuilder(str.length());
        while(chars.size() != 0){
            int randPicker = (int)(Math.random()*chars.size());
            output.append(chars.remove(randPicker));
        }
        return output.toString();
    }


    public static void main(String[] args){
        String wordPerm = "FALL";
        int numTrials = 10000;
        String includePattern = "";
        String excludePattern = "";
        for(int i = 0; i < args.length; i++){
            switch(args[i]){
                case "-w":
                    //set wordPerm
                    wordPerm = args[i+1];
                    if(wordPerm.length() > 20){
                        System.out.println("Word must be less than 20 characters");
                        System.exit(0);
                    }
                    break;
                case "-i":
                    //set includePattern
                    includePattern = args[i+1];
                    break;
                case "-e":
                    //set excludePattern
                    excludePattern = args[i+1];
                    break;
                case "-t":
                    //set numTrials
                    numTrials = Integer.parseInt(args[i+1]);
                    break;
                default:
                    break;
            }
        }







    }

}
