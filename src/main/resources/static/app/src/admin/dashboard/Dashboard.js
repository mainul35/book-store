var Dashboard = (function () {
    return {
        initialize: function (panel) {
            setTimeout(function () {
                ChartForOrders.initialize(panel)
            }, 300)
        }
    }
}())

var ChartForOrders = (function () {
    var presets = window.chartColors;
    var utils = Samples.utils;
    var inputs = {
        min: -100,
        max: 100,
        count: 8,
        decimals: 2,
        continuity: 1
    };

    var options = {
        maintainAspectRatio: false,
        spanGaps: false,
        elements: {
            line: {
                tension: 0.000001
            }
        },
        plugins: {
            filler: {
                propagate: false
            }
        },
        scales: {
            xAxes: [{
                ticks: {
                    autoSkip: false,
                    maxRotation: 0
                }
            }]
        }
    };
    function generateData(config) {
        return utils.numbers(Chart.helpers.merge(inputs, config || {}));
    }

    function generateLabels(config) {
        return utils.months(Chart.helpers.merge({
            count: inputs.count,
            section: 3
        }, config || {}));
    }

    return {
        initialize: function (panel) {


            utils.srand(8);
            new Chart('ChartForOrders', {
                type: 'line',
                data: {
                    labels: generateLabels(),
                    datasets: [{
                        backgroundColor: utils.transparentize(presets.red),
                        borderColor: presets.red,
                        data: generateData(),
                        label: 'Daily Order Chart',
                        fill: false
                    }]
                },
                options: Chart.helpers.merge(options, {
                    title: {
                        text: 'Orders',
                        display: true
                    }
                })
            });
        }
    }
}())