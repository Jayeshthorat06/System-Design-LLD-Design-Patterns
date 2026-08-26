package mypackage;
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

class simpleBuilder
{
    public static void main(String[] args) {
        HttpRequest req = new HttpRequestBuilder()
        .withurl("https://sre1.kfintech.com/sreTool")
        .withmethod("GET")
        .withheaders("content-type", "JSON")
        .withqueryParameter("Dashboard","JayeshAdmin")
        .withbody("Userid", "Jayesh")
        .withbody("Password", "Jayesh")
        .withtimeout(60)
        .build();

        req.execute();
    }
}