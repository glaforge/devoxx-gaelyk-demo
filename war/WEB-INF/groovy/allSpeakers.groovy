import net.sf.json.groovy.JsonSlurper
import services.RestCache

RestCache.cache 'speakers'

def json = new JsonSlurper()
def cachedSpeakersJson = memcache['resource-speakers']

def allSpeakers = cachedSpeakersJson ? json.parseText(cachedSpeakersJson) : []
log.info "Parsed ${allSpeakers.size()} speakers"
def speakersByLetter = allSpeakers.groupBy { it.lastName[0].toUpperCase() }.sort { it.key }

request.speakersByLetter = speakersByLetter

forward '/WEB-INF/pages/allSpeakers.gtpl'