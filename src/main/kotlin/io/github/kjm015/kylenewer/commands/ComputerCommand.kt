package io.github.kjm015.kylenewer.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import io.github.kjm015.kylenewer.util.generateIntelProcessor
import io.github.kjm015.kylenewer.util.generateNvidiaCard
import io.github.kjm015.kylenewer.util.generateRyzenProcessor
import kotlin.random.Random

class ComputerCommand : Command() {

    init {
        this.name = "computer"
        this.help = "Kyle will spit out a computer for you."
        this.aliases = arrayOf("cpu", "gpu", "pc")
        this.guildOnly = false
        this.cooldown = 3
    }

    override fun execute(event: CommandEvent) {
        val gpu = generateNvidiaCard()

        val cpu = if (Random.nextBoolean())
            generateIntelProcessor()
        else
            generateRyzenProcessor()

        event.reply("I would get a PC with ${gpu.toString().withArticle()} and ${cpu.toString().withArticle()}.")
    }
}

fun String.withArticle(): String {
    return if (
            this.startsWith(prefix = "a", ignoreCase = true) ||
            this.startsWith(prefix = "e", ignoreCase = true) ||
            this.startsWith(prefix = "i", ignoreCase = true) ||
            this.startsWith(prefix = "o", ignoreCase = true) ||
            this.startsWith(prefix = "u", ignoreCase = true)
    )
        "an $this"
    else
        "a $this"
}