package io.github.kjm015.kylenewer

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

/**
 * This class sets the properties for this instance of the Discord bot, stored in the application's
 * property file. I mean, you could store the values in here directly, but that would be stupid.
 *
 * Don't do it.
 *
 * @author kjm015
 * @since 7/26/2018
 */
@Configuration
@PropertySource("classpath:application.properties")
class DiscordSettings {

    @Value("\${discord.oauth}")
    val oauth: String? = null

    @Value("\${discord.game}")
    val game: String? = null

    @Value("\${discord.owner}")
    val owner: String? = null

    @Value("\${discord.prefix}")
    val prefix: String? = null

}