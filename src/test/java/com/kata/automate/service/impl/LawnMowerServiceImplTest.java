package com.kata.automate.service.impl;

import com.kata.automate.model.Automate;
import com.kata.automate.model.Coordinate;
import com.kata.automate.model.Grid;
import com.kata.automate.model.Orientation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LawnMowerServiceImplTest {
    private Grid grid;
    private Coordinate coordinate;
    private Automate automate;
    private LawnMowerServiceImpl lawnMowerService;

    @BeforeEach
    void setUp() {
        lawnMowerService = new LawnMowerServiceImpl();
    }






    @DisplayName("Inject empty instructions")
    @ParameterizedTest(name = "{index} : For an initial position of :  {0}, and after no instructions , we end up with exception  ")
    @CsvSource({"5 5 1 2 N, ,1 3 N" })
    public void testAutomate_should_return_exception_when_no_instruction_injected(String initialPosition, String input) {


        //GIVEN
        grid = new Grid(Integer.valueOf(initialPosition.split(" ")[0]), Integer.valueOf(initialPosition.split(" ")[1]) );
        coordinate = new Coordinate(Integer.valueOf(initialPosition.split(" ")[2]), Integer.valueOf(initialPosition.split(" ")[3]));
        automate = new Automate(coordinate, Orientation.valueOf(initialPosition.split(" ")[4].charAt(0)), grid);

        //WHEN
        //THEN
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->lawnMowerService.executeSomeInstructions(automate,input),
                "No instruction injected!");
    }

    @DisplayName("Inject automate without a initial position")
    @ParameterizedTest(name = "{index} : Can't create Automate without an initial position ")
    @CsvSource({"5 5 1 2 N,GAGAGAGAA,1 3 N" })
    public void testAutomate_should_return_exception_when_no_initial_position_injected(String initialPosition, String input) {
        grid = new Grid(Integer.valueOf(initialPosition.split(" ")[0]), Integer.valueOf(initialPosition.split(" ")[1]) );
        Assertions.assertThrows(NullPointerException.class,
                () ->new Automate(null, Orientation.valueOf(initialPosition.split(" ")[4].charAt(0)), grid),
                "Automate must have a coordinate!");

    }

    @DisplayName("Inject automate without orientation")
    @ParameterizedTest(name = "{index} : Can't create Automate without an orientation ")
    @CsvSource({"5 5 1 2 N,GAGAGAGAA,1 3 N" })
    public void testAutomate_should_return_exception_when_no_orientation_injected(String initialPosition, String input) {
        grid = new Grid(Integer.valueOf(initialPosition.split(" ")[0]), Integer.valueOf(initialPosition.split(" ")[1]) );
        coordinate = new Coordinate(Integer.valueOf(initialPosition.split(" ")[2]), Integer.valueOf(initialPosition.split(" ")[3]));
        Assertions.assertThrows(NullPointerException.class,
                () ->new Automate(coordinate, null, grid),
                "Automate must have a orientation!");

    }

    @DisplayName("Inject automate without grid")
    @ParameterizedTest(name = "{index} : Can't create Automate without an orientation ")
    @CsvSource({"5 5 1 2 N,GAGAGAGAA,1 3 N" })
    public void testAutomate_should_return_exception_when_no_grid_injected(String initialPosition, String input) {
        grid = new Grid(Integer.valueOf(initialPosition.split(" ")[0]), Integer.valueOf(initialPosition.split(" ")[1]) );
        coordinate = new Coordinate(Integer.valueOf(initialPosition.split(" ")[2]), Integer.valueOf(initialPosition.split(" ")[3]));
        Assertions.assertThrows(NullPointerException.class,
                () ->new Automate(coordinate,  Orientation.valueOf(initialPosition.split(" ")[4].charAt(0)), null),
                "Automate must have a grid!");

    }

    @DisplayName("inject wrong instruction string")
    @ParameterizedTest(name = "{index} : Can't create Automate without an orientation ")
    @CsvSource({"5 5 1 2 N,GAGAGAGAAS,1 3 N" })
    public void testAutomate_should_return_exception_when_instruction_different_from_A_G_D(String initialPosition, String input, String expected) {
        grid = new Grid(Integer.valueOf(initialPosition.split(" ")[0]), Integer.valueOf(initialPosition.split(" ")[1]) );
        coordinate = new Coordinate(Integer.valueOf(initialPosition.split(" ")[2]), Integer.valueOf(initialPosition.split(" ")[3]));
        automate = new Automate(coordinate, Orientation.valueOf(initialPosition.split(" ")[4].charAt(0)), grid);

        //WHEN
        //THEN
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->lawnMowerService.executeSomeInstructions(automate,input),
                "Not a direction value!");

    }

    @DisplayName("check the automate position after some moves")
    @ParameterizedTest(name = "{index} : For an initial position of :  {0}, and after these instructions : {1}, we end up with this position : {2}  ")
    @CsvSource({"5 5 1 2 N,GAGAGAGAA,1 3 N", "5 5 3 3 E,AADAADADDA,5 1 E" })
    void testAutomate_should_return_the_expected_position(String initialPosition, String input, String expected) {


        //GIVEN
        grid = new Grid(Integer.valueOf(initialPosition.split(" ")[0]), Integer.valueOf(initialPosition.split(" ")[1]) );
        coordinate = new Coordinate(Integer.valueOf(initialPosition.split(" ")[2]), Integer.valueOf(initialPosition.split(" ")[3]));
        automate = new Automate(coordinate, Orientation.valueOf(initialPosition.split(" ")[4].charAt(0)), grid);

        //WHEN
        lawnMowerService.executeSomeInstructions(automate,input);

        //THEN
        Assertions.assertEquals(expected, automate.getCoordinate() + " " + automate.getOrientation());
    }


}