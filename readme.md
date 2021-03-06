# Cohort 1 of The World’s Best Intro to TDD: Level 1, Online Community Edition.

## Releases

### 1.0.0 (on 04.09.2021)

For now, the only way to use this library is by adding the jar file to your project's classpath.
You can download the jar file [here](releases/Fractions-1.0.0.jar).

## Thoughts

* use long instead of int?!
* should numerator now also be a proper type and have a divideBy function like Denominator?

## Inbox

### features
* compatible with whole numbers
  * 4 + 9
* Always express fractions in lowest terms. 
  * 4/6 magically becomes 2/3
* always improper and not mixed. 
  * 7/5, not 1 2/5.
* support add
* support subtract
* support multiply
* support divide

#### test list

~~0 + 0 = 0~~

~~1 + 0 = 1~~

~~1 + 1 = 2~~

~~1/1 = 1~~

~~1/1 + 0 = 1/1~~

~~1/1 + 1 = 1/1~~

~~1/1 + 1/1 = 2/1~~

~~1/0 = InvalidArgumentException~~

~~4/2 = 2/1~~

~~2/4 = 1/2~~

~~1/4 + 1/4 = 1/2~~

~~2/3 + 1/3 = 3/3 = 1/1~~

~~1/4 + 1/2 = 1/4 + 2/4 = 3/4~~

~~4/3 + 1/2 = 8/6 + 3/6 = 11/6~~

~~1/-1 = -1~~

~~-1/1 = -1~~

~~-1/-1 = 1~~
