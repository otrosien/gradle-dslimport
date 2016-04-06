# Testing the Gradle Software Model

This is a sample project using the new Software model by gradle defining and using a DSL for customers and addreses.
It does not have an actual import faciility, like a REST client (yet), but can be used as a proof-of-concept that
you can do feature-rich domain modeling in gradle.

### Introspect the configured model

```
./gradlew model
```

Gives you complete insight into what comes out after the configuration phase of your project.
Here are some interesting parts of the output: 

```
...

+ config
      | Type:   	Config
      | Creator: 	ConfigRules#config
      | Rules:
         ⤷ config { ... } @ build.gradle line 32, column 5
    + randomCustomers
          | Type:   	int
          | Value:  	0
          | Creator: 	ConfigRules#config
    + randomizeIds
          | Type:   	boolean
          | Value:  	false
          | Creator: 	ConfigRules#config
+ people
      | Type:   	org.gradle.model.ModelMap<Person>
      | Creator: 	PeopleRules#people
      | Rules:
         ⤷ people { ... } @ build.gradle line 36, column 5
    + Barry
          | Type:   	Person
          | Creator: 	Barry(Person) { ... } @ build.gradle line 63, column 9
          | Rules:
             ⤷ PeopleRules#applyDefaultRules
             ⤷ PeopleRules#applyValidateRules
             ⤷ DefaultRules#setDefaults
             ⤷ all { ... } @ build.gradle line 58, column 9
             ⤷ ValidateRules#validateLastNameIsNotNull
        + address
              | Type:   	Address
              | Creator: 	Barry(Person) { ... } @ build.gradle line 63, column 9
            + city
                  | Type:   	java.lang.String
                  | Value:  	null
                  | Creator: 	Barry(Person) { ... } @ build.gradle line 63, column 9
            + street
                  | Type:   	java.lang.String
                  | Value:  	null
                  | Creator: 	Barry(Person) { ... } @ build.gradle line 63, column 9
        + firstName
              | Type:   	java.lang.String
              | Value:  	Barry
              | Creator: 	Barry(Person) { ... } @ build.gradle line 63, column 9
        + id
              | Type:   	java.lang.String
              | Value:  	00000000-03cf-e17a-0000-000000000000
              | Creator: 	Barry(Person) { ... } @ build.gradle line 63, column 9
        + lastName
              | Type:   	java.lang.String
              | Value:  	White
              | Creator: 	Barry(Person) { ... } @ build.gradle line 63, column 9
        + name
              | Type:   	java.lang.String
              | Value:  	Barry
              | Creator: 	Barry(Person) { ... } @ build.gradle line 63, column 9

...
```


### Greet all your customers

This is a placeholder task for stuff you can do with your domain entities.


```
./gradlew hello
```

All hello tasks attach themselves as dependency onto the main hello task.

One thing interesting for data import is the usage of stable identifiers. Our customer model is a named entity, so each 
customer can be identified by its name. We can make use of this and construct a stable uuid from it.


```
..
:helloBarry

                        Hello Barry White! 
                            You're Customer 00000000-03cf-e17a-0000-000000000000
:helloJack

                        Hello Jack Smith! 
                            You're Customer 00000000-0023-1bff-0000-000000000000
:helloJohn

                        Hello John Doe! 
                            You're Customer 00000000-0023-512b-0000-000000000000
:hello
..
```

### Greeting a single customer

Of course you can execute tasks on single customers, as e.g. the helloJohn task.


```
./gradlew helloJohn
```

```
...
:helloJohn

                        Hello John Doe! 
                            You're Customer 00000000-0023-512b-0000-000000000000
...
```

### Randomize your customer IDs

If you want to shuffle the IDs e.g. to import them as new objects.


```
./gradlew helloJohn -PrandomizeIds
```

```
:helloJohn

                        Hello John Doe! 
                            You're Customer 606f8351-151d-477a-817b-1dfd2c767f80
```


### Add more random customers

```
./gradlew hello -PrandomCustomers=10
```

Output:


```
...
...
:helloRandom6

                        Hello Judy Burks! 
                            You're Customer 4244041c-1400-4e1b-9df8-b091429a3bb0
:helloRandom7

                        Hello Bruce Jenkins! 
                            You're Customer bae8e416-e2d9-4d7a-bd14-8679796fc8af
:helloRandom8

                        Hello Sam Jackson! 
                            You're Customer f2b54d80-ed72-4f4d-bc14-8d60dd71895b
...
```

### List your customers addresses

This is an ad-hoc task defined within the data-set. This is as simple as can be in gradle.

```
./gradlew listAddresses -PrandomCustomers=10
```

Output:

```
...
Hey Myron. Your address is 1290 Ildewood Street, Phillipsburg!
Hey Vernon. Your address is 914 Norfield Trail, Chula!
Hey Erin. Your address is 693 Stormont Trail, Sylvania!
...

```

## Links

* Gradle userguide: [The Software model - Next generation Gradle builds](https://docs.gradle.org/current/userguide/userguide_single.html#N1777A)

