
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Tower extends SubsystemBase {
    private SupplyCurrentLimitConfiguration supplyCurrentLimitConfiguration = new SupplyCurrentLimitConfiguration(true, 10, 10, 1);
    private String status = "stop";
    private static TalonSRX towerSrx = new TalonSRX(Constants.tower);
    private static double basicPower = 0.065;
    
    public Tower(){
        // towerSrx.configFactoryDefault();
        towerSrx.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.CTRE_MagEncoder_Absolute, 0, Constants.kTimesOut);
        towerSrx.setSelectedSensorPosition(0);

        towerSrx.setNeutralMode(NeutralMode.Brake);      
        towerSrx.setInverted(false);
       
        towerSrx.configPeakOutputForward(0.2, 5);
        towerSrx.configPeakOutputReverse(-0.2, 5);
        towerSrx.configNominalOutputForward(0, Constants.kTimesOut);
        towerSrx.configNominalOutputReverse(0, Constants.kTimesOut);
        towerSrx.configSupplyCurrentLimit(supplyCurrentLimitConfiguration);

        towerSrx.configForwardSoftLimitThreshold(3000, 0);
        towerSrx.configReverseSoftLimitThreshold(-3600, 0);
        towerSrx.configForwardSoftLimitEnable(true, 0);
        towerSrx.configReverseSoftLimitEnable(true, 0);
        towerSrx.setNeutralMode(NeutralMode.Brake);
        towerSrx.configVoltageCompSaturation(10.3);
        towerSrx.enableVoltageCompensation(true);

    }
    public static double lastX = 0;
    public void aimming(){
        double xx=Limelight.getTx();
        double absX = Math.abs(xx);
        int mutiply = 1;

        // towerSrx.set(ControlMode.PercentOutput, xx * );
        if(Limelight.getarea()>0){

            if(absX > 0 && absX < 4) {
                basicPower = 0.06;
            } else {
                basicPower = 0.07;
            }

            if(xx < 0) {
                mutiply = -1;
            } else {
                mutiply = 1;
            }

            towerSrx.set(ControlMode.PercentOutput, -xx * 0.0075 - basicPower * mutiply); 
        }else {
            towerSrx.set(ControlMode.PercentOutput, 0);
        }  
     }
     
    public void towerForward(){
        towerSrx.set(ControlMode.PercentOutput, 0.17);
        status = "turn right";
    }

    public void towerStop(){
        towerSrx.set(ControlMode.PercentOutput, 0);
        status = "stop";
    }

    public void towerReverse(){
        towerSrx.set(ControlMode.PercentOutput, -0.17);
        status = "turn left";
    }

    public String tower_status(){
        return status;
    }

    @Override
    public void periodic(){
        if(Limelight.getarea() > 0){
            lastX = Limelight.getTx();
        }
        SmartDashboard.putNumber("towerpo", towerSrx.getSelectedSensorPosition(0));
        SmartDashboard.putNumber("dis_program", Limelight.getdistances());

    }

    
} 
