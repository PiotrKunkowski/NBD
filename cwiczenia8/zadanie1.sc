import scala.annotation.tailrec
import scala.collection.convert.ImplicitConversions.`seq AsJavaList`
import scala.util.control.Breaks.{break, breakable}

//ZADANIE 1:

def searchDay(day: String) = {
  val workingDays = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek")
  val weekendDays = List("Sobota", "Niedziela")

  day match {
    case a if (workingDays.contains(a)) => "Praca"
    case b if (weekendDays.contains(b)) => "Weekend"
    case _ => "Nie ma takiego dnia"
  }
}

println("Zadanie 1:")
println("Poniedziałek: " + searchDay("Poniedziałek"))
println("Niedziela: " + searchDay("Niedziela"))
println("myszojeleń: " + searchDay("myszojeleń"))