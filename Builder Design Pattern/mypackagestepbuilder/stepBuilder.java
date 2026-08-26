package mypackagestepbuilder;
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

interface optionalStep
{
    public optionalStep withbody(String key, String value);
    public optionalStep withtimeout(int timeout);
    public optionalStep withqueryParameter(String key, String value);
    public HttpRequest build();
}

interface headerStep
{
    public optionalStep withheaders(String key, String value);
}

interface methodStep
{
    public headerStep withmethod(String method);
}

interface urlStep
{
    public methodStep withurl(String url);
}

class HttpRequestBuilder implements urlStep, methodStep, headerStep, optionalStep
{
    private HttpRequest req;

    public HttpRequestBuilder()
    {
        this.req = new HttpRequest();
    }

    public methodStep withurl(String url)
    {
        req.url = url;
        return this;
    }

    public headerStep withmethod(String method)
    {
        req.method = method;
        return this;
    }

    public optionalStep withheaders(String key, String value)
    {
        req.headers.put(key, value);
        return this;
    }

    public optionalStep withqueryParameter(String key, String value)
    {
        req.queryParameter.put(key, value);
        return this;
    }

    public optionalStep withbody(String key, String value)
    {
        req.body.put(key, value);
        return this;
    }

    public optionalStep withtimeout(int timeout)
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

    public static urlStep getBuilder() 
    {
        return new HttpRequestBuilder();
    }
}

class stepBuilder
{
    public static void main(String[] args) {
        HttpRequest stepReq = HttpRequestBuilder.getBuilder()
        .withurl("asset.kfintech.com")
        .withmethod("POST")
        .withheaders("content-type", "application/Json")
        .withtimeout(60)
        .build();

        stepReq.execute();

    }
}