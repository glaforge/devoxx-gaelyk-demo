package services

import com.google.appengine.api.memcache.Expiration
import com.google.appengine.api.memcache.MemcacheServiceFactory
import groovyx.gaelyk.logging.GroovyLogger

class RestCache {
    static final int cacheDuration = 60 * 60

    static final GroovyLogger log = new GroovyLogger('RestCache')

    static void cache(String resource) {
        def memcache = MemcacheServiceFactory.memcacheService

        String key = "resource-${resource}"
        log.config "Memcache resource key: $key (${key.class.name})"

        def content = memcache[key]
        log.config "Content in cache: $content"

        if (!content) {
            log.config "Content for ${resource} not in memcache"
            try {
                def urlString = "https://cfp.devoxx.com/rest/v1/events/1/${resource}"
                log.config "Contacting $urlString"
                def response = new URL(urlString).get(deadline: 10)
                if (response.statusCode == 200) {
                    log.config "Got 200 when calling the REST API"
                    content = response.text
                    memcache.put(key, content, Expiration.byDeltaSeconds(cacheDuration))
                    log.config "Cached the response"
                } else {
                    throw new RuntimeException("Got status code ${response.statusCode} when contacting the REST API")
                }
            } catch (any) {
                log.warning "Impossible to get content from the REST API: ${any.message}"
                content = new File("json-backup/${resource}.json").text
                memcache.put(key, content, Expiration.byDeltaSeconds(cacheDuration))
            }
        } else {
            log.config "Serving ${resource} from cache"
        }
    }
}