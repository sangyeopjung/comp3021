# Lab 12: Lambdas
This lab will cover the following topics:
* Lambdas
* Streams, Higher order functions
* Method references

## Background
Lambdas are one of the coolest features from Java 8. They allow us to write concise code in a functional style. If you've ever used a different programming language that supports a functional coding style, this may seem a bit familiar to you. 

A Java lambda expression is a function which can be created without belonging to any class. It can be passed around as if it were an object and executed on demand. Commonly, they are used to implement event listeners, or used with the Java Streams API.

### Functional interfaces
Lambdas can be used where any variable or parameter expects a functional object (i.e. an object implementing a functional interface). A **functional interface** is **an interface with only one abstract method**. It is good practice to annotate such interfaces with `@FunctionalInterface`

```java
@FunctionalInterface
interface Eatable {
    void eat();
}
```
### Scopes
Lamdba expressions work with the enclosing scope. This means you can't overwrite variables from current scope with variables inside the lambda. 
```java
double x = 0;
f(x -> x + x); //illegal, repeated variable name
```
If a lambda expression wants to access a local variable , the variable must be final or effectively final (i.e. the variable is never changed after declaration). 
```java
void foo(){
    double x = 0;
    f(y -> x = 3.4); //illegal, x is not effectively final anymore
}
```
```java
void foo(){
    double x = 0;
    f(y -> x == 3.4); //legal, x is effectively final 
}
```

It is fine to access instance variables, with or without `this`. 
```java
class MyClass{
    private double x = 0;
    void foo(){
        f(y -> x = 3.4); //modifying instance var is OK
    }
}
```
```java
class MyClass{
    private double x = 0;
    void foo(){
        f(x -> x + this.x); //OK
    }
}
```
### Higher order functions
Higher order functions are functions that accept other functions as arguments, or return functions as a result. Using them with the Streams API allows for concise and elegant data manipulation. For instance, we may want to filter a Stream of people based on their age. Using the `Predicate` interface, we can pass a lambda "function object" which returns true if the person's age is older than a threshold. 

```java
//without lambdas
static List<Person> findAdults(ArrayList<Person> everyone){
    ArrayList<Person> adults = new ArrayList<>();
    for(Person p : everyone){
        if(p.getAge() >= 18)
            adults.add(p);
    }   
    return adults;
}
```
```java
//with lambdas
static List<Person> findAdults(ArrayList<Person> everyone) {
    return everyone.stream().filter(p -> p.getAge() >= 18).collect(Collectors.toList());
}
```
Read more about the [included functional interfaces](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html) that come with Java.
#### Common functional interfaces
```java
interface Predicate<T> {
    boolean test(T t);
}
interface Function<T, R> {
    R apply(T t);
}
interface Consumer<T> {
    void accept(T t);
}
interface Supplier<T> {
    T get();
}
```

##### Predicate<T>: T -> boolean
Creates a function object to test if a condition holds. See above example for finding adults: the `filter` takes in a predicate.

Sidenote: it is possible to chain predicates:
```java
Predicate<Person> isAdult = p -> p.getAge() >= 18;
Predicate<Person> isRich = p -> p.getSalary() >= 1_000_000;
Predicate<Person> isRichAdult = isAdult.and(isRich);
```
See the documentation for other ways to chain predicates.
##### Function<T, R>: T -> R
Applies a function uniformly to all elements provided to it
```java
Function<Person, Double> proposedNewSalary = p -> p.getSalary() * 1.1;
for(Person p : everyone){
    Double result = proposedNewSalary.apply(p);
}
```
It is possible to compose functions (via `compose`), similar to how you can chain predicates.
##### Consumer<T>: T -> void
Consumes the value and does some action with it (i.e. has side effects)
```java
Consumer<Person> doSalaryBump = e -> e.setSalary(e.getSalary() * 1.1);
//Calling accept manually
for(Person p : everyone) {
    doSalaryBump.accept(p);
}
//OR using forEach
everyone.forEach(doSalaryBump);
```
##### Supplier<T>: () -> T
Simply supplies some result. Could potentially be a new or distinct result, but there's no strict requirement.

### Method references
Method references make our code clearer and easier to understand. In reality, a method reference is the shorthand syntax for a lambda expression that just executes one method. An exception: you can't do this with `main`.

There are 4 kinds of method references:
1. Reference to a static method
    * ContainingClass::staticMethodName
2. Reference to an instance method of a particular object
    * ContainingObject::instanceMethodName
3. Reference to an instance method of an arbitrary object of a particular type
    * ContainingType::methodName
4. Reference to a constructor
    * ClassName::new
    
For more details you may refer to the [Java document](https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html).
### Streams
Consider streams as pipelines of information on which we want to perform some operations. We may want to filter them, apply a function to them, count, see if any match a condition, etc. You should definitely [read more about them here](https://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/) before attempting this lab. Streams can also be done in parallel to improve efficiency. This is not covered in this lab, but you can [read more about it here](http://gee.cs.oswego.edu/dl/html/StreamParallelGuidance.html).

You may find the following useful:
* filter
* sorted
* map
* reduce
* limit
* allMatch
* distinct
* collect
* get

#### Primitive Streams
You can use primitive streams to manipulate primitive types.
For example, we can print the numbers from 0 to 9 as follows:
```
IntStream.range(0, 10).forEach(System.out::println);
```
Or, generate an infinite stream via iteration
```
LongStream infiniteStream = LongStream.iterate(1, el -> el + 1);
```
...and print out the first 100 even numbers
```
infiniteStream.filter(el -> el % 2 == 0).limit(100).forEach(System.out::println);
```

We can also convert arrays to streams using the static `stream` method.
```
String[] tags = {"java", "git", "lambdas", "machine-learning"};
Arrays.stream(tags).map(String::toUpperCase).forEach(System.out::println);
```


## What you need to do
Several lambda expression exercises have been prepared in this lab. Read the provided Javadoc comments and fill out the TODOs in TaskUtilities.java. Afterwards, run the test cases.

**Remember**: you have to use lambda expression to finish this lab. Otherwise, your mark will be deducted.

##Submission
* Implement the TODOs
* Make your implementation can pass all the test cases
* Zip your code and submit it to CASS
