package com.test.db.util

import java.io.Closeable

import com.typesafe.config.Config
import javax.sql.DataSource

/**
  * Created by <Chekhonadskikh, Fedor> on 12/17/18 12:39 PM
  */
trait JdbcDataSource extends DataSource with Closeable

/** Create a [[JdbcDataSource]] from a `Config` object */
trait JdbcDataSourceFactory {

  def forConfig(config: Config): JdbcDataSource

}
