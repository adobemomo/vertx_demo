package verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;

public class JokeVerticle extends AbstractVerticle {

    private HttpRequest<?> request;

    @Override
    public void start() {

        request = WebClient.create(vertx) // (1)
                .getAbs("http://192.168.1.232:8081/v1/jobs"); // (2)
//                .ssl(true)  // (3)
//                .putHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0") ; // (4)
//                .as(BodyCodec.jsonObject()) // (5)
//                .expect(ResponsePredicate.SC_OK);  // (6)

        vertx.setPeriodic(3000, id -> fetch());
    }

    private void fetch() {
        request.send(asyncResult -> {
            if (asyncResult.succeeded()) {
                System.out.println(asyncResult.result().bodyAsString()); // (7)
//                System.out.println(asyncResult.result().statusMessage());
                System.out.println("🤣");
                System.out.println();
            }
            else {
                System.out.println("fail!");
            }
        });
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new JokeVerticle());
    }
}