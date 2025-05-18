package io.github.tsgrissom.bukkt.library.command

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player

data class CommandExecutionContext(
    val sender: CommandSender,
    val command: Command,
    val label: String,
    val args: Array<out String?>
) {
    fun isSenderConsole(): Boolean {
        return sender is ConsoleCommandSender
    }

    fun isSenderPlayer(): Boolean {
        return sender is Player
    }

    fun isSenderConsoleOrPlayer(): Boolean {
        return (sender is ConsoleCommandSender || sender is Player)
    }
}