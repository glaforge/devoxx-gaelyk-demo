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

        <h3>Search for presentations</h3>

        <script type="text/javascript">
            jQuery(document).ready(function(){
                jQuery("#searchForm").submit(function() {
                    jQuery.post(
                        "/search",
                        jQuery("#searchForm").serialize(),  
                        function(data) {
                            jQuery("#results").html(data);
                        },
                        "html"
                    );
                    return false;
                });
            });
        </script>

        <form action="/search" method="post" id="searchForm">
            <table id="search-form" cellpadding="2px" cellspacing="2px">
                <tr>
                    <td class="label"><label for="title">Title</label></td>
                    <td class="field"><input type="text" id="title" name="title"></td>
                </tr>
                <tr>
                    <td class="label"><label for="abstract">Abstract</label></td>
                    <td class="field"><input type="text" id="abstract" name="abstract"></td>
                </tr>
                <tr>
                    <td class="label"><label for="abstract">Speaker</label></td>
                    <td class="field"><input type="text" id="speaker" name="speaker"></td>
                </tr>
                <tr>
                    <td class="label"></td>
                    <td class="field"><input type="submit" name="Search"></td>
                </tr>
            </table>
        </form>
    </div>

    <div class="talks" id="results">
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