import java.net.URI;

import at.uibk.dps.ee.docker.manager.ConstantsManager;
import at.uibk.dps.ee.docker.manager.ContainerManager;
import at.uibk.dps.ee.docker.manager.ContainerManagerAPI;
import at.uibk.dps.ee.docker.server.ContainerServer;
import ch.qos.logback.classic.util.ContextInitializer;
import io.vertx.core.Vertx;

/**
 * This class is an example usage of Docker via the java api.
 *
 * @author Fedor Smirnov, Lukas Dötlinger
 */
public class Play {

  public static void main(String[] args) throws Exception {
    System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "./logging/config/logback.xml");
    Vertx vertx = Vertx.vertx();

    // For Windows, TCP connection is needed.
    ContainerManager manager = new ContainerManagerAPI(ConstantsManager.localhost,
      ConstantsManager.defaultDockerHTTPPort);

    // For Unix, using system sockets is recommended.
    //ContainerManager manager = new ContainerManagerAPI(ConstantsManager.defaultDockerUnixSocketLocation,
    //  ConstantsManager.defaultDockerInternalUri);

    //ContainerManager manager = new ContainerManagerDockerAPI(ConstantsManager.defaultDockerInternalUri,
    //  ConstantsManager.defaultDockerHTTPPort);

    ContainerServer server = new ContainerServer(vertx, manager);
    server.start();
  }
}
