// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a project meant to test encoders output and calculate their DPP (Distance Per Pulse)
 * HOW TO CONFIGURE:
 * Change ENCODER_TYPE (the top most member) to the type of connection the encoder you want to test is using:
 * EXTERNAL (via roborio), TALON (via TalonSRX motor controller)
 * Then, configure the ports for it in the apropriate section below.
 */
public class Robot extends TimedRobot
{

    // EXTERNAL, TALON
    private EncoderType ENCODER_TYPE = EncoderType.EXTERNAL;

    // External Encoder
    private int ENCODER_PORT_A = 0;
    private int ENCODER_PORT_B = 1;

    // Via TalonSRX
    private int TALON_PORT = 0;

    Encoder m_encoderExternal;
    WPI_TalonSRX m_talonSRX;

    /**
     * This method is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {
    }

    /**
     * This method is called every robot packet, no matter the mode. Use this for items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
     * <p>
     * This runs after the mode specific periodic methods, but before LiveWindow and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {

        int ticks, factor;
        double ddp, distance;

        switch (ENCODER_TYPE) {
            case EXTERNAL:
                ticks = m_encoderExternal.get();
                factor = m_encoderExternal.getEncodingScale();
                ddp = m_encoderExternal.getDistancePerPulse();
                distance = m_encoderExternal.getDistance();
                break;
            case TALON:
                ticks = m_talonSRX.getSelectedSensorPosition();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + ENCODER_TYPE);
        }
            System.out.println("Encoder Ticks: " + ticks);
            SmartDashboard.putNumber("Ticks", ticks);
            System.out.println("Encoder Factor: " + factor);
            SmartDashboard.putNumber("Factor", factor);
            System.out.println("Encoder DDP:" + ddp);
            SmartDashboard.putNumber("DDP", ddp);
            System.out.println("Encoder Distance: " + m_encoderExternal.getDistance());
            SmartDashboard.putNumber("Distance", distance);


    }

    /**
     * This autonomous (along with the chooser code above) shows how to select between different
     * autonomous modes using the dashboard. The sendable chooser code works with the Java
     * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
     * uncomment the getString line to get the auto name from the text box below the Gyro.
     * <p>
     * You can add additional auto modes by adding additional comparisons to the switch structure
     * below with additional strings. If using the SendableChooser make sure to add them to the
     * chooser code above as well.
     */
    @Override
    public void autonomousInit() {
    }

    /** This method is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {
    }

    /** This function is called once when teleop is enabled. */
    @Override
    public void teleopInit() {}

    /** This method is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {}

    /** This function is called once when the robot is disabled. */
    @Override
    public void disabledInit() {}

    /** This function is called periodically when disabled. */
    @Override
    public void disabledPeriodic() {}

    /** This function is called once when test mode is enabled. */
    @Override
    public void testInit() {}

    /** This method is called periodically during test mode. */
    @Override
    public void testPeriodic() {}

    enum EncoderType {
        EXTERNAL,
        TALON
    }
}
