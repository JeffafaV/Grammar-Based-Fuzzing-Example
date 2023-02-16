# Grammar-Based-Fuzzing-Example
This is an example program that shows how grammar-based fuzzing can be used to test the correctness
of a program. The Parse.java program is the program before fuzzing and the ParseFixed.java is the 
program after fuzzing.

Consider the following simple grammar (S is the start symbol):

S ::= A | B | C <br/>
A ::= "A" int <br/>
B ::= "B" int bool <br/>
C ::= "C" bool <br/>

Here, int matches a string representing a valid integer (e.g., "47") and bool matches only "true" or "false".

parse() should return true for inputs matching the grammar and false otherwise. E.g., it should return 
true for input ["B", "47", "true"] and false for input ["A", "false"]. Assume isBool() and isInt() 
correctly check for bool and int strings.

There are (at least) two bugs in the parse() function, where it returns true for a String[] input not 
in the grammar. These bugs can be discovered via test generation after grammar mutation. Mutations 
can include changing some (non-)terminal of a grammar rule (e.g., changing the second rule to 
A ::= "A" "B") or removing some (non-)terminal in the rule (e.g., changing the second rule to A ::= int). 
Once the grammar is mutated, String[] inputs can be generated from the mutated rule to test the code.

Give two mutated grammar rules, such that when each mutated rule is used for test generation, it would 
expose a bug where parse() returns true for an invalid input.  Explain your answers. Each of your rules 
must expose a distinct bug; you cannot write two mutated rules for the same bug.

First Mutated Grammar:

S ::= A | B | C <br/>
A ::= "A" int <br/>
B ::= "B" bool <br/>
C ::= "C" bool <br/>

Mutating the grammar of B to (B::="B" bool) we will find that we will return true even though the input 
is invalid. This is due to a missing else that should make valid equal to false after the first if statement 
for the B section. So it is possible to enter a non-int after the "B" and the program will return true which 
is a bug.

Second Mutated Grammar:

S ::= A | B | C <br/>
A ::= "A" int <br/>
B ::= "B" bool <br/>
C ::= int bool <br/>

Mutating the grammar of C to (C::=int bool) we will find that the program will return true even though 
the input is invalid. This is due to the branch that is used to get to C is only an else when it should 
be an else if. It should be an else if (val.equals("C")) because if not then any input that isn't A or 
B will reach that else and will return true which is a bug.

Third Mutated Grammar (causes an error):

S ::= A | B | C <br/>
A ::= "A" int <br/>
B ::= "B" bool <br/>
C ::= "C" <br/>

Mutating the grammar of C to (C::="C") we will find that the program will actually terminate with an error.
This is due to the program trying to get the second element of the array even though the array only has one 
element. Including the condition ind < arr.length in the if statement of the C branch would fix this error.