package com.test.db

import com.test.db.util.{HikariJdbcDataSourceFactory, JdbcDataSource}
import com.typesafe.config.Config
import org.jooq.SQLDialect
import org.jooq.impl._

/**
  * Created by <Chekhonadskikh, Fedor> on 12/17/18 11:53 AM
  */
trait SQLContext {

  protected val dbConfig: Config

  protected lazy val dialect: SQLDialect = SQLDialect.POSTGRES_10

  protected lazy val datasource: JdbcDataSource =
    HikariJdbcDataSourceFactory.forConfig(dbConfig)

  implicit def context: SQL = DSL.using(datasource.getConnection(), dialect)

}
