import java.util.Random;

public class RandomNumberPrinter {

    public static void main(String[] args) {
        Random random = new Random();
        int number = random.nextInt((999 - 100) + 1) + 100;
        System.out.println("Generated number: "+number);
        int biggestDigit = 0;
         while(number>0) {
             int rightestDigist = number%10;
             biggestDigit = Math.max( biggestDigit,rightestDigist) ;
             number=number/10;
         }
        System.out.println("The biggest digit is "+biggestDigit);

    }
}
