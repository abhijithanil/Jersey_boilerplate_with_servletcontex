package com.entity_history.api_pack;

import org.json.JSONObject;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.sql.Connection;


@Path("/app")
public class APIManagers {

    @GET
    @Path("/say")

    public Response say(@Context ServletContext context) {
        Connection connection = (Connection) context.getAttribute("connection");
        if (connection != null) {
            JSONObject message = new JSONObject();
            message.put("message", "Unavailable for the time being.., come back soon").put("status", Response.Status.INTERNAL_SERVER_ERROR);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message.toString()).build();
        }
        return null;
    }
}
