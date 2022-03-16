- There are sometimes cases where we want to provide different implementations for a method of a class.
  - or, we want to leave some room for further improvement because we never know all the possibilities existing as of coding
- This is another use case of decorator design pattern
- Stackable traits design pattern is based on mixin composition
  - usually, we have an abstract class/trait, a base implementation, and traits extending the abstract class to stack modifications on it.

Example: 
- A base: StringWriter
- Basic implementation: BasicStringWriter, which just returns a message containing the string
- Traits that can add stackable modifications to StringWriter