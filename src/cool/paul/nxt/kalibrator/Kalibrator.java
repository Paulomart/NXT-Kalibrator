package cool.paul.nxt.kalibrator;

import lejos.nxt.ColorSensor;
import lejos.nxt.ColorSensor.Color;
import lejos.util.Delay;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;

public class Kalibrator {

	public static void main(String[] args) {
		// cali();
		Motor.A.setSpeed(300);
		Motor.B.setSpeed(300);
		//
		Motor.A.rotate(400, true);
		Motor.B.rotate(400);
		//
		Motor.A.rotate(250, true);
		Motor.B.rotate(-250);
		//
		Motor.A.rotate(320, true);
		Motor.B.rotate(320);
		//
		Motor.A.rotate(-280, true);
		Motor.B.rotate(280);
		//
		Motor.A.rotate(1200, true);
		Motor.B.rotate(1200);

	}

	public static void cali() {
		postionAtLine();
		Motor.A.setSpeed(100);
		Motor.B.setSpeed(100);
		Motor.A.rotate(-900, true);
		Motor.B.rotate(-900);
		Delay.msDelay(100);
		Motor.A.rotate(-170, true);
		Motor.B.rotate(170);
		//
		Motor.A.rotate(100, true);
		Motor.B.rotate(100);
		//
		Motor.A.rotate(-160, true);
		Motor.B.rotate(160);
		//
		Motor.A.rotate(500, true);
		Motor.B.rotate(500);

		postionAtLine();
	}

	public static void postionAtLine() {
		ColorSensor sl = new ColorSensor(SensorPort.S2);
		ColorSensor sr = new ColorSensor(SensorPort.S1);
		int x = 0;
		while (x < 8) {
			if (x != 0) {
				Motor.A.setSpeed(50);
				Motor.B.setSpeed(50);
				Motor.A.rotate(-50, true);
				Motor.B.rotate(-50);
			}
			int r = 8 - x * 3;
			if (r < 1) {
				r = 2;
			}
			Motor.A.setSpeed(r * 10);
			Motor.B.setSpeed(r * 10);

			boolean hadRight = false;
			boolean hadLeft = false;
			boolean rightDone = false;
			boolean leftDone = false;
			while (!leftDone || !rightDone) {
				int cr = sr.getColorID();
				int cl = sl.getColorID();
				if (!rightDone) {
					Motor.A.rotate(r);
				}
				if (!leftDone) {
					Motor.B.rotate(r);
				}
				if (cr == Color.WHITE && hadRight) {
					rightDone = true;
				}
				if (cl == Color.WHITE && hadLeft) {
					leftDone = true;
				}
				if (cr == Color.BLACK) {
					hadRight = true;
				}
				if (cl == Color.BLACK) {
					hadLeft = true;
				}

				/*
				 * if (!rightDone && sr.getColorID() != Color.WHITE) {
				 * Motor.A.rotate(r); } else { rightDone = true; } if (!leftDone
				 * && sl.getColorID() != Color.WHITE) { Motor.B.rotate(r); }
				 * else { leftDone = true; }
				 */
			}
			x++;
		}
	}
}
