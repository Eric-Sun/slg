
	function draw(datasets, placeholder){
    var data = [];
    $.each(datasets, function(key, val) {
        data.push(val);
    });
    if (data.length > 0) {
        $.plot(placeholder, data, {
            hoverable: true,
            yaxis: { min: 0 },
            xaxis: { tickDecimals: 0 },
            series: {
                lines: { show: true },
                points: { show: true }
            },
            grid: { hoverable: true, clickable: true }
        });
        placeholder.css('display', 'block');
    }

}

function showTooltip(x, y, contents) {
    $('<div id="tooltip">' + contents + '</div>').css( {
        position: 'absolute',
        display: 'none',
        top: y + 5,
        left: x + 5,
        border: '1px solid #fdd',
        padding: '2px',
        'background-color': '#fee',
        opacity: 0.80
    }).appendTo("body").fadeIn(200);
}

function plothover(event, pos, item) {
    $("#x").text(pos.x.toFixed(2));
    $("#y").text(pos.y.toFixed(2));

    if (item) {
        if (previousPoint != item.dataIndex) {
            previousPoint = item.dataIndex;

            $("#tooltip").remove();
            var x = item.datapoint[0].toFixed(0),
                    y = item.datapoint[1].toFixed(0);

            showTooltip(item.pageX, item.pageY,
                    item.series.label + ":" + y);
        }
    }
    else {
        $("#tooltip").remove();
        previousPoint = null;
    }
}