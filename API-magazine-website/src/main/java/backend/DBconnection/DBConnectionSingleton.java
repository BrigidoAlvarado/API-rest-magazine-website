/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DBconnection;

import java.sql.Connection;
import java.sql.SQLException;
import backend.exception.ServerException;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/**
 *
 * @author brigidoalvarado
 */
public class DBConnectionSingleton {
    
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DATABASE = "magazine_website";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static final String URL_MYSQL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
    
    private static DBConnectionSingleton  onlyDBConnection;
    
    private DataSource dataSource;
    
    private DBConnectionSingleton() throws ServerException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            PoolProperties p = new PoolProperties();
            p.setUrl(URL_MYSQL);
            p.setDriverClassName("com.mysql.cj.jdbc.Driver");
            p.setUsername(USER);
            p.setPassword(PASSWORD);
            p.setJmxEnabled(true);
            p.setTestWhileIdle(false);
            p.setTestOnBorrow(true);
            p.setValidationQuery("SELECT 1");
            p.setTestOnReturn(false);
            p.setValidationInterval(30000);
            p.setTimeBetweenEvictionRunsMillis(30000);
            p.setMaxActive(50);
            p.setInitialSize(5);
            p.setMaxWait(10000);
            p.setRemoveAbandonedTimeout(60);
            p.setMinEvictableIdleTimeMillis(30000);
            p.setMinIdle(10);
            p.setLogAbandoned(true);
            p.setRemoveAbandoned(true);
            p.setJdbcInterceptors(
                    "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
                    + "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
            dataSource = new DataSource();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServerException("Error al crear el pool de conecciones");
        }
    }
    
    public static DBConnectionSingleton getInstance() throws ServerException{
        if (onlyDBConnection == null) {
            onlyDBConnection = new DBConnectionSingleton();
        }
        return onlyDBConnection;
    }
    
    public Connection getConnection() throws ServerException{
        try {
        return  dataSource.getConnection();            
        } catch (SQLException e) {
            throw new ServerException("Error al crear la coneccion");
        }
    }
}
