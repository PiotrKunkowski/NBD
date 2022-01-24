db.people.mapReduce(
	function () {
		this.credit.forEach(function (credit) {
			sumBalance = parseFloat(credit.balance);
	
		emit(credit.currency, {sumBalance, count: 1});
		});
	},
	
	function (key, values) {
	var reducedVal = {
		count: 0,
		sumBalance: 0,
	}
		
	values.forEach(function (value) {
		reducedVal.count += value.count;
		reducedVal.sumBalance += value.sumBalance;
	});
		
	return reducedVal;
	},
	
	{
		out: 'balances',
		query: {
			"nationality": "Poland",
			"sex": "Female"
		},
		finalize: function (key, reducedVal) {			
			reducedVal.sumBalance = (reducedVal.sumBalance).toFixed(2);
			reducedVal.average = (reducedVal.sumBalance / reducedVal.count).toFixed(2);
			delete reducedVal.count;
			return reducedVal;
		},
	}
);

printjson(db.balances.find().toArray())