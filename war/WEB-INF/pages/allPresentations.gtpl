<% include "/WEB-INF/includes/header.gtpl?title=${params.track ?: 'Presentations'}" %>

<h1>Presentations per track</h1>

<div class="tracks-talks">
    <div class="tracks">
        <h2>Tracks</h2>

        <ul>
        <% request.presentationsByTrack.each { track, presentations -> %>
            <li>
                <a href="/presentations/track/${URLEncoder.encode(track)}">${track}</a>
            </li>
        <% } %>
        </ul>
    </div>

    <div class="talks">
        <h2>${request.currentTrack}</h2>

        <ul>
        <% request.presentationsByTrack[request.currentTrack].sort { it.title }.each { talk -> %>
            <li>
                <b><a href="/presentation/${talk.id}">${talk.title}</a></b> &mdash; by 
                <%
                    talk.speakers.eachWithIndex { speaker, idx ->
                        if (idx > 0) out << ', '
                %>
                    <a href="/speaker/${speaker.speakerUri.substring(speaker.speakerUri.lastIndexOf('/') + 1)}">${speaker.speaker}</a>
                <%  } %>
            </li>
        <% } %>
        </ul>
    </div>

    <div class="clear"></div>
</div>

<% include '/WEB-INF/includes/footer.gtpl' %>