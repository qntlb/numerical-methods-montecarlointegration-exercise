package com.christianfries.montecarlo.integration;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
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
public class MonteCarloIntegratonOfSinTimeSinTest {

	@Test
	public void test() {

		System.out.println("Testing getIntegralOfSinTimesSin()");

		boolean success;
		try {
			double integral = AssignmentSolutionMonteCarloIntegration.getIntegralOfSinTimesSin();

			success = Math.abs(integral-4.0) < 5E-2;
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
