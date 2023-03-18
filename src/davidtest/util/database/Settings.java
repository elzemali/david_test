
package davidtest.util.database;

/**
 * Contient juste la configuration de la base de données
 * @author lenovo
 */
public class Settings {
    private static final String DEFAULT_SCHEMA;
    private static final String PROJECT_URL;
    private static final String DISTINATION_MYSQL;
    private static final String DISTINATION_URL_MYSQL;
    private static final String USERNAME;
    private static final String PASSWORD;
    
    static {
        DISTINATION_MYSQL = "localhost";
        USERNAME = "root";
        PASSWORD = "";
        DISTINATION_URL_MYSQL = "D:\\wamp64\\bin\\mysql\\mysql5.7.31";
        DEFAULT_SCHEMA = "test_rec";
        PROJECT_URL = "C:\\Users\\lenovo\\Documents\\NetBeansProjects\\DavidTest\\src";

    }

    public static String getDISTINATION_MYSQL() {
        return DISTINATION_MYSQL;
    }

    public static String getPROJECT_URL() {
        return PROJECT_URL;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getDISTINATION_URL_MYSQL() {
        return DISTINATION_URL_MYSQL;
    }

    public static String getDEFAULT_SCHEMA() {
        return DEFAULT_SCHEMA;
    }
}
