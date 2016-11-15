import java.util.Random;

public class Alice {
    private long[][] acdr;
    private long[][] requestedNumbers;
    private long[] x;
    private long[] y;
    private double[] B;
    private int[] selected;
    private Random rand;
    private int n;
    private int ID;
    private int bankPublicKey;


    public Alice() {
        rand = new Random();
        acdr = new long[20][4];
        requestedNumbers = new long[10][4];
        x = new long[20];
        y = new long[20];
        B = new double[20];
        bankPublicKey = 7;
        n = 143;
        ID = 851212;
    }


    public double[] generateMatrixes() {
        long tmp;
        for (int i = 0; i < acdr.length; i++) { //2000
            for (int j = 0; j < acdr[1].length; j++) { //4
                tmp = rand.nextInt(10) + 1;
                acdr[i][j] = tmp % n;
            }
        }

        fillXY();
        fillB();

        return B;
    }

    private void fillB() {
        for (int i = 0; i < B.length; i++) {
            double pow = Math.pow(acdr[i][3], bankPublicKey);
            long hash = hashCodeF(x[i], y[i]);
            B[i] = (pow * hash) % n;
        }
    }

    private void fillXY() {
        for (int i = 0; i < x.length; i++) {
            x[i] = hashCodeH(acdr[i][0], acdr[i][1]); //a and c
            y[i] = hashCodeH(acdr[i][0] ^ ID, acdr[i][2]); //a XOR ID and d
        }
    }


    public long[][] requestRandomNumbersFrom(int[] indicies) {
        selected = indicies;
        int location = 0;
        for (int i = 0; i < selected.length; i++) {
            if (selected[i] == 1) {
                requestedNumbers[location][0] = acdr[i][0];
                requestedNumbers[location][1] = acdr[i][1];
                requestedNumbers[location][2] = acdr[i][2];
                requestedNumbers[location][3] = acdr[i][3];
                location++;
            }
        }
        return requestedNumbers;
    }

    public long retrieveCoin(long bankCoin) {
        long resR = 1;
        for (int i = 0; i < selected.length; i++) {
            if (selected[i] == 0) {
                resR = ((resR * acdr[i][3])%n);
            }

        }

        long finalCoin = (bankCoin / resR);

        return finalCoin;
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
