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

## Task

To complete your task:

- 1) Implement a class implementing the interface `Integrator` that performs a general Monte-Carlo integrations of
arbitrary functions on general domains.

The function to integrate will be provided to the integrator's method `integrate` as objects implementing the interface `Integrand`.

The integration domain will be provided to the integrator's method `integrate` as objects implementing the interface `IntegrationDomain`.

- 2) Implement a class implementing the interface `MonteCarloIntegratorFactory` that allows to create an object of the class that you have implemented. Note: the `MonteCarloIntegratorFactory` simply calls the constructor of your class.

- 3) To allow us to test you implementation, complete the implementation of `AssignmentSolutionMonteCarloIntegration`. This allows to create an object of your `MonteCarloIntegratorFactory`. Our unit tests will use this to test your code..

- 4) Please add your own UnitTest and JavaDoc documentation.

### Integrand and IntegrationDomain

- Objects implementing `Integrand` provide a function f : A \rightarrow R definied on a domain A.
- Objects implementing `IntegrationDomain` provide a bijective function g : [0,1]^{n} \rightarrow A that transforms the integration domain and the determinant of the derivative (Jacobi matrix) dg/dx.
- Objects implementing `Integrator` provide the integral \int_A f(z) dz using substitution z = f(x). 

## Unit Tests


