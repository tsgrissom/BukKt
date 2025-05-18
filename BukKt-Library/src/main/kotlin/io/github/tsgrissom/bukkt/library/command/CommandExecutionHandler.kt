package io.github.tsgrissom.bukkt.library.command

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

interface CommandExecutionHandler: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String?>): Boolean {
        this.onCommandExecution(CommandExecutionContext(sender, command, label, args))
        return true
    }

    fun onCommandExecution(context: CommandExecutionContext)
}