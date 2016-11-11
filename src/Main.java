


public class Main {

    public static void main(String[] args) {

        Bank b = new Bank();
        Alice a = new Alice();

        long[] B = a.generateNumber();
        System.out.println(B[1] + " " + B[1999]);
        int[] selected = b.selectNumbers(B);
        int[][] fourLists = a.returnRequestedLists(selected);
        if (b.verifyList(fourLists)){
            double bankCoin = b.sendCoin();
            double coin = a.recievedCoin(bankCoin);
            System.out.println("The coin that alice gets is: " + coin);
        }else{
            System.out.println("Alice is not who she says she is.");
        }



    }
}
