import java.util.Random;

public class Bank {
    private Random rand;
    private int[] selected;
    private int[] used;
    private int[] B;
    private int n;

    public Bank() {
        rand = new Random();
        selected = new int[1000];
        B = new int[2000];
        n = 15;
    }


    public int[] selectNumbers(int[] Bnew) {
        for (int i = 0; i < Bnew.length; i++) {
            B[i] = Bnew[i];
        }

        int randomInt = rand.nextInt(2000);

        return selected;

    }

}
