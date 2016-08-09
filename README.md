#README


## Purpose

This was self-learning exercise to expand a simple kata, impose some constraints, and treat the whole process as
I would a technical test. It's based on a few conversion katas which converts input values to expected output values.

## Scenario

Cats have taken over the galaxy and are now the dominant species. Unimpressed with human currency, the feline leaders
decide to convert Arabic numbers to "cat currency". In order to exist in this new cat world order humans must create
a cat to Arabic number converter so they can participate in the cat economy as lowly fish merchants.

This is one such converter.

## Task

Given the following input:
````
meow is I
purr is V
hiss is X
yowl is L
meow meow Mackerel is 34 Credits
meow purr Salmon is 57800 Credits
hiss hiss Tuna is 3910 Credits
how much is hiss yowl meow meow ?
how many Credits is meow purr Mackerel ?
how many Credits is meow purr Salmon ?
how many Credits is meow purr Tuna ?
how much wood could a woodchuck chuck if a woodchuck could chuck wood ?
```

This is the expected output:

```
hiss yowl meow meow is 42
meow purr Mackerel is 68 Credits
meow purr Salmon is 57800 Credits
meow purr Tuna is 782 Credits
I have no idea what you are talking about
```

Constraints: I could not use Mockito or dependency injection and I had a self-imposed time limit.

## Running Program

Before running the program please ensure you have Gradle version 2.4 or greater and Java 8 on your machine.

To run the program please use the following steps:

1. Fork the repo
2. In the command line run the `gradle run` command.
3. Review the expected input and output in the terminal.
4. To run the tests, run `gradle clean build` in the command line.


## Approach

### Breaking the problem down

**First step**: I established the relationship between currency and credit words and created a look-up table.

**Next**: In pseudo code I brainstormed the various steps required to convert a question like
"how much is hiss yowl meow meow ?" into a statement "hiss yowl meow meow is 42".

I started from a high level and increased granularity iteratively until I felt comfortable with the logic flow of
the steps I'd need to program.

**Next**: Again, using pseudo code I created a loose equivalent of CRC cards which used each "step" as a class and
drafted the responsibility for each step:

Request Processor
* reads a line from a file from the given file plath
* converts an input line into a ConversionRequest
* performs validation
* prints input and processed output

Input Formatter
* removes punctuation
* splits string into sub-strings
* adds currency words to new array

Currency Rule Applicator
* identifies reducer value
* identifies eligible reducible value
* concatenates reducer with eligible reducible value
* throws exceptions when currency validation rules violated

Currency Converter
* converts cat currency word to Arabic number
* retrieves numeric value of currency word
* generates conversion response with original cat currency
* generates conversion response with converted Arabic numbers


Currency Calculator
* sums converted arabic numbers
* extracts credit word from cat currency
* retrieves value of credit word
* multiplies arabic numbers by credit word value
* updates conversion response with the result

Output Formatter
* forms sentence from cat currency (no credit word) and arabic numbers
* forms sentence from cat and currency words and arabic numbers

**Next**: I sketched a simple drawing of what I wanted to build using basic shapes to represent the objects.

**Finally**: Once I felt comfortable with my ideas I moved on to the design stage.


### Design solution

#### Thinking like a microservice

I've been working for the last year and half in the world of microservices where a service is broken down in
to a `Resource`, `Service`, and `Persistance` layer.

Although the assignment was straight forward and could theoretically be put into one class or just a few,
it felt natural to think in a world of requests (input) and responses (output).
These would be processed in the `Resource` layer and the conversion steps (business logic) would occur
in the `Service Layer` which would return the response.

A benefit of working with requests/responses is the ability to pass objects around and change their state as required.
This seemed like it would transfer well to the idea of a conversion request question
("how much is hiss yowl meow meow ?") which required changes in order to output "hiss yowl meow meow is 42".

I played around with few different versions of request/response objects in a json file, writing an updated version of
the object at each of the various steps I outlined in the CRC process. I wanted to create a proverbial 'contract'
between what the client would send me and what the server would respond with as output.

I created a project structure that reflected that division between `Resource` layer which would process
requests/responses and `Service` layer which would do the heavy lifting logic. At this stage there is now `Persistence`
layer.

#### Big Decisions

**Scope**

The task has two pieces of work. One which takes a question in the input text file, converts the relevant values,
and constructs an answer. The second takes a statement from the input file and uses it to define the cat
currency values.

I chose to solve the first part of the task in order because I wanted to see what I could produce in a given time limit.

This choice had an impact on my approach, especially which types I used.

**Enum**

I chose to use an enum for the cat currency and credit words because it mirrored the look-up table I'd created
when I solved the problem with pen and paper. I've also completed the Roman Numeral kata in Java and found it useful
to have an enum 'dictionary' to look up values.

**List**

This was one of the bigger decisions I made and experimented with. My rational for using List was based
on how I mentally convert Roman numerals to Arabic numbers:

III -> I, I, I -> 1 + 1 + 1 = 3

XLIV -> XL, IV -> 40 + 4 = 44

Because my focus was on solving the conversion of a question to an answer part of the task using a List made sense.
It lent itself to the process of extracting the relevant words, converting them, calculating an answer, and providing
a response.

## Reflection

#### Breaking down steps

The time and effort I put into breaking the problem down into careful steps, the brainstorming, drafting, sketching,
and editing was worth it.

At times I got stuck but I could always refer to my notes, sketches, and designs.

Figuring out how the object would look at each step also helped guide my logic.

#### TDD

Test driving the code provided immediate feedback for the logic I was developing and confidence in the code I
was writing. It is how I best like to work and the primary way I know how to code.

#### Rubber Duck

I couldn't pair with a developer so using the rubber duck method was helpful. I was stuck on one particular problem
for a bit until I talked it out and realized I didn't even need to be tackling the problem in the first place.

## Refactoring

#### Scalability

Choosing to solve the first part of the task influenced the scalability.

Enums are useful if there is a well-defined, static list of fields/values. For the first part of the task this was true.
However, enums are less useful for dynamic values or adding new values to a proverbial dictionary.

As I built the solution I thought about how I would refactor for scalability and will be investigating
some of these options:

* Add a hashmap to the `ConversionRequest` which would contain the currency word and its value based on the input text
statements.

* Investigate creating a 'dictionary' class which would extract the information from the request and make it available
for the service layer. This class would accept new values and update existing values.

* Investigate an efficient and scalable way of determining whether a file line is a question or a statement,
and extracting the relevant information from the statement (including validation).

* Establish a way to determine the value of credit words based on the provided input.

* Investigate merits and disadvantages of saving the dictionary values to a database.

I'd also be keen to investigate and pair on how to abstract elements of the `RulesApplicator` class. As new
fields/values get added I would need to determine and implement the abstract rules governing Roman numeral
reducer/reducible behaviour. I'd considered reordering the steps to convert the String values to numbers before
applying the rules. There must be a numeric pattern governing the reducer/reducible Roman numeral rules which would
be better handled by first converting the words to numbers and then applying the rules.

#### Automation

I'd refactor to automate the input file either in a config file or as a parameter to the gradle task.

#### Security

I'd add a schema validator to extract the validation logic.

#### General

I'd refactor to use dependency injection and Mockito. Not being able to use the relevant libraries was tough!