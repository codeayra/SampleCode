package com.tangoe.components;

import java.util.Map;


public class PropertyHelper {

	 private Map<String, String> props;


	    public PropertyHelper(PropertyDataLoader propertyDataLoader) {
	        this.props = propertyDataLoader.getProps();
	    }


	    public String getStringProperty(String propName, String defaultVal) {
	        String propVal = props.get(propName);
	        return propVal != null ? propVal : defaultVal;
	    }
}
