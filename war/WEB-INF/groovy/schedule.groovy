import groovy.json.JsonSlurper
import services.RestCache

RestCache.cache 'schedule'

String content = memcache['resource-schedule']
def schedule = new JsonSlurper().parseText(content)

if (params.room) {
    schedule = schedule.findAll { it.room == params.room }
}

def eventsPerDay = schedule.
        findAll { it.title }.
        sort    { it.fromTime }.
        groupBy { it.fromTime[0..10] }

request.eventsPerDay = eventsPerDay

forward '/WEB-INF/pages/schedule.gtpl'