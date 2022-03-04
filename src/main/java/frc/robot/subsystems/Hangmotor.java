package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Hangmotor extends SubsystemBase {
    TalonFX left = new TalonFX(Constants.hangleftmotorno);
    TalonFX right = new TalonFX(Constants.hangrightmotorno);
    private SupplyCurrentLimitConfiguration currentLimit= new SupplyCurrentLimitConfiguration(true, 40, 50, 0.8);
    double v = 0.7;
    private int mode = 0;
    public  Hangmotor(){

        //left is master.
        left.configFactoryDefault();
        right.configFactoryDefault();

        
        left.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        right.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

        left.setSelectedSensorPosition(0);
        right.setSelectedSensorPosition(0);

        left.configSupplyCurrentLimit(currentLimit);
        right.configSupplyCurrentLimit(currentLimit);

        left.setInverted(false);
        right.setInverted(true);

        double voltageCompensation = 10.3;
        left.configVoltageCompSaturation(voltageCompensation);
        left.enableVoltageCompensation(true);

        right.configVoltageCompSaturation(voltageCompensation);
        right.enableVoltageCompensation(true);

        int softLimit = 12000;
        left.configForwardSoftLimitThreshold(softLimit);
        right.configForwardSoftLimitThreshold(softLimit);

        boolean enableLimit = false;
        left.configForwardSoftLimitEnable(enableLimit);
        right.configForwardSoftLimitEnable(enableLimit);
    }

    
    public void  up(){
        left.set(ControlMode.PercentOutput, v);
        right.set(ControlMode.PercentOutput, v);
    }

    public void  stop(){
        left.set(ControlMode.PercentOutput,0);
        right.set(ControlMode.PercentOutput, 0);
    }

    public void  down(){
        if(mode == 0) {
            left.set(ControlMode.PercentOutput, -v);
            right.set(ControlMode.PercentOutput, -v);

        }else if(mode == 1) {
            left.set(ControlMode.PercentOutput, -v);

        }else if(mode == 2) {
            right.set(ControlMode.PercentOutput, -v);

        }else {
            left.set(ControlMode.PercentOutput, -v);
            right.set(ControlMode.PercentOutput, -v);

        } 
    }

    public void switchMode() {
        ++mode;
        mode %= 3;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("hangLeft", left.getSelectedSensorPosition());
        SmartDashboard.putNumber("hangRight", right.getSelectedSensorPosition());
    }
}