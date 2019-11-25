package handlers;


import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

import static constants.MySqlConstant.*;
import static constants.RequestConstant.*;

public class DatabaseHandler {
    public static void saveClusterConfig(RoutingContext context, JDBCClient client){
        client.getConnection(conn -> {
            if (conn.failed()) {
                throw new RuntimeException(conn.cause());
            }

            final SQLConnection connection = conn.result();
            connection.execute(SQL_CREATE_TABLE, res -> {
                if (res.failed()) {
                    throw new RuntimeException(res.cause());
                }
                connection.updateWithParams(SQL_INSERT,
                        new JsonArray().add(context.request().getParam(RPC_ADDR))
                                       .add(context.request().getParam(REST_PORT))
                                       .add(context.request().getParam(CLUSTER_NAME)),
                        ar->{
                            connection.close();
                            if(ar.failed()){
                                throw new RuntimeException(ar.cause());
                            }else{
                                context.response().end("Successfully write cluster configuration into database.");
                                Future.succeededFuture();
                            }
                });
            });
        });
    }

    public static void getClusterList(RoutingContext context, JDBCClient client){
        client.getConnection(conn -> {
            if (conn.failed()) {
                throw new RuntimeException(conn.cause());
            }

            final SQLConnection connection = conn.result();
            connection.query(SQL_GET_CLUSTER_LIST, res -> {
                if (res.failed()) {
                    throw new RuntimeException(res.cause());
                }
                List<JsonObject> rows = res.result().getRows();
                context.response().end(rows.toString());
            });
        });
    }
}
