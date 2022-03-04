package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Intake extends SubsystemBase {
    private static TalonSRX intakemotor=new TalonSRX(Constants.intakemotorno);
    
    public  Intake(){
        intakemotor.configFactoryDefault();
    }

    //Intake.
    public void intakeback(){ 
      	intakemotor.set(ControlMode.PercentOutput, 0);
    }

    public void intakego(){        
      	intakemotor.set(ControlMode.PercentOutput, -0.8);
    }

    public void reverse() {
        intakemotor.set(ControlMode.PercentOutput, 0.7
        );
    }
}
