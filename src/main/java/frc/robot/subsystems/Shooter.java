package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;     




public class Shooter extends SubsystemBase {
    private TalonFX shootermaster = new TalonFX(Constants.shootermasterno);
    private TalonFX shooterslaver = new TalonFX(Constants.shooterslaverno);
    private VictorSPX  sendshootmotor = new VictorSPX(Constants.sendshootmotorno);
    private Joystick joystick;

    private SupplyCurrentLimitConfiguration currentLimit
             = new SupplyCurrentLimitConfiguration(true, 40, 50, 0.8);
    
    public  Shooter(Joystick joystick){
        sendshootmotor.configFactoryDefault();
        shootermaster.configFactoryDefault();
        shooterslaver.follow(shootermaster);
        shooterslaver.setInverted(TalonFXInvertType.OpposeMaster);
        shootermaster.config_kF(0, 0.054);
        shootermaster.config_kP(0, 0.08);
        shootermaster.config_kD(0, 0.001);
        shootermaster.setInverted(true);
        shootermaster.setSensorPhase(true);
        shootermaster.configVoltageCompSaturation(9.8);
        shootermaster.configPeakOutputForward(1, 100);
        shootermaster.configPeakOutputReverse(0, 100);
        shootermaster.configPeakOutputForward(1, 100);
        shootermaster.configPeakOutputReverse(0, 100);
        shootermaster.configSupplyCurrentLimit(currentLimit);
        shootermaster.setNeutralMode(NeutralMode.Coast);
        shootermaster.configNeutralDeadband(0.03, 100);
        shootermaster.configClosedloopRamp(0.5);
        this.joystick = joystick;
    }

    public boolean isReady() {
      double target = Limelight.getTargetVelocity();
      if(target < 0) {
        return false;
      }
      double error =  Math.abs(shootermaster.getSelectedSensorVelocity() - target);

      return error <= 400;
    }

    public boolean isReady(double velocity) {
      double error =  Math.abs(shootermaster.getSelectedSensorVelocity() - velocity);
      return error <= 400;
    }

    public void shoot() {
      sendshootmotor.set(ControlMode.PercentOutput, -0.6);
    }

    public void stopShoot() {
      sendshootmotor.set(ControlMode.PercentOutput, 0);
    }

    public void spin(){
      double velocity = Limelight.getTargetVelocity();

      shootermaster.set(ControlMode.Velocity, velocity);
    }

    public void spin(double velocity) {
      shootermaster.set(ControlMode.Velocity, velocity);
    }

    public void stop(){
        shootermaster.set(ControlMode.PercentOutput, 0);

    }

    public void specialSpin() {
      double velocity = joystick.getThrottle() * 2950 + 9200;
      shootermaster.set(ControlMode.Velocity, velocity);
    }

    @Override
    public void periodic() {
      SmartDashboard.putNumber("flyvel", shootermaster.getSelectedSensorVelocity(0));
      SmartDashboard.putBoolean("isReady", isReady());
      SmartDashboard.putNumber("target_Velocity", Limelight.getTargetVelocity());
    }

}