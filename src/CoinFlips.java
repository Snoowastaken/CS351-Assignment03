import java.util.*;
import java.math.*;
import java.text.DecimalFormat;

public class CoinFlips {

    public static int coinFlips(int numFlips, double probability) {
        char[] flips = new char[numFlips + 1];
        for (int i = 0; i < numFlips; i++) {
            if (Math.random() < probability) {
                flips[i] = 'H';
            } else {
                flips[i] = 'T';
            }
        }
        //count the longest sequence of heads
        int longestSequenceH = 0;
        int currentSequenceH = 0;
        for (int i = 0; i < flips.length; i++) {
            if (flips[i] == 'H') {
                currentSequenceH++;
            } else {
                if (currentSequenceH > longestSequenceH) {
                    longestSequenceH = currentSequenceH;
                }
                currentSequenceH = 0;
            }
        }
        //count the longest sequence of tails
        int longestSequenceT = 0;
        int currentSequenceT = 0;
        for (int i = 0; i < flips.length; i++) {
            if (flips[i] == 'T') {
                currentSequenceT++;
            } else {
                if (currentSequenceT > longestSequenceT) {
                    longestSequenceT = currentSequenceT;
                }
                currentSequenceT = 0;
            }
        }
        int longestSequence = Math.max(longestSequenceH, longestSequenceT);
        return longestSequence;

    }

    public static void histogram(HashMap frequencies, HashMap probabilities, int width){
        //set local variables
        int widthHisto = width;
        HashMap<Integer, Integer> frequenciesHisto = frequencies;
        HashMap<Integer, Double> probabilitiesHisto = probabilities;


    }



    public static void main(String[] args) {
        int numCoinFlips = 10;
        double probHeads = .5;
        int numTrials = 10000;
        int widthHisto = 20;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-n":
                    //set numCoinFlips
                    numCoinFlips = Integer.parseInt(args[i + 1]);
                    if (numCoinFlips > 99) {
                        System.out.println("Coin flips cannot be more than 99.");
                        System.exit(0);
                    }
                    break;
                case "-p":
                    //set probHeads
                    probHeads = Double.parseDouble(args[i + 1]);
                    break;
                case "-t":
                    //set numTrials
                    numTrials = Integer.parseInt(args[i + 1]);
                    break;
                case "-w":
                    //set widthHisto
                    widthHisto = Integer.parseInt(args[i + 1]);
                    break;
                default:
                    break;
            }
        }

        //create array of longest sequences
        int[] longestSequences = new int[numTrials];
        for (int i = 0; i < numTrials; i++) {
            longestSequences[i] = coinFlips(numCoinFlips, probHeads);
        }
        //create hashmap of frequencies of longest sequences
        HashMap<Integer, Integer> frequencies = new HashMap<Integer, Integer>();
        for (int i = 0; i < longestSequences.length; i++) {
            int key = longestSequences[i];
            if (frequencies.containsKey(key)) {
                frequencies.put(key, frequencies.get(key) + 1);
            } else {
                frequencies.put(key, 1);
            }
        }
        //print value and key of hashmap, FOR TESTING DELETE LATER
        for(Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            System.out.println(key + " " + value);
        }
        //add up the values of the hashmap, FOR TESTING DELETE LATER
        int sum = 0;
        for(Map.Entry<Integer, Integer> entry : frequencies.entrySet()){
            sum += entry.getValue();
        }
        //print sum of values of hashmap, FOR TESTING DELETE LATER
        System.out.println("Sum: " + sum);

        //find probability of each longest sequence
        HashMap<Integer, Double> probabilities = new HashMap<Integer, Double>();
        for(Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            double probability = (double) value / numTrials;
            probabilities.put(key, probability);
        }
        //print value and key of hashmap, FOR TESTING DELETE LATER
        for(Map.Entry<Integer, Double> entry : probabilities.entrySet()) {
            int key = entry.getKey();
            double value = entry.getValue();
            System.out.println(key + " " + value);
        }
        //get longest length of sequence
        int longestLength = 0;
        for(Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
            int key = entry.getKey();
            if (key > longestLength) {
                longestLength = key;
            }
        }

        DecimalFormat ft = new DecimalFormat("#.000000");
        System.out.println("Analyzing runs in sequences of length " + longestLength + " with p = " + ft.format(probHeads));
        System.out.println("Results from " + numTrials + " trials:");
        histogram(frequencies, probabilities, widthHisto);



    }
}
