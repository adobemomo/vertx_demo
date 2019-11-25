package constants;

public class MySqlConstant {
    public final static String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS `t_cluster`( " +
            "   `rpc_address` VARCHAR(40) NOT NULL, " +
            "   `rest_port` VARCHAR(40) NOT NULL, " +
            "   `cluster_name` VARCHAR(40) NOT NULL, " +
            "   PRIMARY KEY ( `cluster_name` ) " +
            ")";
    public final static String SQL_INSERT = "INSERT INTO t_cluster values (?, ?, ?)";
    public final static String SQL_GET_CLUSTER_LIST = "SELECT * FROM t_cluster";

    public final static String DB_URL = "jdbc:mysql://localhost:3306/employees";
    public final static String DB_USER = "adobemomo";
    public final static String DB_PASSWORD = "111111";
}
