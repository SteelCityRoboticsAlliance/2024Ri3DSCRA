// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  CANSparkFlex m_frontTop;
  CANSparkFlex m_frontBottom;
  CANSparkFlex m_backTop;
  CANSparkFlex m_backBottom;

  /** Creates a new Shooter. */
  public Shooter() {
    m_frontTop = new CANSparkFlex(Constants.FRONT_TOP_SHOOTER, MotorType.kBrushless);
    m_frontBottom = new CANSparkFlex(Constants.FRONT_BOTTOM_SHOOTER, MotorType.kBrushless);
    m_backTop = new CANSparkFlex(Constants.BACK_TOP_SHOOTER, MotorType.kBrushless);
    m_backBottom = new CANSparkFlex(Constants.BACK_BOTTOM_SHOOTER, MotorType.kBrushless);

    m_frontTop.setInverted(true);
    m_backTop.setInverted(true);
    m_frontBottom.setInverted(false);
    m_backBottom.setInverted(false);

    m_frontTop.setIdleMode(IdleMode.kCoast);
    m_backTop.setIdleMode(IdleMode.kCoast);
    m_frontBottom.setIdleMode(IdleMode.kCoast);
    m_backBottom.setIdleMode(IdleMode.kCoast);
  }

  public void runFront(double power) {
    m_frontTop.set(power);
    m_frontBottom.set(power);
  }

  public void runBack(double power) {
    m_backTop.set(power);
    m_backBottom.set(power);
  }

  public Command runShooterFactory(DoubleSupplier power) {
    return run(() -> {
      runFront(power.getAsDouble());
      runBack(power.getAsDouble());
    });
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
