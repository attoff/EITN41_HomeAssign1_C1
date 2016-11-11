import java.util.Random;

public class Bank {
    private Random rand;
    private int[] selected;
    private int[] used;
    private int[] B;
    private int[]x;
    private int[]y;
    private int[]bCalc;
    private int n;
    private int ID;

    public Bank() {
        rand = new Random();
        selected = new int[1000];
        B = new int[2000];
        x=new int[1000];
        y = new int[1000];
        bCalc = new int[1000];
        n = 15;
        ID = 851212;
    }


    public int[] selectNumbers(int[] Bnew) {
        for (int i = 0; i < Bnew.length; i++) {
            B[i] = Bnew[i];
        }
        for (int i = 0; i < selected.length; i++) {
            int randomInt = rand.nextInt(2000);
            while (used[randomInt]== 1){
                randomInt = rand.nextInt(2000);
            }
            selected[i]= randomInt;
            used[randomInt] = 1;
        }
        return selected;
    }
    public boolean verifyList(int[][] bRes){
        hashH(bRes);
        hashB(bRes);
        for (int i =0; i<selected.length;i++){
            if (bCalc[i]!= B[selected[i]]){
                return false;
            }
        }

        return true;
    }
    private void hashH(int[][] bRes) {
        for (int i = 0; i < x.length; i++) {
            x[i] = hashCodeH(bRes[i][0], bRes[i][1]);
            y[i] = hashCodeH(bRes[i][0] ^ ID, bRes[i][2]);
        }
    }
    private void hashB(int[][]bRes) {
        for (int i = 0; i < bCalc.length; i++) {
            bCalc[i] = ((int) Math.pow(bRes[i][3], 3) * hashCodeF(x[i], y[i])) % n;
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
