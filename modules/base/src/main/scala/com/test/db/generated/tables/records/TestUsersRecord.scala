/*
 * This file is generated by jOOQ.
 */
package com.test.db.generated.tables.records


import com.test.db.generated.tables.TestUsers
import javax.annotation.Generated
import org.jooq.{Field, Record1, Record2, Row2}
import org.jooq.impl.UpdatableRecordImpl


/**
 * This class is generated by jOOQ.
 */
@Generated(
  value = Array(
    "http://www.jooq.org",
    "jOOQ version:3.11.7"
  ),
  comments = "This class is generated by jOOQ"
)
class TestUsersRecord extends UpdatableRecordImpl[TestUsersRecord](TestUsers.TEST_USERS) with Record2[Integer, String] {

  /**
   * Setter for <code>public.test_users.id</code>.
   */
  def setId(value : Integer) : Unit = {
    set(0, value)
  }

  /**
   * Getter for <code>public.test_users.id</code>.
   */
  def getId : Integer = {
    val r = get(0)
    if (r == null) null else r.asInstanceOf[Integer]
  }

  /**
   * Setter for <code>public.test_users.name</code>.
   */
  def setName(value : String) : Unit = {
    set(1, value)
  }

  /**
   * Getter for <code>public.test_users.name</code>.
   */
  def getName : String = {
    val r = get(1)
    if (r == null) null else r.asInstanceOf[String]
  }

  // -------------------------------------------------------------------------
  // Primary key information
  // -------------------------------------------------------------------------
  override def key : Record1[Integer] = {
    return super.key.asInstanceOf[ Record1[Integer] ]
  }

  // -------------------------------------------------------------------------
  // Record2 type implementation
  // -------------------------------------------------------------------------

  override def fieldsRow : Row2[Integer, String] = {
    super.fieldsRow.asInstanceOf[ Row2[Integer, String] ]
  }

  override def valuesRow : Row2[Integer, String] = {
    super.valuesRow.asInstanceOf[ Row2[Integer, String] ]
  }
  override def field1 : Field[Integer] = TestUsers.TEST_USERS.ID
  override def field2 : Field[String] = TestUsers.TEST_USERS.NAME
  override def component1 : Integer = getId
  override def component2 : String = getName
  override def value1 : Integer = getId
  override def value2 : String = getName

  override def value1(value : Integer) : TestUsersRecord = {
    setId(value)
    this
  }

  override def value2(value : String) : TestUsersRecord = {
    setName(value)
    this
  }

  override def values(value1 : Integer, value2 : String) : TestUsersRecord = {
    this.value1(value1)
    this.value2(value2)
    this
  }

  /**
   * Create a detached, initialised TestUsersRecord
   */
  def this(id : Integer, name : String) = {
    this()

    set(0, id)
    set(1, name)
  }
}
