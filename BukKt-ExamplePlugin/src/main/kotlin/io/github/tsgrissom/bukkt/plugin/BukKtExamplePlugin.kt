package io.github.tsgrissom.bukkt.plugin

import io.github.tsgrissom.bukkt.library.BukKtPlugin

class BukKtExamplePlugin: BukKtPlugin() {

    override fun onEnable() {
        val bukktCommandHandler = BukKtCommandHandler(this)
        registerCommandHandler(bukktCommandHandler)
    }
}