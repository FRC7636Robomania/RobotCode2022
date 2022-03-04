package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;




public class Sendball extends SubsystemBase {
    private VictorSPX  brushsmotor = new VictorSPX(Constants.brushmotorno);
    private Shooter shooter = null;

    
    public Sendball(Shooter shooter){
        brushsmotor.configFactoryDefault();
        this.shooter = shooter;
    }

    public void  brushtheball(){
        brushsmotor.set(ControlMode.PercentOutput, 0.5);
    }

    public void brushstop(){
        brushsmotor.set(ControlMode.PercentOutput, 0);
    }

    public void tryShoot() {
        if(shooter.isReady()) { //shooter.isReady()
            brushtheball();
            shooter.shoot();
        }
    }

    public void tryShoot(double velocity) {
        if(shooter.isReady(velocity)) { //shooter.isReady()
            brushtheball();
            shooter.shoot();
        }
    }



    public void stopAll() {
        shooter.stopShoot();
        brushstop();
    }


    public void specialShoot() {
        brushtheball();
        shooter.shoot();
    }
  

}

