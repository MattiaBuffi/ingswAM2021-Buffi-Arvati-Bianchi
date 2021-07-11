# Masters of Renaissance


Final Test of "**Software Engineering**".

**Professor** Alessandro Margara

FINAL EVALUATION : 30/30

## _Project Specification_

For this Project we made a Java version of the Game Masters of Renaissance, board game made by Cranio Creation.

![](/src/main/resources/ReadMeImages/Masters-of-Renaissance.png)

The final version of the Project includes:
- Initial UML;
- Final UML;
- Working implementation of the game;
- Source code;
- Source code of Tests.

## Implemented Features


- Basic Rules
- Complete Rules
- Uniqueness of the nickname guaranteed by the server
- Socket
- CLI
- GUI
- Multiple Game
- Local Game

## Developing Team
- [Giovanni Arvati](https://github.com/arva29)
- [Emanuele Bianchi](https://github.com/EmanueleBianchi)
- [Mattia Buffi](https://github.com/MattiaBuffi)
## JavaDocs

Here you can read the [JavaDocs](https://mattiabuffi.github.io/ingswAM2021-Buffi-Arvati-Bianchi/) of this Project.

# Installation

You will need:
- Linux, MacOS or Windows OS with an active terminal.
- Java SE JDK 14 (OracleJDK or OpenJDK).
- Maven framework version 3.0 (or newer).

To Enjoy our Game.
## Using Jar File
After cloning this repo, open a terminal and go to the folder where you downloaded the Repo.

Use the command 
> java -jar AM60-< version >-jar-with-dependencies.jar -arg

##### _You have to choose arg between:_
 - cli
 - gui
 - server

To Play an online game You need to Run the server first. 
Server Automatically run on ip 127.0.0.1 and port 1337, you can use a custom port adding arg -port number(Custom Ports only works on Windows).
You can also Play a Local Game, in that case you don't need to run the Server. (Only SinglePlayer game).

For a Better Experience with the CLi, after starting the game, resize the terminal so that it contains the entire CLI.
For a Better Experience with the GUI, you need a 1920x1080 desktop without any taskbar, the game will occupy the entire screeen.

**In order to play multiplayer from different locations port forwarding is needed.**

**CLI and GUI Tested on Windows and Linux Terminal, we can't test MacOs because none of Us have a Mac.**
 
 #### _Cheats_
 
It is possible to start the game with 99 resources of each type inside the chest, but to do so it is necessary to change the source code.
You must uncomment line 60 of the StateSetupResources class, present at the path src / main / java / it.polimi.ingsw / Model / Player / States.
 
**After this you will need to generate the jar again.**



