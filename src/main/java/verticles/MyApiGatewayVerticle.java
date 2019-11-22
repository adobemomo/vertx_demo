package verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.handler.BodyHandler;

public class MyApiGatewayVerticle extends AbstractVerticle {
    public static void main(String[] args){
        MyApiGatewayVerticle verticle = new MyApiGatewayVerticle();
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(verticle);
    }

    @Override
    public void start(Future<Void> future) throws Exception {
        super.start();

        // get HTTP host and port from configuration, or use default value
        String host = "localhost";
        int port = 7475;

        Router router = Router.router(vertx);

        // body handler
        router.route().handler(BodyHandler.create());


        // api dispatcher
        router.route("/*").handler(this::dispatchRequests);


        // create http server
        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(port, host, ar -> {
                    if (ar.succeeded()) {
                        future.complete();
                    } else {
                        future.fail(ar.cause());
                    }
                });
    }

    private void dispatchRequests(RoutingContext context) {
        String uri = context.request().uri();
        String restHost = "http://192.168.1.232:8081";
        String version = "/v1";
        WebClient webClient = WebClient.create(vertx);
        webClient.getAbs(restHost+version+uri)
                .send(ar -> {
                    if(ar.succeeded()){
                        context.response().end(ar.result().bodyAsString());
                    }
                });
    }
}
