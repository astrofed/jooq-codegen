package com.test.db.util

import java.io.PrintWriter
import java.sql.Connection
import java.util.Properties
import java.util.logging.Logger

import com.test.logging.AppLogger
import com.typesafe.config.Config
import pureconfig.{CamelCase, ConfigFieldMapping, KebabCase}

/**
  * Created by <Chekhonadskikh, Fedor> on 12/17/18 1:07 PM
  */
/** A JdbcDataSource for a HikariCP connection pool. */
class HikariJdbcDataSource(val ds: com.zaxxer.hikari.HikariDataSource) extends JdbcDataSource {

  def close(): Unit = ds.close()

  override def getConnection: Connection = ds.getConnection

  override def getConnection(username: String, password: String): Connection = ds.getConnection(username, password)

  override def getLogWriter: PrintWriter = ds.getLogWriter

  override def setLogWriter(out: PrintWriter): Unit = ds.setLogWriter(out)

  override def setLoginTimeout(seconds: Int): Unit = ds.setLoginTimeout(seconds)

  override def getLoginTimeout: Int = ds.getLoginTimeout

  override def getParentLogger: Logger = ds.getParentLogger

  override def unwrap[T](iface: Class[T]): T = ds.unwrap(iface)

  override def isWrapperFor(iface: Class[_]): Boolean = ds.isWrapperFor(iface)

}

object HikariJdbcDataSourceFactory extends JdbcDataSourceFactory with AppLogger {

  import com.zaxxer.hikari._

  private lazy val mapper: ConfigFieldMapping = ConfigFieldMapping(KebabCase, CamelCase)

  def forConfig(config: Config): JdbcDataSource = {

    val properties: Properties = new Properties

    config.entrySet().forEach(entry => properties.setProperty(mapper(entry.getKey), config.getString(entry.getKey)))

    val hconf = new HikariConfig(properties)

    val ds = new HikariDataSource(hconf)

    new HikariJdbcDataSource(ds)

  }

}
