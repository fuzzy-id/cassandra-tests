package de.babibo.cassandra.problems

import com.outworkers.phantom.dsl._

case class StringWrapper(getString: String) {
  lazy val prefix: Int = {
    val pref = this.## % 8
    if (pref < 0)
      -pref
    else
      pref
  }
}

object StringWrapper {
  implicit val stringWrapperPrimitive: Primitive[StringWrapper] =
    Primitive.derive[StringWrapper, String](_.getString)(StringWrapper.apply)
}

case class Foo(
  bar: StringWrapper,
  name: Option[String],
)

abstract class FooTbl extends Table[FooTbl, Foo] {
  object bar extends Col[StringWrapper] with PrimaryKey
  object baz extends OptionalStringColumn
  object prefix extends IntColumn with PartitionKey
}

class FooDatabase(
  override val connector: CassandraConnection
) extends Database[FooDatabase](connector) {
  object fooTbl extends FooTbl with Connector
}
