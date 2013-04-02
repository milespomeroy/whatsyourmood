package com.milespomeroy.whatsyourmood;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.milespomeroy.whatsyourmood.config.TwilioConfiguration;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.db.DatabaseConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

    public DatabaseConfiguration getDatabaseConfiguration() {
        return database;
    }
}
