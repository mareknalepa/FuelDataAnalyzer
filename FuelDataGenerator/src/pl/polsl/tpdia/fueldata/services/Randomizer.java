package pl.polsl.tpdia.fueldata.services;

import java.util.Random;

public class Randomizer {

	private Random random;

	public Randomizer() {
		random = new Random();
	}

	public int getRandom(int min, int max) {
		return random.nextInt((max - min) + 1) + min;
	}
}
