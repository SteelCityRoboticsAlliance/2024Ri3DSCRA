// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  CANSparkFlex m_frontTop;
  CANSparkFlex m_frontBottom;
  CANSparkMax m_funnelLeft;
  CANSparkMax m_funnelRight;

  /** Creates a new Shooter. */
  public Shooter() {
    m_frontTop = new CANSparkFlex(Constants.FRONT_TOP_SHOOTER, MotorType.kBrushless);
    m_frontBottom = new CANSparkFlex(Constants.FRONT_BOTTOM_SHOOTER, MotorType.kBrushless);
    m_funnelLeft = new CANSparkMax(Constants.FUNNEL_LEFT, MotorType.kBrushed);
    m_funnelRight = new CANSparkMax(Constants.FUNNEL_RIGHT, MotorType.kBrushed);

    m_frontTop.setInverted(true);
    m_frontBottom.setInverted(false);
    m_funnelRight.setInverted(true);
    m_funnelLeft.setInverted(false);

    m_frontTop.setIdleMode(IdleMode.kCoast);
    m_funnelLeft.setIdleMode(IdleMode.kCoast);
    m_frontBottom.setIdleMode(IdleMode.kCoast);
    m_funnelRight.setIdleMode(IdleMode.kCoast);
  }

  public void runShooter(double power) {
    m_frontTop.set(power);
    m_frontBottom.set(power);
  }

  public void runFunnel(double power) {
    m_funnelLeft.set(power);
    m_funnelRight.set(power);
  }

  public Command runShooterFactory(DoubleSupplier power) {
    return run(() -> {
      runShooter(power.getAsDouble());
      runFunnel(power.getAsDouble() == 0.0 ? 0.0 : 0.5);  
    });
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
