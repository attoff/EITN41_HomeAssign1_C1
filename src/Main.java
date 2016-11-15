


public class Main {

    public static void main(String[] args) {

        Alice a = new Alice();
        double[] allB = a.generateMatrixes();

        Bank b = new Bank();
        int[] selectedIndicies = b.chooseIndicies(allB);

        long[][] requestedNumbers = a.requestRandomNumbersFrom(selectedIndicies);

        if (b.checkIfOK(requestedNumbers)){
            long bankCoin = b.computeCoin();
            long coin = a.retrieveCoin(bankCoin);
            System.out.println("The final coin is "+ coin);
        }else{
            System.out.println("Alice is not verified correctly - ID not verified.");
        }
    }
}
