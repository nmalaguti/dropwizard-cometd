package com.nmalaguti.service.echo

import org.cometd.annotation.Listener
import org.cometd.annotation.Service
import org.cometd.annotation.Session
import org.cometd.bayeux.server.BayeuxServer
import org.cometd.bayeux.server.ServerMessage
import org.cometd.bayeux.server.ServerSession
import java.util.*
import javax.inject.Inject

/**
 * Created by nmalaguti on 8/7/16.
 */
@Service("hello")
class HelloService {

    @Inject
    lateinit private var bayeux: BayeuxServer

    @Session
    lateinit private var serverSession: ServerSession

    @Listener("/service/hello")
    fun hello(remote: ServerSession, message: ServerMessage.Mutable) {
        val input = message.dataAsMap
        val name = input["name"] as String

        val output = HashMap<String, Any>()
        output.put("greeting", "Hello, " + name)
        remote.deliver(serverSession, "/hello", output)
    }
}