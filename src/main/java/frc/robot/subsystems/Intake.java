// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class Intake extends SubsystemBase {
  CANSparkMax m_intake;
  /** Creates a new Intake. */
  public Intake() {
    m_intake = new CANSparkMax(Constants.INTAKE_ID, MotorType.kBrushless);
  }

  public void setIntakeSpeed(double power) {
    m_intake.set(power);
  }

  public Command setIntakeSpeedFactory(DoubleSupplier power) {
    return run(() -> setIntakeSpeed(power.getAsDouble()));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
