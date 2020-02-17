var config = {
	type : 'pie',
	data : {
		datasets : [ {
			data : [ 1, 2, 3 ],
			backgroundColor : [ "#3e95cd", "#8e5ea2", "#3cba9f" ],
			label : 'Dataset 1'
		} ],
		labels : [ "#3e95cd", "#8e5ea2", "#3cba9f" ]

	},
	options : {
		responsive : true
	}
}

window.onload = function() {
	var ctx = document.getElementById('myPieChart').getContext('2d');
	window.myPie = new Chart(ctx, config);
}
