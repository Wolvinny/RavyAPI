[discord-invite]: https://discord.gg/d6sGxdfFk9
[aero-icon]: https://cdn.discordapp.com/emojis/941839556406292500.webp?size=44&quality=lossless

[ ![aero-icon][] ][discord-invite] The official aero discord!
# RavyAPI
A wrapper for the Ravy API


## Summary
This api offers support for <a href=https://docs.ravy.org/share/5bc92059-64ef-4d6d-816e-144b78e97d89/doc/the-api-AjLeh3dpsp>the ravy.org api</a>.
you can apply for a token in <a href=https://discord.gg/d6sGxdfFk9>the discord server</a>

## Usage
### Constructing the API object
```java
RavyAPI api = new RavyAPI("your-token-here");
```
If your token is invalid, this will throw an InvalidTokenException

### Fetching Token data
The first thing i'd recommend to do is to check your scopes to know which routes you have access to. You can view your scopes when your token application was accepted, or but using the Token class
```java
String[] scopes = api.getToken().getScopes();
```
Additionally you can also retrieve the data about your application:
```java
Token token = api.getToken();
String user = token.getUser();
String app = token.getApplication();
TokenType type = token.getType();
```
The `TokenType` is the type of your token. This is either `ravy` (default) or `ksoft`. Note that Ksoft tokens only have access to the `ksoft.bans` scope.

### Fetching User data.
#### Fetching user data can be done in multiple ways depending on the scopes your token has. Note that for every type, you need to pass in the id. All possible return types are explained beneath
#### `user.*` 
For this scope you'd want to use the FullUserInfo class.
```java
FullUserInfo info = api.getFullUserInfo("userId");
String pronouns = info.getPronouns();
Trust trust = info.getTrust();
boolean whitelist = info.isWhitelisted() //! always check before retrieving whitelists, else an error might occur
WhitelistEntry[] entry = info.getWhitelists();
boolean isBanned() = info.isBanned(); //! always check before retrieving bans, else an error might occur
BanEntry[] bans = info.getBans();
ReputationEntry rep = info.getReputation();
SentinelEntry sentinel = info.getSentinel();
```
`user.pronouns`
This can be done directly via the main RavyAPI object using
```java
String pronouns = api.getPronouns("userId"):
```

`user.bans` **This is the most common scope**
For the `user.bans` scope, you need to use the `UserInfo` class

```java
UserInfo info = api.getUserInfo("userId");
boolean isBanned() = info.isBanned(); //! always check before retrieving bans, else an error might occur
BanEntry[] bans = info.getBans();
Trust trust = info.getTrust();
```

`users.rep`
#### Gets the DiscordRep reputation of the user. If you have a valid DiscordRep token, you can also construct a DiscordRep object
```java
Rep
































