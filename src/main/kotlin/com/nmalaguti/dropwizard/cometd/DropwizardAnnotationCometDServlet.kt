package com.nmalaguti.dropwizard.cometd

import org.cometd.annotation.AnnotationCometDServlet
import org.cometd.annotation.ServerAnnotationProcessor
import javax.servlet.ServletException

/**
 * Created by nmalaguti on 8/7/16.
 */
class DropwizardAnnotationCometDServlet : AnnotationCometDServlet() {

    @Volatile
    private var processor: ServerAnnotationProcessor? = null

    private val servicesToRegister = mutableListOf<Any>()

    override fun init() {
        super.init()
        processor = newServerAnnotationProcessor(bayeux)

        servicesToRegister.forEach {
            processor!!.process(it)
            if (_logger.isDebugEnabled)
                _logger.debug("Processed annotated service $it")
            services.add(it)
            registerService(it)
        }
    }

    fun register(service: Any) = servicesToRegister.add(service)
}