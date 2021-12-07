package pentomino.ga;

import pentomino.ga.Trainer;

public class Trainer {
	final static int popSize = 25;
	final static int weightCount = 5;
	final static int samples = 50;

	public static void main(String[] args) {
		Population pop = new Population(samples, popSize, weightCount);

		for (int i = 0; i != -1; i++) {
			pop.calculate_fitness();
			pop.sort();
			System.out.println("Gen " + i + ":  Best score " + pop.get_best_fitness() + ", ["
					+ pop.get_best_weight_string() + "]\n");
			pop.select(popSize / 2);
			pop.crossover(popSize);
			pop.mutate(0.25);
		}
	}
}
