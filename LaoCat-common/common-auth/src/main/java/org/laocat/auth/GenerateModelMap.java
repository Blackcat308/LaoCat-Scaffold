package org.laocat.auth;

import org.springframework.ui.ModelMap;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description
 */
public class GenerateModelMap {

    public static ModelMap generateMap(Integer value, String message) {
        ModelMap modelMap = new ModelMap();
        modelMap.put("code", value);
        modelMap.put("message", message);
        return modelMap;
    }
}
