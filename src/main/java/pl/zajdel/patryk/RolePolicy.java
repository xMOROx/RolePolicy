package pl.zajdel.patryk;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;

import java.io.File;
import java.io.IOException;

public class RolePolicy {
    public static boolean verifyResources(String json) {
        return false;
    }

    public static boolean verifyJsonFormat(File file) throws IOException {
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(RolePolicy.class.getResourceAsStream("/role-policy-schema.json"));
        JsonNode jsonNode = new ObjectMapper().readTree(file);
        return schema.validate(jsonNode).isEmpty();
    }
}