package mypackagedirector;
import java.util.HashMap;
import java.util.Map;


class HttpRequest
{
    String url;
    String method;
    Map<String, String> headers = new HashMap<>();
    Map<String, String> queryParameter = new HashMap<>();
    Map<String, String> body = new HashMap<>();
    int timeout;

    HttpRequest(){}

    public void execute()
    {
        System.out.println("Suucessfully executed URL:\t" + this.url);
        System.out.println("Method:\t" + this.method);
        System.out.println("Headers:\t" + this.headers);
        System.out.println("Query Parameters:\t"+ this.queryParameter);
        System.out.println("Body:\t" + this.body);
        System.out.println("URL Timeout:\t" + this.timeout);
    }
}

class HttpRequestBuilder
{
    private HttpRequest req;

    public HttpRequestBuilder()
    {
        this.req = new HttpRequest();
    }

    public HttpRequestBuilder withurl(String url)
    {
        req.url = url;
        return this;
    }

    public HttpRequestBuilder withmethod(String method)
    {
        req.method = method;
        return this;
    }

    public HttpRequestBuilder withheaders(String key, String value)
    {
        req.headers.put(key, value);
        return this;
    }

    public HttpRequestBuilder withqueryParameter(String key, String value)
    {
        req.queryParameter.put(key, value);
        return this;
    }

    public HttpRequestBuilder withbody(String key, String value)
    {
        req.body.put(key, value);
        return this;
    }

    public HttpRequestBuilder withtimeout(int timeout)
    {
        req.timeout = timeout;
        return this;
    }

    public HttpRequest build()
    {
        if(req.url == null)
        {
            throw new RuntimeException("URL is missing");
        }

        return req;
    }
}

class HttpRequestDirector
{
    public static HttpRequest createGetRequest(String url)
    {
        return new HttpRequestBuilder()
        .withurl(url)
        .withmethod("GET")
        .build();
    }

    public static HttpRequest createJsonPostRequest(String url, String body)
    {
        return new HttpRequestBuilder()
        .withurl(url)
        .withmethod("POST")
        .withheaders("content-type", "application/JSON")
        .withqueryParameter("authorization","false")
        .withbody(body, body)
        .withtimeout(60)
        .build();
    }
}

class builderwithDirector
{
    public static void main(String[] args) {
        HttpRequest normalReq = new HttpRequestBuilder()
        .withurl("https://sre1.kfintech.com/sreTool")
        .withmethod("GET")
        .withheaders("content-type", "JSON")
        .withqueryParameter("Dashboard","JayeshAdmin")
        .withbody("Userid", "Jayesh")
        .withbody("Password", "Jayesh")
        .withtimeout(60)
        .build();

        normalReq.execute();

        HttpRequest getRequest = HttpRequestDirector.createGetRequest("www.google.com");
        getRequest.execute();

        HttpRequest postRequest = HttpRequestDirector.createJsonPostRequest("Asset.kfintech.com", "Jayesh");
        postRequest.execute();
    }
}