package verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

public class HelloWorldVerticle extends AbstractVerticle {
    public static void main(String[] args){
        HelloWorldVerticle verticle = new HelloWorldVerticle();
        Vertx vertx = Vertx.vertx();

        // 部署verticle，这里会调用start方法
        vertx.deployVerticle(verticle);
    }

    @Override
    public void start(){
        // 在这里可以通过this.vertx获取到当前的Vertx
        Vertx vertx = this.vertx;

        HttpServer server = vertx.createHttpServer();

        server.requestHandler(request->{
            // 获取到response对象
            HttpServerResponse response = request.response();
            response.putHeader("Content-type", "text/html;charset=utf-8");

            // 响应数据
            response.end("Hello World");
        });

        server.listen(7474);

    }

    @Override
    public void stop() throws Exception{
        super.stop();
    }
}
