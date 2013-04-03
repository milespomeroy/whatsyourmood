package com.milespomeroy.whatsyourmood;

import com.milespomeroy.whatsyourmood.health.ConfigHealthCheck;
import com.milespomeroy.whatsyourmood.jdbi.UserDAO;
import com.milespomeroy.whatsyourmood.resources.SmsResource;
import com.milespomeroy.whatsyourmood.resources.TestResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.jdbi.DBIFactory;
import org.skife.jdbi.v2.DBI;

public class WhatsYourMoodService extends Service<WhatsYourMoodConfiguration> {
    public static void main(String[] args) throws Exception {
        new WhatsYourMoodService().run(args);
    }

    @Override
    public void initialize(Bootstrap<WhatsYourMoodConfiguration> bootstrap) {
        bootstrap.setName("whats-your-mood");
    }

    @Override
    public void run(WhatsYourMoodConfiguration configuration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDatabaseConfiguration(), "postgresql");

        environment.addResource(new TestResource("pong"));

        final UserDAO userDAO = jdbi.onDemand(UserDAO.class);
        environment.addResource(new SmsResource(userDAO));

        environment.addHealthCheck(new ConfigHealthCheck(configuration));
    }
}
