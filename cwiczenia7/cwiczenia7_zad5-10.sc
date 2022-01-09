import scala.annotation.tailrec
import scala.collection.convert.ImplicitConversions.`seq AsJavaList`
import scala.util.control.Breaks.{break, breakable}

//ZADANIE 5

println("Zadanie 5:")

val products = Map("Dog food" -> 10, "Cat food" -> 15, "Fish food" -> 5)
println("Przed obniżką: " + products)

val productsSale = products.map{
  case (label, price) => label -> price*0.9 
}
println("Po obniżce: " + productsSale)

//ZADANIE 6

def printTuple(tup: (Any, Any, Any)) = {
  println(tup._1)
  println(tup._2)
  println(tup._3)
} 

val tuple = ("Skyrim", 50, 0.75)
println("\nZadanie 6:")
println(printTuple(tuple))

//ZADANIE 7

def searchProduct(map : Map[String, Int], key : String): Any = {
      map.get(key).getOrElse("Wyszukany produkt nie istnieje")
    }
println("\nZadanie 7:")
println("Wyszukiwanie istniejącego produktu: " + searchProduct(products, "Cat food"))
println("Wyszukiwanie nieistniejącego produktu: " + searchProduct(products, "Bird food"))

//ZADANIE 8:

val numbers = List[Int](0,1,2,3,0,4,5,0)

@tailrec
def zeroCut(list: List[Int], accumulator: List[Int] = List.empty): List[Int] = list match {
  case Nil => accumulator //if Empty
  case 0::tail => zeroCut(tail, accumulator)
  case head::tail => zeroCut(tail, accumulator:+head)
}

println("\nZadanie 8:")
println("Oryginana lista: " + numbers)
println("Lista po usunięciu zer: " + zeroCut(numbers))

//ZADANIE 9:

def listMap(list: List[Int]): List[Int] = {
  list.map{ 
    case (k) => k+1
  }
}

println("\nZadanie 9:")
println("Lista zwiększona o 1: " + listMap(numbers))

//ZADANIE 10:

val numbersReal = List[Double](-12,-10.5,-3,-1,0,2.5,5,7.2,10,12,15,18)
  
def filterList(list: List[Double]): List[Double] = {
  var listFiltered = list.filter(_ >= -5).filter(_ <= 12)
  listFiltered.map(_.abs)
}

println("\nZadanie 10:")
println("Oryginana lista: " + numbersReal) 
println("Po filtrowaniu: " + filterList(numbersReal))
