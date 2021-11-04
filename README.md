# mcmidi/`server`
A plugin which hosts an endpoint that receives mcmidi note requests and relays them to other plugins.

Installing `server` onto a Minecraft server by itself
will do nothing as the plugin only fires events for
other plugins to use. It must be paired with a plugin
which listens to those events, such as [mcmidi/`player`](https://github.com/mcmidi-uwu/player).

This repository contains the following subprojects:
- `server-api` (for developers to code against)
- `server-paper` (a paper implementation of the api, put *this* on your Minecraft server)
