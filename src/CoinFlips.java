import java.util.*;
public class CoinFlips {



   public static void main(String[] args){
      int numCoinFlips = 10;
      double probHeads = .5;
      int numTrials = 10000;
      int widthHisto = 20;
      for(int i = 0; i < args.length; i++){
         switch(args[i]){
            case "-n":
               //set numCoinFlips
               numCoinFlips = Integer.parseInt(args[i+1]);
               if(numCoinFlips > 99){
                  System.out.println("Coin flips cannot be more than 99.");
                  System.exit(0);
               }
               break;
            case "-p":
               //set probHeads
               probHeads = Double.parseDouble(args[i+1]);
               break;
            case "-t":
               //set numTrials
               numTrials = Integer.parseInt(args[i+1]);
               break;
            case "-w":
               //set widthHisto
               widthHisto = Integer.parseInt(args[i+1]);
               break;
            default:
               break;
         }
      }






   }
}
