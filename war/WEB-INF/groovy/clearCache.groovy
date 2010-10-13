log.info "Clearing cache"

memcache.delete('resource-speakers')
memcache.delete('resource-presentations')
memcache.delete('resource-schedule')

memcache.clearCacheForUri('/speakers')
memcache.clearCacheForUri('/presentations')
memcache.clearCacheForUri('/schedule')

log.info "Cache cleared"