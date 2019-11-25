package handlers;

import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.WebClient;
import utils.UriUtil;

import static constants.RequestConstant.*;

public class RestHandler {
    public static void getClusterConfig(RoutingContext context, WebClient webClient){
        String host = context.request().getParam(RPC_ADDR);
        String port = context.request().getParam(REST_PORT);
        String path = "/jobmanager/config";
        String abs = UriUtil.AbsUriBuilder("http", host, port, 1, path);

        webClient.getAbs(abs)
                .send(ar -> {
                    if(ar.succeeded()){
                        context.response().end(ar.result().bodyAsString());
                    }
                });
    }
    public static void getTaskManagersList(RoutingContext context, WebClient webClient){
        String host = context.request().getParam(RPC_ADDR);
        String port = context.request().getParam(REST_PORT);
        String path = "/taskmanagers";
        String abs = UriUtil.AbsUriBuilder("http", host, port, 1, path);

        webClient.getAbs(abs)
                .send(ar -> {
                    if(ar.succeeded()){
                        context.response().end(ar.result().bodyAsString());
                    }
                });
    }

    public static void getTaskManagerDetail(RoutingContext context, WebClient webClient){
        String host = context.request().getParam(RPC_ADDR);
        String port = context.request().getParam(REST_PORT);
        String taskManagerId = context.request().getParam(TASKMANAGER_ID);
        String path = "/taskmanagers/" + taskManagerId;
        String abs = UriUtil.AbsUriBuilder("http", host, port, 1, path);

        webClient.getAbs(abs)
                .send(ar -> {
                    if(ar.succeeded()){
                        context.response().end(ar.result().bodyAsString());
                    }
                });
    }

    public static void getJobsList(RoutingContext context, WebClient webClient){
        String host = context.request().getParam(RPC_ADDR);
        String port = context.request().getParam(REST_PORT);
        String path = "/jobs";
        String abs = UriUtil.AbsUriBuilder("http", host, port, 1, path);

        webClient.getAbs(abs)
                .send(ar -> {
                    if(ar.succeeded()){
                        context.response().end(ar.result().bodyAsString());
                    }
                });
    }

    public static void getJobDetail(RoutingContext context, WebClient webClient){
        String host = context.request().getParam(RPC_ADDR);
        String port = context.request().getParam(REST_PORT);
        String jobId = context.request().getParam(JOB_ID);
        String path = "/jobs/" + jobId;
        String abs = UriUtil.AbsUriBuilder("http", host, port, 1, path);

        webClient.getAbs(abs)
                .send(ar -> {
                    if(ar.succeeded()){
                        context.response().end(ar.result().bodyAsString());
                    }
                });
    }
}
