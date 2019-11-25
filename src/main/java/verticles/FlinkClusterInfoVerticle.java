package verticles;

import handlers.DatabaseHandler;
import handlers.RestHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.WebClient;
import org.jetbrains.annotations.NotNull;
import utils.JdbcUtil;


/**
 * /database/config?rpc_addr=&rest_port=&cluster_name=
 * /database/list
 *
 * /rest/cluster/configs?rpc_addr=&rest_port
 * /rest/taskmanagers/list?rpc_addr=&rest_port
 * /rest/taskmanagers/detail?rpc_addr=&rest_port&jm_id=
 * /rest/jobs/list/?rpc_addr=&rest_port
 * /rest/jobs/detail/?rpc_addr=&rest_port&jobid=
 *
 */

public class FlinkClusterInfoVerticle extends AbstractVerticle {
    public static void main(String[] args){
        FlinkClusterInfoVerticle verticle = new FlinkClusterInfoVerticle();
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(verticle);
    }

    @Override
    public void start(){
        HttpServer server = vertx.createHttpServer();
        Router router = routerInit();
        server.requestHandler(router).listen(6015);
    }

    @Override
    public void stop(){

    }

    private Router routerInit(){
        Router mainRouter = Router.router(vertx);
        Router databaseRouter = Router.router(vertx);
        Router restRouter = Router.router(vertx);

        databaseRouter.route("/config").handler(this::saveClusterConfig);
        databaseRouter.route("/list").handler(this::getClustersList);
        restRouter.route("/cluster/configs").handler(this::getClusterConfig);
        restRouter.route("/taskmanagers/list").handler(this::getTaskManagersList);
        restRouter.route("/taskmanagers/detail").handler(this::getTaskManagerDetail);
        restRouter.route("/jobs/list").handler(this::getJobsList);
        restRouter.route("/jobs/detail").handler(this::getJobDetail);


        mainRouter.mountSubRouter("/rest", restRouter);
        mainRouter.mountSubRouter("/database", databaseRouter);

        return mainRouter;
    }


    private void saveClusterConfig(@NotNull RoutingContext context){
        JDBCClient dbClient = new JdbcUtil(vertx).getJdbcClient();
        DatabaseHandler.saveClusterConfig(context, dbClient);
    }

    private void getClustersList(RoutingContext context){
        JDBCClient dbClient = new JdbcUtil(vertx).getJdbcClient();
        DatabaseHandler.getClusterList(context, dbClient);
    }

    private void getClusterConfig(@NotNull RoutingContext context){
        WebClient webClient = WebClient.create(vertx);
        RestHandler.getClusterConfig(context, webClient);
    }

    private void getTaskManagersList(@NotNull RoutingContext context){
        WebClient webClient = WebClient.create(vertx);
        RestHandler.getTaskManagersList(context, webClient);
    }

    private void getTaskManagerDetail(@NotNull RoutingContext context){
        WebClient webClient = WebClient.create(vertx);
        RestHandler.getTaskManagerDetail(context, webClient);
    }

    private void getJobsList(@NotNull RoutingContext context){
        WebClient webClient = WebClient.create(vertx);
        RestHandler.getJobsList(context, webClient);
    }

    private void getJobDetail(@NotNull RoutingContext context) {
        WebClient webClient = WebClient.create(vertx);
        RestHandler.getJobDetail(context, webClient);
    }
}
