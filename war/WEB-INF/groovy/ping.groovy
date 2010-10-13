import services.RestCache

log.info "Application got pinged by:${request.remoteHost} (${request.remoteAddr})"

RestCache.cache 'speakers'
RestCache.cache 'presentations'
RestCache.cache 'schedule'

log.info "Pinged json cache"
