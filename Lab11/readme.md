# Lab 11: Generics
This lab will cover the following topics:
* Generic types, methods
* Bounds, wildcards

## Generics
Generics were added in JDK5 to allow for an extra layer of abstraction over types, allowing us to parameterize a class, an interface or a method.
You may have already used them, e.g. in ArrayList, where you can store elements of a generic type. For example, `ArrayList<String>` is an arraylist for holding strings.  

Generics work for:
* Classes
* Interfaces
* Methods

For example, the below is a generic class:
```
public class GenericStack<E> { ... }
```

A common generic interface you may have already encountered:
```
public interface Comparable<T> { ... }
```

### How it works: type erasure
After compiliation, there is no trace of generics in the Java bytecode. The compiler replaces all the generics related code with the pre JDK5-style casting from the `Object` class manually. This allows for backwards compatibility. This also prevents any runtime overhead. Read more about it [here](https://www.baeldung.com/java-type-erasure).

### Benefits

Generics was added in Java 5 to (1) provide compile-time type checking and (2) remove risk of `ClassCastException` that was common while working with collection classes. The whole collection framework was re-written to use generics for type-safety. Let’s see how generics help us using collection classes safely.

Before Generics are introduced:
```
List list = new ArrayList();
list.add("abc");
list.add(new Integer(5)); //OK

for(Object obj : list){
	//type casting leading to ClassCastException at runtime
    String str=(String) obj; 
}
```
Above code compiles fine but throws `ClassCastException` at runtime because we are trying to cast Object in the list to String whereas one of the element is of type Integer.

Now with generics, we can use ArrayList as below:
```
List<String> list1 = new ArrayList<>(); 
list1.add("abc");
//list1.add(new Integer(5)); //compiler error

for(String str : list1){
     //no type casting needed, avoids ClassCastException
}
```
This avoids runtime errors by detecting possible errors at compilation time and object casting is no more needed.

It looks simple for trivial examples, but for more complex pieces of code, it's easy to lose track of the type quickly and introduce hard to find bugs. Our diamond operator `<>` containing the type allows us to narrow the ArrayList to only the String type.

### Generic Types
A generic class is shared by all its instances regardless of its actual type.
In the below example, the JVM only loaded the `GenericStack` class once.
```
Stack<String> s1 = new Stack<>();
Stack<Integer> s2 = new Stack<>(); 

s1 instanceof Stack; //true
s2 instanceof Stack; //true
```

The type parameter follows the class name in the class declaration. Afterwards, within the class declaration, you can access the type directly via its name. For example,

```
class Test<E> {
    public <E> void foo(E obj) { ... }
}
```

#### Inheritance of generic types
* Suppose ClassA is a subtype of ClassB
* Stack\<ClassA\> is NOT a subtype of Stack\<ClassB\>  

We will resolve the problem of polymorphism with generic types later, using wildcards.

### Generic methods
Generic methods are written with a single method declaration, but can be called with arguments of different types. Some facts about generic methods:
* The type parameter should be placed before the return type
* Type parameters can be bounded
* Generic methods can have more than 1 type parameter, separated by commas in method signature
* Method body is just like a normal method body
```
public static <E> void print(E[] list){
    for (int i = 0; i < list.length; i++)
        System.out.println(list[i] + " ");
}
```

#### Bounded generics
We can restrict the types that can be accepted by a method. For example, we can specify that we accept the type and all its subclasses (using the `extends` keyword). In the below example, we accept any class that is a Number.
```
public <T extends Number> List<T> fromArrayToList(T[] a) { ... }
```

#### Bounded and unbounded wildcards
Wildcards are represented using the `?` character, referring to an unknown type. Recall the facts about inheritance regarding generic types:
* Suppose `Building` is the parent of `House`
* `List<Building>` is NOT the parent of `List<House>`! 

Therefore, suppose we had a function for painting buildings like so:
```
public static void paintAllBuildings(List<Building> buildings) {
    buildings.forEach(Building::paint); //using method reference
}
``` 
Passing in an ArrayList of Houses would not work!

To solve this problem, we need to use wildcards:
```
public static void paintAllBuildings(List<? extends Building> buildings) {
    ...
}
```
This allows us to pass in a List of Buildings, Houses, or any other child class of Building. This is also called an upper bounded wildcard. If we wanted to set a lower bound instead, we could write ` <? super Building> `. If we want our generic method to work for all types, we could simply write ` < ? > `. 

When in doubt, use the `alt enter` keyboard command in Intellij. It will usually autocomplete the correct choice for you. Note that you cannot use wildcards within [at the class level](https://stackoverflow.com/questions/32421639/can-we-use-wildcards-at-class-level-in-java).

#### Wildcards and generic inheritance
What we demonstrated above can be summarized as saying that wildcards allow us to use generic classes as polymorphic types. More examples for your reference:  
* `ArrayList<Object>` IS a subclass of `ArrayList<?>`
* `ArrayList<Integer>` IS a subclass of `ArrayList<? extends Number>`
* `ArrayList<Number>` IS a subclass of `ArrayList<? extends Number>`
* `ArrayList<?>` IS a subclass of `ArrayList<? extends Object>`
* `List<? extends Integer>` IS a subtype of `List<? extends Number>`


### Restrictions on generics
* Cannot instantiate a generic type (e.g. `new E()`), since we don't know how much memory to allocate
* Cannot create array of generic type (e.g. `new E[100]`) for the same reason
* Exception classes cannot be generic
* Generic type paramater of a class is not allowed in static context
```
public class Test<E>{
    public static void foo(E o1) {} //illegal
    public static E obj; //illegal

```


## Lab Outcome & Submission 
You will implement a [generic min heap](https://en.wikipedia.org/wiki/Heap_(data_structure)) for comparable types.
* Read the Javadoc comments and fill out the TODOs.
* Make sure your implementation can pass the provided test cases
* Zip your project and submit it to CASS before deadline
