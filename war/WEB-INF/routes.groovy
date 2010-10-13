
def cacheDuration = 24.hours

get "/ping",                    forward: "/ping.groovy"

get "/schedule",                forward: "/schedule.groovy",                    cache: cacheDuration
get "/schedule/room/@id",       forward: "/schedule.groovy?room=@id",           cache: cacheDuration

get "/speakers",                forward: "/allSpeakers.groovy",                 cache: cacheDuration
get "/speaker/@id",             forward: "/speaker.groovy?id=@id",              cache: cacheDuration

get "/presentations",           forward: "/allPresentations.groovy",            cache: cacheDuration
get "/presentations/track/@id", forward: "/allPresentations.groovy?track=@id",  cache: cacheDuration
get "/presentation/@id",        forward: "/presentation.groovy?id=@id",         cache: cacheDuration

get "/json/@resource",          forward: "/jsonResource.groovy?resource=@resource"

get "/favicon.ico",             forward: "/images/gaelyk-small-favicon.png"

