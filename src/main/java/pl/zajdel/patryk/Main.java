package pl.zajdel.patryk;


import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/resources/validPolicy.json");
        RolePolicy rolePolicy = RolePolicy.fromJsonWithGivenSchema(file, "role-policy-schema.json");
        System.out.println(rolePolicy.verifyResources());
    }
}