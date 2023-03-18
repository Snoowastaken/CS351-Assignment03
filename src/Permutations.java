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

    public static int getnumberOfPermutations(String str){

    }

    public static void main(String[] args){
        String wordPerm = "FALL";
        int numTrials = 10000;
        String includePattern = null;
        String excludePattern = null;
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
        String[] permutations = new String[numTrials];

        for(int i = 0; i < numTrials; i++){
            permutations[i] = shuffle(wordPerm);
        }
        //print out all permutations
        for(int i = 0; i < permutations.length; i++){
            System.out.println(permutations[i]);}
        //check if includePattern is null, if not, make sure only permutations that include the pattern are in the array
        if(includePattern != null){
            for(int i = 0; i < permutations.length; i++){
                if(!permutations[i].contains(includePattern)){
                    permutations[i] = null;
                }
            }
        }
        //check if excludePattern is null, if not, make sure only permutations that do not include the pattern are in the array
        if(excludePattern != null){
            for(int i = 0; i < permutations.length; i++){
                if(permutations[i].contains(excludePattern)){
                    permutations[i] = null;
                }
            }
        }
        //remove null values from array
        List<String> list = new ArrayList<String>(Arrays.asList(permutations));
        list.removeAll(Collections.singleton(null));
        permutations = list.toArray(new String[list.size()]);

        HashMap<String, Integer> repetitions = new HashMap<String, Integer>();
        for(int i = 0; i< permutations.length; i++){
            String key = permutations[i];
            if(repetitions.containsKey(key)){
                repetitions.put(key, repetitions.get(key) + 1);
            }else{
                repetitions.put(key, 1);
            }
        }
        StringBuilder output = new StringBuilder();
        int unique = 0;
        for(Map.Entry<String, Integer> entry : repetitions.entrySet()){
            if(entry.getValue() >= 1){
                unique++;
                output.append("\n");
                output.append(entry.getKey());
                output.append(" : ");
                output.append(entry.getValue());
            }
        }

        System.out.println("Base word: " + wordPerm);
        System.out.println("Number of unique permutations from simulation: " + unique);
        System.out.println("Number of trials: " + numTrials);
        System.out.print(output.toString());

    }

}
