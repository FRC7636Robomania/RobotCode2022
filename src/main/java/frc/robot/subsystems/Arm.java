// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
  private final DoubleSolenoid intakedoubleSolenoid =new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);
  protected static Compressor intakecompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
  private boolean out = false;
  /** Creates a new Arm. */
  public Arm() {
  }

  public void act() {
    if(out) {
      intakedoubleSolenoid.set(DoubleSolenoid.Value.kForward);
    } else {
      intakedoubleSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    out = !out;
  }

  public void armout() {
      intakedoubleSolenoid.set(DoubleSolenoid.Value.kReverse);
 
  }
  
  public void armin() {
      intakedoubleSolenoid.set(DoubleSolenoid.Value.kForward);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
