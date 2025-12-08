package my.important.job;

public class Main {

    private Main() { }

    public static void main(String[] args) throws Exception {
        new WrapperJettyServer().start();
    }
}