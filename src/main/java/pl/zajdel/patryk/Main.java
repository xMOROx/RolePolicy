package pl.zajdel.patryk;


import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/validPolicy.json");
        System.out.println(RolePolicy.verifyJsonFormat(file, "role-policy-schema.json"));
    }
}