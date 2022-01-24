import riak
client = riak.RiakClient(pb_port=8098)

bucket = client.bucket('s24070_8')

#dodaj do bazy:
person = bucket.new('person1', data={
    'name': 'John Smith',
    'age': 28,
    'job': 'actor',
})
person.store()
print('Dodano rekord')

#pobierz i wypisz:
getPerson = bucket.get('person1')
print('Pobrano dane: ' + str(getPerson.data))

#modyfikuj dane:
getPerson.data['age'] = 29
getPerson.store()

#pobierz i wypisz po raz drugi:
getPerson2 = bucket.get('person1')
print('Zmodyfikowane dane: ' + str(getPerson2.data))

#usuń z bazy:
getPerson2.delete()
print('Usunieto rekord')
getPerson3 = bucket.get('person1')
print('Wynik proby pobrania danych po usunieciu: ' + str(getPerson3.data))