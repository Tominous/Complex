package io.brightskyz.complex.helpers;

import java.util.HashMap;
import java.util.Map;

public class Cache {

    private Map<String, CacheShard> shardMap;

    public Cache() {
        this.shardMap = new HashMap<>();
    }

    public CacheShard getShard(String name) {
        if (!shardMap.containsKey(name)) {
            shardMap.put(name, new CacheShard());
        }
        return shardMap.get(name);
    }

    public class CacheShard {

        private Map<String, Object> shardData;

        public CacheShard() {
            this.shardData = new HashMap<>();
        }

        public boolean has(String name) {
            return shardData.containsKey(name);
        }

        public void set(String name, Object value) {
            if (shardData.containsKey(name)) {
                shardData.replace(name, value);
            } else {
                shardData.put(name, value);
            }
        }

        public void remove(String name) {
            if (shardData.containsKey(name)) {
                shardData.remove(name);
            }
        }

        public Object getObject(String name) {
            return shardData.getOrDefault(name, null);
        }

        public String getString(String name) {
            return (String) shardData.getOrDefault(name, null);
        }

        public int getInteger(String name) {
            return (int) shardData.getOrDefault(name, 0);
        }

        public boolean getBoolean(String name) {
            return (boolean) shardData.getOrDefault(name, false);
        }
    }
}
