// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.ChassisTeleopDriveCommand;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Shooter;

public class RobotContainer {
  Shooter m_shooter;
  ChassisSubsystem m_chassis;
  Intake m_intake;
  Pivot m_pivot;

  CommandXboxController m_controller;

  public RobotContainer() {
    m_shooter = new Shooter();
    m_chassis = new ChassisSubsystem();
    m_intake = new Intake();
    m_pivot = new Pivot();
    m_controller = new CommandXboxController(0);

    SmartDashboard.putNumber("Percent Output", 0);
    configureBindings();
  }

  private void configureBindings() {
    m_chassis.setDefaultCommand(new ChassisTeleopDriveCommand(m_chassis, m_controller));

    m_controller.a()
      .whileTrue(m_shooter.runShooterFactory(() -> SmartDashboard.getNumber("Percent Output", 0)))
      .whileFalse(m_shooter.runShooterFactory(() -> 0));

    m_controller.b().whileTrue(m_intake.setIntakeSpeedFactory(() -> 0.75)
        .alongWith(new InstantCommand(() -> m_shooter.runFunnel(0.5))))
      .whileFalse(m_intake.setIntakeSpeedFactory(() -> 0.0)
        .alongWith(new InstantCommand(() -> m_shooter.runFunnel(0.0))));
    m_controller.y().whileTrue(m_intake.setIntakeSpeedFactory(() -> -0.75))
      .whileFalse(m_intake.setIntakeSpeedFactory(() -> 0.0));

    m_controller.x().onTrue(new InstantCommand(() -> m_chassis.resetGyro()));

    m_controller.leftBumper().onTrue(new InstantCommand(() -> m_shooter.runFunnel(0.5)))
      .onFalse(new InstantCommand(() -> m_shooter.runFunnel(0)));
    m_controller.rightBumper().onTrue(new InstantCommand(m_pivot::toggleBrakeMode).ignoringDisable(true));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
