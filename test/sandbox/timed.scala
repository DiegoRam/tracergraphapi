package sandbox


import org.specs2._
import time._
import specification._
import execute._

trait Timed extends Around {
  def around[T : AsResult](t: =>T): Result = {
    // use `ResultExecution.execute` to catch possible exceptions
    val (result, timer) = withTimer(ResultExecution.execute(AsResult(t)))

    // update the result with a piece of text which will be displayed in the console
    result.updateExpected("Execution time: "+timer.time)
  }

  /** mesure the execution time of a piece of code */
  def withTimer[T](t: =>T): (T, SimpleTimer) = {
    val timer = (new SimpleTimer).start
    val result = t
    (result, timer.stop)
  }
}
