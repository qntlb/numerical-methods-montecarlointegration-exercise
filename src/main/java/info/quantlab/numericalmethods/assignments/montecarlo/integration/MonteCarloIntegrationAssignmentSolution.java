package info.quantlab.numericalmethods.assignments.montecarlo.integration;

import java.util.function.DoubleBinaryOperator;

import info.quantlab.numericalmethods.assignments.montecarlo.check.MonteCarloIntegrationAssignment;
import info.quantlab.numericalmethods.lecture.montecarlo.integration.MonteCarloIntegratorFactory;

public class MonteCarloIntegrationAssignmentSolution implements MonteCarloIntegrationAssignment {

	/**
	 * The solution of the first part of exercise, implementing a MonteCarloIntegratorFactory providing a MonteCarloIntegrator
	 * 
	 * @return A class implementing MonteCarloIntegratorFactory
	 */
	@Override
	public MonteCarloIntegratorFactory getMonteCarloIntegratorFactory() {
		
		// Replace the following line by a statement return-ing a new instance of your implementation of MonteCarloIntegratorFactory 
		throw new UnsupportedOperationException("Method not yet implemented.");
	}

	/**
	 * The solution of the second part of exercise.
	 * Calculating the integral f(x,y) dx dy for a general binary operator
	 * using the integration domain [a,b] x [c,d] 
	 * using your Monte-Carlo Integrator.
	 * 
	 * @param function The function to integrate, given as a {@link DoubleBinaryOperator}.
	 * @param lowBoundX The lower bound a for the integral of dx.
	 * @param upperBoundX The upper bound b for the integral of dx.
	 * @param lowerBoundY The lower bound c for the integral of dy.
	 * @param upperBoundY The upper bound d for the integral of dy.
	 * @return The value of the integral f(x,y) dx dy.
	 */
	@Override
	public double getIntegral(DoubleBinaryOperator function, double lowerBoundX, double upperBoundX, double lowerBoundY, double upperBoundY) {
		
		// Delete the following line and implement this method returning an integral for the given function and bounds.
		throw new UnsupportedOperationException("Method not yet implemented.");
	}
}
