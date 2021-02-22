# server
A plugin which hosts a web server that receives note requests and relays them to other plugins.

On its own, installing server onto a Minecraft server
will do nothing as it just fires events intended for other plugins to use.
It must be paired with a plugin which listens to those events, such as
[player](https://github.com/mcmidi-uwu/player).

This repo is not just one, but two! It contains the following subprojects:
- server-api (for developers to code against)
- server-paper (a paper plugin implementation of the api, put *this* on your Minecraft server)
