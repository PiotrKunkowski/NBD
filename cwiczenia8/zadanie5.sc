class Person(val name: String, val surname: String, private var _tax: Double) {
  def tax: Double = _tax
}

trait Worker extends Person {
  var salary: Double = 1000
  override def tax: Double = 0.2*salary
}

trait Student extends Person {
  override def tax: Double = 0
}

trait Teacher extends Worker {
  override def tax: Double = 0.1*salary
}

println("Zadanie 5:")
//pracownik
val worker1 = new Person("Jan", "Kowalski", 10) with Worker
worker1.salary = 7500
println("Pensja pracownika: " + worker1.salary)
println("Podatek od tej pensji (20%): " + worker1.tax + "\n")

//student
val student1 = new Person("Marek", "Studencki", 10) with Student
println("Podatek dla studenta: " + student1.tax + "\n")

//nauczyciel
val teacher1 = new Person("Filip", "Nowak", 10) with Teacher
teacher1.salary = 4500
println("Pensja nauczyciela: " + teacher1.salary)
println("Podatek od tej pensji (10%): " + teacher1.tax + "\n")

//student-pracownik
val studentWorker = new Person("Michał", "Kwiatek", 10) with Student with Worker
studentWorker.salary = 2000
println("Pensja studenta-pracownika: " + studentWorker.salary)
println("Podatek dla studenta-pracownika: " + studentWorker.tax + "\n")

//pracownik-student
val workerStudent = new Person("Miłosz", "Drzewo", 10) with Worker with Student
workerStudent.salary = 2000
println("Pensja pracownika-studenta: " + workerStudent.salary)
println("Podatek dla pracownika-studenta: " + workerStudent.tax)

//WNIOSEK: drugi trait przesłania pierwszy