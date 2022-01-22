class BankAccount(startingBalance: Double) {
  private var _balance: Double = startingBalance
  
  def balance : Double = _balance //stan poczatkowy

  def depositMoney(amount: Double): Double = {
    _balance += amount
    return _balance
  }

  def withdrawMoney(amount: Double): Double = {
    if (_balance - amount < 0) return _balance

    _balance -= amount
    return _balance
  }
}

println("Zadanie 2:")
val account1 = new BankAccount(1000)
println("Stan poczatkowy: " + account1.balance)
println("Po wpłacie 50: " + account1.depositMoney(50))
println("Po wypłacie 150: " + account1.withdrawMoney(150))


