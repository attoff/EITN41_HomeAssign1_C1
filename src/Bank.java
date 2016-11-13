import java.util.Random;

public class Bank {
    private Random rand;
    private int choosen;
    private int[] selected;
    private int aliceID;
    private int bankVerificationKey;
    private int n;
    private double[] allB;

    public Bank() {
        rand = new Random();
        selected = new int[2000];
        aliceID = 851212;
        bankVerificationKey = 7;
        n = 143;
    }


    public int[] chooseIndicies(double[] allB) {
        this.allB = allB;
        for (int i = 0; i < 1000; i++) {
            //get a new random place
            choosen = rand.nextInt(2000);
            //if indency has been chosen before, choose another
            while (selected[choosen] == 1) {
                choosen = rand.nextInt(2000);
            }
            //choose it
            selected[choosen] = 1;
        }
        return selected;

    }

    public boolean checkIfOK(long[][] selectedNumbers) {
        long[] x = new long[1000];
        long[] y = new long[1000];
        double[] B = new double[1000];

        for (int i = 0; i < selectedNumbers.length; i++) {
            x[i] = hashCodeH(selectedNumbers[i][0], selectedNumbers[i][1]); //a and c
            y[i] = hashCodeH(selectedNumbers[i][0] ^ aliceID, selectedNumbers[i][2]); //a XOR ID and d
        }

        for (int i = 0; i < B.length; i++) {
            double pow = Math.pow(selectedNumbers[i][3], bankVerificationKey);
            long hash = hashCodeF(x[i], y[i]);
            B[i] = (pow * hash) % n;
        }

        //Check if the value in B corresponds with Alices B-values.
        int index = 0;
        for (int i = 0; i < selected.length; i++) {
            if (selected[i] == 1) {
                if (allB[i] != B[index]) {
                    return false;
                }
                index++;
            }
        }
        return true;
    }

    public long computeCoin() {
        long resultedCoin = 1;
        int inverse = 103;//findInverseOf(bankVerificationKey);             // <-- fel!
        for (int i = 0; i < 2000; i++) {
            if (selected[i] == 0) {
                long pow = (long) (Math.pow(allB[i], inverse) %n);
                resultedCoin = resultedCoin * pow;
                System.out.println(resultedCoin);
            }
        }
        return resultedCoin;
    }

    private int findInverseOf(int key) {
        int temp = 1;
        while (((key * temp) % n) != 1) {
            temp++;
        }
        return temp;
    }

    private long hashCodeH(long a, long b) {
        long hash = 3;
        hash = hash * 5 + a;
        hash = hash * 5 + b;
        return hash;
    }

    private long hashCodeF(long a, long b) {
        long hash = 1;
        hash = hash * 7 + a;
        hash = hash * 7 + b;
        return hash;
    }

}
