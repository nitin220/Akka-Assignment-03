package actors

import akka.actor.Actor
import model.{Food, Response}

class FoodBillProcessorActor extends Actor{

  def receive:Receive ={
    case response: Response =>
      sender() ! (response,Food)
  }

}
