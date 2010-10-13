<% include "/WEB-INF/includes/header.gtpl?title=${request.preso.title}" %>

<h1>Presentation</h1>

<h2>${request.preso.title}</h2>

<ul>
    <li>
        Speakers:
        <ul>
        <% request.preso.speakers.each { speaker -> %>
            <li>
                <a href="/speaker/${speaker.speakerUri.substring(speaker.speakerUri.lastIndexOf('/') + 1)}">${speaker.speaker}</a>
            </li>
        <% } %>
    </ul>
    </li>
    <br>
    <li>
        Track: <a href="/presentations/track/${URLEncoder.encode(request.preso.track)}">${request.preso.track}</a>
    </li>
    <br>
    <li>
        Experience: <b>${request.preso.experience}</b>
    </li>
    <li>
        Type: <b>${request.preso.type}</b>
    </li>
</ul>

<h3>Summary</h3>

<div class="summary">
    ${request.preso.summary.replaceAll('\n', '<br/>')}
</div>

<% include '/WEB-INF/includes/footer.gtpl' %>