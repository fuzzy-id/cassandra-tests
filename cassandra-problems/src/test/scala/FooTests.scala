package de.babibo.cassandra.tests

import de.babibo.cassandra.problems._

import com.outworkers.phantom.dsl._
import com.outworkers.util.samplers._
import org.scalatest._

object TestConnector {
  val connector = ContactPoints(Seq("dev-cassndr.containers"), 9042)
    .noHeartbeat()
    .keySpace("foo")
}

class TestDatabase extends FooDatabase(TestConnector.connector)

class CassandraTests extends FlatSpec with Matchers {
  val service = new TestDatabase {}

  // Results in:
  // [error] /home/vince/midas/tests/cassandra-problems/src/test/scala/FooTests.scala:21:16: type mismatch;
  // [error]  found   : Option[com.outworkers.util.domain.GenerationDomain.FullName]
  // [error]     (which expands to)  Option[com.outworkers.util.domain.Definitions.FullName]
  // [error]  required: Option[String]
  // [error]     val f = gen[Foo]
  // [error]                ^
  "gen[Foo]" should "work" in {
    val f = gen[Foo]
    "foo" shouldEqual "foo"
  }
}
