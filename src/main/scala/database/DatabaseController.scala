package database

import model.{AccountsDetails, BillerDetails}

import scala.collection.mutable.ListBuffer


object DatabaseController {

  val listOfUser = new ListBuffer[AccountsDetails]()

  val listOfBiller = new ListBuffer[BillerDetails]()



}
