var spo2Loaded = pulseLoaded = movementLoaded = false;

function renderSpO2Data() {
    addLoadingOverlay();
    $('.history-wrapper').hide();
    $('#spo2-wrapper').show()
    $('.btn.active').removeClass('active');
    $('#spo2-btn').addClass('active');

    if (!spo2Loaded) {
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
            removeLoadingOverlay();
        });

        spo2Loaded = true;
    }
}

function renderPulseData() {
    addLoadingOverlay();
    $('.history-wrapper').hide();
    $('#pulse-wrapper').show()
    $('.btn.active').removeClass('active');
    $('#pulse-btn').addClass('active');

    if (!pulseLoaded) {
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
            removeLoadingOverlay();
        });
        pulseLoaded = true;

    }

}

function renderMovementData() {
    addLoadingOverlay();
    $('.history-wrapper').hide();
    $('#movement-wrapper').show()
    $('.btn.active').removeClass('active');

    $('#movement-btn').addClass('active');
    if (!movementLoaded) {
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
            removeLoadingOverlay();
        });
        movementLoaded = true;

    }
}

