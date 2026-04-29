package com.montyhall;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        int simulationCount = 0;
        boolean inputValid = false;
        Scanner scanner = new Scanner(System.in);

        while(!inputValid){
            try {
                System.out.println("Enter number of simulations: ");
                String input = scanner.nextLine();
                simulationCount = Integer.parseInt(input);
                inputValid = true;
            }catch (NumberFormatException ex){
                inputValid = false;
            }
        }
        scanner.close();

        MontyHallSimulator simulateNoChange = new MontyHallSimulator(simulationCount,GameType.NOT_CHANGE_CHOICE);
        MontyHallSimulator simulateWithChange = new MontyHallSimulator(simulationCount,GameType.CHANGE_CHOICE);

        System.out.println("Win rate, when NOT changing your original choice: " + simulateNoChange.getWinRate() + "%");
        System.out.println("Win rate, when changing your original choice: " + simulateWithChange.getWinRate() + "%");

        if(simulateWithChange.getWinRate() > simulateNoChange.getWinRate()){
            System.out.println("Do I stand a better chance to win if I change my mind? - YES");
        } else {
            System.out.println("Do I stand a better chance to win if I change my mind? - NO");
        }

    }
}
