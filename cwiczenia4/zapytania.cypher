// 1
MATCH (darj:town { name: "Darjeeling" }), (sand:peak { name: "Sandakphu" })
MATCH path = ShortestPath((darj)-[*]->(sand))
RETURN path


// 2
MATCH (darj:town { name: "Darjeeling" }), (sand:peak { name: "Sandakphu" })
MATCH path = ShortestPath((darj)-[*]->(sand))
WHERE ALL (r IN relationships(path) WHERE r.winter = "true")
RETURN path


//3a - wg dystansu
MATCH p = (source:town {name: "Darjeeling"})-[r*]->(target:peak {name: "Sandakphu"})
UNWIND r as rel
WITH p, COLLECT(rel.distance) AS distances
WITH p, reduce(acc = 0, d in distances | acc + d) as distance
RETURN p, distance
ORDER BY distance ASC

//3b - rower
MATCH p = (source:town {name: "Darjeeling"})-[r:twowheeler*]->(target)
WHERE ALL (r IN relationships(p) WHERE r.summer = "true")
RETURN target //nie ma takich ścieżek

//4
MATCH (a:Airport)<-[:ORIGIN]-(f:Flight)
WITH a, count(f) as flights
RETURN a, flights
ORDER BY flights ASC

//5
MATCH p = (origin:Airport { name:"LAX" })<-[r1:ORIGIN]-(:Flight)-[r2:ORIGIN|DESTINATION*..10]->(destination:Airport)
WHERE REDUCE(acc = 0, n IN [x IN NODES(p) WHERE 'Flight' IN LABELS(x)] | acc + [(n)<-[:ASSIGN]-(ticket) | ticket.price][0]) < 3000
RETURN p, price

//6
MATCH p = (origin:Airport { name:"LAX" })<-[r1:ORIGIN]-(:Flight)-[r2:ORIGIN|DESTINATION*..10]->(destination:Airport {name: "DAY"})
WITH p, REDUCE(acc = 0, n IN [x IN NODES(p) WHERE 'Flight' IN LABELS(x)] | acc + [(n)<-[:ASSIGN]-(ticket) | ticket.price][0]) as price
RETURN p, price
ORDER BY price DESC //nie ma takich połączeń

---------------------------------------------------

//7
MATCH p = (origin:Airport { name:"LAX" })<-[r1:ORIGIN]-(:Flight)-[r2:ORIGIN|DESTINATION*..10]->(destination:Airport {name: "DAY"})
WITH p, REDUCE(acc = 0, n IN [x IN NODES(p) WHERE 'Flight' IN LABELS(x)] |
  acc + [(n)<-[:ASSIGN]-(ticket) | ticket.price][0]
  ) as price
RETURN p, price
ORDER BY price asc LIMIT 1 //nie ma takich połączeń

//8
MATCH p = (origin:Airport { name:"LAX" })<-[r1:ORIGIN]-(:Flight)-[r2:ORIGIN|DESTINATION*..10]->(destination:Airport {name: "DAY"})
WITH p, REDUCE(acc = 0, n IN [x IN NODES(p) WHERE 'Flight' IN LABELS(x)] |
  acc + [(n)<-[:ASSIGN]-(ticket {class: 'business'}) | ticket.price][0]
  ) as price
RETURN p, price
ORDER BY price asc LIMIT 1 //nie ma takich połączeń

//9
MATCH (a:Airport)<-[:ORIGIN]-(f:Flight)-[:DESTINATION]->(destination:Airport)
WITH [a.name, destination.name, f.airline] AS connection, f
RETURN f.airline AS airline, COUNT(DISTINCT connection) AS connections
ORDER BY connections desc

//10
MATCH p = (a1:Airport)<-[r1:ORIGIN]-(f1:Flight)-[r2:DESTINATION]->(a2:Airport)<-[r3:ORIGIN]-(f2:Flight)-[r4:DESTINATION]->(a3:Airport)
WHERE not(a1=a2) AND not(a1=a3) AND not(a2=a3)
WITH p, REDUCE(acc = 0, n IN [x IN NODES(p) WHERE 'Flight' IN LABELS(x)] | acc + [(n)<-[:ASSIGN]-(ticket {class: 'economy'}) | ticket.price][0]) as price
RETURN p, price
ORDER BY price asc LIMIT 1