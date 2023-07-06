package org.laocat.security.serializer.mixin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @description: HashSet Mixin
 * @author: LaoCat
 * @date: 2023/7/6
 */
public abstract class HashSetMixin {
    @JsonCreator
    public HashSetMixin(@JsonProperty("elements") Object elements) {

    }

    @JsonProperty("elements")
    public abstract Object getElements();

}
