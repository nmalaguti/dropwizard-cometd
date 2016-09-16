package com.nmalaguti.service.echo

import org.cometd.annotation.Service
import org.cometd.bayeux.server.BayeuxServer
import javax.inject.Inject

/**
 * Created by nmalaguti on 8/7/16.
 */
@Service("echo")
class EchoService {

    @Inject
    lateinit private var bayeux: BayeuxServer


}