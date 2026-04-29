package com.montyhall;

import java.util.Random;

public class MontyHallSimulator {

    private final int  simulationCount;
    private final GameType gameType;
    private int winCount;

    private final static int NUMBER_OF_BOXES = 3;

    public MontyHallSimulator(int simulationCount, GameType gameType){
        this.simulationCount = simulationCount;
        this.gameType = gameType;
        this.winCount = 0;

        startSimulation();
    }

    private void startSimulation(){
        for(int i = 0; i < simulationCount; i++){
            boolean gameWon = simulateGame();
            if(gameWon){
                winCount++;
            }
        }
    }

    private boolean simulateGame() {
        Random random = new Random();

        int prizeLocation = random.nextInt(NUMBER_OF_BOXES);
        int playersChoice = random.nextInt(NUMBER_OF_BOXES);

        int hostsChoice = random.nextInt(NUMBER_OF_BOXES);

        while (hostsChoice == prizeLocation || hostsChoice == playersChoice){
            hostsChoice = random.nextInt(NUMBER_OF_BOXES);
        }

        BoxState[] boxStates = {BoxState.EMPTY,BoxState.EMPTY,BoxState.EMPTY};
        boxStates[prizeLocation] = BoxState.PRIZE;

        if(gameType == GameType.CHANGE_CHOICE){
            playersChoice = NUMBER_OF_BOXES - hostsChoice - playersChoice;
        }

        return boxStates[playersChoice] == BoxState.PRIZE;
    }

    public double getWinRate(){
        return this.winCount * 100d / this.simulationCount;
    }
}