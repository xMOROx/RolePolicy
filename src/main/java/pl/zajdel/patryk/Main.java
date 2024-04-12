package pl.zajdel.patryk;


import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JsonFile file = null;

        do {
            System.out.println("Enter the path to the file or type 'default': ");
            String path = scanner.nextLine();
            if (path.equalsIgnoreCase("default")) {
                file = JsonFile.fromPath("src/main/resources/ValidPolicy.json").get();
                break;
            }

            try {
                file = JsonFile.fromPath(path).orElse(null);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid extension. Try again.");
                continue;
            }

            if (file == null) {
                System.out.println("File not found. Try again.");
            }


        } while (file == null);

        RolePolicy rolePolicy;

        try {
            rolePolicy = RolePolicy.fromJsonWithGivenSchema(file, "RolePolicy.json");

        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("The resources verification result: " + rolePolicy.verifyResources());


    }
}