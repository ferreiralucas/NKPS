/*/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamanagement;

import java.util.Iterator;
import java.util.Map;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.exist.xmldb.DatabaseImpl;
import org.xmldb.api.modules.XQueryService;
/**
 *
 * @author kuangwentao
 */
public class NewClass {
    private static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    public static void main(String[] args) throws Exception {
    final String driver = "org.exist.xmldb.DatabaseImpl";
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
//        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        Collection col=DatabaseManager.getCollection(URI+"/db/kps");
        
    String xquery= "for $c in fn:doc('Kps_manager.xml')/Business_events/mail_events/mail\n" +
"return\n" +
"   <a>{$c/event/text()},{$c/event_time/text()},{$c/weight/text()},{$c/volume/text()},{$c/time/text()},{$c/priority_id/text()},{$c/origin/text()},{$c/destination/text()},{$c/price/text()},{$c/cost/text()}</a>";  
    
  
    
    
    XQueryService service = (XQueryService) col.getService("XQueryService","1.0");
    service.setProperty("indent","yes");
    ResourceSet result =service.query(xquery);
    ResourceIterator i =result.getIterator();
    while(i.hasMoreResources()){
        Resource r=i.nextResource();
        String Value=(String)r.getContent();
        String[] Value2 = Value.substring(3, (Value.length()-4)).split(",");       
        System.out.println(Value2[0]);
    }    
    }}
 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
