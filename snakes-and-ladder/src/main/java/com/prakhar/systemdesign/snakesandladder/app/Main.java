package com.prakhar.systemdesign.snakesandladder.app;

import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of players");
        int numberOfPlayers = sc.nextInt();
        String players[] = new String[numberOfPlayers];
        System.out.printf("Hello and welcome Players! \nTotal players %d \n", numberOfPlayers);

        for (int i = 0; i < numberOfPlayers; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.printf("Enter your name Player = %d name: ", i+1);
            players[i] = sc.next();
            System.out.println();
        }
        System.out.println("All players are:");
        for (int i = 0; i < numberOfPlayers; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.printf("%s \n",  players[i]);
        }

    }
}