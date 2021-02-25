package com.naman.quad.subsystems;

import com.pi4j.component.servo.ServoBase;
import com.pi4j.component.servo.impl.GenericServo;
import com.pi4j.component.servo.impl.PCA9685GpioServoDriver;
import com.pi4j.component.servo.impl.Servo;
import com.pi4j.gpio.extension.pca.PCA9685Pin;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.exception.ValidationException;

public abstract class ServoSubsystem {

	private PCA9685GpioServoDriver gpioServoDriver;
	private GenericServo servo;

	private int minLimit, maxLimit;

	private int homePos;

	public ServoSubsystem(Pin pin, boolean isReversed, String name, int homePos, int minLimit, int maxLimit) {
		gpioServoDriver = new PCA9685GpioServoDriver(Body.getGpioProvider(), pin);
		servo = new GenericServo(gpioServoDriver, name);
		this.homePos = homePos;
		servo.setReversed(isReversed);
		setMinLimit(minLimit);
		setMaxLimit(maxLimit);
	}

	public void home() {
		servo.setPosition(homePos);
	}

	public void setMinLimit(int min) {
		this.minLimit = min;
	}

	public void setMaxLimit(int max) {
		this.maxLimit = max;
	}

	private float validateLimit(float position) {
		if (position < minLimit || position > maxLimit) {
			throw new ValidationException("Position [" + position + "] must be between " + minLimit + "(%) and +"
					+ maxLimit + "(%) but is [" + position + "]");
		}
		return position;
	}

	public void goToMax() {
		servo.setPosition(maxLimit);
	}

	public void goToMin() {
		servo.setPosition(minLimit);
	}

	public void setPosition(float pos) {
		pos = validateLimit(pos);
		servo.setPosition(pos);
	}

	public void disable() {
		gpioServoDriver.setServoPulseWidth(0);
	}

	public PCA9685GpioServoDriver getServoDriver() {
		return gpioServoDriver;
	}

	public GenericServo getServo() {
		return servo;
	}

}