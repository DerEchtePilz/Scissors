import java.util.Locale

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.4.0"
}

if (!file(".git").exists()) {
    val errorText = """
        
        =====================[ ERROR ]=====================
         The Scissors project directory is not a properly cloned Git repository.
         
         In order to build Scissors from source you must clone
         the Scissors repository using Git, not download a code
         zip from GitHub.
         
         See <coming soon>
         for further information on building and modifying Paper and Forks
         just like this.
        ===================================================
    """.trimIndent()
    error(errorText)
}

rootProject.name = "Scissors"

for (name in listOf("scissors-api", "scissors-server")) {
    val projName: String = name.toLowerCase(Locale.ENGLISH)
    include(projName)
    findProject(":$projName")!!.projectDir = file(name)
}
