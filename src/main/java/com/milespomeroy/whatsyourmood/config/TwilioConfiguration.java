package com.milespomeroy.whatsyourmood.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class TwilioConfiguration {
    @NotEmpty
    @JsonProperty
    private String sid;

    @NotEmpty
    @JsonProperty
    private String auth;

    public String getSid() {
        return sid;
    }

    public String getAuth() {
        return auth;
    }
}
