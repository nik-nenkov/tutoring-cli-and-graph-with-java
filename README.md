# excel-documentation

This project requires implementations of the following data structures, design patterns, tools and technologies:

<a href="https://www.geeksforgeeks.org/expression-tree/">
Expression Tree
</a><br/>
<br/>
<a href="https://www.electricmonk.nl/docs/dependency_resolving_algorithm/dependency_resolving_algorithm.html">
Dependency Resolving Algorithm
</a><br/>
<br/>
<a href="https://www.geeksforgeeks.org/graph-data-structure-and-algorithms/">
Graph Data Structure And Algorithms
</a><br/>
<br/>
<a href="http://faculty.cs.niu.edu/~hutchins/csci241/eval.htm">
Using A Stack To Evaluate An Expression
</a><br/>
<br/>
<a href="https://stackoverflow.com/questions/546655/finding-all-cycles-in-a-directed-graph/549402#549402">
Finding All Cycles In A Directed Graph
</a><br/>
<br/>
<a href="https://stackoverflow.com/questions/3422673/evaluating-a-math-expression-given-in-string-form">
Evaluating A Math Expression
</a><br/>
<br/>
<a href="https://www.geeksforgeeks.org/composite-design-pattern/">
Composite Design Pattern
</a><br/>
<br/>
<a href="https://www.journaldev.com/1739/observer-design-pattern-in-java">
Observer Design Pattern
</a> (<a href="https://dzone.com/articles/observer-pattern-java">more</a>)
</a> (<a href="https://en.wikipedia.org/wiki/Observer_pattern">wikipedia</a>)<br/>
<br/>
<a href="http://www.google.com">
...search Google for other topics...
</a><br/>

<br/>

1. Create Table class which can loadFrom and saveTo a file. <br/>
TableCell stores Date, String, Integer, Expression, Double...
<br/> Table takes String as input and calculates values as needed.
<br/> Table class can be visualized on screen as per requirements.
<br/> Each TableCell can be 'initialized' or have an 'error'
<br/><br/>
2. Create Expression class that stores mathematical expressions.
<br/> Make those an ExpressionTree and add calculateExpression(),
<br/> Make other cells available as nodes of the ExpressionTree.
<br/> Parse ExpressionTrees from Strings and vice versa,
<br/> Each Node can be:<br/> Leaf(Numerical or Cell), 
<br/> Inner(Operation or Expression)
<br/> Root stores value (answer) answer of that Expression
<br/><br/>
3. Recalculate values only when needed, using a DependencyGraph
<br/> Prevent circular dependencies from happening;
<br/> If a value comes to be the same as previous value - do not notify!