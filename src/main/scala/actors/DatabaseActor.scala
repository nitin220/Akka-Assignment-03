package actors

import akka.actor.{Actor, ActorLogging}
import database.DatabaseController
import model._

class DatabaseActor extends Actor with ActorLogging {

  def receive: Receive = {


    case accountsDetails: AccountsDetails =>
      if(!DatabaseController.listOfUser.exists(user=>user.userName==accountsDetails.userName))
        DatabaseController.listOfUser += accountsDetails
      else {
        throw new Exception
      }

    case billerDetails: BillerDetails =>
      DatabaseController.listOfBiller += billerDetails

    case (salary: Long, accountNumber: Int, userName: String) => {

      val userData = DatabaseController.listOfUser.filter(_.accountNumber == accountNumber).head

      val userWithNewSalary = userData.copy(initialAmount = userData.initialAmount + salary)
      DatabaseController.listOfUser -= userData
      DatabaseController.listOfUser += userWithNewSalary

      sender() tell(Response(accountNumber), self)

    }

    case (response: Response, category: Category) => {


      val billerOfUser = DatabaseController.listOfBiller.filter {
        value => value.accountNumber == response.accountNumber && value.billerCategory == category
      }.head


      if (billerOfUser.executedIterations <= billerOfUser.totalIterations) {

        val amount = billerOfUser.amount

        val user = DatabaseController.listOfUser.filter(_.accountNumber == response.accountNumber).head
        if(amount <= user.initialAmount) {
          val newUser = user.copy(initialAmount = user.initialAmount - amount)

          DatabaseController.listOfUser -= user
          DatabaseController.listOfUser += newUser
          val newbiller = billerOfUser.copy(
            executedIterations = billerOfUser.executedIterations + 1,
            paidAmount = billerOfUser.paidAmount + amount)

          DatabaseController.listOfBiller -= billerOfUser
          DatabaseController.listOfBiller += newbiller
        }
        else {
          log.error("Not Enough amount to pay bills")
        }

      }
      else log.error("Subscription ended!!")

    }

    case FetchData =>

      sender() !(DatabaseController.listOfUser, DatabaseController.listOfBiller)


  }

}
