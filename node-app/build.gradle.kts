import com.github.gradle.node.npm.task.NpmTask
import com.github.gradle.node.task.NodeTask

// This file shows how to use this plugin with the Kotlin DSL.
// All the properties are set, most of the time with the default value.
// /!\ We recommend to set only the values for which the default value is not satisfying.

plugins {

    id("com.github.node-gradle.node")
}
//https://dzone.com/articles/integrating-java-and-npm-builds-using-gradle
node {
    version.set("16.13.1")
    npmVersion.set("8.2.0")
    download.set(true)
}

tasks.npmInstall {
    nodeModulesOutputFilter {
        exclude("notExistingFile")
    }
}

val testTaskUsingNpm = tasks.register<NpmTask>("testNpm") {
    dependsOn(tasks.npmInstall)
    npmCommand.set(listOf("run", "test"))
    args.set(listOf("test"))
    ignoreExitValue.set(false)
    environment.set(mapOf("MY_CUSTOM_VARIABLE" to "hello"))
    workingDir.set(projectDir)
    execOverrides {
        standardOutput = System.out
    }
    inputs.dir("node_modules")
    inputs.file("package.json")
    inputs.dir("src")
    inputs.dir("test")
    outputs.upToDateWhen {
        true
    }
}

tasks.register<NodeTask>("run") {
//    dependsOn(testTaskUsingNpm)
    dependsOn(tasks.npmInstall)
    script.set(file("src/server.js"))
    args.set(listOf("Bobby"))
    ignoreExitValue.set(false)
    environment.set(mapOf("MY_CUSTOM_VARIABLE" to "hello"))
    workingDir.set(projectDir)
    execOverrides {
        standardOutput = System.out
    }
    inputs.dir("src")
    outputs.upToDateWhen {
        false
    }
}

val buildTaskUsingNpm = tasks.register<NpmTask>("buildNpm") {
    dependsOn(tasks.npmInstall)
    npmCommand.set(listOf("run", "build"))
    args.set(listOf("--", "--out-dir", "${buildDir}/npm-output"))
    inputs.dir("src")
    outputs.dir("${buildDir}/npm-output")
}

tasks.register<Zip>("package") {
    archiveFileName.set("app.zip")
    destinationDirectory.set(buildDir)
    from(buildTaskUsingNpm) {
        into("npm")
    }
}