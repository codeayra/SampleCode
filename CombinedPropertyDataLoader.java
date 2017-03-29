/**
 * 
 */
package com.tangoe.components;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ankitkumar
 *
 */
public class CombinedPropertyDataLoader implements PropertyDataLoader {

	private JDBCPropertyDataLoader jdbcPropertyDataLoader;
    private FilePropertyDataLoader filePropertyDataLoader;


    public CombinedPropertyDataLoader(JDBCPropertyDataLoader jdbcPropertyDataLoader,
                                      FilePropertyDataLoader filePropertyDataLoader) {

        this.jdbcPropertyDataLoader = jdbcPropertyDataLoader;
        this.filePropertyDataLoader = filePropertyDataLoader;

    }

    @Override
    public void startLoading() {
    	jdbcPropertyDataLoader.startLoading();
    	filePropertyDataLoader.startLoading();

    }

    @Override
    public void stopLoading() {
    	jdbcPropertyDataLoader.stopLoading();
    	filePropertyDataLoader.stopLoading();
    }

    @Override
    public Map<String, String> getProps() {
        Map<String, String> props = new HashMap<>();
        //Database property takes precedence in case of clash.
        props.putAll(filePropertyDataLoader.getProps());
        props.putAll(jdbcPropertyDataLoader.getProps());
        return props;
    }

}
