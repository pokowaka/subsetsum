Subsetsum
=========

This sample contains implementations of the algorithms presented in [Computing Partitions with Applications to the Knapsack Problem](http://dl.acm.org/citation.cfm?id=321823)
(Available for download [here](https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&cad=rja&uact=8&ved=0CB4QFjAA&url=http%3A%2F%2Fwww.cise.ufl.edu%2F~sahni%2Fpapers%2FcomputingPartitions.pdf&ei=ua80VMSWKsqdjALAloGwAg&usg=AFQjCNGpcMZlQkRj_I3XvSgGN_yfyBWMbQ&sig2=OURhcKnZYU4yPqUFXr0VlA))

Details on how the algorithms function can be found in the paper. Basically all of them present different methods for enumerating all posible combinations. Feel free to add your own algorithm by implementing the ```SubsetSumStrategy``` and adding your algorithm to the unit test, you can see how it fares compared to the others.

You can run the the unit test with ```gradle test``` which will:

* Run a series of negative tests, no sum exists in the set.
* Run a series of positive tests, a sum exists in the set.
* Solve a specific instance of a puzzle. 

It will output the running time in ms. of each algorithm. These algorithms can be very slow, particularly the naive approach, so be patient.

You can also run ```gradle eclipse``` and run the unit tests from within eclipse. Note that some of the algorithms require a lot of memory so your mileage may vary.

Notes & Todo's
-----
* It doesn't include any pseudo polyonomial or approximization strategies.
* Some algorithms could be improved by making use of heuristics, this is something still open for exploration.
* It appears that the MultiSubsetSumStrategy2b performs the fastest on average. 
This seems to be the way to go if you have the memory available.

Some results:
-----

Here are the worst case running times (i.e. no sum exists) for the
various algorithms:

| Algorithm                 | Set size | Time in ms |
|---------------------------|:--------:|-----------:|
| NaiveSubsetSumStrategy    |  28      | 18686      |
| NaiveSubsetSumStrategy    |  29      | 37831      |
| MultiSubsetSumStrategy2a  |  28      | 717        |
| MultiSubsetSumStrategy2a  |  29      | 1415       |
| MultiSubsetSumStrategy2b  |  28      | 94         |
| MultiSubsetSumStrategy2b  |  29      | 189        |
| RecursiveSubsetSumStrategy|  28      | 990        |
| RecursiveSubsetSumStrategy|  29      | 1576       |

It looks like ```MultiSubsetSumStrategy2b``` is the winner here. The
only drawback about this one is that it uses O(2^n) space.. 
