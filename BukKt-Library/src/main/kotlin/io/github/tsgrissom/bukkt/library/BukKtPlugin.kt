package io.github.tsgrissom.bukkt.library

import io.github.tsgrissom.bukkt.library.command.CommandDetails
import io.github.tsgrissom.bukkt.library.command.CommandExecutionHandler
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

open class BukKtPlugin: JavaPlugin() {

    fun registerCommandHandler(handler: CommandExecutionHandler) {
        val clazz = handler::class.java

        var commandDetails: CommandDetails? = null
        val clazzAnnotation: CommandDetails? = clazz.getAnnotation(CommandDetails::class.java)

        if (clazzAnnotation != null) {
            if (clazzAnnotation.name.isEmpty())
                throw Error("Failed to register command handler: Class CommandDetails annotation cannot have an empty command name")

            commandDetails = clazzAnnotation
        } else {
            // Fallback to CommandDetails annotation attached to onCommandExecution method

            for (method in clazz.declaredMethods) {
                if (method.name != "onCommandExecution") continue

                val methodAnnotation: CommandDetails? = method.getAnnotation(CommandDetails::class.java)
                if (methodAnnotation != null) {
                    if (methodAnnotation.name.isEmpty())
                        throw Error("Failed to register command handler: Method CommandDetails annotation cannot have an empty command name")

                    commandDetails = methodAnnotation
                }
            }
        }

        if (commandDetails == null)
            throw Error("Failed to register command handler: Could not find a CommandDetails annotation")

        val annotatedName = commandDetails.name
        val annotatedAliases = commandDetails.aliases
        val annotatedPermission = commandDetails.permission

        if (annotatedName.isEmpty())
            throw Error("Failed to register command handler: Empty command names cannot be registered")

        val command = this.getCommand(annotatedName)

        if (command == null)
            throw Error("Failed to register command handler: Command \"${annotatedName}\" is not in the plugin.yml")

        command.setExecutor(handler)
        command.aliases = annotatedAliases.toList()

        if (annotatedPermission.isNotEmpty()) {
            command.permission = annotatedPermission
        }

        Bukkit.getLogger().info("[BukKt] Registered plugin command \"${command.name}\" for plugin \"${this.description.name}\"")
    }
}