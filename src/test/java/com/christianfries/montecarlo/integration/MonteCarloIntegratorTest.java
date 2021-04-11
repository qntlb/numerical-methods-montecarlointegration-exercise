package com.christianfries.montecarlo.integration;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.christianfries.integration.assignment.AssignmentSolutionMonteCarloIntegration;

import info.quantlab.numericalmethods.lecture.montecarlo.check.MonteCarloIntegrationChecker;
import info.quantlab.numericalmethods.lecture.montecarlo.integration.MonteCarloIntegratorFactory;

/**
 * This class tests the implementation of your class.
 * The actual test code is not part of this project.
 * 
 * @author Christian Fries
 */
@RunWith(Parameterized.class)
public class MonteCarloIntegratorTest {

	@Parameters(name="{0}")
	public static Collection<Object[]> data() {

		return Arrays.asList(new Object[][] {
				new Object[] { "unit circle" },
				new Object[] { "normal cdf" }
		});
	}

	private final String testCase;

	public MonteCarloIntegratorTest(String testCase) {
		this.testCase = testCase;
	}

	@Test
	public void testIntegration() {
		MonteCarloIntegratorFactory integratorFactory = AssignmentSolutionMonteCarloIntegration.getMonteCarloIntegratorFactory();

		System.out.println("Testing " + integratorFactory.getClass().getCanonicalName() + " for " + testCase);

		boolean success;
		try {
			success = MonteCarloIntegrationChecker.check(integratorFactory, testCase);
		}
		catch(Exception e) {
			System.out.println(" Failed with exception " + e.getMessage());
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
