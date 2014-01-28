/*
 * PURPOSE
 * The purpose of this program is to simulate a machine that prints out tickets
 * for a state lottery. The program will start by asking the user how many
 * tickets will be purchased. The user must then input an integer from 1 to 5,
 * inclusive. Each lottery ticket contains 5 integers and a special integer.
 * The first 5 integers are randomly generated from the range 1-56, inclusive.
 * The special integer is randomly generated from the range 1-46, inclusive.
 * The first 5 integers will be sorted in non decreasing order. The tickets
 * are then compared to insure there are no duplicates. The tickets are then
 * printed to the screen.
 */

import java.util.Scanner; //for reading user input
import java.util.Random; //for generating random integers
import java.util.Arrays;

public class StateLottery {
    public static void main(String[] args) {
        System.out.printf("Welcome to the State Lottery\n");

        int numberOfTickets = 0;
        boolean appropriateInput = false;
        do {
            try {
                System.out.printf("\nHow many tickets are to be purchased?\n");
                System.out.printf("Tickets purchased must be at least 1 and no more than 5\n");
                Scanner userInput = new Scanner(System.in);
                numberOfTickets = userInput.nextInt();
                switch (numberOfTickets) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        appropriateInput = true; //breaks while loop
                        break;
                    default:
                        throw new Exception(); //exception thrown with undesired input
                }
            } catch (Exception e) {
                System.out.printf("Please re-enter an integer from 1 to 5\n");
            }
        } while(!appropriateInput); //loop breaks when appropriateInput is true

        int tickets[][] = new int[numberOfTickets][6];
        Random generator = new Random();
        for(int[] ticket : tickets) {
            for(int i = 0; i < 5; i++)
                ticket[i] = generator.nextInt(56) + 1;
            ticket[5] = generator.nextInt(46) + 1;
        }

        for(int[] ticket : tickets)
            Arrays.sort(ticket, 0, 4); //initially sort generated tickets

        boolean duplicate = false;
        do {
            for(int i = 0; i < numberOfTickets; i++)
                for(int j = i+1; j < numberOfTickets; j++)
                    if(Arrays.equals(tickets[i], tickets[j])) {
                        duplicate = true;
                        for(int k = 0; k < 5; k++)
                            tickets[j][k] = generator.nextInt(56) + 1;
                        tickets[j][5] = generator.nextInt(46) + 1;
                    }
        } while(duplicate);

        for(int[] ticket : tickets)
            Arrays.sort(ticket, 0, 4); //sorts the tickets again if duplicates were found

        System.out.printf("\nPrinting lottery tickets\n");
        for(int[] ticket : tickets) {
            for(int i : ticket)
                System.out.printf("%3d", i);
            System.out.printf("\n");
        }
    }
}