# Graph Generator

This program generates files containing graph information that can be later parsed by graph traversal algorithms. Generated graphs can be either weighted or unweighted.

The format of such a file is:
```yaml
n                                  # number of vertices
node_left_1 node_right_1 weight_1  # first edge
node_left_2 node_right_2 weight_2  # second edge
...
node_left_n node_right_n weight_n  # n-th edge
```

> __Note__:  For unweighted graphs, the weight of the edges will not be specified at all, meaning that every row (except for the first one) will contain two space-delimited integers.



## Usage
The `.jar` file of the generator can be run with the following arguments:
```yaml
-n <integer> # number of nodes
-w <boolean> # is the graph weighted or not?
-f <string> # path to the output file
```

For example, the command below will create a weighted graph with 5 nodes, and store the output in `output.txt`:
```
java -jar generator-1.0-SNAPSHOT.jar -n 5 -w true -f output.txt
```

## Future enhancements

- [ ] Generate disconnected graphs
- [ ] Generate graphs with specified weight range
- [ ] Generate graphs with given maximum number of edges
