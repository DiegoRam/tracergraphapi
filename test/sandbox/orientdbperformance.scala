package sandbox

import java.util.UUID

import com.ansvia.graph.BlueprintsWrapper._
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory
import org.diegoram.{User, Tweet}
import org.specs2.mutable.Specification
import java.util.Date
import com.tinkerpop.blueprints.Direction
import scala.collection.JavaConversions._


class OrientDbPerformanceTest extends Specification{
  val factory  = new OrientGraphFactory("plocal:/Users/diegoram/dev/orientdataper")
  implicit val db = factory.getNoTx

  val diegoram = User(UUID.randomUUID().toString, "DiegoTest").save()

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

    "create 100 edges form a given Vertex" >> {
      (for(i <- 1 to 100) yield {
        diegoram --> "own" --> Tweet(UUID.randomUUID().toString, "text " + i.toString, new Date().getTime).save() <()
      }).size should beEqualTo(100)
    }

    /*"create 100K nodes" >> {
      val userList = for(i <- 1 to 100000) yield (
        User(UUID.randomUUID().toString, "Diego_" + i.toString).save
        )
      userList.size should beEqualTo(100000)

    }*/

    "retrieve a single Node by id" >> {
      db.getVertex(diegoram.getId).toCC[User].get.screenName must contain("Diego")
    }


    "retrieve a list of node by a given Node (related ones)" in {
      diegoram.getVertices(Direction.OUT, "own").iterator.toList.size should beEqualTo(99)
    }
    "retrieve a list of nodes by a given query" in {
      diegoram.pipe.out("own").toList.size should beEqualTo(99)
    }
  }


}
