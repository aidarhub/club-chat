package my.important.job;

import my.important.job.config.JerseyConfig;
import my.important.job.util.PropertiesUtil;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.servlet.ServletContainer;

public class WrapperJettyServer {

    private final Server server;

    public WrapperJettyServer() {
        final var port = Integer.parseInt(PropertiesUtil.getOrElse(PropertiesUtil.SERVER_PORT,
                PropertiesUtil.SERVER_PORT_DEFAULT_VALUR));
        this.server = new Server(port);
        final var contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        contextHandler.setContextPath("/");
        server.setHandler(contextHandler);

        final var jerseyServlet = new ServletHolder(new ServletContainer());

        jerseyServlet.setInitParameter("jakarta.ws.rs.Application", JerseyConfig.class.getCanonicalName());
        contextHandler.addServlet(jerseyServlet, "/api/*");
    }

    public void start() throws Exception {
        this.server.start();
    }

    public void stop() throws Exception {
        this.server.stop();
    }
}
