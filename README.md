# numerical-methods-montecarlointegration-exercise

In this exercise you should implement classes that provide a fairly flexible
framework for Monte-Carlo integration.

The framework should be flexible enough to allow

- integration of different function f : R^{n} \rightarrow R

- flexible specification of the integration domains using a transformation from [0,1]^{n} to a subset of R^{n}

- flexible use for different random number generators

For convenience we provide the interfaces that define the framework. You can find these interfacea in the package

```
info.quantlab.numericalmethods.lecture.montecarlo.integration
```

of the project `numerical-methods-lecture`, see [github.com/qntlb/numerical-methods-lecture](https://github.com/qntlb/numerical-methods-lecture). This project is already defined as a Maven dependency to this project.

## Interfaces (provided)

- `Integrand`
- `IntegrationDomain`
- `Integrator`
- `MonteCarloIntegratorFactory`

The MonteCarloIntegratorFactory's method requires a class implementing an `RandomNumberGenerator`. This interface and some classes implementing this interface can be found in the package

```
info.quantlab.numericalmethods.lecture.randomnumbers
```

## Classes

You may use the classes providing random number generators that will be or were developed during the lecture, e.g.,

- `RandomNumberGeneratorFrom1D`
- `MersenneTwister`

## Task

The exercise consists of two separate tasks.

### Task 1: A MonteCarloIntegrator

To complete your task:

- 1) Implement a class implementing the interface `Integrator` that performs a general Monte-Carlo integrations of
arbitrary functions on general domains.

The function to integrate will be provided to the integrator's method `integrate` as an object implementing the interface `Integrand`.

The integration domain will be provided to the integrator's method `integrate` as an object implementing the interface `IntegrationDomain`.

- 2) Implement a class implementing the interface `MonteCarloIntegratorFactory` that allows to create an object of the class that you have implemented in 1). Note: the `MonteCarloIntegratorFactory` simply calls the constructor of your class.

- 3) To allow us to test you implementation, complete the implementation of the method `getMonteCarloIntegratorFactory` of `MonteCarloIntegrationAssignmentSolution`. This allows to create an object of your `MonteCarloIntegratorFactory`. Our unit tests will use this to test your code..

- 4) Feel free to create your own UnitTests and JavaDoc documentation.

Suggestion: you may test your integrator with different random number generators, e.g. `MersenneTwister` via

```
	final long seed = 3141;
	RandomNumberGenerator randomNumberGenerator = new RandomNumberGeneratorFrom1D(new MersenneTwister(seed), domain.getDimension());
```

or a `HaltonSequence`.

### Task 2: Using your MonteCarloIntegrator to calculate the integral of a DoubleBinaryFunction

- 5) Complete the method `getIntegral` of `MonteCarloIntegrationAssignmentSolution`. Use your Monte-Carlo integrator to calculate the integral. 

## Integrand and IntegrationDomain

- Objects implementing `Integrand` provide a function f : A \rightarrow R defined on a domain A.
- Objects implementing `IntegrationDomain` provide a bijective function g : [0,1]^{n} \rightarrow A that transforms the integration domain and the determinant of the derivative (Jacobi matrix) dg/dx.
- Objects implementing `Integrator` provide the integral \int_A f(z) dz using substitution z = f(x). 

## Unit Tests

We encourage you writing your own unit tests.

## Optional Tasks

Can you implement an `Integrator` that implements a multi-dimensional Simpson's rule?

This is not tested in our unit tests.

