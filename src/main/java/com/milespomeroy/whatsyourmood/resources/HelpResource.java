package com.milespomeroy.whatsyourmood.resources;

import com.twilio.sdk.verbs.Sms;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/help")
@Produces(MediaType.APPLICATION_XML)
public class HelpResource {

    public HelpResource() {

    }

    @GET
    @Timed
    public String sendHelp() {
        TwiMLResponse twiml = new TwiMLResponse();
        Sms sms = new Sms("Help me!");
        try {
            twiml.append(sms);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }

        return twiml.toXML();
    }
}
