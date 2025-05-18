package io.github.tsgrissom.bukkt.plugin

import io.github.tsgrissom.bukkt.library.command.CommandDetails
import io.github.tsgrissom.bukkt.library.command.CommandExecutionContext
import io.github.tsgrissom.bukkt.library.command.CommandExecutionHandler
import org.bukkit.ChatColor

@CommandDetails("bukkt", ["bkt", "example"], "bukkt.command.bukkt")
class BukKtCommandHandler(val plugin: BukKtExamplePlugin): CommandExecutionHandler {

    override fun onCommandExecution(context: CommandExecutionContext) {
        val version = "v${plugin.description.version}"
        context.sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "BukKt Example Plugin"))
        context.sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "Version: &e${version}"))
    }
}