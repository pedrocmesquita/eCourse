package eapli.framweork.bootstrap;

public abstract class BaseApplication {

    public void run (String[] args) {
        configure();
        doMain(args);
        System.exit(0);
    }

    protected void configure() {
        configureAuthz();
    }

    protected abstract void doMain(String[] args);

    protected abstract void configureAuthz();
}
