package org.example;

import java.util.Arrays;

class DataHandler {
    private static final int TARGET_BLOCK_SIZE = 6;
    private static final int TARGET_DATABLOCK_OFFSET = 2;

    static Byte[] reconstructTargets(Byte[] postMissionTargetsArray, Byte[] preMissionTargetsArray) {
        System.out.println("Reading pre mission targets array\n" + Arrays.toString(preMissionTargetsArray));
        System.out.println("Reading post mission targets array\n" + Arrays.toString(postMissionTargetsArray));
        //check how many targets were added
        int targetsAddedAmount = calculateNumberOfTargetsAdded(preMissionTargetsArray, postMissionTargetsArray);
        System.out.println("Number of targets added since last mission: " + targetsAddedAmount);
        if (targetsAddedAmount == 0) {
            System.out.println("No new targets added.");
            return postMissionTargetsArray;
        }
        //step 1 - move newly added targets to the end of the array filled with empty records
        int desiredTargetRecordOffset = (preMissionTargetsArray.length - TARGET_DATABLOCK_OFFSET) / TARGET_BLOCK_SIZE;
        Byte[] targetData = getTargetData(postMissionTargetsArray, 0, targetsAddedAmount);
        inputTargetData(targetData, postMissionTargetsArray, desiredTargetRecordOffset, targetsAddedAmount);
        System.out.println(targetsAddedAmount + " target(s) from record offset: 0 moved to offset: " + desiredTargetRecordOffset);
        System.out.println("Step 1 targets array\n" + Arrays.toString(postMissionTargetsArray));
        //step 2 - restore overwritten targets from preMissionTargetsArray
        int preMissionTargetsAmount = getNumberOfTargets(preMissionTargetsArray);
        int targetsToRestoreAmount = Math.min(targetsAddedAmount, preMissionTargetsAmount);
        targetData = getTargetData(preMissionTargetsArray, 0, targetsToRestoreAmount);//
        inputTargetData(targetData, postMissionTargetsArray, 0, targetsToRestoreAmount);
        System.out.println("Restored " + targetsToRestoreAmount + " target(s) from offset: 0");
        System.out.println("Step 2 targets array\n" + Arrays.toString(postMissionTargetsArray));
        return postMissionTargetsArray;
    }

    static Byte[] getTargetData(Byte[] targetsDataArray, int targetOffset, int numberOfTargets) {
        Byte[] result = new Byte[numberOfTargets * TARGET_BLOCK_SIZE];
        for (int i = 0; i < TARGET_BLOCK_SIZE * numberOfTargets; i++) {
            result[i] = targetsDataArray[TARGET_DATABLOCK_OFFSET + targetOffset * TARGET_BLOCK_SIZE + i];
        }
        return result;
    }

    static void inputTargetData(Byte[] targetData, Byte[] destinationArray, int targetOffset, int numberOfTargets) {
        for (int i = 0; i < TARGET_BLOCK_SIZE * numberOfTargets; i++) {
            destinationArray[TARGET_DATABLOCK_OFFSET + targetOffset * TARGET_BLOCK_SIZE + i] = targetData[i];
        }
    }

    static int getNumberOfTargets(Byte[] array) {
        return (array.length - TARGET_DATABLOCK_OFFSET) / TARGET_BLOCK_SIZE;
    }

    static int calculateNumberOfTargetsAdded(Byte[] preMissionArray, Byte[] postMissionArray) {
        if(preMissionArray.length > postMissionArray.length) return 0;
        return (postMissionArray.length - preMissionArray.length) / TARGET_BLOCK_SIZE;
    }
}
