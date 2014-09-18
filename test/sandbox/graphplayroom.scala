package sandbox

import java.util.{Date, UUID}


import com.ansvia.graph.ObjectConverter
import com.tinkerpop.blueprints.Element
import com.tinkerpop.blueprints.Direction
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory
import org.diegoram.User
import org.specs2.mutable.Specification
import com.ansvia.graph.BlueprintsWrapper._
import scala.collection.JavaConversions._

/**
 * Created by diegoram on 9/17/14.
 */
class OrientDbTest extends Specification{

  val factory = new OrientGraphFactory("plocal:/Users/diegoram/dev/orientdata")
  implicit val db = factory.getNoTx

  val arjones = User(UUID.randomUUID().toString, "Gustavo Arjones").save
  val diegoram = User(UUID.randomUUID().toString, "Diego Ramirez").save


  "graph database" should {
    sequential
    "create a embedded database" in {
      db should not beNull
    }

    "create a simple vertex for arjones and convert it" in {
      val unmarshalledArjones = arjones.toCC[User].get
      unmarshalledArjones.screenName === "Gustavo Arjones"
    }

    "retrieve a recently created vertex from the storage" in {
      val unmashalledArjones = db.getVertex(arjones.getId)
      unmashalledArjones.toCC[User].get.screenName == "Gustavo Arjones"

    }

    "create a simple relation arjones -> knows -> diegoram" in {
      val newEdge = arjones --> "knows" --> diegoram <()
      newEdge.setProperty("since", new Date())
      newEdge.setProperty("introducedBy", "Lufo")
      newEdge.isInstanceOf[Element]
    }

    "retrieve known people by arjones" in {
      val knownByArjones = arjones.pipe.out("knows").toList
      knownByArjones.printDump("arjones knows", "screenName")
      knownByArjones.size must beEqualTo(1)
      knownByArjones.toList.head.toCC[User].get.screenName === "Diego Ramirez"
    }

    "retrieve known people arjones knows in a vertex list" in {
      val knowList = arjones.getVertices(Direction.OUT, "knows").iterator.toList
      knowList.size must beEqualTo(1)
      knowList.head.toCC[User].get.screenName == "Diego Ramirez"
    }
  }

}
