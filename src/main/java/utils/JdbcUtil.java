package utils;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;

import static constants.MySqlConstant.*;


public class JdbcUtil {
    private static JDBCClient dbClient;

    public JdbcUtil(Vertx vertx){
        JsonObject dbConfig = new JsonObject();

        dbConfig.put("url", DB_URL);
        dbConfig.put("user", DB_USER);
        dbConfig.put("password", DB_PASSWORD);

        dbClient = JDBCClient
                .createShared(vertx, dbConfig);
    }

    public static JDBCClient getJdbcClient(){
        return dbClient;
    }
}
