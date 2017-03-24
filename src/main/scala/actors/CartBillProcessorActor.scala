package actors

import akka.actor.Actor
import model.{Car, Response}


class CartBillProcessorActor extends Actor{

  def receive:Receive ={
    case response: Response =>
      sender() ! (response,Car)
  }

}
