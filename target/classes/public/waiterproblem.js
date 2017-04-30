var container = d3.select("#cse555");
var clearBtn = d3.select("#clear");
var computeBtn = d3.select("#compute");

var containerDim = container.node().getBoundingClientRect();
var margin = { top: 40, right: 20, bottom: 20, left: 40 };
var radius = 10;
var xMax = 100, yMax = 100;

var svg = container.append("svg");
svg.attr({
    "width" : containerDim.width,
    "height" : containerDim.height
});;

var xScale = d3.scale.linear()
  .domain([0, xMax])
  .range([margin.left, containerDim.width - margin.right]);  

var yScale = d3.scale.linear()
  .domain([0, yMax])
  .range([margin.top, containerDim.height - margin.bottom]);  

var xAxis = d3.svg.axis().scale(xScale).orient("top");
var yAxis = d3.svg.axis().scale(yScale).orient("left");


svg.append("g").attr({
	"class": "axis", 
	transform: "translate(" + [0, margin.top] + ")" 
}).call(xAxis); 

svg.append("g").attr({
	"class": "axis",
	transform: "translate(" + [margin.left, 0] + ")"
}).call(yAxis);

var circleAttrs = {
  cx: function(d) { return xScale(d.x); },
  cy: function(d) { return yScale(d.y); },
  r: radius
};

var dataset = []

svg.on("click", handleClick);

clearBtn.on("click", function() {
	clear();
});

computeBtn.on("click", function() {
	compute();
});

function clear() {
	dataset = [];
	svg.on("click", handleClick);
	svg.selectAll("circle").remove();
	svg.selectAll(".numbers").remove();
}

function compute() {

    var radiobtn = d3.select("input[name='type']:checked").node();
    if(!radiobtn) {
        alert("No type selected");
        return;
    }

    var req = {};
    switch(radiobtn.value) {
        case "1":
        req.heuristicName = "MinDistanceFixedCOM";
        break;
        case "2":
        req.heuristicName = "MaxDistanceFixedCOM";
        break;
        case "3":
        req.heuristicName = "MinimizeCOMVariance";
        break;
        case "4":
        req.heuristicName = "ClosestDistanceVariableCOM";
        break;
    }

    req.points = dataset;
    if(! d3.select("#k").node().value) {
        req.k = dataset.length;
    } else
        req.k = parseInt(d3.select("#k").node().value);

    d3.json('/api')
    .post(JSON.stringify(req), function(error, data) {

            var order = data.order;


            d3.selectAll("circle").attr({
              fill: "orange",
              r: radius * 2
            });

            d3.selectAll("circle").on("mouseover", null);
            d3.selectAll("circle").on("mouseout", null);
            svg.on("click", null);

            for(i = 0; i < dataset.length; ++i) {
                svg.append("text").attr({
                class : "numbers",
                id: "t" + dataset[i].x + "-" + dataset[i].y + "-#" + i,
                    x: function() { return xScale(dataset[i].x) - (radius/2)},
                    y: function() { return yScale(dataset[i].y)}
                })
                .text(function() {
                    if(order[i] == -1) return "-";
                    else return order[i] + 1;
                });
            }

            var s = "";
            for (var key in data.scores) {
                s += key + " : " + data.scores[key] + "<br />"
            }

            var xcom = 0;
            var ycom = 0;
            for(i = 0; i < dataset.length; ++i) {
                xcom += dataset[i].x;
                ycom += dataset[i].y;
            }

            xcom = xcom / dataset.length;
            ycom = ycom / dataset.length;

            s += "Center of Mass + [" + xcom  + ", " + ycom + "] <br />";

            d3.select("#info").node().innerHTML = s;
        });
}

function handleClick() {
	var coords = d3.mouse(this);

	var newData= {
		x: Math.round( xScale.invert(coords[0])),  
		y: Math.round( yScale.invert(coords[1]))
	};

	dataset.push(newData); 

	svg.selectAll("circle")
		.data(dataset)
		.enter()
		.append("circle")
		.attr(circleAttrs) 
		.on("mouseover", handleMouseOver)
		.on("mouseout", handleMouseOut);
}

function handleMouseOver(d, i) {  
	d3.select(this).attr({
	  fill: "orange",
	  r: radius * 2
	});

	svg.append("text").attr({
	   class : "numbers",
	   id: "t" + d.x + "-" + d.y + "-" + i,  
	    x: function() { return xScale(d.x) - 30; },
	    y: function() { return yScale(d.y) - 15; }
	})
	.text(function() {
	  return [d.x, d.y];
	});
}

function handleMouseOut(d, i) {
	d3.select(this).attr({
	  fill: "black",
	  r: radius
	});
	d3.select("#t" + d.x + "-" + d.y + "-" + i).remove(); 
}

