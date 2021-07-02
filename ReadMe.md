# Masters of Renaissance


Final Test of "**Software Engineering**".

**Professor** Alessandro Margara

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

# Installation

You will need:
- Linux, MacOS or Windows OS with an active terminal.
- Java SE JDK 14 (OracleJDK or OpenJDK).
- Maven framework version 3.0 (or newer).

To Enjoy our Game.
## Using Jar File
After cloning this repo, open a terminal and go to the folder where you downloaded the Repo.

Use the command 
> java -jar AM60-<version>-jar-with-dependencies.jar -arg

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

**CLI and GUI Tested on Windows and Linux Terminal, we can't test MacOs because none of Us have a Mac**


