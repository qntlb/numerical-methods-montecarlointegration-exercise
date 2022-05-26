# numerical-methods-montecarlointegration-exercise

In this exercise you should implement classes that provide a fairly flexible
framework for Monte-Carlo integration.

The framework should be flexible enough to allow

- integration of different function f : R^{n} \rightarrow R

- flexible specification of the integration domains using a transformation from [0,1]^{n} to a subset of R^{n}

- flexible use for different random number generators

For convenience we provide the interfaces that define the framework. You can find these interface in the package

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

### Integrand and IntegrationDomain

- Objects implementing `Integrand` provide a function f : A \rightarrow R defined on a domain A.
- Objects implementing `IntegrationDomain` provide a bijective function g : [0,1]^{n} \rightarrow A that transforms the integration domain and the determinant of the derivative (Jacobi matrix) dg/dx.
- Objects implementing `Integrator` provide the integral \int_A f(z) dz using substitution z = f(x). 

## Classes

You may use the classes providing random number generators that will be or were developed during the lecture, e.g.,

- `RandomNumberGeneratorFrom1D`
- `MersenneTwister`

## Task

The exercise consists of two separate tasks.

### Task 1: A MonteCarloIntegrator

To complete your task:

- 1) Implement a class implementing the interface `Integrator` that performs a general Monte-Carlo integration of
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

- 5) Complete the method `getIntegral` of `MonteCarloIntegrationAssignmentSolution`. Use your Monte-Carlo integrator with approximately 1 million sample points to calculate the integral.

### Tasks 3: Implement a SimpsonsIntegrator for the general Simpson's rule in d dimension

To complete your task:

- 6) Implement a class implementing the interface `Integrator` that performs a general (composite) Simpson's rule integration
in d dimension of arbitrary functions on general domains.

The function to integrate will be provided to the integrator's method `integrate` as an object implementing the interface `Integrand`.

The integration domain will be provided to the integrator's method `integrate` as an object implementing the interface `IntegrationDomain`.

- 7) Implement a class implementing the interface `IntegratorFactory` that allows to create an object of the class that you have implemented in 1). Note: the `IntegratorFactory` simply calls the constructor of your class.

- 8) To allow us to test you implementation, complete the implementation of the method `getSimpsonsIntegratorFactory` of `MonteCarloIntegrationAssignment`. This allows to create an object of your `IntegratorFactory`. Our unit tests will use this to test your code.

- 9) Feel free to create your own UnitTests and JavaDoc documentation.

#### Hints

- Note that your Simpsons integral and your Monte-Carlo integral only operator on [0,1]^d (the object implementing the Domain will provide you with the transformation).

- Your Simpsons integrator should accept the `numberOfValuationPoints` as an argument. This should be the *minumum total number of valuation points*. Since the Simpsons has to use an odd number of points in every dimension, you may use the following code to round this number appropriately to `numberOfSamplePointsEffective`, using `numberOfSamplePointsPerDimension` per dimension.

```
	int dimension = integrationDomain.getDimension();
	int numberOfSamplePointsPerDimension = 2 * (int) (Math.ceil(Math.pow(numberOfValuationPoints, 1.0/dimension))/2) + 1;
	int numberOfSamplePointsEffective = (int) Math.pow(numberOfSamplePointsPerDimension, dimension);
```

- You might realise that you need to think a bit to find a short algorithm to implement the Simpsons integration in arbitrary dimensions. It is possible to create a fairly short implementation if you implement a multi-index `index` - an array of length `dimension` where each entry runs from `0` to `numberOfSamplePointsPerDimension-1`.


## Unit Tests

We encourage you writing your own unit tests.

## Further Research

For those interested, this project offers the opportunity to explore Monte-Carlo integration in more detail.
Here are a few suggestions:

- *Explore the dependency on the dimension:* Consider the integration of x &rarr; product(i=0,...,d-1) sin(x<sub>i</sub>) for 0 < x<sub>i</sub> < &pi;. The value of the integral is 2^d. This is an d-dimensional integral. For this function, compare the accuracy of Monte-Carlo integration and Simpsons integration with d = 1, 2, 4, 8 using for example n = 5^8 = 390625 sample points.

- *Explore the dependency on the smoothness of the function:* Consider the integration of (x<sub>0</sub>,x<sub>1</sub>) &rarr; x<sub>0</sub><sup>2</sup> + x<sub>1</sub><sup>2</sup> &lt; 1.0 ? 1.0 : 0.0 for 0 < x<sub>i</sub> < 1. The analytic value of this integral &pi;. For this function, compare the accuracy of Monte-Carlo integration and Simpsons integration using n = 101^2 = 10201 sample points.
