import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    idea
}

group = "com.pe.otel"

version = "0.0.1-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_11

repositories { mavenCentral() }

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> { useJUnitPlatform() }

tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun> {
    jvmArgs =
            mutableListOf(
                    "-javaagent:$rootDir/jvm-app/opentelemetry-javaagent.jar", // !interesting: this
                    // is where we add
                    // the java agent to
                    // instrument the
                    // app. file lives
                    // locally ATM
                    // set properties in here
                    "-Dotel.exporter.otlp.traces.endpoint=http://localhost:4317"
            )
    environment =
            mapOf( // !interesting: using environment variables to configure the
                    // opentelemetry-javaagent
                    "OTEL_RESOURCE_ATTRIBUTES" to
                            "service.name=jvmapptest,service.version=1.1,deployment.environment=test",
                    // "OTEL_TRACES_EXPORTER" to "otlp",
                    // "OTEL_EXPORTER_OTLP_ENDPOINT" to "http://localhost:4317",
                    // "OTEL_EXPORTER_OTLP_TRACES_ENDPOINT" to "http://localhost:4317",
                    // "OTEL_EXPORTER_OTLP_PROTOCOL" to "grpc"

                    // "OTEL_METRICS_EXPORTER" to "otlp"
                    // "OTEL_EXPORTER_OTLP_ENDPOINT" to "http://localhost:4317",
                    )
    // configure opentelemetry-javaagent, can be done via jvmArgs or environment variables.
}
