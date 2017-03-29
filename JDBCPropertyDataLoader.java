/**
 * 
 */
package com.tangoe.components;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

/**
 * @author ankitkumar
 *
 */
public class JDBCPropertyDataLoader implements PropertyDataLoader,Runnable {

	private volatile Map<String, String> props;
    private DataSource dataSource;
    private ScheduledExecutorService es;
    private volatile boolean isLoaded;
    private static JDBCPropertyDataLoader instance;

    private JDBCPropertyDataLoader(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public static JDBCPropertyDataLoader getInstance(DataSource datasource){
    	if(instance == null){
            synchronized (JDBCPropertyDataLoader.class) {
                if(instance == null){
                    instance = new JDBCPropertyDataLoader(datasource);
                }
            }
        }
        return instance;
    	
    }

    private synchronized void loadData() {

        Map<String, String> props = new HashMap<String, String>();
        try {
            Connection con = dataSource.getConnection();
            String sql = "select prop_name,prop_value from property_table";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String propName = rs.getString("prop_name");
                String propValue = rs.getString("prop_value");

                props.put(propName, propValue);
            }
            this.props = Collections.unmodifiableMap(props);
        } catch (SQLException exp) {
            throw new RuntimeException("Unable to load properties from DB.");
        }

    }

    @Override
    public synchronized void startLoading() {
    	if(!isLoaded){
    		loadData();
            ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
            this.es = es;
            es.schedule(this, 10, TimeUnit.MINUTES);
            isLoaded=true; 
    	}
        
    }

    @Override
    public synchronized void stopLoading() {
    	if(isLoaded){
    		 es.shutdown();
    	}
    }

    @Override
    public Map<String, String> getProps() {
    	if(isLoaded){
    		return props;
    	}else{
    		throw new IllegalStateException("Properties not loaded");
    	}
        
    }

    @Override
    public void run() {
        loadData();

    }
    
}
