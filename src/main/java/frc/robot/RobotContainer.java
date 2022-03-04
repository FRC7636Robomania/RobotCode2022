// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Shooting;
import frc.robot.commands.SimpleAuto;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.ControlDrivetrain;
import frc.robot.subsystems.Hangair;
import frc.robot.subsystems.Hangmotor;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Sendball;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Tower;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private Joystick stick = new Joystick(1);
  private Shooter shooter = new Shooter(stick);
  private Intake intake = new Intake();
  private Hangair hangair = new Hangair();
  private Sendball sendball = new Sendball(shooter);
  private Hangmotor hangmotor = new Hangmotor();
  private Tower tower = new Tower();
  private Arm arm = new Arm();
  private XboxController driverStation = new XboxController(2);
  private final SendableChooser<Command> chooser = new SendableChooser<Command>();
  private ControlDrivetrain controlDrivetrain = new ControlDrivetrain();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    Status();
    teleop();
    chooser();
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  public void chooser(){
    chooser.setDefaultOption("moveForward", new SimpleAuto(controlDrivetrain, intake, arm, tower, shooter, sendball));

    chooser.addOption("Null", null);
    SmartDashboard.putData(chooser);
  }
  private void configureButtonBindings() {

    //吸球反轉
    new JoystickButton(stick, 4)                    .whenHeld(new InstantCommand(() -> intake.reverse()))
                                                    .whenHeld(new InstantCommand(() -> intake.intakeback()));
    
    //手臂伸縮
    new JoystickButton(stick, 3)                    .whenHeld(new InstantCommand(() -> arm.act()));
                                                  // .whenHeld(new InstantCommand(() -> intake.intakeback()))
                                                  // .whenHeld(new InstantCommand(() -> sendball.brushstop()));

    //吸球
    new JoystickButton(stick, 2)                    .whenHeld(new InstantCommand(() -> intake.intakego(), intake))
                                                    .whenHeld(new InstantCommand(() -> sendball.brushtheball()))
                                                    .whenReleased(new InstantCommand(() -> intake.intakeback()))
                                                    .whenReleased(new InstantCommand(() -> sendball.stopAll()));
    //飛輪加速
    new JoystickButton(driverStation, 4)           	.whenHeld(new Shooting(shooter, sendball))
                                                    .whenReleased(new InstantCommand(()->shooter.stop(), shooter))
                                                    .whenReleased(new InstantCommand(() -> sendball.stopAll()));
                                        
    //上吊氣動
    new JoystickButton(driverStation, 7)            .whenHeld(new InstantCommand(()->hangair.hangairout(), hangair));

    new JoystickButton(driverStation, 9)           	.whenHeld(new InstantCommand(()->hangair.hangairin(), hangair));

    //上吊馬達
    new JoystickButton(driverStation, 1)      .whenHeld(new RunCommand(()->hangmotor.up(), hangmotor))
                                              .whenReleased(new InstantCommand(()->hangmotor.stop(), hangmotor));
    
    new JoystickButton(driverStation, 8)      .whenHeld(new RunCommand(()->hangmotor.down(), hangmotor))
                                              .whenReleased(new InstantCommand(()->hangmotor.stop(), hangmotor));

    new JoystickButton(driverStation, 5)      .whenHeld(new InstantCommand(() -> hangmotor.switchMode()));
                                    
    //自動瞄準
    new JoystickButton(driverStation, 6)			.whenHeld(new RunCommand(() -> tower.aimming()))
                                              .whenReleased(new InstantCommand(() -> tower.towerStop()));
    //轉塔左右
    new JoystickButton(driverStation, 2)      .whenHeld(new RunCommand(()->tower.towerForward()))
                                              .whenReleased(new InstantCommand(()->tower.towerStop()));

    new JoystickButton(driverStation, 3)      .whenHeld(new RunCommand(()->tower.towerReverse()))
                                              .whenReleased(new InstantCommand(()->tower.towerStop()));

    //joystick special shoot
    new JoystickButton(stick, 11)             .whenHeld(new RunCommand(() -> shooter.specialSpin(), shooter))
                                              .whenReleased(new InstantCommand(() -> shooter.stop(), shooter));

    new JoystickButton(stick, 12)             .whenHeld(new RunCommand(() -> sendball.specialShoot(), sendball))
                                              .whenReleased(new InstantCommand(() -> sendball.stopAll(), sendball));
  }

  public void teleop(){
  
    controlDrivetrain.setDefaultCommand(
      new RunCommand(()->
      controlDrivetrain.curvatureDrive(stick.getY() * 0.5, 
                                       stick.getZ() * 0.2, 
                                       true, 
                                       stick.getRawButton(2)),
        controlDrivetrain)
    );

  }
  public void Status(){

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
	  return chooser.getSelected();
  }
}
