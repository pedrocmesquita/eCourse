package eapli.framweork.bootstrap;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootstrapApplication extends BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootstrapApplication.class, args);
    }

    @Override
    protected void doMain(String[] args) {

    }

    @Override
    protected void configureAuthz() {
        AuthzRegistry.configure
    }
}
