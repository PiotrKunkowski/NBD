def manipulateInt(num: Int, intFun: Int => Int) : Int = {
  return intFun(intFun(intFun(num)))
}

def addTwo(num: Int): Int = {
  return num + 2
}

val number: Int = 10
println("Zadanie 4:")
println("Liczba: " + number)
print("Liczba po trzykrotnym zastosowaniu funkcji: " + manipulateInt(number, addTwo))