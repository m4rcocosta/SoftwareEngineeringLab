/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypackage;

import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author biar
 */
@Produces("text/xml")
public class CantanteRepository {
    
    private Map<Integer, Cantante> cantanti;
    private Database mydb;
    
    
    public CantanteRepository(){
        
        mydb = new Database();
        mydb.connect();
        mydb.createNewTable();
        mydb.populateDb();
        mydb.disConnect();
        /*
        cantanti = new HashMap<Integer, Cantante>();
        Cantante c1 = new Cantante(1, "Mauro");
        Cantante c2 = new Cantante(2, "Francesco");
        Cantante c3 = new Cantante(3, "Tiziano");
        
        
        //idcanzone, idcantante, nomecanzone, anno
        Canzone ca1 = new Canzone(1, 1, "Ciao", 1900);
        Canzone ca2 = new Canzone(2, 1, "Bubu", 1987);
        Canzone ca3 = new Canzone(3, 2, "Sali", 1382);
        Canzone ca4 = new Canzone(4, 3, "Andare via", 1234);
        
        List<Canzone> canz1 = new ArrayList<Canzone>();
        List<Canzone> canz2 = new ArrayList<Canzone>();
        List<Canzone> canz3 = new ArrayList<Canzone>();
     
        canz1.add(ca1);
        canz1.add(ca2);
        canz2.add(ca3);
        canz3.add(ca4);
        
        
        c1.setCanzoni(canz1);
        c2.setCanzoni(canz2);
        c3.setCanzoni(canz3);
        
        
        cantanti = new HashMap<Integer, Cantante>();
        cantanti.put(c1.getIdCantante(), c1);
        cantanti.put(c2.getIdCantante(), c2);
        cantanti.put(c3.getIdCantante(), c3);
*/
    }
    
    private Cantante findById(int idCantante){
        mydb.connect();
        Cantante c = mydb.selectCantante(idCantante);
        mydb.disConnect();
        return c;
    }
    
    private Canzone findByIdCanzone(int idCanzone, int idCantante){
        mydb.connect();
        Canzone c = mydb.selectCanzone(idCanzone, idCantante);
        mydb.disConnect();
        return c;
    }
    
    @GET
    @Path("{cantanteId}/{canzoneId}")
    public Canzone getCanzone(@PathParam("cantanteId") int cantanteId, @PathParam("canzoneId")int canzoneId) {
        
        //System.out.println("La size di cantanti è: "+cantanti.size());
        Cantante c = findById(cantanteId);
        //if (c == null)
        //    return new Canzone(12, 1, "ciaociao", 1233);
        
        //System.out.println(c);
        Canzone res = findByIdCanzone(canzoneId, cantanteId);
        
        return res;
            
        //return c.findById(canzoneId);
        
    }
    
    
    @GET
    @Path("{cantanteId}")
    public Cantante getCantante(@PathParam("cantanteId") int cantanteId){
        return findById(cantanteId);
    }
    
}
