package com.metodipaskov;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Location {

    private int locationId;
    private String locationDescription;
    private Map<String, Integer> exists;
    private Map<String, String> directionDictionary = new HashMap<>();

    public Location(int locationId, String locationDescription) {
        this.locationId = locationId;
        this.locationDescription = locationDescription;
        this.exists = new TreeMap<>();
        this.exists.put("Q", 0);

        directionDictionary.put("U", "UP(U)");
        directionDictionary.put("D", "DOWN(D)");
        directionDictionary.put("N", "NORTH(N)");
        directionDictionary.put("E", "EAST(E)");
        directionDictionary.put("W", "WEST(W)");
        directionDictionary.put("S", "SOUTH(S)");
        directionDictionary.put("Q", "QUIT(Q)");
    }

    public int getLocationId() {
        return locationId;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public Map<String, Integer> getExists() {
        return exists;
    }

    public void addExist(String direction, int location) {
        exists.put(direction, location);
    }

    public void printExists() {
        String value = "";
        for (Map.Entry<String, Integer> exit : exists.entrySet()){
            String key = exit.getKey();

            value += value.isEmpty() ? " " + directionDictionary.get(key) : ", " + directionDictionary.get(key);
        }
        System.out.println(value);
    }
}
