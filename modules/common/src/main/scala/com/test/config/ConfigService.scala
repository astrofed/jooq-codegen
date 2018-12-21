package com.test.config

import java.io.File

import com.typesafe.config.{Config, ConfigFactory}
import pureconfig.Derivation._
import pureconfig._
import pureconfig.generic.auto._
import scala.collection.JavaConverters._

/**
  * Created by <Chekhonadskikh, Fedor> on 12/14/18 17:42 AM
  */
object ConfigService {

  private final lazy val CONF_NAME: String = "config"

  private lazy val configPath = sys.props.get(CONF_NAME)

  private lazy val envConfig: Config =
    ConfigFactory.parseMap(sys.env.asJava)

  private lazy val sysConf: Config =
    ConfigFactory
      .systemProperties()

  protected lazy val commonConfig: Config = {
    val comConf =
      ConfigFactory.parseResources("application.props")

    val appConf =
      configPath
        .map(new File(_))
        .collect { case f if f.exists() => ConfigFactory.parseFile(f) }

    val config =
      envConfig
          .withFallback(
            appConf
              .map(sysConf.withFallback)
              .getOrElse(sysConf)
              .withFallback(comConf)
              .resolve()
          )

    config
  }

  lazy val config: AppConfig =
    loadConfigWithFallbackOrThrow[AppConfig](commonConfig)

}
