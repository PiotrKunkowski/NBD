db.people.remove({'height': {'$gt':'190'}});
printjson(db.people.find({'height':{'$gt':'190'}}).count());