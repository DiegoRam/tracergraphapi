package sandbox

import java.util.UUID

import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory
import org.diegoram.User
import org.specs2.mutable.Specification



class OrientDbPerformanceTest extends Specification{
  var factory  = new OrientGraphFactory("plocal:/Users/diegoram/dev/orientdataper")
  implicit var db = factory.getNoTx

  "orient db test" should {
    sequential
    "check Database" in {
      db should not beNull
    }

    "create a new database" >> {
      (new OrientGraphFactory("plocal:/Users/diegoram/dev/orientdataTest")).getNoTx should not beNull
    }

    "create 1000 nodes" >> {
      val userList = for(i <- 1 to 1000) yield (
        User(UUID.randomUUID().toString, "Diego_" + i.toString).save
        )
      userList.size should beEqualTo(1000)
    }
    "create 10K nodes" >> {
      val userList = for(i <- 1 to 10000) yield (
        User(UUID.randomUUID().toString, "Diego_" + i.toString).save
        )
      userList.size should beEqualTo(10000)

    }

    "create 100K nodes" >> {
      val userList = for(i <- 1 to 100000) yield (
        User(UUID.randomUUID().toString, "Diego_" + i.toString).save
        )
      userList.size should beEqualTo(100000)

    }

    "retrieve a single Node by id" in {pending}
    "retrieve a list of node by a given Node (related ones)" in {pending}
    "retrieve a list of nodes by a given query" in {pending}
  }


}
