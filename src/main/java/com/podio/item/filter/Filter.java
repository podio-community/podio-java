package com.podio.item.filter;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Filter<VALUES> {

    private final String key;

    public Filter(String key) {
        this.key = key;
    }

    @JsonProperty("values")
    public abstract VALUES getValues();

    @JsonProperty("key")
    public String getKey() {
        return key;
    }
}
