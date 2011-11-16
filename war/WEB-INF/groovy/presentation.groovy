import groovy.json.JsonSlurper
import services.RestCache

RestCache.cache 'presentations'

def content = memcache['resource-presentations']
def json = new JsonSlurper()
def presentations = json.parseText(content)
def preso = presentations.find { it.id == params.id as int }

request.preso = preso

forward "/WEB-INF/pages/presentation.gtpl"
