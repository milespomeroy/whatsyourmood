package com.milespomeroy.whatsyourmood.resources;

import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ping")
@Produces(MediaType.TEXT_PLAIN)
public class TestResource {
    private final String pongText;

    public TestResource(String pongText) {
        this.pongText = pongText;
    }

    @GET
    @Timed
    public String pong() {
        return pongText;
    }
}
