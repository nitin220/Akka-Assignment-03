package actors

import akka.actor.Actor

import model._


class ElectricityBillProcessorActor extends Actor{

  def receive:Receive ={
    case response: Response =>
      sender() ! (response,Electricity)
  }


}
