package com.kata.automate.service.impl;

import com.kata.automate.model.*;
import com.kata.automate.service.LawnMowerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

import static com.kata.automate.model.Orientation.*;
import static com.kata.automate.model.Orientation.EAST;
import static com.kata.automate.model.PivoteDirection.GAUCHE;

@Service
public class LawnMowerServiceImpl implements LawnMowerService {


    public static final char AVANCER = 'A';

    public void executeSomeInstructions(Automate automate, String instructions) {

        if(StringUtils.isBlank(instructions)){
            throw new IllegalArgumentException("No instruction injected!");
        }
        System.out.println(String.format("[BEGIN] Here is my postion :%s , and my orientation :%s.", automate.getCoordinate(), automate.getOrientation().getValue()));

        Stream<Character> instructionsChars = instructions.chars().mapToObj(c -> (char) c);
        instructionsChars.forEach(c -> makeMove(automate, c));

        System.out.println(String.format("[End] Here is my postion :%s , and my orientation :%s.", automate.getCoordinate(), automate.getOrientation().getValue()));
    }

    private void makeMove(Automate automate, char action) {
        if (AVANCER == action) {
            move(automate);
        } else {
            pivoter(automate, PivoteDirection.valueOf(action));
        }
    }

    private void move(Automate automate) {
        if (isMovePossible(automate)) {
            if (automate.getOrientation() == NORTH) moveOneCaseNorth(automate);
            if (automate.getOrientation() == SOUTH) moveOneCaseSouth(automate);
            if (automate.getOrientation() == WEST) moveOneCaseWest(automate);
            if (automate.getOrientation() == EAST) moveOneCaseEast(automate);
        }
    }

    private void moveOneCaseEast(Automate automate) {
        automate.getCoordinate().setX(automate.getCoordinate().getX() + 1);
    }

    private void moveOneCaseWest(Automate automate) {
        automate.getCoordinate().setX(automate.getCoordinate().getX() - 1);
    }

    private void moveOneCaseSouth(Automate automate) {
        automate.getCoordinate().setY(automate.getCoordinate().getY() - 1);
    }

    private void moveOneCaseNorth(Automate automate) {
        automate.getCoordinate().setY(automate.getCoordinate().getY() + 1);
    }

    private boolean isMovePossible(Automate automate) {
        return switch (automate.getOrientation()) {
            case NORTH -> isAllowedToMoveNorth(automate);
            case SOUTH -> isAllowedToMoveSouth(automate);
            case WEST -> isAllowedToMoveWest(automate);
            case EAST -> isAllowedToMoveEast(automate);
        };
    }

    private boolean isAllowedToMoveEast(Automate automate) {
        return automate.getCoordinate().getX() + 1 <= automate.getGrid().getNumColumns();
    }

    private boolean isAllowedToMoveWest(Automate automate) {
        return automate.getCoordinate().getX() - 1 >= 0;
    }

    private boolean isAllowedToMoveSouth(Automate automate) {
        return automate.getCoordinate().getY() - 1 >= 0;
    }

    private boolean isAllowedToMoveNorth(Automate automate) {
        return automate.getCoordinate().getY() + 1 <= automate.getGrid().getNumRows();
    }

    public void pivoter(Automate automate, PivoteDirection pivoteDirection) {
        switch (automate.getOrientation()) {
            case NORTH -> {
                if (GAUCHE == pivoteDirection) automate.setOrientation(WEST);
                else automate.setOrientation(EAST);
            }
            case SOUTH -> {
                if (GAUCHE == pivoteDirection) automate.setOrientation(EAST);
                else automate.setOrientation(WEST);
            }
            case WEST -> {
                if (GAUCHE == pivoteDirection) automate.setOrientation(SOUTH);
                else automate.setOrientation(NORTH);
            }
            case EAST -> {
                if (GAUCHE == pivoteDirection) automate.setOrientation(NORTH);
                else automate.setOrientation(SOUTH);
            }
        }
    }
}
