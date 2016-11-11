import java.util.Random;

public class Alice {
    private Random rand;
    private int[] a;
    private int[] c;
    private int[] d;
    private int[] r;
    private int[] x;
    private int[] y;
    private int[] B;
    private int ID;

    public Alice() {
        rand = new Random();
        a = new int[2000];
        c = new int[2000];
        d = new int[2000];
        r = new int[2000];
        x = new int[2000];
        y = new int[2000];
        ID = 851212;

    }

    public void generateNumber() {
        for (int i = 0; i < a.length; i++) {
            a[i] = rand.nextInt();
            c[i] = rand.nextInt();
            d[i] = rand.nextInt();
            r[i] = rand.nextInt();
        }
        hashH();
        hashB();
    }

    private void hashH() {
        for (int i = 0; i < a.length; i++) {
            x[i] = hashCodeH(a[i], c[i]);
            y[i] = hashCodeH(a[i] ^ ID, d[i]);
        }
    }

    private void hashB() {
        for (int i = 0; i < B.length; i++) {
            B[i] = ((int) Math.pow(r[i], 3) * hashCodeF(x[i], y[i])) % 3;
        }

    }


    private int hashCodeH(int a, int b) {
        int hash = 17;
        hash = hash * 31 + a;
        hash = hash * 31 + b;
        return hash;
    }

    private int hashCodeF(int a, int b) {
        int hash = 13;
        hash = hash * 17 + a;
        hash = hash * 17 + b;
        return hash;
    }


}
