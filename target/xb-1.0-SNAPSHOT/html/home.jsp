<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>主页</title>
</head>
<body>
<jsp:include page="/incloud/header.jsp" />

<div class="d-flex align-items-stretch">
    <!-- Sidebar Navigation-->
    <jsp:include page="/incloud/sidebar.jsp" />
    <!-- Sidebar Navigation end-->
    <div class="page-content">
        <div class="page-header">
            <div class="container-fluid">
                <h2 class="h5 no-margin-bottom">主面板</h2>
            </div>
        </div>
        <section class="no-padding-top no-padding-bottom">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-3 col-sm-6">
                        <div class="statistic-block block">
                            <div class="progress-details d-flex align-items-end justify-content-between">
                                <div class="title">
                                    <strong>新增用户</strong>
                                </div>
                                <div class="number dashtext-1">${homeCount.userCount}</div>
                            </div>
                            <div class="progress progress-template">
                                <!--这个百分比由前端计算,取接近100%的数,例如 140就是接近200于是为40%-->
                                <div role="progressbar" style="width: 27%" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100" class="progress-bar progress-bar-template dashbg-1"></div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-md-3 col-sm-6">
                        <div class="statistic-block block">
                            <div class="progress-details d-flex align-items-end justify-content-between">
                                <div class="title">
                                    <strong>新增文章</strong>
                                </div>
                                <div class="number dashtext-3">${homeCount.articleCount}</div>
                            </div>
                            <div class="progress progress-template">
                                <!--这个百分比由前端计算,取接近100%的数,例如 140就是接近200于是为40%-->
                                <div role="progressbar" style="width: 40%" aria-valuenow="55" aria-valuemin="0" aria-valuemax="100" class="progress-bar progress-bar-template dashbg-3"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <div class="statistic-block block">
                            <div class="progrebarChartExample1ss-details d-flex align-items-end justify-content-between">
                                <div class="title">
                                    <strong>新开会议</strong>
                                </div>
                                <div class="number dashtext-6">${homeCount.meetingCount}</div>
                            </div>
                            <div class="progress progress-template">
                                <!--这个百分比由前端计算,取接近100%的数,例如 140就是接近200于是为40%-->
                                <div role="progressbar" style="width: 40%" aria-valuenow="55" aria-valuemin="0" aria-valuemax="100" class="progress-bar progress-bar-template dashbg-5"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="no-padding-bottom">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="line-cahrt block">
                            <!--画布可以去看charts-home.js-->
                            <canvas id="lineCahrt"></canvas>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="/incloud/footer.jsp" />
    </div>
</div>

</body>
</html>
<script>
    $(function () {
        'use strict';

        Chart.defaults.global.defaultFontColor = '#75787c';


        // ------------------------------------------------------- //
        // Line Chart
        // ------------------------------------------------------ //
        var legendState = true;
        if ($(window).outerWidth() < 576) {
            legendState = false;
        }
		
		// 当前时间
		var date = new Date();

		// 存放计算的天数
		var days = [];

		// 计算近7天的日期
		for (var i = -6; i <= 0; i++) {

			date.setDate(date.getDate() + i);

			var month = date.getMonth() + 1;
			var day = date.getDate();
			if (month < 10) {
				month = "0" + month;
			}

			if (day < 10) {
				day = "0" + day;
			}
			days.push(month + "." + day);

			// date回归原位
			date.setDate(date.getDate() - i)
		}

        var LINECHART = $('#lineCahrt');
        var myLineChart = new Chart(LINECHART, {
            type: 'line',
            options: {
                scales: {
                    xAxes: [{
                        display: true,
                        gridLines: {
                            display: false
                        }
                    }],
                    yAxes: [{
                        ticks: {
                            max: 30,
                            min: 0
                        },
                        display: true,
                        gridLines: {
                            display: false
                        }
                    }]
                },
                legend: {
                    display: legendState
                }
            },
            data: {
                labels: days,
                datasets: [
                    {
                        label: "用户",
                        fill: true,
                        lineTension: 0.2,
                        backgroundColor: "transparent",
                        borderColor: 'rgb(134, 77, 217)',
                        pointBorderColor: '#864959',
                        pointHoverBackgroundColor: '#864959',
                        borderCapStyle: 'butt',
                        borderDash: [],
                        borderDashOffset: 0.0,
                        borderJoinStyle: 'miter',
                        borderWidth: 2,
                        pointBackgroundColor: "#fff",
                        pointBorderWidth: 5,
                        pointHoverRadius: 5,
                        pointHoverBorderColor: "#fff",
                        pointHoverBorderWidth: 2,
                        pointRadius: 1,
                        pointHitRadius: 0,
                        data: ${detail.get(0)},
                        spanGaps: false
                    },

                    {
                        label: "文章",
                        fill: true,
                        lineTension: 0.2,
                        backgroundColor: "transparent",
                        borderColor: "rgb(233, 95, 113)",
                        pointBorderColor: 'rgb(233, 95, 113)',
                        pointHoverBackgroundColor: "rgb(233, 95, 113)",
                        borderCapStyle: 'butt',
                        borderDash: [],
                        borderDashOffset: 0.0,
                        borderJoinStyle: 'miter',
                        borderWidth: 2,
                        pointBackgroundColor: "#fff",
                        pointBorderWidth: 5,
                        pointHoverRadius: 5,
                        pointHoverBorderColor: "#fff",
                        pointHoverBorderWidth: 2,
                        pointRadius: 1,
                        pointHitRadius: 10,
                        data: ${detail.get(1)},
                        spanGaps: false
                    },
                    {
                        label: "会议",
                        fill: true,
                        lineTension: 0.2,
                        backgroundColor: "transparent",
                        borderColor: "rgb(0, 123, 255)",
                        pointBorderColor: 'rgb(0, 123, 255)',
                        pointHoverBackgroundColor: "rgb(0, 123, 255)",
                        borderCapStyle: 'butt',
                        borderDash: [],
                        borderDashOffset: 0.0,
                        borderJoinStyle: 'miter',
                        borderWidth: 2,
                        pointBackgroundColor: "#fff",
                        pointBorderWidth: 5,
                        pointHoverRadius: 5,
                        pointHoverBorderColor: "#fff",
                        pointHoverBorderWidth: 2,
                        pointRadius: 1,
                        pointHitRadius: 10,
                        data: ${detail.get(2)},
                        spanGaps: false
                    }
                ]
            }
        });


        var pieChartExample = {
            responsive: true
        };


    })
</script>