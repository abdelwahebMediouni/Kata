package com.kata.automate.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 class OrientationTest {

    @Test
     void should_return_Orientation_when_call_valueOf() {
        Orientation orientation = Orientation.valueOf('W');
        Assertions.assertEquals(Orientation.WEST,orientation);
    }

    @Test
     void should_throw_excetion_when_call_valueOf_with_no_orientation_value() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->Orientation.valueOf("Cat"),
                "Not a Orientation value!");
    }


}