package actors

import akka.actor.Actor
import model.{Internet, Response}


class InternetBillProcessorActor extends Actor{

  def receive:Receive ={
    case response: Response =>
      sender() ! (response,Internet)
  }

}
