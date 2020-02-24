let labels = [];
let pieDatas = [];

for (i = 0; i < chartData.length; i++) {
	console.log(chartData[i].label + "," + chartData[i].value);
	labels.push(chartData[i].label);
	pieDatas.push(chartData[i].value);
}

var config = {
	type : 'pie',
	data : {
		datasets : [ {
			data : pieDatas,
			backgroundColor : [ "#3e95cd", "#8e5ea2", "#3cba9f" ],
			label : 'Dataset 1'
		} ],
		labels : labels

	},
	options : {
		responsive : true
	}
}

window.onload = function() {
	var ctx = document.getElementById('myPieChart').getContext('2d');
	window.myPie = new Chart(ctx, config);
}
