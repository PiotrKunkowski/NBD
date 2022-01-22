case class Person(val name: String, val surname: String) {}

def greetPerson(person: Person) = person match {
  case Person("Frodo", "Baggins") => "Witaj, Frodo"
  case Person("Harry", "Potter") => "Jesteś czarodziejem, Harry!"
  case Person(name, _) => s"Cześć, $name"
}

println("Zadanie 3:")
println("Frodo: " + greetPerson(new Person("Frodo", "Baggins")))
println("Jan Kowalski: " + greetPerson(new Person("Jan", "Kowalski")))