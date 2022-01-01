## What is it good for?
1. As with other factories, the details of object creation are hidden. 
   ---> if we change the way a specific instance has to be created, we would have to change only the factory methods that create it (this might involve a lot of creators though, depending on the design). 
2. The factory method allows us to use the abstract version of a class and defer the object creation to subclasses.


## What is it not so good for?
1. If we have more than one factory method --> require the programs to write many more methods