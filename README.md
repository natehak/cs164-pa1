# PA1
Writing the parser was surprisingly straightforward especially because I planned ahead and converted the grammar to EBNF before I touched any code.

Major issues started with the NFASimulator which I thought would be a straightforward DFS, but there were some subtleties I did not expect like false positive recognition on inputs longer than the regex. Also speeding it up was challenging.

Eventually I just realized that DFS was a bad idea because of backtracking and switched to the algorithm in class.
