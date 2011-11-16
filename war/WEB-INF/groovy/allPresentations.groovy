import groovy.json.JsonSlurper
import services.RestCache

RestCache.cache 'presentations'

def cachedPresentationsJson = memcache['resource-presentations']
def json = new JsonSlurper()
def allPresentations = cachedPresentationsJson ? json.parseText(cachedPresentationsJson) : []
def presentationsByTrack = allPresentations.groupBy { it.track }

def currentTrack = params.track ?: presentationsByTrack.find { it }.key

request.presentationsByTrack = presentationsByTrack
request.currentTrack = currentTrack

forward "/WEB-INF/pages/allPresentations.gtpl"
