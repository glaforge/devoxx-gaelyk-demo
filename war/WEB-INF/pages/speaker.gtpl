<% include "/WEB-INF/includes/header.gtpl?title=${request.speaker.firstName}%20${request.speaker.lastName}" %>

<div>
    <h1>Speaker</h1>
    <img src="${request.speaker.imageURI}" alt="${request.speaker.lastName}" class="bio-picture" align="right">
    <h2>${request.speaker.firstName} ${request.speaker.lastName} &mdash; ${request.speaker.company}</h2>

    <h3>Biography</h3>
    <p>${request.speaker.bio}</p>

    <h3>Talks</h3>

    <ul>
        <%
            request.speaker.talks.each { talk ->
            def presentationId = talk.presentationUri.substring(talk.presentationUri.lastIndexOf('/') + 1)
        %>
        <li>
            <a href="/presentation/${presentationId}">${talk.title}</a>
        </li>
        <%  } %>
    </ul>
</div>

<% include '/WEB-INF/includes/footer.gtpl' %>