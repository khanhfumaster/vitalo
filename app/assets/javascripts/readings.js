function renderSpO2Data() {
    $('.history-wrapper').hide();
    $('#spo2-wrapper').show()
    $('.btn.active').removeClass('active');
    $('#spo2-btn').addClass('active');

    $.getJSON('http://localhost:3000/readings/chart?device_id='+window.vitalo_device_id+'&sensor=spo2', function (data) {

        $('#spo2-chart').highcharts({
            chart: {
                zoomType: 'x'
            },
            title: {
                text: 'SpO2'
            },
            subtitle: {
                text: document.ontouchstart === undefined ?
                    'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
            },
            xAxis: {
                type: 'datetime'
            },
            yAxis: {
                title: {
                    text: 'SpO2'
                }
            },
            legend: {
                enabled: false
            },
            plotOptions: {

            },

            series: [{
                type: 'spline',
                name: 'SpO2',
                data: data
            }]
        });
    });
}

function renderPulseData() {
    $('.history-wrapper').hide();
    $('#pulse-wrapper').show()
    $('.btn.active').removeClass('active');
    $('#pulse-btn').addClass('active');

    $.getJSON('http://localhost:3000/readings/chart?device_id='+window.vitalo_device_id+'&sensor=pulse', function (data) {

        $('#pulse-chart').highcharts({
            chart: {
                zoomType: 'x'
            },
            title: {
                text: 'Pulse'
            },
            subtitle: {
                text: document.ontouchstart === undefined ?
                    'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
            },
            xAxis: {
                type: 'datetime'
            },
            yAxis: {
                title: {
                    text: 'Pulse'
                }
            },
            legend: {
                enabled: false
            },
            plotOptions: {

            },

            series: [{
                type: 'spline',
                name: 'Pulse',
                data: data
            }]
        });
    });
}

function renderMovementData() {
    $('.history-wrapper').hide();
    $('#movement-wrapper').show()
    $('.btn.active').removeClass('active');
    $('#movement-btn').addClass('active');

    $.getJSON('http://localhost:3000/readings/chart?device_id='+window.vitalo_device_id+'&sensor=movement', function (data) {

        $('#movement-chart').highcharts({
            chart: {
                zoomType: 'x'
            },
            title: {
                text: 'Movement'
            },
            subtitle: {
                text: document.ontouchstart === undefined ?
                    'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
            },
            xAxis: {
                type: 'datetime'
            },
            yAxis: {
                title: {
                    text: 'Movement'
                }
            },
            legend: {
                enabled: false
            },
            plotOptions: {

            },

            series: [{
                type: 'spline',
                name: 'Movement',
                data: data
            }]
        });
    });
}