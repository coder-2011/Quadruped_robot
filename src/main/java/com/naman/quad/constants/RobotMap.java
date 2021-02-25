package com.naman.quad.constants;

import com.pi4j.gpio.extension.pca.PCA9685Pin;
import com.pi4j.io.gpio.Pin;

public class RobotMap {
	
	public static final Pin FRONT_LEFT_HIP = PCA9685Pin.PWM_00;
    public static final Pin FRONT_RIGHT_HIP = PCA9685Pin.PWM_01;
    public static final Pin BACK_LEFT_HIP = PCA9685Pin.PWM_02;
    public static final Pin BACK_RIGHT_HIP = PCA9685Pin.PWM_03;
    
    public static final Pin FRONT_LEFT_FOOT = PCA9685Pin.PWM_04;
    public static final Pin FRONT_RIGHT_FOOT = PCA9685Pin.PWM_05;
    public static final Pin BACK_LEFT_FOOT = PCA9685Pin.PWM_06;
    public static final Pin BACK_RIGHT_FOOT = PCA9685Pin.PWM_07;

}