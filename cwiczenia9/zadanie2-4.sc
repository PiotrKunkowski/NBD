trait Maybe[A]

class No extends Maybe[Nothing] {
  def applyFunction[A,R](f: A => R) : No = {
    new No()
  }
  def getOrElse(elseParam: Any) = elseParam
}

class Yes[A](val x: A) extends Maybe[A] {
  def applyFunction[R](f: A => R) : R = {
    f(x)
  }
  def getOrElse(elseParam: Any) = x
}

val no = new No
val yes = new Yes[String]("yeeeees")

//ZADANIE 2
println("Zadanie 2: ")
println("Czy No jest podtypem Maybe[_]? - " + no.isInstanceOf[Maybe[_]])
println("Czy Yes jest podtypem Maybe[_]? - " +yes.isInstanceOf[Maybe[_]])

//ZADANIE3
println("\nZadanie 3: ")
println("No funkcja: " + no.applyFunction((x: String) => "nooooooooo! " + x))
println("Yes funkcja: " + yes.applyFunction((x: String) => "yeeeeeeeah! " + x))

//ZADANIE 4
println("\nZadanie 4: ")
println(no.getOrElse("No - getOrElse"))
println(yes.getOrElse("Yes - getOrElse"))