/**
 * 
 */
package com.tangoe.components;

import java.util.Map;

/**
 * @author ankitkumar
 *
 */
public interface PropertyDataLoader {
    
	void startLoading();

    void stopLoading();

    Map<String, String> getProps();

}
