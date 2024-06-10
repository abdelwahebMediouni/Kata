package com.kata.automate.service.impl;

import com.kata.automate.model.Automate;
import com.kata.automate.model.Coordinate;
import com.kata.automate.model.Grid;
import com.kata.automate.model.Orientation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class LawnMowerServiceImplFileTest {

    private LawnMowerServiceImpl lawnMowerService;
    private BufferedReader reader;


         @Test
        public void testAutomate_should_return_the_correct_last_position() {
        //GIVEN
        lawnMowerService = new LawnMowerServiceImpl();
        List<String> lines = reader.lines().collect(Collectors.toList());
        Grid grid = new Grid(Integer.valueOf( lines.get(0).split(" ")[0]), Integer.valueOf( lines.get(0).split(" ")[1]) );
        //GET first automate
        String initialPos = lines.get(1);
        String someInstructions1 = lines.get(2);
        Coordinate  coordinate = new Coordinate(Integer.valueOf(initialPos.split(" ")[0]), Integer.valueOf(initialPos.split(" ")[1]));
        Automate automate1 = new Automate(coordinate, Orientation.valueOf(initialPos.split(" ")[2].charAt(0)), grid);
        //GET second automate
        String initialPos2 = lines.get(3);
        String someInstructions2 = lines.get(4);
        Coordinate  coordinate2 = new Coordinate(Integer.valueOf(initialPos2.split(" ")[0]), Integer.valueOf(initialPos2.split(" ")[1]));
        Automate automate2 = new Automate(coordinate2, Orientation.valueOf(initialPos2.split(" ")[2].charAt(0)), grid);

        //WHEN
        lawnMowerService.executeSomeInstructions(automate1,someInstructions1);
        lawnMowerService.executeSomeInstructions(automate2,someInstructions2);

        //THEN
        Assertions.assertEquals("1 3 N", automate1.getCoordinate() + " " + automate1.getOrientation());
        Assertions.assertEquals("5 1 E", automate2.getCoordinate() + " " + automate2.getOrientation());
    }

    @Before
    public void z_setUpBufferedReader() throws IOException {
        reader = Files.newBufferedReader(
                Paths.get("src/test/resources/data.csv"), StandardCharsets.UTF_8);
    }

    @After
    public void z_closeBufferedReader() throws IOException {
        reader.close();
    }


}
