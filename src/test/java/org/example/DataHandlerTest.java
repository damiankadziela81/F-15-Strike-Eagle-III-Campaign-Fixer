package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataHandlerTest {

    @Test
    void shouldReturnNumberOfTargets() {
        //given
        Byte[] array1 = new Byte[8];
        Byte[] array2 = new Byte[14];
        Byte[] array3 = new Byte[20];
        //when
        int result1 = DataHandler.getNumberOfTargets(array1);
        int result2 = DataHandler.getNumberOfTargets(array2);
        int result3 = DataHandler.getNumberOfTargets(array3);
        //then
        assertEquals(1,result1);
        assertEquals(2,result2);
        assertEquals(3,result3);
    }

    @Test
    void shouldCalculateNumberOfTargetsAdded() {
        //given
        Byte[] array1 = new Byte[8];
        Byte[] array2 = new Byte[14];
        Byte[] array3 = new Byte[20];
        //when
        int result1 = DataHandler.calculateNumberOfTargetsAdded(array1,array2);
        int result2 = DataHandler.calculateNumberOfTargetsAdded(array1,array3);
        int result3 = DataHandler.calculateNumberOfTargetsAdded(array3,array1);
        //then
        assertEquals(1,result1);
        assertEquals(2,result2);
        assertEquals(0,result3);
    }


    @Test
    void shouldReturnTargetData() {
        //given
        Byte[] array = {2,0,1,1,1,1,1,1,2,2,2,2,2,2};
        //when
        Byte[] result1 = DataHandler.getTargetData(array,0,1);
        Byte[] result2 = DataHandler.getTargetData(array,1,1);
        Byte[] result3 = DataHandler.getTargetData(array,0,2);
        //then
        assertArrayEquals(new Byte[] {1,1,1,1,1,1}, result1);
        assertArrayEquals(new Byte[] {2,2,2,2,2,2}, result2);
        assertArrayEquals(new Byte[] {1,1,1,1,1,1,2,2,2,2,2,2}, result3);
    }

    @Test
    void shouldInputTargetData() {
        //given
        Byte[] targetData1 = {1,1,1,1,1,1};
        Byte[] targetData2 = {2,2,2,2,2,2};
        Byte[] targetData3 = {3,3,3,3,3,3,4,4,4,4,4,4};
        Byte[] destinationArray = {4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        //when
        DataHandler.inputTargetData(targetData1,destinationArray,0,1);
        DataHandler.inputTargetData(targetData2,destinationArray,1,1);
        DataHandler.inputTargetData(targetData3,destinationArray,2,2);
        //then
        assertArrayEquals(new Byte[] {4,0,1,1,1,1,1,1,2,2,2,2,2,2,3,3,3,3,3,3,4,4,4,4,4,4},destinationArray);
    }

    @Test
    void shouldReconstructTargetsWhenTargetsAddedGreaterThanPreMissionTargets() {
        //given
        Byte[] preArray = {1,0,1,1,1,1,1,1};
        Byte[] postArray = {3,0,2,2,2,2,2,2,3,3,3,3,3,3,0,0,0,0,0,0};
        //when
        Byte[] result = DataHandler.reconstructTargets(postArray, preArray);
        //then
        assertArrayEquals(new Byte[] {3,0,1,1,1,1,1,1,2,2,2,2,2,2,3,3,3,3,3,3},result);
    }

    @Test
    void shouldReconstructTargetsWhenTargetsAddedNotGreaterThanPreMissionTargets() {
        //given
        Byte[] preArray = {2,0,1,1,1,1,1,1,2,2,2,2,2,2};
        Byte[] postArray = {3,0,3,3,3,3,3,3,2,2,2,2,2,2,0,0,0,0,0,0};
        //when
        Byte[] result = DataHandler.reconstructTargets(postArray, preArray);
        //then
        assertArrayEquals(new Byte[] {3,0,1,1,1,1,1,1,2,2,2,2,2,2,3,3,3,3,3,3},result);
    }
}