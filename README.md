# Percolation

http://coursera.cs.princeton.edu/algs4/assignments/percolation.html

Programming Assignment 1 for the "Algorithms, Part I" course on Coursera.


[Problem specification](assignment/ProgrammingAssignment1_Specification.pdf) (Course starting date October 3rd, 2016).  
[Problem checklist](assignment/ProgrammingAssignment1_Checklist.pdf) (Course starting date October 3rd, 2016).

## The problem
Given a composite systems comprised of randomly distributed insulating and metallic materials: what fraction of the materials need to be metallic so that the composite system is an electrical conductor? Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able to drain through to the bottom (or the oil to gush through to the surface)?

## Challenges
I found particularly interesting trying to solve the problem of "backwash". By using just one union-find data structure that uses the trick seen in class that creates a virtual top and bottom site I can't seem to find a solution. The approach with two virtual sites seems to be the best one when it comes to understand if a system percolates or not. Without the two nodes we would need to loop for each site at the bottom sites and check the connection for each top site thus resulting in poor runtime performances.
The backwash problem can be solved with the aid of a mirror union-find data structure that differs from the main one because it misses the bottom virtual site. This prevents all the site at the bottom to be virtually connected with each other thus preventing backwash when the system does indeed percolates.

## For fun
As suggested [here](http://coursera.cs.princeton.edu/algs4/assignments/percolation.html) I created my own percolation file starting from a solved nonogram [[link](http://en.japonskie.ru/pics/full/master_yoda.gif)]. I have designed the bitmap image in Photoshop and created a class that converts the black and white BMP image to the TXT file format as accepted by the [PercolationVisualizer](http://coursera.cs.princeton.edu/algs4/testing/percolation/PercolationVisualizer.java) provided as a testing tool for this assignment. Files can be found in /Percolation/src/resources/. A PSD template is available as well to create other custom BMP images.

The raw BMP image:  
![BMP image](/Percolation/src/resources/yoda-80.bmp?raw=true)  

The output of the PercolationVisualizer:  
![PercolationVisualizer out image](/Percolation/src/resources/yoda-80.png?raw=true) 

Execution of the PercolationVisualizer on the custom BMP:  
![PercolationVisualizer animation](/Percolation/src/resources/yoda-80.gif?raw=true)

## Assessment Summary
Compilation:  PASSED  
Style:        PASSED  
Findbugs:     No potential bugs found.  
API:          PASSED

Correctness:  26/26 tests passed  
Memory:       5/8 tests passed  
Timing:       9/9 tests passed

Aggregate score: 96.25% [Correctness: 65%, Memory: 10%, Timing: 25%, Style: 0%]

------
(Note that for this project to work a reference to [algs4.jar](http://algs4.cs.princeton.edu/code/algs4.jar) has to be added.) 
