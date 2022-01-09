import scala.annotation.tailrec
import scala.collection.convert.ImplicitConversions.`seq AsJavaList`
import scala.util.control.Breaks.{break, breakable}

val days = List(
  "Poniedziałek", 
  "Wtorek",
  "Środa",
  "Czwartek",
  "Piątek",
  "Sobota",
  "Niedziela")

//ZADANIE 1:

def stringFor(days: List[String]) = {
  var daysStr = ""
  for (i <- days) {
    if (daysStr != "")
      daysStr += ", " + i
    else
      daysStr += i
  }
  daysStr
}

def stringForP(days: List[String]) = {
  var daysStr = ""
  for (i <- days.filter(_.startsWith("P"))) {
    if (daysStr != "")
      daysStr += ", " + i
    else
      daysStr += i
  }
  daysStr
}

def stringWhile(days: List[String]) = {
  var daysStr = ""
  var i = 0;
  while (i < days.length) {
    if (daysStr != "")
      daysStr += ", " + days.get(i)
    else
      daysStr += days.get(i)
    i += 1;
  }
  daysStr
} 

println("Zadanie 1a:")
println(stringFor(days))

println("Zadanie 1b:")
println(stringForP(days))

println("Zadanie 1c:")
println(stringWhile(days))

//ZADANIE 2:

def stringRecursive(days: List[String]): String = {
  if (!days.tail.isEmpty)
    days.head + ", " + stringRecursive(days.tail)
  else 
    days.head
}

def stringRecursiveRev(days: List[String]): String = {
  if (!days.tail.isEmpty)
    stringRecursiveRev(days.tail) + ", " + days.head
  else 
    days.head
}

println("\nZadanie 2a:")
println(stringRecursive(days))

println("Zadanie 2b:")
println(stringRecursiveRev(days))

//ZADANIE 3:

def stringRecursiveTail(days: List[String]): String = {
  @tailrec def tailRecur(days: List[String], str: String): String = {
    if(!days.isEmpty)
      tailRecur(days.tail, str + ", " + days.head)
    else 
      str
  }
  tailRecur(days.tail, days.head)
}

println("\nZadanie 3:")
println(stringRecursiveTail(days))

//ZADANIE 4:

def stringFoldLeft(days: List[String]) = {
  days.tail.foldLeft(days.head)(_+", "+_)
}

def stringFoldRight(days: List[String]) = {
  days.dropRight(1).foldRight(days.last)(_+", "+_)
}

def stringFoldLeftP(days: List[String]) = {
  days.filter(_.startsWith("P")).tail.foldLeft(days.head)(_+", "+_)
}

println("\nZadanie 4a:")
println(stringFoldLeft(days))

println("Zadanie 4b:")
println(stringFoldRight(days))

println("Zadanie 4c:")
println(stringFoldLeftP(days))
