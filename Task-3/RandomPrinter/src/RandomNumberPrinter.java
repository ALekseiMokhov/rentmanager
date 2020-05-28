import java.util.Random;

public class RandomNumberPrinter {

    public static void main(String[] args) {
       int num = generateThreeDigitsNumber();
       System.out.println("Random 3-digit number is "+ num);
       System.out.println("The largest digit in given number is "+ largestDigit( num ));

    }

    private static int generateThreeDigitsNumber(){
        Random random = new Random();
        int number = random.nextInt((999 - 100) + 1) + 100;
        return number;
    }
    private static int largestDigit(int number){
        int biggestDigit = 0;
        while(number>0) {
            int rightestDigist = number%10;
            biggestDigit = Math.max( biggestDigit,rightestDigist) ;
            number=number/10;
        }
        return biggestDigit;
    }

}
