# numerical-methods-montecarlointegration-exercise

In this exercise, you should implement classes that provide a reasonably flexible
framework for Monte-Carlo integration in arbitrary dimensions.

The framework should be flexible enough to allow

- integration of different functions f : <b>R</b><sup>n</sup> &rarr; <b>R</b>

- flexible specification of the integration domains using a transformation from [0,1]<sup>n</sup> to a subset of <b>R</b><sup>n</sup>

- flexible use for different random number generators

For convenience, we provide the interfaces that define the framework. You can find these interfaces in the package

```
info.quantlab.numericalmethods.lecture.montecarlo.integration
```

of the project `numerical-methods-lecture`, see [github.com/qntlb/numerical-methods-lecture](https://github.com/qntlb/numerical-methods-lecture). This project is already defined as a Maven dependency to this project. This project is pre-configured and "knows" these interfaces.

## Interfaces (provided)

- `Integrand`
- `IntegrationDomain`
- `Integrator`
- `MonteCarloIntegratorFactory`

See [the package info.quantlab.numericalmethods.lecture.montecarlo.integration](https://github.com/qntlb/numerical-methods-lecture/tree/master/src/main/java/info/quantlab/numericalmethods/lecture/montecarlo/integration)

The `MonteCarloIntegratorFactory`'s method requires a class implementing a `RandomNumberGenerator`. This interface and some classes implementing this interface can be found in the package

```
info.quantlab.numericalmethods.lecture.randomnumbers
```

### Integrand and IntegrationDomain

- Objects implementing `Integrand` provide a function f : A &rarr; <b>R</b> defined on a domain A &sub; <b>R</b><sup>n</sup>.
- Objects implementing `IntegrationDomain` provide a bijective function g : [0,1]<sup>n</sup> &rarr; A that transforms the integration domain and the determinant of the derivative (Jacobi matrix) dg/dx.
- Objects implementing `Integrator` provide the integral &int;<sub>A</sub> f(z) dz using substitution z = g(x). 

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

The integration domain will be provided to the `Integrator`'s method `integrate` as an object implementing the interface `IntegrationDomain`.

- 2) Implement a class implementing the interface `MonteCarloIntegratorFactory` that allows creating an object of the class that you have implemented in 1). Note: the `MonteCarloIntegratorFactory` simply calls the constructor of your class.

- 3) To allow us to test your implementation, complete the implementation of the method `getMonteCarloIntegratorFactory` of `MonteCarloIntegrationAssignmentSolution`. This allows the creation of an object of your `MonteCarloIntegratorFactory`. Our unit tests will use this to test your code.

- 4) Feel free to create your own UnitTests and JavaDoc documentation.

Suggestion: You may test your `Integrator` implementation with different random number generators, e.g. `MersenneTwister` via

```
	final long seed = 3141;
	RandomNumberGenerator randomNumberGenerator = new RandomNumberGeneratorFrom1D(new MersenneTwister(seed), domain.getDimension());
```

or a `HaltonSequence`.

Note: As known from the lecture, using a 1-dimensional quasi-random number sequence in a d-dimensional integration will lead to wrong results. Hence, it makes usage of your integrator safer, if you explicitly require that the dimension of the domain matches the dimension of the random number sequence (an throw `IllegalArgumentException` if not), such that the user has to explicitly use `RandomNumberGeneratorFrom1D` when appropriate. You could however allow that an n-dimension sequence is used for a d-dimensional domain if n>d.

### Task 2: Using your MonteCarloIntegrator to calculate the integral of a DoubleBinaryFunction

- 5) Complete the method `getIntegral` of `MonteCarloIntegrationAssignmentSolution`. Use your Monte-Carlo integrator with approximately 1 million sample points to calculate the integral.

### Tasks 3: Implement a SimpsonsIntegrator for the general Simpson's rule in d dimension

To complete your task:

- 6) Implement a class implementing the interface `Integrator` that performs a general (composite) Simpson's rule integration
in d dimension of arbitrary functions on general domains.

The function to integrate will be provided to the integrator's method `integrate` as an object implementing the interface `Integrand`.

The integration domain will be provided to the integrator's method `integrate` as an object implementing the interface `IntegrationDomain`.

- 7) Implement a class implementing the interface `IntegratorFactory` that allows creating an object of the class that you have implemented in 6). Note: the `IntegratorFactory` simply calls the constructor of your class.

- 8) To allow us to test you implementation, complete the implementation of the method `getSimpsonsIntegratorFactory` of `MonteCarloIntegrationAssignment`. This allows the creation of an object of your `IntegratorFactory`. Our unit tests will use this to test your code.

- 9) Feel free to create your own UnitTests and JavaDoc documentation.

#### Hints

- Note that your Simpson's integral and your Monte-Carlo integral only operator on [0,1]^d (the object implementing the Domain will provide you with the transformation).

- Your Simpson's integrator should accept the `numberOfValuationPoints` as an argument. This should be the *minimum total number of valuation points*. Since the Simpson's rule uses an odd number of points in every dimension, you may use the following code to round this number appropriately to `numberOfSamplePointsEffective`, using `numberOfSamplePointsPerDimension` per dimension.

```
	int dimension = integrationDomain.getDimension();
	int numberOfValuationPointsPerDimension = 2 * (int) (Math.ceil(Math.pow(numberOfValuationPoints, 1.0/dimension))/2) + 1;
	int numberOfValuationPointsEffective = (int) Math.pow(numberOfValuationPointsPerDimension, dimension);
```

- You might realise that you need to think a bit to find a short algorithm to implement the Simpson's integration in arbitrary dimensions. It is possible to create a fairly short implementation if you implement a multi-index `index` - an array of length `dimension` where each entry runs from `0` to `numberOfSamplePointsPerDimension-1`.

### Task 4 (optional): Use your MonteCarloIntegrator with an RandomNumberGenerator generating a low-discrepancy in d dimensions.

Since the random number generator is an input to your implementation of the MonteCarloIntegrator interface, it should be possible to feed in low discrepancy sequences (given that they implement the `RandomNumberGeneratorRandomNumberGenerator` interface).

Conduct a an experiment using low discrepancy sequence to perform the Monte-Carlo integration and compare the integration error to that of using a pseudo random number sequence.

## Unit Tests

We encourage you to write your own unit tests.

## Further Research

This project offers the opportunity to explore Monte-Carlo integration in more detail for those interested.
Here are a few suggestions:

- *Explore the dependency on the dimension:* Consider the integration of x &rarr; product(i=0,...,d-1) sin(x<sub>i</sub>) for 0 < x<sub>i</sub> < &pi;. The value of the integral is 2^d. This is an d-dimensional integral. For this function, compare the accuracy of Monte-Carlo integration and Simpson's integration with d = 1, 2, 4, 8 using for example n = 5^8 = 390625 sample points.

- *Explore the dependency on the smoothness of the function:* Consider the integration of (x<sub>0</sub>,x<sub>1</sub>) &rarr; x<sub>0</sub><sup>2</sup> + x<sub>1</sub><sup>2</sup> &lt; 1.0 ? 1.0 : 0.0 for 0 < x<sub>i</sub> < 1. The analytic value of this integral &pi;. For this function, compare the accuracy of Monte-Carlo integration and Simpson's integration using n = 101^2 = 10201 sample points.