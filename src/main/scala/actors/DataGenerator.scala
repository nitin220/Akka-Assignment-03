package actors

import akka.actor.SupervisorStrategy.{Escalate, Stop, Restart, Resume}
import akka.actor.{OneForOneStrategy, Actor, ActorRef}
import akka.dispatch.{BoundedMessageQueueSemantics, RequiresMessageQueue}
import model.{AccountsDetails, BillerDetails}
import scala.concurrent.duration._
import Exception._

class DataGenerator(ref: ActorRef) extends Actor with RequiresMessageQueue[BoundedMessageQueueSemantics] {


  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 second) {
      case arithmeticException: ArithmeticException => Resume
      case nullPointerException: NullPointerException => Restart
      case illegalArgumentException: IllegalArgumentException => Stop
      case _: Exception => Resume
    }

  def receive: Receive = {
    case accountsDetails: AccountsDetails =>
      ref ! accountsDetails

    case billerDetails: BillerDetails =>
      ref ! billerDetails

  }

}
