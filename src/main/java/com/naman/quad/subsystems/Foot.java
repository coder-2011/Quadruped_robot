package com.naman.quad.subsystems;

import com.pi4j.io.gpio.Pin;

public class Foot extends ServoSubsystem{

	public Foot(Pin pin, int homePos) {
        super(pin, false, "Foot", homePos, 0, 180);
	}
	
	public Foot(Pin pin, boolean isReversed, String name, int homePos) {
        super(pin, isReversed, name, homePos, 0, 180);
	}
	
	public Foot(Pin pin, boolean isReversed, String name, int homePos, int maxLimit, int minLimit) {
        super(pin, isReversed, name, homePos, maxLimit, minLimit);

    }
    
  

}