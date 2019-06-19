<#-- @ftlvariable name="" type="sk.fri.uniza.views.GraphView" -->
<!-- calls getPersons().getName() and sanitizes it -->
<div class="container">
    <br><br>
    <div class="row">
        <div class="col s12 ">
            <div class="card">
                <div class="card-content">
                    <span class="card-title">Teplota</span>
                    <div class="row">
                        <div class="col s12">
                            <canvas id="chartTemperature">

                            </canvas>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col s12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title">Vlhkosť</span>
                    <div class="row">
                        <div class="col s12">
                            <canvas id="chartHumidity">

                            </canvas>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col s12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title">Tlak Vzduchu</span>
                    <div class="row">
                        <div class="col s12">
                            <canvas id="chartPressure">

                            </canvas>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
        <br><br>
</div>

<#-- ********** Graf s teplotou-->
<script defer>
    var ctx2 = document.getElementById('chartTemperature');

    var data1 = ${getDataTempTime(100)};

    var myChart = new Chart(ctx2, {
        type: 'line',
        data: {
            labels: "",
            datasets: [{
                label: 'Teplota',
                data: data1,
                fill:false,
                backgroundColor: 'rgb(246,255,37)',
                borderColor: 'rgb(255,222,0)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            elements: { point: { radius: 0 } },
            title: {
                display: false,
                text: 'Chart.js Line Chart'
            },
            tooltips: {
                mode: 'index',
                intersect: false,
            },
            hover: {
                mode: 'nearest',
                intersect: false
            },
            scales: {
                xAxes: [{
                    type: 'time',
                    display: true
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: '°C'
                    }
                }]
            }
        }
    });
</script>



<#-- ********** Graf s Vlhkostou-->
<script defer>
    var ctx2 = document.getElementById('chartHumidity');
    var myChart = new Chart(ctx2, {
        type: 'line',
        data: {
            labels: "",

            datasets: [{
                label: 'Vlkosť',
                data: ${getDataHumTime(100)},

                fill:false,
                backgroundColor: 'rgb(44,62,255)',
                borderColor: 'rgb(131,134,255)',
                borderWidth: 1

            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,

            elements: { point: { radius: 0 } },

            title: {
                display: false,
                text: 'Chart.js Line Chart'
            },
            tooltips: {
                mode: 'index',
                intersect: false,
            },
            hover: {
                mode: 'nearest',
                intersect: false
            },
            scales: {
                xAxes: [{
                    type: 'time',
                    display: true
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: '%'
                    }
                }]
            }
        }
    });
</script>

<#-- ******* Graf tlak-->
<script defer>
    var ctx3 = document.getElementById('chartPressure');
    var myChart = new Chart(ctx3, {
        type: 'bar',
        data: {
            labels: "",

            datasets: [{
                label: 'Tlak vzduchu',
                data: ${getDataPressureTime(100)},
                fillColor:'rgb(151,255,88)',
                strokeColor: "black",
                // backgroundColor:'rgb(151,255,88)'
                //     // 'rgba(54, 162, 235, 0.2)',
                //     // 'rgba(255, 206, 86, 0.2)',
                //     // 'rgba(75, 192, 192, 0.2)',
                //     // 'rgba(153, 102, 255, 0.2)',
                //     // 'rgba(255, 159, 64, 0.2)'
                // ,
                // borderColor:
                //     'rgba(255, 99, 132, 1)'
                //     // 'rgba(54, 162, 235, 1)',
                //     // 'rgba(255, 206, 86, 1)',
                //     // 'rgba(75, 192, 192, 1)',
                //     // 'rgba(153, 102, 255, 1)',
                //     // 'rgba(255, 159, 64, 1)'
                // ,
                borderWidth: 2
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {

                xAxes: [{
                    type: 'time',
                    display: true
                }],
                yAxes: [{
                    display: true,
                    ticks: {
                        beginAtZero: false
                        // min: '800'
                    }
                }]
            }

        }
    });
</script>
