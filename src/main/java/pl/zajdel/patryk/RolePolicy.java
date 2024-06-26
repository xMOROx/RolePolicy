package pl.zajdel.patryk;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;

import java.io.IOException;
import java.util.List;

public class RolePolicy {
    private final JsonNode policy;

    private RolePolicy(JsonNode policy) {
        this.policy = policy;
    }

    public static boolean verifyJsonFormat(JsonFile jsonFile, String schemaName) throws IOException {
        JsonSchema schema = JsonSchemaFactory
                .getInstance(SpecVersion.VersionFlag.V4)
                .getSchema(RolePolicy.class.getResourceAsStream("/schemas/" + schemaName));

        JsonNode jsonNode = new ObjectMapper().readTree(jsonFile.getFile());

        return schema.validate(jsonNode).isEmpty();
    }

    public static RolePolicy fromJsonWithGivenSchema(JsonFile jsonFile, String schemaName) throws IOException, IllegalArgumentException {
        if (verifyJsonFormat(jsonFile, schemaName)) {
            return new RolePolicy(new ObjectMapper().readTree(jsonFile.getFile()));
        }
        throw new IllegalArgumentException("Invalid policy");
    }

    public boolean verifyResources() {
        List<JsonNode> resources = getResources();
        boolean result = true;

        for (JsonNode resource : resources) {
            result = categorizeResources(resource);
            if (!result) {
                break;
            }
        }

        return result;
    }

    private boolean categorizeResources(JsonNode resource) {
        if (resource.isArray()) {
            return verifyResourceDefinedAsArray(resource);
        }
        return verifyResourceDefinedAsString(resource);
    }

    private boolean verifyResourceDefinedAsArray(JsonNode resource) {
        for (JsonNode node : resource) {
            if (node.toString().equals("\"*\"")) {
                return false;
            }
        }
        return true;
    }

    private boolean verifyResourceDefinedAsString(JsonNode resource) {
        return !resource.toString().equals("\"*\"");
    }

    private List<JsonNode> getResources() {
        JsonNode PolicyDocument = policy.get("PolicyDocument");
        JsonNode Statement = PolicyDocument.get("Statement");
        return Statement.findValues("Resource");
    }
}
