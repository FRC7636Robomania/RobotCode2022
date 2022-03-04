// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistribution;

/** Add your docs here. */
public class PDP {
    private final static PowerDistribution pdp = new PowerDistribution();

    public static double getCurrent(int channel){
        return pdp.getCurrent(channel);
    }
}