db.people.insert(
    {
    "sex": "Male",
    "first_name": "Piotr",
    "last_name": "Kunkowski",
    "job": "RPA Developer",
    "email": "mail@gmail.com",
    "location": {
        "city": "Warsaw",
        "address": {
            "streetname": "Ulica",
            "streetnumber": "1"
        }
    },
    "description": "Ala ma kota",
    "height": "156",
    "weight": "69",
    "birth_date": "1997-03-17T01:01:01Z",
    "nationality": "Poland",
    "credit": [{
        "type": "mastercard",
        "number": "11111111111",
        "currency": "PLN",
        "balance": "0.00"
    }]
}
);

printjson(db.people.find({'last_name':'Kunkowski'}));