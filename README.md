# ACL-2020-kbc

v6
In repertory Pacman

How to compile ACL-2020-kbc v6.0 ?
Using Maven 4.0.0 :
>> mvn install

How to run ACL-2020-kbc v6.0 ?
>> Retrieve JAR from target/ACL_2020_kbc-6.0-SNAPSHOT.jar
>> java -jar ACL_2020_kbc-6.0-SNAPSHOT.jar

Note : the JAR can be launched from everywhere

Libraries required :
java.awt
javax.swing
java.util
java.io

Used Java version : 8

Note :

- Maven uses the same version for compiling the sources
- Some maps can be found inside the folder Pacman/resources/Map

Design :
All design documents can be found inside the folder conception

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
