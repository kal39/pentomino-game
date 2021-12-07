package pentomino.ga;

import pentomino.bot.BotTest;

import java.text.DecimalFormat;
import java.util.Random;

public class Individual implements Comparable<Individual> {
	final static int botType = 2;

	private static final DecimalFormat df = new DecimalFormat("0.00");

	private double[] chromosome;
	private double fitness;

	int samples;
	static Random rand = new Random();

	public Individual(int sampleCount) {
		samples = sampleCount;
		fitness = 0;
	}

	public void generate_chromosome(int weightCount) {
		chromosome = new double[weightCount];

		for (int i = 0; i < chromosome.length; i++) {
			chromosome[i] = -1.0 + 2 * rand.nextDouble();
		}
	}

	public double[] get_weights() {
		return chromosome;
	}

	public String get_weight_string() {
		String ret = new String();
		for (int i = 0; i < chromosome.length; i++) {
			ret += df.format(chromosome[i]) + " ";
		}

		return ret;
	}

	public void calculate_fitness() {
		fitness = BotTest.test_times(botType, samples, chromosome);
	}

	public double get_fitness() {
		return fitness;
	}

	public Individual crossover(Individual pair) {
		Individual child = new Individual(samples);
		child.chromosome = new double[chromosome.length];

		for (int i = 0; i < chromosome.length; i++) {
			child.chromosome[i] = i < chromosome.length / 2 ? chromosome[i] : pair.chromosome[i];
		}

		return child;
	}

	public void mutate() {
		chromosome[rand.nextInt(chromosome.length)] = -1.0 + 2 * rand.nextDouble();
	}

	@Override
	public int compareTo(Individual other) {
		return fitness > other.get_fitness() ? 1 : fitness < other.get_fitness() ? -1 : 0;
	}
}
