package service

import akka.actor.ActorRef
import model._

class BillerAndAccountService {

  def dataGenerator(limit: Int, ref: ActorRef) = {
    for (count <- 1 to limit) {

      val accountNumber = count
      val accountHolderName = s"User$count"
      val userAddress = "Gurgaon"
      val userName = s"User$count"
      ref ! AccountsDetails(accountNumber, accountHolderName, userAddress, userName)



      for (count2 <- 1 to 5) {

        if (count2 == 1) {
          val billerCategory = Phone
          val billerName = "airtel"
          val accountNum = accountNumber
          val transactionDate = "01-01-2017"
          val amount = 500
          val paidAmount = 0

          ref ! BillerDetails(billerCategory, billerName, accountNum, transactionDate, amount, paidAmount)
        }
        if (count2 == 2) {
          val billerCategory = Electricity
          val billerName = "Relince"
          val accountNum = accountNumber
          val transactionDate = "01-01-2017"
          val amount = 2000
          val paidAmount = 0

          ref ! BillerDetails(billerCategory, billerName, accountNum, transactionDate, amount, paidAmount)
        }
        if (count2 == 3) {
          val billerCategory = Internet
          val billerName = "Exitel"
          val accountNum = accountNumber
          val transactionDate = "01-01-2017"
          val amount = 1500
          val paidAmount = 0

          ref ! BillerDetails(billerCategory, billerName, accountNum, transactionDate, amount, paidAmount)
        }
        if (count2 == 4) {
          val billerCategory = Food
          val billerName = "Haldiram"
          val accountNum = accountNumber
          val transactionDate = "01-01-2017"
          val amount = 2000
          val paidAmount = 0

          ref ! BillerDetails(billerCategory, billerName, accountNum, transactionDate, amount, paidAmount)
        }
        if (count2 == 5) {
          val billerCategory = Car
          val billerName = "FirstChoice"
          val accountNum = accountNumber
          val transactionDate = "01-01-2017"
          val amount = 5000
          val paidAmount = 0

          ref ! BillerDetails(billerCategory, billerName, accountNum, transactionDate, amount, paidAmount)
        }


      }

    }


  }

}
