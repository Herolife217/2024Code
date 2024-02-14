// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkBase.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
public class Arm extends SubsystemBase {
  /** Creates a new Arm. */
  CANSparkMax rotate1;
  CANSparkMax rotate2;

  public Arm() {
    rotate1 = new CANSparkMax(14, MotorType.kBrushless);
    rotate2 = new CANSparkMax(15, MotorType.kBrushless);
    rotate1.setIdleMode(IdleMode.kBrake);
    rotate2.setIdleMode(IdleMode.kBrake);
    rotate1.setInverted(false);
    rotate2.setInverted(true);


  }
  /**
   * This method is an example of the 'subsystem factory' style of command creation. A method inside
   * the subsytem is created to return an instance of a command. This works for commands that
   * operate on only that subsystem, a similar approach can be done in RobotContainer for commands
   * that need to span subsystems. The Subsystem class has helper methods, such as the startEnd
   * method used here, to create these commands.
   */
  public Command getIntakeCommand() {
    // The startEnd helper method takes a method to call when the command is initialized and one to
    // call when it ends
    return this.startEnd(
        // When the command is initialized, set the wheels to the intake speed values
        () -> {
          setRotate1Speed(.25);
          setRotate2Speed(.25);
        },
        // When the command stops, stop the wheels
        () -> {
          stop();
        });
  }
 
  // An accessor method to set the speed (technically the output percentage) of the launch wheel
  public void setRotate1Speed(double speed) {
    rotate1.set(speed);
  }

  // An accessor method to set the speed (technically the output percentage) of the feed wheel
  public void setRotate2Speed(double speed) {
    rotate2.set(speed);
  }

  // A helper method to stop both wheels. You could skip having a method like this and call the
  // individual accessors with speed = 0 instead
  public void stop() {
    rotate1.set(0);
    rotate2.set(0);
  }
}
