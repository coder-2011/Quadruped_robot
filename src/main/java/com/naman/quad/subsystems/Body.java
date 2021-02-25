/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.naman.quad.subsystems;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pi4j.component.servo.impl.PCA9685GpioServoDriver;
import com.pi4j.gpio.extension.pca.PCA9685GpioProvider;
import com.pi4j.gpio.extension.pca.PCA9685Pin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.naman.quad.constants.RobotConstants;
import com.naman.quad.constants.RobotMap;
import com.naman.quad.utils.Logger;

/**
 * Represents a shelbot's body
 * 
 * @author lakshbhambhani
 */
public class Body extends Subsystem {

	private static PCA9685GpioProvider gpioProvider;
	
	private static Hip frontRightHip;
	private static Hip frontLeftHip;
    private static Hip backLeftHip;
    private static Hip backRightHip;
	
	private static Foot frontRightFoot;
	private static Foot frontLeftFoot; 
    private static Foot backLeftFoot; 
    private static Foot backRightFoot; 

	public Body() {
		
		final GpioController gpio = GpioFactory.getInstance();
		
		try {
			gpioProvider = createProvider();
		} catch (UnsupportedBusNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frontRightHip = new Hip(RobotMap.FRONT_RIGHT_HIP, false, "frontRightHip", RobotConstants.frontRightHipHomePos, 0, 180);
		backRightHip = new Hip(RobotMap.BACK_RIGHT_HIP, false, "backRightHip", RobotConstants.backRightHipHomePos, 0, 180);
        backLeftHip = new Hip(RobotMap.BACK_LEFT_HIP, false, "backLeftHip", RobotConstants.backLeftHipHomePos, 0, 180);
        frontLeftHip = new Hip(RobotMap.FRONT_LEFT_HIP, false, "frontLeftHip", RobotConstants.frontLeftHipHomePos, 0, 180);
		
		frontRightFoot = new Foot(RobotMap.FRONT_RIGHT_FOOT, false, "frontRightFoot", RobotConstants.frontRightFootHomePos, 0, 180);
		FrontLeftFoot = new Foot(RobotMap.FRONT_LEFT_FOOT, false, "frontLeftFoot", RobotConstants.frontLeftFootHomePos, 0, 180);
        backRightFoot = new Foot(RobotMap.BACK_RIGHT_FOOT, false, "backRightFoot", RobotConstants.backRightFootHomePos, 0, 180);
        backLeftFoot = new Foot(RobotMap.BACK_LEFT_FOOT, false, "backLeftFoot", RobotConstants.backLeftFootHomePos, 0, 180);


        // Define outputs in use for this example
        provisionPwmOutputs(gpioProvider);
    
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(null);
	}
	
	private PCA9685GpioProvider createProvider() throws UnsupportedBusNumberException, IOException {
        BigDecimal frequency = PCA9685GpioProvider.ANALOG_SERVO_FREQUENCY;
        BigDecimal frequencyCorrectionFactor = new BigDecimal("1.0578");

        I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
        return new PCA9685GpioProvider(bus, 0x40, frequency, frequencyCorrectionFactor);
    }
	
	private GpioPinPwmOutput[] provisionPwmOutputs(final PCA9685GpioProvider gpioProvider) {
        GpioController gpio = GpioFactory.getInstance();
        GpioPinPwmOutput myOutputs[] = {
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_00, "Servo 00"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_01, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_02, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_03, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_04, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_05, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_06, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_07, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_08, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_09, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_10, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_11, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_12, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_13, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_14, "not used"),
                gpio.provisionPwmOutputPin(gpioProvider, PCA9685Pin.PWM_15, "not used")};
        return myOutputs;
    }
	
	public static PCA9685GpioProvider getGpioProvider() {
		return gpioProvider;
	}
	
	public Hip getFrontRightHip() {
		return frontRightHip;
	}
	
	public Hip getFrontLeftHip() {
		return frontLeftHip;
	}
	
	public Knee getBackLeftHip() {
		return backLeftHip;
	}
	
	public Knee getBackRightHip() {
		return backRightHip;
	}
	
	public Foot getFrontRightFoot() {
		return frontRightFoot;
	}
	
	public Foot getFrontLeftFoot() {
		return frontLeftFoot;
	}
	
	public Foot getBackLeftFoot() {
	  return backLeftFoot;
	}
	
    public Foot getBackRightFoot() {
	  return backRightFoot;
	}
    
	// public Hip[] getHips() {
	// 	return new Hip[] {getRightHip(), getLeftHip()};
	// }
	
	// public Foot[] getFeet() {
	// 	return new Foot[] {getRightFoot(), getLeftFoot()};
	// }

	// public Knee[] getKnees(){
	// 	return new Knee[] {getRightKnee(), getLeftKnee()};
	// }

	// public ServoSubsystem[] getRightLeg(){
	// 	return new ServoSubsystem[] {getRightHip(), getRightKnee(), getRightFoot()};
	// }

	// public ServoSubsystem[] getLeftLeg(){
	// 	return new ServoSubsystem[] {getLeftHip(), getLeftKnee(), getLeftFoot()};
	// }
	
	public void logAllServoPositions() {
		Logger.consoleLog("FrontRightHip %s", getFrontRightHip().getServo().getPosition());
		Logger.consoleLog("FrontLeftHip %s", getFrontLeftHip().getServo().getPosition());
		Logger.consoleLog("BackLeftHip %s", getBackLeftHip().getServo().getPosition());
		Logger.consoleLog("FrontLeftHip %s", getFrontLeftHip().getServo().getPosition());
		Logger.consoleLog("FrontRightFoot %s", getFrontRightFoot().getServo().getPosition());
		Logger.consoleLog("FrontLeftFoot %s", getFrontLeftFoot().getServo().getPosition());
        Logger.consoleLog("BackLeftFoot %s", getBackLeftFoot().getServo().getPosition());
        Logger.consoleLog("BackRightFoot %s", getBackRightFoot().getServo().getPosition());
	}
	
}