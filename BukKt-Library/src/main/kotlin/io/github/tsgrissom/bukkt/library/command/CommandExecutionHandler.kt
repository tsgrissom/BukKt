package io.github.tsgrissom.bukkt.library.command

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

interface CommandExecutionHandler: CommandExecutor {

    private fun getCommandDetailsAnnotation(): CommandDetails? {
        val clazz = this.javaClass
        val clazzAnnotation: CommandDetails? = clazz.getAnnotation(CommandDetails::class.java)

        var commandDetails: CommandDetails? = null

        if (clazzAnnotation != null && clazzAnnotation.name.isNotEmpty()) {
            commandDetails = clazzAnnotation
        } else {
            for (method in clazz.declaredMethods) {
                if (method.name != "onCommandExecution") {
                    continue
                }

                val methodAnnotation: CommandDetails? = method.getAnnotation(CommandDetails::class.java)

                if (methodAnnotation == null) {
                    continue
                }

                if (methodAnnotation.name.isNotEmpty()) {
                    commandDetails = clazzAnnotation
                }
            }
        }

        return commandDetails
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String?>): Boolean {
        val commandDetailsAnnotation = this.getCommandDetailsAnnotation()
        val requiredPermission = commandDetailsAnnotation?.permission ?: ""

        if (requiredPermission != "" && !sender.hasPermission(requiredPermission)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You do not have permission to do that"))
            return true
        }

        this.onCommandExecution(CommandExecutionContext(sender, command, label, args))
        return true
    }

    fun onCommandExecution(context: CommandExecutionContext)
}