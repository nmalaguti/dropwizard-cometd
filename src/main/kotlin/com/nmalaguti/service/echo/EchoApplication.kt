package com.nmalaguti.service.echo

import be.tomcools.dropwizard.websocket.WebsocketBundle
import com.nmalaguti.dropwizard.cometd.CometDBundle
import com.nmalaguti.dropwizard.cometd.DropwizardAnnotationCometDServlet
import io.dropwizard.Application
import io.dropwizard.bundles.assets.ConfiguredAssetsBundle
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment

class EchoApplication : Application<EchoConfiguration>() {

    private val cometDBundle = CometDBundle("/cometd/*")

    override fun getName(): String {
        return "hello-world"
    }

    override fun initialize(bootstrap: Bootstrap<EchoConfiguration>) {
        // nothing to do yet
        bootstrap.addBundle(cometDBundle)
        bootstrap.addBundle(ConfiguredAssetsBundle("/assets/", "/"))
    }

    override fun run(configuration: EchoConfiguration, environment: Environment) {
        // nothing to do yet
        cometDBundle.register(HelloService())
    }

    companion object {
        @Throws(Exception::class)
        @JvmStatic fun main(args: Array<String>) {
            EchoApplication().run(*args)
        }
    }
}