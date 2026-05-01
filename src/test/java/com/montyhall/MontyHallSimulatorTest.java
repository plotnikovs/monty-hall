package com.montyhall;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MontyHallSimulatorTest {

    @Test
    void player_should_win_100_percent_when_choosing_correct_and_not_changing_choice(){

        int prizeLocation = 0;
        int playersChoice = 0;
        int hostsChoice = 1;

        int numberOfSimulations = 100;
        GameType gameType = GameType.NOT_CHANGE_CHOICE;
        Random randomMock = getRandomMock(prizeLocation,playersChoice,hostsChoice);

        MontyHallSimulator simulator = new MontyHallSimulator(numberOfSimulations,gameType,randomMock);

        assertEquals(100d,simulator.getWinRate());
    }

    @Test
    void player_should_win_100_percent_when_choosing_incorrect_and_changing_choice(){

        int prizeLocation = 0;
        int playersChoice = 1;
        int hostsChoice = 2;

        int numberOfSimulations = 100;
        GameType gameType = GameType.CHANGE_CHOICE;
        Random randomMock = getRandomMock(prizeLocation,playersChoice,hostsChoice);

        MontyHallSimulator simulator = new MontyHallSimulator(numberOfSimulations,gameType,randomMock);

        assertEquals(100d,simulator.getWinRate());
    }

    @Test
    void player_should_win_0_percent_when_choosing_incorrect_and_not_changing_choice(){

        int prizeLocation = 0;
        int playersChoice = 1;
        int hostsChoice = 2;

        int numberOfSimulations = 100;
        GameType gameType = GameType.NOT_CHANGE_CHOICE;
        Random randomMock = getRandomMock(prizeLocation,playersChoice,hostsChoice);

        MontyHallSimulator simulator = new MontyHallSimulator(numberOfSimulations,gameType,randomMock);

        assertEquals(0d,simulator.getWinRate());
    }


    @Test
    void player_should_win_0_percent_when_choosing_correct_and_changing_choice(){

        int prizeLocation = 0;
        int playersChoice = 0;
        int hostsChoice = 2;

        int numberOfSimulations = 100;
        GameType gameType = GameType.CHANGE_CHOICE;
        Random randomMock = getRandomMock(prizeLocation,playersChoice,hostsChoice);

        MontyHallSimulator simulator = new MontyHallSimulator(numberOfSimulations,gameType,randomMock);

        assertEquals(0d,simulator.getWinRate());
    }

    Random getRandomMock(int prizeLocation, int playersChoice, int hostsChoice){

        // Implementation provided by Copilot AI

        Random randomMock = mock(Random.class);
        AtomicInteger counter = new AtomicInteger(0);
        when(randomMock.nextInt(3)).thenAnswer(invocation -> {
            int[] values = {prizeLocation, playersChoice, hostsChoice};
            return values[counter.getAndIncrement() % values.length];
        });

        return  randomMock;
    }
}