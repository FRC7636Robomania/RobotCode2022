// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.ControlDrivetrain;
import frc.robot.subsystems.Intake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Move extends SequentialCommandGroup {
  /** Creates a new Move. */
  public Move(ControlDrivetrain base, Intake intake, Arm arm, boolean isIntake, int mutiply, double seconds) {

    addRequirements(base, intake);
    if(isIntake) {
      addCommands(new InstantCommand(() -> arm.armout(), arm));
      addCommands(new WaitCommand(1.6));
      addCommands(new InstantCommand(() -> intake.intakego(), intake));
    }
    
    addCommands(new RunCommand(() -> base.curvatureDrive(-0.2 * mutiply, 0, false, false), base).withTimeout(seconds));
    addCommands(new InstantCommand(() -> base.curvatureDrive(0, 0, false, false), base));

    if(isIntake) {
      addCommands(new InstantCommand(() -> intake.intakeback(), intake));
      addCommands(new InstantCommand(() -> arm.armin(), arm));
      addCommands(new WaitCommand(0.5));
    }
  }
}
