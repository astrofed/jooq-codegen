import com.test.db.DB
import com.test.db.generated.tables.TestUsers._
import org.jooq.scalaextensions.Conversions._
import org.scalatest.FunSuite

/**
  * Created by <Chekhonadskikh, Fedor> on 12/20/18 2:44 PM
  */
class UsersFetchSpec extends FunSuite {

  private val sql = DB.context

  test("insert user") {
    val result = sql insertInto TEST_USERS set(TEST_USERS.NAME, "test") execute

    assert(result != 0)
  }

  test("testQueryingAfterMigration") {
    val result = sql select TEST_USERS.* from TEST_USERS where (TEST_USERS.NAME eq "test") fetch

    assert(1 == result.size)
  }


}
