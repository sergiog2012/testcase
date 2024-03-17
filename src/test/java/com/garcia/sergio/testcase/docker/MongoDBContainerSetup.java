package com.garcia.sergio.testcase.docker;

import lombok.Data;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

@Data
public abstract class MongoDBContainerSetup {

    private static final String MONGO_IMAGE_VERSION = "mongo:7";
    static final GenericContainer<?> mongoDbContainer;

    static {
        mongoDbContainer = new GenericContainer<>(
                DockerImageName.parse(MONGO_IMAGE_VERSION))
                .withExposedPorts(27017)
                .withCopyFileToContainer(MountableFile.forClasspathResource("./docker/schema.js")
                        , "/docker-entrypoint-initdb.d/init-script.js");

        mongoDbContainer.start();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.host", mongoDbContainer::getHost);
        registry.add("spring.data.mongodb.port", mongoDbContainer::getFirstMappedPort);
        registry.add("spring.data.mongodb.database", () -> "test");
    }
}
