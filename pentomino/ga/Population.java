package pentomino.ga;

import java.util.Arrays;
import java.util.Random;
import java.util.Collections;

public class Population {
	private Individual[] individuals;
	private int samples;

	static Random rand = new Random();

	public Population(int sampleCount, int size, int weightCount) {
		samples = sampleCount;
		individuals = new Individual[size];
		for (int i = 0; i < size; i++) {
			individuals[i] = new Individual(samples);
			individuals[i].generate_chromosome(weightCount);
		}
	}

	public void calculate_fitness() {
		for (int i = 0; i < individuals.length; i++) {
			individuals[i].calculate_fitness();
		}
	}

	public void sort() {
		Arrays.sort(individuals, Collections.reverseOrder());
	}

	public void select(int count) {
		Individual[] tmp = new Individual[count];
		for (int i = 0; i < count; i++) {
			tmp[i] = individuals[i];
		}
		individuals = tmp;
	}

	public void crossover(int count) {
		Individual[] tmp = new Individual[count];
		for (int i = 0; i < count; i++) {
			tmp[i] = individuals[rand.nextInt(individuals.length / 2)]
					.crossover(individuals[rand.nextInt(individuals.length / 2)]);
		}
		individuals = tmp;
	}

	public void mutate(double chance) {
		for (int i = 0; i < individuals.length; i++) {
			if (rand.nextDouble() < chance)
				individuals[i].mutate();
		}
	}

	public double get_best_fitness() {
		return individuals[0].get_fitness();
	}

	public String get_best_weight_string() {
		return individuals[0].get_weight_string();
	}

	public void print() {
		for (int i = 0; i < individuals.length; i++) {
			System.out.println(
					i + ": " + individuals[i].get_weight_string() + ", score: " + individuals[i].get_fitness());
		}
	}
}
