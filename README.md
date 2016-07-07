# dungeon-crawler

This is a text-based dungeon crawler game programmed in Java. 
"Runner" contains the bulk of the code and, well, runs the game.
"Dungeon" generates maps for each level of the dungeon, and also contains various miscellanous methods. 
"Fight" and "Shop" are classes that are called when a player enters each one respectively. 
The rest of the classes are instances of each object that are stored within the generated map. 

Known bugs:
* Sometimes map generation will get stuck in a loop in between floors. 
* Rarely, the player will not be able to navigate to the floor's door due to a glitch in map generation. 
