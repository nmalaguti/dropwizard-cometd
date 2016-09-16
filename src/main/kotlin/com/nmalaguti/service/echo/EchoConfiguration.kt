package com.nmalaguti.service.echo

import com.fasterxml.jackson.annotation.JsonProperty
import com.nmalaguti.dropwizard.cometd.CometDBundleConfiguration
import com.nmalaguti.dropwizard.cometd.CometDConfiguration
import io.dropwizard.Configuration
import io.dropwizard.bundles.assets.AssetsBundleConfiguration
import io.dropwizard.bundles.assets.AssetsConfiguration
import org.hibernate.validator.constraints.NotEmpty
import javax.validation.Valid
import javax.validation.constraints.NotNull

class EchoConfiguration : Configuration(), AssetsBundleConfiguration, CometDBundleConfiguration {

    @NotEmpty
    var template: String? = null

    @NotEmpty
    var defaultName = "Stranger"

    @Valid
    @NotNull
    @JsonProperty
    private val assets = AssetsConfiguration.builder().build()

    @Valid
    @NotNull
    @JsonProperty
    private val cometd = CometDConfiguration()

    override fun getAssetsConfiguration(): AssetsConfiguration = assets

    override fun getCometDConfiguration(): CometDConfiguration = cometd
}