package info.quantlab.numericalmethods.assignments.montecarlo.integration;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.Test;

import info.quantlab.numericalmethods.assignments.montecarlo.check.MonteCarloIntegrationAssignment;
import info.quantlab.numericalmethods.assignments.montecarlo.check.MonteCarloIntegrationImplementationChecker;
import info.quantlab.numericalmethods.lecture.montecarlo.integration.MonteCarloIntegratorFactory;

/**
 * This class tests the implementation of your class.
 * The actual test code is not part of this project.
 * 
 * @author Christian Fries
 */
public class MonteCarloIntegrationAssignmentTest {

	@Test
	public void testMonteCarloIntegrator() {

		boolean success = true;
		try {
			MonteCarloIntegratorFactory integratorFactory = new MonteCarloIntegrationAssignmentSolution().getMonteCarloIntegratorFactory();

			for(String testCase : List.of("unit circle", "normal cdf")) {
				System.out.println("Testing " + integratorFactory.getClass().getCanonicalName() + " for " + testCase);
				success &= MonteCarloIntegrationImplementationChecker.check(integratorFactory, testCase);
			}
		}
		catch(Exception e) {
			System.out.println("\tFailed with exception " + e.getMessage());
			success = false;
		}

		if(!success) {
			System.out.println("Sorry, the test failed.");
		}
		else {
			System.out.println("Congratulation! You solved this part of the exercise.");
		}

		System.out.println("_".repeat(79));

		if(!success) fail();
	}

	@Test
	public void testIntegralOfDoubleBinaryOperator() {

		boolean success = true;
		try {
			MonteCarloIntegrationAssignment solution = new MonteCarloIntegrationAssignmentSolution();

			System.out.println("Testing " + solution.getClass().getCanonicalName() + " for getIntegral method.");
			success &= MonteCarloIntegrationImplementationChecker.check(new MonteCarloIntegrationAssignmentSolution(), "getIntegral");
		}
		catch(Exception e) {
			System.out.println("\tFailed with exception " + e.getMessage());
			success = false;
		}

		if(!success) {
			System.out.println("Sorry, the test failed.");
		}
		else {
			System.out.println("Congratulation! You solved this part of the exercise.");
		}

		System.out.println("_".repeat(79));

		if(!success) fail();
	}
}
