// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class Pivot extends SubsystemBase {
  CANSparkMax m_pivotLeft;
  CANSparkMax m_pivotRight;
  /** Creates a new Pivot. */
  public Pivot() {
    m_pivotLeft = new CANSparkMax(Constants.PIVOT_LEFT, MotorType.kBrushless);
    m_pivotRight = new CANSparkMax(Constants.PIVOT_RIGHT, MotorType.kBrushless);

    m_pivotLeft.setIdleMode(IdleMode.kCoast);
    m_pivotRight.setIdleMode(IdleMode.kCoast);
  }

  public void toggleBrakeMode() {
    if (m_pivotLeft.getIdleMode() == IdleMode.kCoast && m_pivotRight.getIdleMode() == IdleMode.kCoast) {
      m_pivotLeft.setIdleMode(IdleMode.kBrake);
      m_pivotRight.setIdleMode(IdleMode.kBrake);
    } else {
      m_pivotLeft.setIdleMode(IdleMode.kCoast);
      m_pivotRight.setIdleMode(IdleMode.kCoast);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
