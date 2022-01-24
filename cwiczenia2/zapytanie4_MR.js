db.people.mapReduce(
  function () {
	let sumBmi = (parseFloat(this.weight) / Math.pow(parseFloat(this.height) / 100, 2));
    let maxBmi = (parseFloat(this.weight) / Math.pow(parseFloat(this.height) / 100, 2));
    let minBmi = (parseFloat(this.weight) / Math.pow(parseFloat(this.height) / 100, 2));
	let count = 1;
	
    emit(this.nationality, {sumBmi, minBmi, maxBmi, count});
  },
  
  function (key, values) {
		var reducedVal = {
			count: 0,
			sumBmi: 0,
			minBmi: values[0].minBmi,
			maxBmi: values[0].maxBmi,
		}
	
		values.forEach(function (value) {
			reducedVal.count += value.count;
			reducedVal.sumBmi += value.sumBmi;
			reducedVal.minBmi = (Math.min(reducedVal.minBmi, value.minBmi)).toFixed(2);
			reducedVal.maxBmi = (Math.max(reducedVal.maxBmi, value.maxBmi)).toFixed(2);
		});
	
		return reducedVal;
	},
	
	{
		out: 'bmi',
		finalize: function (key, reducedVal) {
			reducedVal.avergeBmi = (reducedVal.sumBmi / reducedVal.count).toFixed(2);
			delete reducedVal.sumBmi;
			delete reducedVal.count;
			return reducedVal;
		},
	}
);

printjson(db.bmi.find().toArray())