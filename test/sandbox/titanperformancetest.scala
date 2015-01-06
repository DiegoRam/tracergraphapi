package sandbox

import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory
import org.specs2.main.Arguments
import org.specs2.mutable.Specification



class TitanPerformanceTest extends Specification with Timed{
  var factory  = new OrientGraphFactory("plocal:/Users/diegoram/dev/orientdataper")
  implicit var db = factory.getNoTx

  "titan db test" should {
    sequential

    "create Database" in {
      db should not beNull
    }

    "create 1000 nodes" in {pending}
    "create 1M nodes" in {pending}
    "retrieve a single Node by id" in {pending}
    "retrieve a list of node by a given Node (related ones)" in {pending}
    "retrieve a list of nodes by a given query" in {pending}
  }


}
