import net.sf.json.groovy.JsonSlurper
import services.RestCache

RestCache.cache 'speakers'

def content = memcache['resource-speakers']

def json = new JsonSlurper()
def speakers = json.parseText(content)

request.speaker = speakers.find { it.id == params.id as int }

forward "/WEB-INF/pages/speaker.gtpl"
