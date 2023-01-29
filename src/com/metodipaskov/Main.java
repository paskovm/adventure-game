package com.metodipaskov;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Location> locations = new ArrayList<>();

    public static void main(String[] args) {
        loadGame();

        Scanner scanner = new Scanner(System.in);
        String input;
        Location currentLocation = locations.get(0);


        System.out.println(currentLocation.getLocationDescription());
        System.out.println("Do you want to start your journey? (Y/N): ");
        input = scanner.nextLine();

        if (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("yes")) {
            System.exit(0);
        }

        currentLocation = locations.get(1);
        System.out.println(currentLocation.getLocationDescription());

        while (true) {
            System.out.print("Available options are:");
            currentLocation.printExists();

            System.out.println("Enter your choice: ");
            input = scanner.nextLine().toUpperCase();

            if (input.equals("Q")) {
                break;
            }

            if (input.length() > 1) {
                System.out.println("Wrong choice selected!");

            } else {
                Integer exit = currentLocation.getExists().getOrDefault(input, null);
                if (exit != null) {
                    currentLocation = locations.get(exit);
                    System.out.println(currentLocation.getLocationDescription());
                } else {
                    System.out.println("Wrong choice selected!");
                }
            }
        }

    }

    private static void loadGame() {
        try (BufferedReader br1 = new BufferedReader(new FileReader(".\\resources\\locations.txt"));
             BufferedReader br2 = new BufferedReader(new FileReader(".\\resources\\directions.txt"))) {

            collectLocations(br1);
            collectDirections(br2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void collectLocations(BufferedReader bufferedReader) throws IOException {
        Scanner scanner = new Scanner(bufferedReader);
        scanner.useDelimiter(",");

        while (scanner.hasNext()) {
            int locationId = scanner.nextInt();
            scanner.skip(scanner.delimiter());
            String description = scanner.nextLine();

            locations.add(new Location(locationId, description));
        }
    }

    private static void collectDirections(BufferedReader bufferedReader) throws IOException {
        String input;

        while ((input = bufferedReader.readLine()) != null) {
            int locationId = Integer.parseInt(input.substring(0, input.indexOf(",")));
            String direction = input.substring(input.indexOf(",") + 1, input.indexOf(",", input.indexOf(",") + 1));
            int nextLocation = Integer.parseInt(input.substring((input.indexOf(",", input.indexOf(",") + 1)) + 1));

            for (Location location : locations) {
                if (location.getLocationId() == locationId) {
                    location.addExist(direction, nextLocation);
                    break;
                }
            }
        }
    }
}