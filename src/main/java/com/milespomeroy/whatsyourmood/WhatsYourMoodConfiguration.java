package com.milespomeroy.whatsyourmood;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.milespomeroy.whatsyourmood.config.TwilioConfiguration;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.db.DatabaseConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;

public class WhatsYourMoodConfiguration extends Configuration {
    @Valid
    @NotNull
    @JsonProperty
    private TwilioConfiguration twilio = new TwilioConfiguration();

    @Valid
    @NotNull
    @JsonProperty
    private DatabaseConfiguration database = new DatabaseConfiguration();

    public TwilioConfiguration getTwilioConfiguration() {
        return twilio;
    }

    public DatabaseConfiguration getDatabaseConfiguration() throws URISyntaxException{
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        database.setUrl("jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + "/" + dbUri.getPath());
        database.setUser(dbUri.getUserInfo().split(":")[0]);
        database.setPassword(dbUri.getUserInfo().split(":")[1]);

        return database;
    }
}
