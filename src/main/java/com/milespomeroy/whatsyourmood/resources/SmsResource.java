package com.milespomeroy.whatsyourmood.resources;

import com.milespomeroy.whatsyourmood.core.User;
import com.milespomeroy.whatsyourmood.jdbi.UserDAO;
import com.twilio.sdk.verbs.Sms;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/sms")
@Produces(MediaType.APPLICATION_XML)
public class SmsResource {

    private static final String WELCOME_MESSAGE =
            "Welcome to What's Your Mood? SMS service. Let's get to know each other. What's your name?";

    public static final String NAME_MESSAGE =
            "%s, what's the number of the person whose mood you want to know?";

    public final UserDAO userDAO;

    public SmsResource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GET
    @Timed
    public String smsRouter(@QueryParam("From") String fromPhone, @QueryParam("Body") String body) {
        TwiMLResponse twiml = new TwiMLResponse();
        String message;

        User user = userDAO.findUserByPhone(fromPhone);

        if (user == null) {
            message = WELCOME_MESSAGE;
        } else {
            message = String.format(NAME_MESSAGE, body);
        }

        Sms sms = new Sms(message);

        try {
            twiml.append(sms);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }

        return twiml.toXML();
    }
}
