import java.util.*;
import java.math.*;

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


    }
}
