package org.example;

import java.util.Arrays;

class DataHandler {

    static Byte[] reconstructTargets(Byte[] postMissionTargetsArray, Byte[] preMissionTargetsArray) {
        System.out.println("Reading pre mission targets array\n" + Arrays.toString(preMissionTargetsArray));
        System.out.println("Reading post mission targets array\n" + Arrays.toString(postMissionTargetsArray));
        //check how many targets were added
        int numberOfTargetsAdded = (postMissionTargetsArray.length - preMissionTargetsArray.length) / 6;
        System.out.println("Number of targets added between last mission: " + numberOfTargetsAdded);
        if(numberOfTargetsAdded==0){
            System.out.println("No new targets added, or file reached its size limit.");
            return postMissionTargetsArray;
        }
        //reconstruct targets
        int numberOfTargetsToMove = numberOfTargetsAdded;
        int iterationGetTargetOffset = 0;
        int iterationInputTargetOffset = (postMissionTargetsArray.length - 2) / 6 - numberOfTargetsAdded;
        while (numberOfTargetsToMove>0) {
            //step 1 - move newly added targets to postMissionArray to the end of the array
            Byte[] targetData = getTargetData(postMissionTargetsArray, iterationGetTargetOffset);
            inputTargetData(targetData,postMissionTargetsArray, iterationInputTargetOffset);
            System.out.println("Target at offset: " + iterationGetTargetOffset +
                    " moved to offset: " + iterationInputTargetOffset);
            System.out.println("Step 1 targets array\n" + Arrays.toString(postMissionTargetsArray));
            //step 2 - restore overwritten targets from preMissionTargetsArray
            targetData = getTargetData(preMissionTargetsArray,iterationGetTargetOffset);
            inputTargetData(targetData,postMissionTargetsArray, iterationGetTargetOffset);
            System.out.println("Restored target from offset: " + iterationGetTargetOffset);
            System.out.println("Step 2 targets array\n" + Arrays.toString(postMissionTargetsArray));
            //update counters
            iterationGetTargetOffset++;
            iterationInputTargetOffset++;
            numberOfTargetsToMove--;
        }
        return postMissionTargetsArray;
    }

    static Byte[] getTargetData (Byte[] targetsDataArray, int targetOffset) {
        Byte[] result = new Byte[6];
        for(int i=0; i<6;i++){
            result[i] = targetsDataArray[2 + targetOffset * 6 + i];
        }
        return result;
    }

    static void inputTargetData (Byte[] targetData, Byte[] destinationArray, int targetOffset) {
        for(int i=0; i<6; i++) {
            destinationArray[2 + targetOffset * 6 + i] = targetData[i];
        }
    }

    static boolean isTargetDataSame (Byte[] targetData1, Byte[] targetData2) {
        for(int i=0; i<6; i++) {
            if(!targetData1[i].equals(targetData2[i])) return false;
        }
        return true;
    }

}
