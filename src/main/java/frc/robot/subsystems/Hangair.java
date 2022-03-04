package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;




public class Hangair extends SubsystemBase {
   private final DoubleSolenoid hangdoubleSolenoid =new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);

    public  Hangair(){
    }

    public void hangairin(){ 
      hangdoubleSolenoid.set(DoubleSolenoid.Value.kForward);
    }

        
    public void hangairout(){        
      hangdoubleSolenoid.set(DoubleSolenoid.Value.kReverse);
    }


}