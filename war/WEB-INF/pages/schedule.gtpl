<% include '/WEB-INF/includes/header.gtpl?title=Schedule' %>

<% if (params.room) { %>
<h1>Schedule in ${params.room}</h1>
<% } else { %>
<h1>Schedule</h1>
<% } %>

<ul class="tabs">
<%  request.eventsPerDay.keySet().each { day -> %>
    <li><a id="t${day}" href="#${day}" style="width: 134px">${day}</a></li>
<%  } %>
</ul>

<div class="panes">
<%  request.eventsPerDay.each { day, allEvents ->
        def eventsByHour = allEvents.groupBy { it.fromTime } %>
    <div style="height: 1450px">
    <%  eventsByHour.each { hour, events -> %>
        <h3 class="times">${hour[11..15]} &mdash; ${events[0].toTime[11..15]} : ${events[0].type}</h3>
        <ul>
        <%  events.each { event ->
                def presentationId = event.presentationUri.substring(event.presentationUri.lastIndexOf('/') + 1) %>
            <li>
                <b><a href="/presentation/${presentationId}">${event.title}</a></b> &mdash; by
                <%  event.speakers.eachWithIndex { speaker, idx ->
                        if (idx > 0) out << ', ' %>
                    <a href="/speaker/${speaker.speakerUri.substring(speaker.speakerUri.lastIndexOf('/') + 1)}">${speaker.speaker}</a>
                <%  } %>
                in <a href="/schedule/room/${URLEncoder.encode(event.room)}">${event.room}</a>
            </li>
        <%  } %>
        </ul>
    <%  } %>
    </div>
<%  } %>
</div>

<% include '/WEB-INF/includes/tabsJavaScript.gtpl' %>
<% include '/WEB-INF/includes/footer.gtpl' %>