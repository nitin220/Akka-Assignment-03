package actors

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem}
import model.{AccountsDetails, BillerDetails, FetchData, GenerateReport}

import scala.collection.mutable.ListBuffer
import scala.concurrent.duration._


class ReportGeneratorActor(ref: ActorRef, system: ActorSystem) extends Actor with ActorLogging {
  override def receive: Receive = {


    case GenerateReport => {
      import system.dispatcher
      system.scheduler.schedule(0 second, 5 minutes, ref, FetchData)

    }
    case (userList: ListBuffer[AccountsDetails], billerList: ListBuffer[BillerDetails]) =>
      userList.map {
        user => {
          log.info("UserName : " + user.userName)
          log.info("Account Number : " + user.accountNumber)
          log.info("Account Balance : " + user.initialAmount + "\n")

          val billersOfUser = billerList.filter(biller => biller.accountNumber == user.accountNumber)
          billersOfUser.map {
            billers => {
              log.info("\n" + "Biller Details :-\n")
              log.info("Biller Category : " + billers.billerCategory)
              log.info("Biller Name : " + billers.billerName)
              log.info("Date of Transaction : " + billers.transactionDate)
              log.info("Total Subscription : " + billers.totalIterations)
              log.info("Service used " + billers.executedIterations)
              log.info("Total Amount Paid till Date: " + billers.paidAmount)
            }
          }
        }
      }
  }
}
