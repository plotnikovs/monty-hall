package com.montyhall;

import java.util.Random;

public class MontyHallSimulator {

    private final int  simulationCount;
    private final GameType gameType;
    private int winCount;
    private final Random random;

    private final static int NUMBER_OF_BOXES = 3;

    public MontyHallSimulator(int simulationCount, GameType gameType, Random random){
        this.simulationCount = simulationCount;
        this.gameType = gameType;
        this.winCount = 0;
        this.random = random;

        startSimulation();
    }

    public MontyHallSimulator(int simulationCount, GameType gameType){
        this(simulationCount, gameType, new Random());
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

        int prizeLocation = this.random.nextInt(NUMBER_OF_BOXES);
        int playersChoice = this.random.nextInt(NUMBER_OF_BOXES);

        int hostsChoice = this.random.nextInt(NUMBER_OF_BOXES);

        while (hostsChoice == prizeLocation || hostsChoice == playersChoice){
            hostsChoice = this.random.nextInt(NUMBER_OF_BOXES);
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