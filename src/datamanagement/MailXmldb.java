package datamanagement;

/**
 *
 * @author Lite
 */
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.*;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import domain.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.exist.xmldb.DatabaseImpl;

import org.xmldb.api.modules.XQueryService;

public class MailXmldb {

    private static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    public static void main(String[] args) throws Exception {
        
        String mailId;
        double mailWeight;
        double mailVolume;
        long mailTime;
        Route mailRoute;
        double mailPrice;
        
        
        final String driver = "org.exist.xmldb.DatabaseImpl";

        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        //        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        Collection col=DatabaseManager.getCollection(URI+"/db/kps");

        //String[] xquery = new String[5];
        String xquery = "for $c in fn:doc('Kps_client.xml')/Kps/mails/mail return $c";    
        XQueryService service = (XQueryService) col.getService("XQueryService","1.0");
        service.setProperty("indent","yes");
        ResourceSet result =service.query(xquery);
        ResourceIterator i =result.getIterator();
        
        Mail mail = new Mail();
        

//        Mail m = (Mail)xstream.fromXML('Kps_client.xml');
//        mail.setId(mailId);
//        mail.setPrice(mailPrice);
//        mail.setRoute(mailRoute);
//        mail.setTime(mailTime);
//        mail.setVolume(mailVolume);
//        mail.setWeight(mailWeight);
        
        while(i.hasMoreResources()){
            Resource r=i.nextResource();
            String value=(String)r.getContent();
            
            //   Mail mail = xmlToMail(r);
            //Map<String,Mail> map = new HashMap<>();
            //Object o = xml2Bean(map, Value);
            if (value.toLowerCase().contains("mail")){
                Mail m = new Mail();
                
                //System.out.prinln("value: " + value);
                String[] lines = value.split("\n");
                for (int j = 0; j != lines.length; ++j){
                    System.out.println("LINE "  + j + ": " + lines[j]);
                }                       
            }
            
            // System.out.println(value);
            //System.out.println $( fn:split(Value, '5') );

        }
    
    }
    
    public Mail xmlToMail(Resource r){
//        try {
// 
//		File file = new File("C:\\file.xml");
//		JAXBContext jaxbContext = JAXBContext.newInstance(Mail.class);
// 
//		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//		Mail mail = (Mail) jaxbUnmarshaller.unmarshal(r);
//		//System.out.println(customer);
// 
//                return mail;
//	  } catch (JAXBException e) {
//		e.printStackTrace();
//	  }
        return null;
    }
    
    
    public static Object xml2Bean(Map<String, Class> clazzMap, String xml) { 
        XStream xstream = new XStream(); 
        for (Iterator it = clazzMap.entrySet().iterator(); it.hasNext();) { 
            Map.Entry<String, Class> m = (Map.Entry<String, Class>) it.next(); 
            xstream.alias(m.getKey(), m.getValue()); 
        } 
        Object bean = xstream.fromXML(xml); 
        return bean; 
    }
}
