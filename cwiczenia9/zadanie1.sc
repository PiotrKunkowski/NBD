class Container[A](x: A){
  def getContent() : A = x
  
  def applyFunction[R](f: A => R) : R = f(x)
}
println("Zadanie1: ")
val container1 = new Container("666");
println(container1.getContent())
println(container1.applyFunction((a: String) => Integer.parseInt(a)+3))
println("\n")

val container2 = new Container("Działa?");
println(container2.getContent())
println(container2.applyFunction((a: String) => a+" Działa"))
println("\n")

val container3 = new Container(10);
println(container3.getContent())
println(container3.applyFunction((a: Int) => a - 2))
println("\n")

val container4 = new Container(4.5);
println(container4.getContent())
println(container4.applyFunction((a: Double) => a - 2))