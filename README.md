# ACL-2020-kbc

v7
In repertory Pacman

How to compile ACL-2020-kbc v7.0 ?
Using Maven 4.0.0 :
>> mvn install

How to run ACL-2020-kbc v7.0 ?
>> Retrieve JAR from target/ACL_2020_kbc-7.0-SNAPSHOT.jar
>> java -jar ACL_2020_kbc-7.0-SNAPSHOT.jar

Libraries required :
java.awt
javax.swing
java.util
java.io

Used Java version : 8

Notes :

- Maven uses the same version for compiling the sources ;
- some maps can be found inside the folder Pacman/resources/Map ;
- the JAR can be launched from everywhere ;
- you can even launch the game by double-clicking it (JAR).

Design :
All design documents can be found inside the folder conception

How to play ?

The goal is to get the chest in each level of the game.
The game ends when the player gets the chest in the final level, or dies.

There are several monsters in each level, which can cause damage to the character.
The character can attack the monsters.
Each attack sets a 1PV damage to the character or a monster.

The character has 10 PV.
A monster has 3 PV.

There are magical items that can provide powers to the character (increased speed, ability to walk through walls, increased attack range).
There are trap items that affect negative effects to the character (incapacity to move, decreased speed, character's movements could be confused).
Each effect lasts 5 seconds.

Commands :
- Move left : →
- Move right : ←
- Move up : ↑
- Move down : ↓

- Attack up : z
- Attack left : q
- Attack down : s
- Attack right : d
---

The following characters can be used in map files :
- Ground : +
- Wall : -
- End : e
- Magic : m
- Bow : b
- Ghost : g
- Speed : >
- Trap : t
- Slow : <
- Stop : x
- Stun : ~
- Passage : p
- Treasure : k
- Pacman : 1
- Monster : 2
