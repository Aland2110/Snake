package Eksamensforberedelse;
import java.util.Scanner;


public class Uge4_Parameter {
    public static void main(String[] args) {
        //skala();
        /*Scanner amount = new Scanner(System.in);
        System.out.println("How many numbers do you want to enter?");
        int number = amount.nextInt();
        smallestLargest(number);
        amount.close();*/
        //printBogstaver("HelloWorld");
        //printFaktorer(24);
        quadratic();
    }

    public static void skala(){ //Opg. 1
        Scanner number = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int grade = number.nextInt();
        number.close();

        String answer = "Det giver: ";

        if(grade == 00){
            System.out.println(answer + "-3");
        }

        else if(grade == 03 || grade == 5){
            System.out.println(answer + "00");
        }

        else if(grade == 6){
            System.out.println(answer + "02");
        }

        else if(grade == 7){
            System.out.println(answer + "4");
        }

        else if(grade == 8 || grade == 9){
            System.out.println(answer + "7");
        }

        else if(grade == 10){
            System.out.println(answer + "10");
        }

        else if(grade == 11 || grade == 12){
            System.out.println(answer + "12");
        } else{
            System.out.println("Invalid input!");
        }
    }

    public static void smallestLargest(int number){ //Opg. 2
        Scanner num = new Scanner(System.in);
        System.out.println("Number 1:");
        int first = num.nextInt();
        int smallest = first;
        int largest = first;
        for(int i = 0; i < number - 1; i++){
            System.out.println("Number " + (i + 2) + ": ");
            int next = num.nextInt();
            
            if(next < smallest){
                smallest = next;
            }

            else if (next > largest){
                largest = next;
            }
        }

        System.out.println("The largest number is: " + largest);
        System.out.println("Smallest number is: " + smallest);
        num.close();
    }

    public static void printBogstaver(String ord){ //Opg. 3
        /*Scanner word = new Scanner(System.in);
        System.out.println("Dit ord: ");
        String ord = word.nextLine();*/
        System.out.print(ord.charAt(0));
        for(int i = 1; i < ord.length(); i++){
            System.out.print("_" + ord.charAt(i));
        }
        //word.close();
    }

    public static void printFaktorer(int number){ //Opg. 4
        /*Scanner num = new Scanner(System.in);
        System.out.println("Number: ");
        int number = num.nextInt();*/
        System.out.print(1);
        for (int i = 2; i <= number; i++){
            if(number % i == 0){
                System.out.print("_" + i);   
            }
        }

        //num.close();
    }

    public static void quadratic(){ //Opg. 6
        Scanner num = new Scanner(System.in);
        num.useDelimiter("\\s*");

        try{
            System.out.println("Enter a, b, c with a space between them: ");
            String numbers = num.nextLine();
            String[] variables = numbers.split(" ");
            int a = Integer.parseInt(variables[0]);
            int b = Integer.parseInt(variables[1]);
            int c = Integer.parseInt(variables[2]);

            if(a == 0){
                System.out.println("x = " + (-c/b));
            }
            
            else if(a >= 1){

                double D = Math.pow(b, 2) - 4 * a * c;

                if (D < 0){
                    System.out.println("There is no quadratic coefficient");
                }

                else if (D == 0){
                    double answer = (-b)/(2*a);
                    System.out.println("x = " + answer);
                }

                else if (D > 0){
                    double answer_1 = ((-b) + Math.sqrt(D))/(2*a);
                    double answer_2 = ((-b) - Math.sqrt(D))/(2*a);
                    System.out.println("x_1 = " + answer_1);
                    System.out.println("x_2 = " + answer_2);
                }
            }
            
        
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter valid integers for a, b, and c.");
        } 
        
        num.close();
        
    }

}
