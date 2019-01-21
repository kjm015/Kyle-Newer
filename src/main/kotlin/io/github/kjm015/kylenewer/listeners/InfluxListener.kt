package io.github.kjm015.kylenewer.listeners

import net.dv8tion.jda.client.events.group.GroupUserJoinEvent
import net.dv8tion.jda.client.exceptions.VerificationLevelException
import net.dv8tion.jda.core.events.guild.GuildUnbanEvent
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.core.exceptions.InsufficientPermissionException
import net.dv8tion.jda.core.hooks.ListenerAdapter
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * This class handles events where users join a server or are unbanned from it.
 * Eventually, this class should implement a message template service for its
 * messages, as this allows for a greater variety of messages.
 *
 * TODO: Implement templates for outgoing messages
 *
 * @author kjm015
 * @since 01/11/2019
 */
class InfluxListener : ListenerAdapter() {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * This method gets called when a user joins a group chat with New Kyle.
     * This should probably not be used, as New Kyle is meant to be for servers
     * and not for group chats.
     *
     * @param event - an event where a user joins a group chat
     */
    override fun onGroupUserJoin(event: GroupUserJoinEvent) {
        super.onGroupUserJoin(event)

        log.info("User \"${event.user.name}\" has joined a group with New Kyle.")
        try {
            event.group.sendMessage("Hey, ${event.user.name} just joined.")
                    .queue()
        } catch (e: Exception) {
            log.error("Could not post messages to group ${event.group}:\n$e")
        }
    }

    /**
     * This method gets called when a user joins a guild/server.
     * He will notify said server, and will tell the guild the name of the user that joined.
     *
     * @param event - an event where a user joins a guild or server
     */
    override fun onGuildMemberJoin(event: GuildMemberJoinEvent) {
        super.onGuildMemberJoin(event)

        log.info("User \"${event.member.effectiveName}\" just joined guild \"${event.guild.name}\".")

        try {
            event.guild.getTextChannelById("general")
                    .sendMessage("Hey guys, someone named \"${event.member.effectiveName}\" just joined the server.")
                    .queue()
        } catch (e: NullPointerException) {
            log.warn("General channel in ${event.guild} not found:\n$e")
        } catch (e: InsufficientPermissionException) {
            log.warn("New Kyle does not have permission to post in general channel in ${event.guild}:\n$e")
        } catch (e: VerificationLevelException) {
            log.warn("New Kyle does not meet verification requirements in ${event.guild}:\n$e")
        }
    }

    /**
     * This method gets called when a user gets unbanned in a server that New Kyle is in.
     * He will notify the server of the event with the name of the person that was unbanned.
     *
     * @param event - an event where a user was unbanned from a guild
     */
    override fun onGuildUnban(event: GuildUnbanEvent) {
        super.onGuildUnban(event)

        log.info("User \"${event.user.name}\" has been unbanned from guild \"${event.guild.name}\".")

        try {
            event.guild.getTextChannelById("general")
                    .sendMessage("Good news, people. That dude named ${event.user.name} just got unbanned from the server.")
                    .queue()
        } catch (e: NullPointerException) {
            log.warn("General channel in ${event.guild} not found:\n$e")
        } catch (e: InsufficientPermissionException) {
            log.warn("New Kyle does not have permission to post in general channel in ${event.guild}:\n$e")
        } catch (e: VerificationLevelException) {
            log.warn("New Kyle does not meet verification requirements in ${event.guild}:\n$e")
        }
    }

}