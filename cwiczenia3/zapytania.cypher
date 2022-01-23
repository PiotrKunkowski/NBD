//1
MATCH (movies:Movie) RETURN movies

//2
MATCH (hw:Person {name: "Hugo Weaving"})-[:ACTED_IN]->(hwMovies) RETURN hw,hwMovies

//3
MATCH (hw:Person {name: "Hugo Weaving"})-[:ACTED_IN]->(hwMovies)<-[:DIRECTED]-(directors) RETURN hwMovies, directors

//4
MATCH (hw:Person {name:"Hugo Weaving"})-[:ACTED_IN]->(m)<-[:ACTED_IN]-(actors) RETURN actors, m

//5
MATCH (m:Movie)<-[:ACTED_IN]-(person) 
WHERE (person)-[:ACTED_IN]->(:Movie { title: "The Matrix" })
RETURN m, person

//6
MATCH (actor:Person)-[:ACTED_IN]->(actorMovies) 
WITH actor, count(actorMovies) as moviesCount 
WHERE moviesCount > 0 
RETURN actor, moviesCount

//7
MATCH (people:Person)-[:WROTE]->(movie) 
WHERE (people)-[:DIRECTED]->(movie)
RETURN people, movie

//8
MATCH (hugoWeaving:Person {name:"Hugo Weaving"})-[:ACTED_IN]->(movie)<-[:ACTED_IN]-(keanuReeves:Person {name:"Keanu Reeves"}) 
RETURN movie, hugoWeaving, keanuReeves

//9 - dodaj film
CREATE (CaptainAmerica:Movie {title:"Captain America: The First Avenger", released:2011, tagline:"When patriots become heroes"})
CREATE (Markus:Person {name:"Christopher Markus", born:1970})
CREATE (McFelly:Person {name:"Stephen McFeely", born:1969})
CREATE (Evans:Person {name:"Chris Evans", born:1981})
CREATE (Weaving:Person {name:"Hugo Weaving", born:1960})
CREATE (Jackson:Person {name:"Samuel L. Jackson", born:1948})
CREATE (Johnston:Person {name:"Joe Johnston", born:1950 })
CREATE
  (Evans)-[:ACTED_IN {roles:["Captain America / Steve Rogers"]}]->(CaptainAmerica),
  (Weaving)-[:ACTED_IN {roles:["Johann Schmidt / Red Skull"]}]->(CaptainAmerica),
  (Jackson)-[:ACTED_IN {roles:["Nick Fury"]}]->(CaptainAmerica),
  (Johnston)-[:DIRECTED]->(CaptainAmerica),
  (Markus)-[:WROTE]->(CaptainAmerica),
  (McFelly)-[:WROTE]->(CaptainAmerica)

//9 - pokaż dodany film
MATCH (captainAmerica { title: "Captain America: The First Avenger" })<-[:ACTED_IN|:DIRECTED|:WROTE]->(person) 
RETURN captainAmerica, person