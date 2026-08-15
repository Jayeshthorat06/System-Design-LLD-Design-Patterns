interface IReports
{
    public String getJsonData(String data);
}

class XMLDataProvider
{
    public String getXMLData(String data)
    {
        return data + "\t converted to XML";
    }
}

class XMLDataProviderAdapter implements IReports
{
    XMLDataProvider xml;

    public XMLDataProviderAdapter(XMLDataProvider xml)
    {
        this.xml = xml;
    }

    public String getJsonData(String data)
    {
        return xml.getXMLData(data) + "\t Converted to JSON" ;
    }
}

class client
{
    IReports report;
    public client(IReports report)
    {
        this.report = report;
    }

    public void converter(String data)
    {
        System.out.println(this.report.getJsonData(data));
    }
    
}

class Adapter
{
    public static void main(String[] args) {

        XMLDataProvider xml = new XMLDataProvider();

        IReports report = new XMLDataProviderAdapter(xml);
        client cli = new client(report);

        cli.converter("Jayesh : 25");
    }
}