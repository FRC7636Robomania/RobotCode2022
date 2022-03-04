// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.ControlDrivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Sendball;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Tower;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SimpleAuto extends SequentialCommandGroup {
  /** Creates a new SimpleAuto. */
  public SimpleAuto(ControlDrivetrain drivetrain, Intake intake, Arm arm, Tower tower, Shooter shooter, Sendball sendball) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    
    addCommands(new AutoAim(tower).withTimeout(1));
    addCommands(new Shooting(shooter, sendball).withTimeout(2));
    
    addCommands(new Move(drivetrain, intake, arm, true, 1, 2));
    addCommands(new WaitCommand(0.6));

    addCommands(new Move(drivetrain, intake, arm, false, -1, 1.8));
    addCommands(new WaitCommand(0.6));

    addCommands(new AutoAim(tower).withTimeout(1));
    addCommands(new Shooting(shooter, sendball).withTimeout(2));
  }


}
