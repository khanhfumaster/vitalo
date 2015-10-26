function renderSpO2Data() {
    $('.history-wrapper').hide();
    $('#spo2-wrapper').show()
    $('.btn.active').removeClass('active');
    $('#spo2-btn').addClass('active');

    if (!window.spo2Loaded) {
        addLoadingOverlay();
        $.getJSON('/readings/chart?device_id='+window.vitalo_device_id+'&sensor=spo2', function (data) {

            $('#spo2-chart').highcharts("StockChart", {
                rangeSelector: {
                    allButtonsEnabled: true,
                    selected: 0
                },
                credits: {
                    enabled: false
                },
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
                    },
                    plotLines: data.thresholds
                },
                legend: {
                    enabled: false
                },
                plotOptions: {
                    area: {
                        fillColor: {
                            linearGradient: {
                                x1: 0,
                                y1: 0,
                                x2: 0,
                                y2: 1
                            },
                            stops: [
                                [0, Highcharts.getOptions().colors[0]],
                                [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                            ]
                        },
                        marker: {
                            radius: 2
                        },
                        lineWidth: 1,
                        states: {
                            hover: {
                                lineWidth: 1
                            }
                        },
                        threshold: null
                    }
                },

                series: [
                    {
                        type: 'area',
                        name: 'SpO2',
                        id: 'dataseries',
                        data: data.results
                    },
                    {
                        type: 'flags',
                        data: data.notifications,
                        onSeries: 'dataseries',
                        shape: 'squarepin',
                        width: 45
                    }
                ]
            });

            $('#spo2-daily-chart').highcharts({
                chart: {
                    type: 'column'
                },
                credits: {
                    enabled: false
                },
                title: {
                    text: 'Daily SpO2 Average'
                },
                xAxis: {
                    "type": "datetime",
                    "minTickInterval": 86400000,
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'SpO2'
                    }
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0
                    }
                },
                series: [{
                    "name": "Daily Average",
                    "data": data.daily_average
                }]
            });

            $('#spo2-weekly-chart').highcharts({
                chart: {
                    type: 'column'
                },
                credits: {
                    enabled: false
                },
                title: {
                    text: 'Weekly SpO2 Average'
                },
                xAxis: {
                    "type": "datetime",
                    "minTickInterval": 86400000,
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'SpO2'
                    }
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0
                    }
                },
                series: [{
                    "name": "Weekly Average",
                    "data": data.weekly_average
                }]
            });

            $('#spo2-monthly-chart').highcharts({
                chart: {
                    type: 'column'
                },
                credits: {
                    enabled: false
                },
                title: {
                    text: 'Monthly SpO2 Average'
                },
                xAxis: {
                    "type": "datetime",
                    "minTickInterval": 86400000,
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'SpO2'
                    }
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0
                    }
                },
                series: [{
                    "name": "Monthly Average",
                    "data": data.monthly_average
                }]
            });

            removeLoadingOverlay();
        });

        spo2Loaded = true;
    }
}

function renderPulseData() {

    $('.history-wrapper').hide();
    $('#pulse-wrapper').show()
    $('.btn.active').removeClass('active');
    $('#pulse-btn').addClass('active');

    if (!window.pulseLoaded) {
        addLoadingOverlay();
        $.getJSON('/readings/chart?device_id='+window.vitalo_device_id+'&sensor=pulse', function (data) {

            $('#pulse-chart').highcharts("StockChart", {
                rangeSelector: {
                    allButtonsEnabled: true,
                    selected: 0
                },
                credits: {
                    enabled: false
                },
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
                    },
                    plotLines: data.thresholds
                },
                legend: {
                    enabled: false
                },
                plotOptions: {
                    area: {
                        fillColor: {
                            linearGradient: {
                                x1: 0,
                                y1: 0,
                                x2: 0,
                                y2: 1
                            },
                            stops: [
                                [0, Highcharts.getOptions().colors[0]],
                                [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                            ]
                        },
                        marker: {
                            radius: 2
                        },
                        lineWidth: 1,
                        states: {
                            hover: {
                                lineWidth: 1
                            }
                        },
                        threshold: null
                    }
                },

                series: [
                    {
                        type: 'area',
                        name: 'Pulse',
                        id: 'dataseries',
                        data: data.results
                    },
                    {
                        type: 'flags',
                        data: data.notifications,
                        onSeries: 'dataseries',
                        shape: 'squarepin',
                        width: 45
                    }
                ]
            });

            $('#pulse-daily-chart').highcharts({
                chart: {
                    type: 'column'
                },
                credits: {
                    enabled: false
                },
                title: {
                    text: 'Daily Pulse Average'
                },
                xAxis: {
                    "type": "datetime",
                    "minTickInterval": 86400000,
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'Pulse'
                    }
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0
                    }
                },
                series: [{
                    "name": "Daily Average",
                    "data": data.daily_average
                }]
            });

            $('#pulse-weekly-chart').highcharts({
                chart: {
                    type: 'column'
                },
                credits: {
                    enabled: false
                },
                title: {
                    text: 'Weekly Pulse Average'
                },
                xAxis: {
                    "type": "datetime",
                    "minTickInterval": 86400000,
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'Pulse'
                    }
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0
                    }
                },
                series: [{
                    "name": "Weekly Average",
                    "data": data.weekly_average
                }]
            });

            $('#pulse-monthly-chart').highcharts({
                chart: {
                    type: 'column'
                },
                credits: {
                    enabled: false
                },
                title: {
                    text: 'Monthly Pulse Average'
                },
                xAxis: {
                    "type": "datetime",
                    "minTickInterval": 86400000,
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'SpO2'
                    }
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0
                    }
                },
                series: [{
                    "name": "Monthly Average",
                    "data": data.monthly_average
                }]
            });

            removeLoadingOverlay();
        });
        pulseLoaded = true;

    }

}

function renderMovementData() {
    $('.history-wrapper').hide();
    $('#movement-wrapper').show()
    $('.btn.active').removeClass('active');

    $('#movement-btn').addClass('active');
    if (!window.movementLoaded) {
        addLoadingOverlay();
        $.getJSON('/readings/chart?device_id='+window.vitalo_device_id+'&sensor=movement', function (data) {

            $('#movement-chart').highcharts("StockChart", {
                rangeSelector: {
                    allButtonsEnabled: true,
                    selected: 0
                },
                credits: {
                    enabled: false
                },
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
                    },
                    plotLines: data.thresholds
                },
                legend: {
                    enabled: false
                },
                plotOptions: {
                    area: {
                        fillColor: {
                            linearGradient: {
                                x1: 0,
                                y1: 0,
                                x2: 0,
                                y2: 1
                            },
                            stops: [
                                [0, Highcharts.getOptions().colors[0]],
                                [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                            ]
                        },
                        marker: {
                            radius: 2
                        },
                        lineWidth: 1,
                        states: {
                            hover: {
                                lineWidth: 1
                            }
                        },
                        threshold: null
                    }
                },

                series: [
                    {
                        type: 'area',
                        name: 'Movement',
                        id: 'dataseries',
                        data: data.results
                    },
                    {
                        type: 'flags',
                        data: data.notifications,
                        onSeries: 'dataseries',
                        shape: 'squarepin',
                        width: 45
                    }
                ]
            });

            $('#movement-daily-chart').highcharts({
                chart: {
                    type: 'column'
                },
                credits: {
                    enabled: false
                },
                title: {
                    text: 'Daily Movement Average'
                },
                xAxis: {
                    "type": "datetime",
                    "minTickInterval": 86400000,
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'Movement'
                    }
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0
                    }
                },
                series: [{
                    "name": "Daily Average",
                    "data": data.daily_average
                }]
            });

            $('#movement-weekly-chart').highcharts({
                chart: {
                    type: 'column'
                },
                credits: {
                    enabled: false
                },
                title: {
                    text: 'Weekly Movement Average'
                },
                xAxis: {
                    "type": "datetime",
                    "minTickInterval": 86400000,
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'Movement'
                    }
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0
                    }
                },
                series: [{
                    "name": "Weekly Average",
                    "data": data.weekly_average
                }]
            });

            $('#movement-monthly-chart').highcharts({
                chart: {
                    type: 'column'
                },
                credits: {
                    enabled: false
                },
                title: {
                    text: 'Monthly Movement Average'
                },
                xAxis: {
                    "type": "datetime",
                    "minTickInterval": 86400000,
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'Movement'
                    }
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0
                    }
                },
                series: [{
                    "name": "Monthly Average",
                    "data": data.monthly_average
                }]
            });

            removeLoadingOverlay();
        });
        movementLoaded = true;

    }
}

function loadMonitor() {
    $.getJSON('/readings/monitor_chart?device_id='+window.vitalo_device_id, function (data) {
        window.lastPoll = Date.now();
        $('#monitor-spo2-chart').highcharts('StockChart', {
            chart : {
                events : {
                    load : function () {

                        // set up the updating of the chart each second
                        var spo2Series = this.series[0];
                        var pulseSeries = this.series[1];
                        var movementSeries = this.series[2];
                        setInterval(function () {

                            $.getJSON('/readings/monitor?device_id='+window.vitalo_device_id+'&time='+window.lastPoll, function(d) {
                                window.lastPoll = Date.now();

                                for (var i = 0; i < d.spo2.length; i++) {
                                    spo2Series.addPoint(d.spo2[i], true, true);
                                }

                                for (var i = 0; i < d.pulse.length; i++) {
                                    pulseSeries.addPoint(d.pulse[i], true, true);
                                }

                                for (var i = 0; i < d.movement.length; i++) {
                                    movementSeries.addPoint(d.movement[i], true, true);
                                }
                            })


                        }, 1000);


                    }
                }
            },
            legend: {
                enabled: true
            },
            rangeSelector: {
                buttons: [{
                    count: 1,
                    type: 'minute',
                    text: '1M'
                }, {
                    count: 5,
                    type: 'minute',
                    text: '5M'
                }, {
                    type: 'all',
                    text: 'All'
                }],
                inputEnabled: false,
                selected: 0
            },

            title : {
                text : 'Monitor'
            },

            exporting: {
                enabled: false
            },

            series : [{
                name : 'SpO2',
                data: data.spo2
            },{
                name : 'Pulse',
                data: data.pulse
            },{
                name : 'Movement',
                data: data.movement
            }]
        });
    })
}

