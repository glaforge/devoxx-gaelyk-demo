<% include '/WEB-INF/includes/header.gtpl?title=Speakers' %>

<h1>Speakers and their presentations</h1>

<ul class="tabs">
<%  request.speakersByLetter.keySet().each { letter -> %>
    <li>
        <a id="t${letter}" href="#${letter}" style="width: 30px">${letter}</a>
    </li>
<%  } %>
</ul>

<div class="panes">
<%  request.speakersByLetter.each { letter, speakers -> %>
    <div style="height: 1200px">
        <ul>
        <%  speakers.each { speaker -> %>
            <li>
                <span class="speaker-name">
                    <a href="/speaker/${speaker.id}">${speaker.firstName} ${speaker.lastName}</a> &mdash; ${speaker.company}
                </span>
                <ul>
                <%  speaker.talks.each { talk ->
                    def presentationId = talk.presentationUri.substring(talk.presentationUri.lastIndexOf('/') + 1) %>
                    <li>
                        <a href="/presentation/${presentationId}">${talk.title}</a>
                    </li>
                <%  } %>
                </ul>
                <br>
            </li>
        <%  } %>
        </ul>
    </div>
<%  } %>
</div>

<% include '/WEB-INF/includes/tabsJavaScript.gtpl' %>
<% include '/WEB-INF/includes/footer.gtpl' %>