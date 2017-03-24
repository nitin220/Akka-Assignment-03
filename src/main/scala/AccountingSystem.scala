import actors.{ReportGeneratorActor, DataGenerator, DatabaseActor, SalaryDepositActor}
import akka.actor.{ActorSystem, Props}
import model.GenerateReport
import service.{SalaryDepositService, BillerAndAccountService}

object AccountingSystem extends App {

  val obj = new BillerAndAccountService
  val obj1 = new SalaryDepositService
  val system = ActorSystem("AccountingSystem")
  val databaseActor = system.actorOf(Props[DatabaseActor], "DatabaseActor")
  val dataGenerator = system.actorOf(Props(classOf[DataGenerator], databaseActor), "DataGenerator")
  val salaryDepositActor = system.actorOf(Props(classOf[SalaryDepositActor], databaseActor), "SalaryDepositActor")

  val reportGeneratorActor = system.actorOf(Props(classOf[ReportGeneratorActor],databaseActor, system),"ReportGeneratorActor")

  obj.dataGenerator(10, dataGenerator)

  obj1.depositSalary(30000,10,salaryDepositActor)

  reportGeneratorActor ! GenerateReport


}
