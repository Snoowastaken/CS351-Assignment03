import java.util.*;
import java.text.DecimalFormat;

public class Permutations {

    public static String shuffle(String str) {
        List<Character> chars = new ArrayList<Character>();
        for (char c : str.toCharArray()) {
            chars.add(c);
        }
        StringBuilder output = new StringBuilder(str.length());
        while (chars.size() != 0) {
            int randPicker = (int) (Math.random() * chars.size());
            output.append(chars.remove(randPicker));
        }
        return output.toString();
    }

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static int uniquePermutations(String str) {
        List<Character> chars = new ArrayList<Character>();
        for (char c : str.toCharArray()) {
            chars.add(c);
        }
        int upper = factorial(str.length());
        int lower = 1;
        HashMap<Character, Integer> repetitions = new HashMap<Character, Integer>();
        for (int i = 0; i < chars.size(); i++) {
            char key = chars.get(i);
            if (repetitions.containsKey(key)) {
                repetitions.put(key, repetitions.get(key) + 1);
            } else {
                repetitions.put(key, 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : repetitions.entrySet()) {
            if (entry.getValue() >= 2) {
                lower *= factorial(entry.getValue());
            }
        }
        return upper / lower;
    }

    public static void normal(String str, int numTrials) {
        String[] permutations = new String[numTrials];
        for (int i = 0; i < numTrials; i++) {
            permutations[i] = shuffle(str);
        }
        HashMap<String, Integer> repetitions = new HashMap<String, Integer>();
        for (int i = 0; i < permutations.length; i++) {
            String key = permutations[i];
            if (repetitions.containsKey(key)) {
                repetitions.put(key, repetitions.get(key) + 1);
            } else {
                repetitions.put(key, 1);
            }
        }
        StringBuilder output = new StringBuilder();
        int unique = 0;
        for (Map.Entry<String, Integer> entry : repetitions.entrySet()) {
            if (entry.getValue() >= 1) {
                unique++;
                output.append("\n");
                output.append(entry.getKey());
                output.append(" : ");
                output.append(entry.getValue());
            }
        }
        System.out.println("Base word: " + str);
        System.out.println("Number of permutations from simulation: " + unique);
        System.out.println("Number of unique permutations: " + uniquePermutations(str));
        System.out.println("Results from " + numTrials + " trials:");
        System.out.print(output.toString());


    }

    public static void include(String str, int numTrials, String includePattern) {
        String[] permutations = new String[numTrials];
        for (int i = 0; i < numTrials; i++) {
            permutations[i] = shuffle(str);
        }
        for (int i = 0; i < permutations.length; i++) {
            if (!permutations[i].contains(includePattern)) {
                permutations[i] = null;
            }
        }
        //remove null values from array
        List<String> list = new ArrayList<String>(Arrays.asList(permutations));
        list.removeAll(Collections.singleton(null));
        permutations = list.toArray(new String[list.size()]);
        int successfulTrials = permutations.length;
        double proportion = (double) successfulTrials / numTrials;
        HashMap<String, Integer> repetitions = new HashMap<String, Integer>();
        for (int i = 0; i < permutations.length; i++) {
            String key = permutations[i];
            if (repetitions.containsKey(key)) {
                repetitions.put(key, repetitions.get(key) + 1);
            } else {
                repetitions.put(key, 1);
            }
        }
        int estimatedNum = (int) (proportion * uniquePermutations(str));
        System.out.println("Base word: " + str);
        System.out.println("Number of unique permutations: " + uniquePermutations(str));
        System.out.println("Estimated number of permutations that include " + includePattern + ": " + estimatedNum);
        DecimalFormat ft = new DecimalFormat("#.0000");
        System.out.println(successfulTrials + " successes in " + numTrials + " trials; proportion: " + ft.format(proportion));
    }

    public static void exclude(String str, int numTrials, String excludePattern) {
        String[] permutations = new String[numTrials];
        for (int i = 0; i < numTrials; i++) {
            permutations[i] = shuffle(str);
        }
        for (int i = 0; i < permutations.length; i++) {
            if (permutations[i].contains(excludePattern)) {
                permutations[i] = null;
            }
        }
        List<String> list = new ArrayList<String>(Arrays.asList(permutations));
        list.removeAll(Collections.singleton(null));
        permutations = list.toArray(new String[list.size()]);

        int successfulTrials = permutations.length;
        double proportion = (double) successfulTrials / numTrials;

        HashMap<String, Integer> repetitions = new HashMap<String, Integer>();
        for (int i = 0; i < permutations.length; i++) {
            String key = permutations[i];
            if (repetitions.containsKey(key)) {
                repetitions.put(key, repetitions.get(key) + 1);
            } else {
                repetitions.put(key, 1);
            }
        }
        int estimatedNum = (int) (proportion * uniquePermutations(str));
        System.out.println("Base word: " + str);
        System.out.println("Number of unique permutations: " + uniquePermutations(str));
        System.out.println("Estimated number of permutations that exclude " + excludePattern + ": " + estimatedNum);
        DecimalFormat ft = new DecimalFormat("#.0000");
        System.out.println(successfulTrials + " successes in " + numTrials + " trials; proportion: " + ft.format(proportion));
    }

    public static void ExcludeandInclude(String str, int numTrials, String includePattern, String excludePattern) {
        String[] permutations = new String[numTrials];
        for (int i = 0; i < numTrials; i++) {
            permutations[i] = shuffle(str);
        }
        for (int i = 0; i < permutations.length; i++) {
            if (!permutations[i].contains(includePattern) || permutations[i].contains(excludePattern)) {
                permutations[i] = null;
            }
        }
        List<String> list = new ArrayList<String>(Arrays.asList(permutations));
        list.removeAll(Collections.singleton(null));
        permutations = list.toArray(new String[list.size()]);

        int successfulTrials = permutations.length;
        double proportion = (double) successfulTrials / numTrials;
        HashMap<String, Integer> repetitions = new HashMap<String, Integer>();
        for (int i = 0; i < permutations.length; i++) {
            String key = permutations[i];
            if (repetitions.containsKey(key)) {
                repetitions.put(key, repetitions.get(key) + 1);
            } else {
                repetitions.put(key, 1);
            }
        }
        int estimatedNum = (int) (proportion * uniquePermutations(str));
        System.out.println("Base word: " + str);
        System.out.println("Number of unique permutations: " + uniquePermutations(str));
        System.out.println("Estimated number of permutations that include " + includePattern + " and exclude " + excludePattern + ": " + estimatedNum);
        DecimalFormat ft = new DecimalFormat("#.0000");
        System.out.println(successfulTrials + " successes in " + numTrials + " trials; proportion: " + ft.format(proportion));
    }

    public static void main(String[] args) {
        String wordPerm = "FALL";
        int numTrials = 10000;
        String includePattern = null;
        String excludePattern = null;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-w":
                    //set wordPerm
                    wordPerm = args[i + 1];
                    if (wordPerm.length() > 20) {
                        System.out.println("Word must be less than 20 characters");
                        System.exit(0);
                    }
                    break;
                case "-i":
                    //set includePattern
                    includePattern = args[i + 1];
                    break;
                case "-e":
                    //set excludePattern
                    excludePattern = args[i + 1];
                    break;
                case "-t":
                    //set numTrials
                    numTrials = Integer.parseInt(args[i + 1]);
                    break;
                default:
                    break;
            }
        }


        if (includePattern != null && excludePattern != null) {
            ExcludeandInclude(wordPerm, numTrials, includePattern, excludePattern);
        } else if (includePattern != null) {
            include(wordPerm, numTrials, includePattern);
        } else if (excludePattern != null) {
            exclude(wordPerm, numTrials, excludePattern);
        } else {
            normal(wordPerm, numTrials);
        }


    }

}
