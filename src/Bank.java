import java.math.BigInteger;
import java.util.Random;

public class Bank {
    private Random rand;
    private int choosen;
    private int[] selected;
    private int aliceID;
    private int publicKey;
    private int n;
    private double[] allB;

    public Bank() {
        rand = new Random();
        selected = new int[20];
        aliceID = 851212;
        publicKey = 7;
        n = 143;
    }


    public int[] chooseIndicies(double[] allB) {
        this.allB = allB;
        for (int i = 0; i < 10; i++) {
            //get a new random place
            choosen = rand.nextInt(20);
            //if indency has been chosen before, choose another
            while (selected[choosen] == 1) {
                choosen = rand.nextInt(20);
            }
            //choose it
            selected[choosen] = 1;
        }
        return selected;

    }

    public boolean checkIfOK(long[][] selectedNumbers) {
        long[] x = new long[10];
        long[] y = new long[10];
        double[] B = new double[10];

        for (int i = 0; i < selectedNumbers.length; i++) {
            x[i] = hashCodeH(selectedNumbers[i][0], selectedNumbers[i][1]); //a and c
            y[i] = hashCodeH(selectedNumbers[i][0] ^ aliceID, selectedNumbers[i][2]); //a XOR ID and d
        }

        for (int i = 0; i < B.length; i++) {
            double pow = Math.pow(selectedNumbers[i][3], publicKey);
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
        int inverse = findInverseOf(publicKey);
        for (int i = 0; i < 20; i++) {
            if (selected[i] == 0) {
                long pow = (long) (Math.pow(allB[i], inverse) % n);
                resultedCoin = resultedCoin * pow;
                System.out.println(resultedCoin);
            }
        }
        return resultedCoin;
    }

    private int findInverseOf(int key) {
        int returnvalue;
        int totient = 120;
        BigInteger temp = BigInteger.valueOf(key).modInverse(BigInteger.valueOf(totient));
        returnvalue = temp.intValue();
        return returnvalue;
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
