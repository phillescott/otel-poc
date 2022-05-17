import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.1" apply false
	id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
	kotlin("jvm") version "1.6.0" apply false
	kotlin("plugin.spring") version "1.6.0" apply false
	id("com.github.node-gradle.node") version "3.0.0" apply false
	id("com.linkedin.python-sdist") version "0.3.9" apply false
}

group = "com.pe.otel"
version = "0.0.1-SNAPSHOT"
