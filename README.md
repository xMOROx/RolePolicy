# RolePolicy

Simple task to verify https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-iam-role-policy.html

The format for `PolicyDocument` is get from

- https://docs.aws.amazon.com/IAM/latest/UserGuide/access_policies.html#access_policies-json
- https://docs.aws.amazon.com/IAM/latest/UserGuide/reference_policies_elements.html

but without advanced features like `NotResource` or `NotPrincipal`.

The schema was defined in `src/main/resources/schemas/RolePolicy.json` using JsonSchema.

## Prerequisites

- Java 21
- Maven 4.0.0

## How to run

```mvn clean compile exec:java```

You need no provide path to a file with valid json format or choose `default` to use the default file.
Some of defined files are in `src/main/resources/examples` folder.

## Tests
The tests are in `src/test/java` folder and can be run with the following command:

```mvn clean test```