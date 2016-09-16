package com.nmalaguti.dropwizard.cometd

import be.tomcools.dropwizard.websocket.WebsocketBundle
import io.dropwizard.ConfiguredBundle
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment

/**
 * Created by nmalaguti on 8/7/16.
 */
class CometDBundle(val servletPath: String) : ConfiguredBundle<CometDBundleConfiguration> {

    private val cometDServlet = DropwizardAnnotationCometDServlet()

    override fun run(configuration: CometDBundleConfiguration, environment: Environment) {
        val registration = environment.servlets().addServlet("cometd", cometDServlet)
        registration.setInitParameter("ws.cometdURLMapping", servletPath)
        registration.addMapping(servletPath)
        registration.setAsyncSupported(true)
    }

    override fun initialize(bootstrap: Bootstrap<*>) {
        bootstrap.addBundle(WebsocketBundle())
    }

    fun register(service: Any) = cometDServlet.register(service)
}