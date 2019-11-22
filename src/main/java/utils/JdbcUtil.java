package utils;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.ext.jdbc.JDBCClient;


public class JdbcUtil {
    private JDBCClient jdbcClient;

    public JdbcUtil(Vertx vertx){
        JsonObject dbConfig = new JsonObject();

        jdbcClient = JDBCClient
                .createShared(io.vertx.rxjava.core.Vertx.newInstance(vertx)
                        , dbConfig);
    }

    public JDBCClient getJdbcClient(){
        return jdbcClient;
    }
}
