[discord-invite]: https://discord.gg/d6sGxdfFk9
[aero-icon]: https://cdn.discordapp.com/emojis/941839556406292500.webp?size=44&quality=lossless

[ ![aero-icon][] ][discord-invite] The official aero discord!
# RavyAPI
## A wrapper for the Ravy API, made by me (Wolvinny/Luna). This is my first ever api wrapper so this was a learning experience for me. 
### Please note that bugs might occur. All suggestions are welcome.


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
The first thing i'd recommend to do is to check your scopes to know which routes you have access to. You can view your scopes when your token application was accepted, or by using the Token class
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

`user.pronouns`:
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
Trust trust = info.getTrust(); //the trust based on the bans
```

`users.rep`
#### Gets the DiscordRep reputation of the user. If you have a valid DiscordRep token, you can also construct a DiscordRep object (explained below)
```java
ReputationEntry rep = info.getReputation();
Trust trust = rep.getTrust(); //the trust based on the reputation
```

`users.whitelists`
```java
boolean whitelist = info.isWhitelisted() //! always check before retrieving whitelists, else an error might occur
WhitelistEntry[] entry = info.getWhitelists();
Trust trust = info.getTrust(); //the trust based on the whitelists
```
## Fetching info about a guild (`guilds`)
### Checks whether the guild is banned. 
```java
GuildInfo info = api.getGuildInfo("guildId");
boolean isBanned() = info.isBanned(); //! always check before retrieving bans, else an error might occur
BanEntry[] bans = info.getBans();
Trust trust = info.getTrust(); //gets the trust based on the bans
```


## Fetching BanEntries
#### After having checked if the user has bans, you can retrieve info about a ban:
```java
BanEntry ban = bans[0]; //depending on the amount of bans the user has, this can be multiple
String reason = ban.getReason();
String moderator = ban.getModerator(); //the id of the moderator
String provider = ban.getProvider(); //the name of the provider
ReasonKey key = ban.getReasonKey(); //a short version of the Reason, returned as a single ReasonKey enum object;
```

## Fetching Ksoft bans (`ksoft.bans`)
### For thos who still have a ksoft token, you can retirieve bans the following way. Note that the `user` scope also includes ksoft bans, but not the other way round.
```java
KsoftBanEntry ban = api.getKsoftBans("userId");
boolean banned = ban.isBanned(); //!!Make sure to check as if the user isn't banned all of the following fields are null;
//not gonna list all classes, pretty self-explanatory.
```

## SentinelEntry (`users`)
### Whether the user is verified on the Sentinel platform or not
```java
SentinelEntry sen = api.getFullUserInfo().getSentinel()
boolean isVerified = sen.isVerified()
```
This is a small class, but kept for maybe future expansion. You'll notice there is an `.getInternalDebugId()` but i have no clue why you'd ever need that

## Whitelist and WhitelistEntry (`users.whitelists`)
### This is to check whether the user is whitelisted 

```java
Whitelist white = api.getWhitelists("userid");
boolean isWhitelisted = white.isWhitelisted(); //! always check before retrieving, else an error might occur
WhitelistEntry[] entryarr = white.getWhitelists();
WhitelistEntry entry = entryarr[0];
String provider = entry.getProvider();
String reason = entry.getReason(); //Usually 'STAFF'

Trust trust = white.getTrust(); //trust based on whitelistentries
``` 

## URLS (`urls` or `urls.cached`)
### The api also offers fraudulent url checking
Params (* = optional) :
`url`: the url too lookup
`*phishermanToken`: A phisherman.gg token, if you have one
`*phishermanUserId`: the id this token belongs to
```java
URL url = api.getUrl(url, *token, *userId);
boolean isFraudulent = url.isFraudulent(); //whether this url is fraudulent
String reason = url.getReason(); //the reason why the url is fraudulent
```

## Avatar (`avatars`)
### Avatars can also be checked whether they are fraudulent or not. This can be done in two ways: 
Params:
`avatar`: The avatar. This has to be a cdn.discordapp.com url or a file
`threshold`: How similar the avatar needs to be to match (0-1, default 0.97)
`method`: The method to use: Method.SSIM, Method.PHASH, Method.DEFAULT. Only change this of you know what you are doing. Method.PHASH and Method.DEFAULT are the same, but has been names as default aswell to make it easier
```java
Avatar av = api.getAvatar(avatar, threshold, method);
boolean isMatched = av.isMatched();
String key = av.getKey(); //wich avatar is matched, eg modmail-blurple
Double similarity = av.getSimilarity(); //how similar the avatar is (0.00-1.00)
```

## Trust
### You'll see this one in a lot of classes. This differs for every class. The trust is based on the class, eg if you'd get the trust inside the BanEntry class, it'd return a trust based on the bans. So lets say the user is banned, the trust level would be '1', but if we were to retreive the 'ReputationEntry' trust object, it'd just return the default level, which is 3 (assuming the user isnt found on drep)

```java
Trust trust = <class>.getTrust(); //depends on which class you use
int level = trust.getLevel(); //returns a level from (0-6), 3 being default
String label = trust.getLabel(); //the label that belongs to the level
```


































