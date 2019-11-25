package utils;

public class UriUtil {
    public static String AbsUriBuilder(String protocol, String host, String port, int version, String path){
        return protocol + "://" + host + ":" + port + "/v" + String.valueOf(version) + path;
    }
}
