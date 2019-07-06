/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab1se.ecommercelab15club;

/**
 *
 * @author biar
 */
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("clab")
@Produces("text/xml")
public class UserRepository {

    private Map<Integer, User> users = new HashMap<>();

    {
        
        User user1 = new User();
        User user2 = new User();
        user1.setId(1);
        user1.setUsername("Mario");
        user2.setId(2);
        user2.setUsername("Giggi");

        users.put(1, user1);
        users.put(2, user2);
    }

    @GET
    @Path("users/{userId}")
    public User getUser(@PathParam("userId") int userId) {
        return findById(userId);
    }
    @GET
    @Path("users")
    public List<User> getAllUser() {
        int i=1;
        List<User> uss=new ArrayList<>();
        User u=findById(i);
        while(u!=null){
            uss.add(u);
            u=findById(i);
            
        }
        return uss; 
   }
    @POST
    @Path("users/")
    @Consumes("text/plain")
    public Response addUser(String user) {
        /*
        User existingUser = findById(user.getId());
        if(existingUser==null){
            users.put(userId, user);

            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if(existingUser.equals(user)){
            return Response.notModified().build();
        }
        */
        System.out.println("in addUser");
        System.out.println(user);
        return Response.ok().build();
        
    }
    @PUT
    @Path("users/{userId}")
    public Response updateUser(@PathParam("userId") int userId, User user) {
        User existingUser = findById(userId);
        if (existingUser == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (existingUser.equals(user)) {
            return Response.notModified().build();
        }
        users.put(userId, user);
        return Response.ok().build();
    }

    

    private User findById(int id) {
        for (Map.Entry<Integer, User> user : users.entrySet()) {
            if (user.getKey() == id) {
                return user.getValue();
            }
        }
        return null;
    }
}
