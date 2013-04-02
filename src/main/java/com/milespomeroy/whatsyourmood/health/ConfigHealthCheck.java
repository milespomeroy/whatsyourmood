package com.milespomeroy.whatsyourmood.health;

import com.milespomeroy.whatsyourmood.WhatsYourMoodConfiguration;
import com.yammer.metrics.core.HealthCheck;

public class ConfigHealthCheck extends HealthCheck {
    private final WhatsYourMoodConfiguration configuration;

    public ConfigHealthCheck(WhatsYourMoodConfiguration configuration) {
        super("config");
        this.configuration = configuration;
    }

    @Override
    protected Result check() throws Exception {
        if (
            configuration.getTwilioConfiguration().getSid() != null
            && configuration.getTwilioConfiguration().getAuth() != null
        ) {
            return Result.healthy();
        } else {
            return Result.unhealthy("Config needs twilio sid and auth.");
        }
    }
}
