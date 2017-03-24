package actors

import akka.actor.Actor
import model.{Phone, Response}


class PhoneBillProcessorActor extends Actor{

  def receive:Receive ={
    case response: Response =>
      sender() ! (response,Phone)
  }

}
