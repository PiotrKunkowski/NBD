db.people.mapReduce(
	function() {
		let key = this.sex;
		let value = { 
			height: parseFloat(this.height),
			weight: parseFloat(this.weight),
			count: 1
		};
	emit(key, value);
	}, 
	
	function(key, values) {
		let reduced = { 
			height: 0,
			weight: 0,
			count: 0
		};
	values.forEach(function(value){
		reduced.height += value.height,
		reduced.weight += value.weight,
		reduced.count += value.count;
	});
		return reduced;
	},
	
	{ 
	out: 'avgs',
	finalize: function(key, reduced) 
		{
		reduced.height = reduced.height / reduced.count;
		reduced.weight = reduced.weight / reduced.count;
		return reduced;
		},
	}
)

printjson(db.avgs.find().toArray())