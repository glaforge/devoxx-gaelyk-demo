import net.sf.json.groovy.JsonSlurper

def searchQueryParams = params.collect { k, v -> "${k}=${URLEncoder.encode(v)}"}.join('&')
def urlString = "http://cfp.devoxx.com/rest/v1/events/1/presentations/search?${searchQueryParams}"

def response
try {
    response = new URL(urlString).get(deadline: 10)
} catch (any) {
    response = [statusCode: 500, text: '[]']
}

def ajaxCall = headers['X-Requested-With'] == 'XMLHttpRequest'

if (!ajaxCall) {
    include '/WEB-INF/includes/header.gtpl'
    html.h1 "Search Results"
} else {
    html.h2 "Search results"
}

html.div {
    if (response.statusCode == 200) {
        def json = new JsonSlurper()
        def presentations = json.parseText(response.text)
        if (presentations.size() > 0) {
            ul {
                presentations.each { talk ->
                    li {
                        b {
                            a href: "/presentation/${talk.id}", talk.title
                        }
                    }
                }
            }
        } else {
            p "No results found for your query."
        }
    } else {
        p class: 'Error', 'An error occured while contacting the Devoxx server. Please try again later.'
    }
}

if (!ajaxCall) {
    include '/WEB-INF/includes/footer.gtpl' 
}
