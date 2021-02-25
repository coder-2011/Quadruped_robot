package com.naman.quad.subsystems;

import com.pi4j.io.gpio.Pin;

public class Hips extends ServoSubsystem{

	public Hips(Pin pin, int homePos) {
        super(pin, false, "Hips", homePos, 0, 180);
	}
	
	public Hips(Pin pin, boolean isReversed, String name, int homePos) {
        super(pin, isReversed, name, homePos, 0, 180);
	}
	
	public Hips(Pin pin, boolean isReversed, String name, int homePos, int maxLimit, int minLimit) {
        super(pin, isReversed, name, homePos, maxLimit, minLimit);

    }
    
  

}