package service

import akka.actor.ActorRef


class SalaryDepositService {


  def depositSalary(salary:Long,limit:Int,ref:ActorRef)={

    for(count <- 1 to limit){
      ref ! (salary,count,s"User$count")
    }
  }

}
