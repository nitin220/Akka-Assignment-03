package actors

import akka.actor.SupervisorStrategy.{Stop, Restart, Resume}
import akka.actor._
import model.Response
import scala.concurrent.duration._
import Exception._

class SalaryDepositActor(ref: ActorRef) extends Actor with ActorLogging{

  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 second) {
      case arithmeticException: ArithmeticException => Resume
      case nullPointerException: NullPointerException => Stop
      case illegalArgumentException: IllegalArgumentException => Stop
      case _: Exception => Resume
    }

  def receive: Receive = {
    case (salary: Long, accountNumber: Int, userName: String) =>
      ref tell((salary, accountNumber, userName), self)
    /*val response=Await.result(ref ? (salary,accountNumber,userName) ,5 second)
      if(response == Response){

      }*/

    case response: Response => {

      val phoneChild = context.actorOf(Props[PhoneBillProcessorActor])
      val internetChild = context.actorOf(Props[InternetBillProcessorActor])
      val electricityChild = context.actorOf(Props[ElectricityBillProcessorActor])
      val carChild = context.actorOf(Props[CartBillProcessorActor])
      val foodChild = context.actorOf(Props[FoodBillProcessorActor])

      phoneChild forward (response)
      internetChild forward (response)
      electricityChild forward (response)
      carChild forward (response)
      foodChild forward (response)


    }

  }

}
