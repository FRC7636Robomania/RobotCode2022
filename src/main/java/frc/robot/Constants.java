/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static class Path{
        public static String moveForward = "output/MoveForward.json";

    }
    public static final int kTimesOut = 10;  

    /*馬達腳位*/


    public static final int tower            = 3;
    public static final int intakemotorno    = 2;
    public static final int sendshootmotorno    =1 ;
    public static final int brushmotorno   =5 ; 
    public static final int hangleftmotorno   = 18 ;
    public static final int hangrightmotorno   = 4;
    public static final int shootermasterno = 16;
    public static final int shooterslaverno = 19;

    public static class Value{
        public static boolean      aimming       = false;
    }

    public static class Button{
        public static final int rateChanger = 8;  //old
    }

    public static class Chassis{
        public static final int leftMaster    = 17;
        public static final int leftFollewer  = 20;
        public static final int rightMaster   = 9;
        public static final int rightFollower = 15;
        public static final double distancePerPulse = 0.1524 * Math.PI / 2048 / 9.7;
        public static final double wheelPitch = 0.54;

        public static final boolean isRightMotorInvert = true;
        public static final boolean isLeftMotorInvert = false;
        public static final boolean isRightPhaseInvert = true;
        public static final boolean isLeftPhaseInvert = false;

        public static final double kS = 0.9;
        public static final double kV = 0.2;
        public static final double kA = 0.008;
        public static final double kP = 1.0;
    }
}