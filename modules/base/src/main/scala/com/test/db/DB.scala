package com.test.db

import com.test.config.ConfigService
import com.typesafe.config.Config

/**
  * Created by <Chekhonadskikh, Fedor> on 12/18/18 4:17 PM
  */
object DB extends SQLContext {

  override protected val dbConfig: Config = ConfigService.config.datasource

}
