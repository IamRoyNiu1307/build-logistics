<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="robots" content="all,follow">

<title>Zoo物流 河南管理中心</title>
<link rel="shortcut icon" href="img/favicon.ico">

<!-- global stylesheets -->
<link href="https://fonts.googleapis.com/css?family=Roboto+Condensed"
	rel="stylesheet">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet"
	href="font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/font-icon-style.css">
<link rel="stylesheet" href="css/style.default.css"
	id="theme-stylesheet">

<!-- Core stylesheets -->
<link rel="stylesheet" href="css/ui-elements/card.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<header class="header"> <jsp:include page="basic-top.jsp"></jsp:include>
	</header>

	<div class="page-content d-flex align-items-stretch">

		<!--***** 左侧导航栏 *****-->
		<!-- 用户栏 -->
		<nav class="side-navbar"> <jsp:include page="basic-nav.jsp"></jsp:include>
		</nav>


		<!-- 页面主体 -->
		<div class="content-inner">

			<!--***** REPORT-1	最上栏的四个信息总汇	 *****-->
			<div class="row" id="report1">
				<div class="col-sm-3">
					<div class="card">
						<div class="card-block">
							<div class="text-left report1-cont">
								<h2 class="font-light m-b-0">
									<i class="ti-arrow-up text-success"></i> 19,220件
								</h2>
								<span class="text-muted">省总仓库存储量</span>
							</div>
							<span class="text-success">66%</span>
							<div class="progress">
								<div class="progress-bar bg-success" role="progressbar"
									style="width: 66%; height: 6px;" aria-valuenow="25"
									aria-valuemin="0" aria-valuemax="100"></div>
							</div>
							<p>
								<small>预估还可存放10000件</small>
							</p>
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="card">
						<div class="card-block">
							<div class="text-left report1-cont">
								<h2 class="font-light m-b-0">
									<i class="ti-arrow-up text-danger"></i> 7,688件
								</h2>
								<span class="text-muted">省内件数</span>
							</div>
							<span class="text-danger">40%</span>
							<div class="progress">
								<div class="progress-bar bg-danger" role="progressbar"
									style="width: 40%; height: 6px;" aria-valuenow="25"
									aria-valuemin="0" aria-valuemax="100"></div>
							</div>
							<p>
								<small>占总比</small>
							</p>
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="card">
						<div class="card-block">
							<div class="text-left report1-cont">
								<h2 class="font-light m-b-0">
									<i class="ti-arrow-up text-warning"></i> 11,532件
								</h2>
								<span class="text-muted">省外件数 </span>
							</div>
							<span class="text-warning">60%</span>
							<div class="progress">
								<div class="progress-bar bg-warning" role="progressbar"
									style="width: 60%; height: 6px;" aria-valuenow="25"
									aria-valuemin="0" aria-valuemax="100"></div>
							</div>
							<p>
								<small>占总比</small>
							</p>
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="card">
						<div class="card-block">
							<div class="text-left report1-cont">
								<h2 class="font-light m-b-0">
									<i class="ti-arrow-up text-info"></i> 362件
								</h2>
								<span class="text-muted">异常件</span>
							</div>
							<span class="text-info">6%</span>
							<div class="progress">
								<div class="progress-bar bg-info" role="progressbar"
									style="width: 6%; height: 6px;" aria-valuenow="25"
									aria-valuemin="0" aria-valuemax="100"></div>
							</div>
							<p>
								<small>占总比</small>
							</p>
						</div>
					</div>
				</div>
			</div>

			<!--***** REPORT-2 多表实现	*****-->
			<div class="row" id="report2">
				<div class="col-md-4">
					<div class="card card-c1">
						<div class="card-header card-chart" data-background-color="green">
							<canvas class="ct-chart" id="myChart1" height="250"></canvas>
						</div>
						<div class="card-content">
							<h4 class="title">昨日成交额</h4>
							<p class="category">
								<span class="text-danger"><i
									class="fa fa-long-arrow-down"></i> 55% </span> 同比下降
							</p>
						</div>
						<div class="card-footer">
							<div class="stats">
								<i class="fa fa-clock-o"></i> 4分钟 前 更新
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card card-c1">
						<div class="card-header card-chart" data-background-color="orange">
							<canvas class="ct-chart" id="myChart2" height="250"></canvas>
						</div>
						<div class="card-content">
							<h4 class="title">当月成交额</h4>
							<p class="category">
								<span class="text-success"><i class="fa fa-long-arrow-up"></i>
									30% </span> 同比增长
							</p>
						</div>
						<div class="card-footer">
							<div class="stats">
								<i class="fa fa-clock-o"></i> 2天 前 更新
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card card-c1">
						<div class="card-header card-chart" data-background-color="red">
							<canvas class="ct-chart" id="myChart3" height="250"></canvas>
						</div>
						<div class="card-content">
							<h4 class="title">发往各地区物流占比</h4>
							<p class="category">上月数据</p>
						</div>
						<div class="card-footer">
							<div class="stats">
								<i class="fa fa-clock-o"></i> 2天 前 更新
							</div>
						</div>
					</div>
				</div>
			</div>

			<!--***** REPORT-3 *****-->
			<div class="row" id="report3">
				<div class="col-md-8">
					<div class="card card-c2">
						<table class="table table-hover">
							<thead>
								<div class="dropdown pull-right menu-sett-card">
									<a id="notifications" class="nav-link" rel="nofollow"
										data-target="#" href="#" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false"> <i
										class="fa fa-ellipsis-v men"></i>
									</a>
									<ul aria-labelledby="notifications" class="dropdown-menu">
										<li><a rel="nofollow" href="#"
											class="dropdown-item nav-link">Edit</a></li>
										<li><a rel="nofollow" href="#"
											class="dropdown-item nav-link">FAQ</a></li>
										<li><a rel="nofollow" href="#"
											class="dropdown-item nav-link">Support</a></li>
									</ul>
								</div>
								<tr>
									<th>订单追踪(目的地)</th>
									<th>更新时间</th>
									<th>进度 <!-- <i class="fa fa-ellipsis-v pull-right" ></i> -->
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>河南省-周口市</td>
									<td>23/03/2018</td>
									<td>
										<div class="progress">
											<div class="progress-bar bg-info" role="progressbar"
												style="width: 75%; height: 10px;" aria-valuenow="25"
												aria-valuemin="0" aria-valuemax="100"></div>
										</div>
									</td>
								</tr>
								<tr>
									<td>辽宁省-黑龙江市</td>
									<td>02/06/2018</td>
									<td>
										<div class="progress">
											<div class="progress-bar bg-info" role="progressbar"
												style="width: 100%; height: 10px;" aria-valuenow="65"
												aria-valuemin="0" aria-valuemax="100"></div>
										</div>
									</td>
								</tr>
								<tr>
									<td>北京市</td>
									<td>11/02/2018</td>
									<td>
										<div class="progress">
											<div class="progress-bar bg-info" role="progressbar"
												style="width: 95%; height: 10px;" aria-valuenow="5"
												aria-valuemin="0" aria-valuemax="100"></div>
										</div>
									</td>
								</tr>
								<tr>
									<td>福建省-福州市</td>
									<td>05/03/2018</td>
									<td>
										<div class="progress">
											<div class="progress-bar bg-info" role="progressbar"
												style="width: 75%; height: 10px;" aria-valuenow="5"
												aria-valuemin="0" aria-valuemax="100"></div>
										</div>
									</td>
								</tr>
								<tr>
									<td>陕西省-西安市</td>
									<td>24/02/2018</td>
									<td>
										<div class="progress">
											<div class="progress-bar bg-info" role="progressbar"
												style="width: 100%; height: 10px;" aria-valuenow="5"
												aria-valuemin="0" aria-valuemax="100"></div>
										</div>
									</td>
								</tr>
								<tr>
									<td>四川省-成都市</td>
									<td>01/09/2018</td>
									<td>
										<div class="progress">
											<div class="progress-bar bg-info" role="progressbar"
												style="width: 55%; height: 10px;" aria-valuenow="5"
												aria-valuemin="0" aria-valuemax="100"></div>
										</div>
									</td>
								</tr>
								<tr>
									<td>吉林省-太原市</td>
									<td>15/12/2018</td>
									<td>
										<div class="progress">
											<div class="progress-bar bg-info" role="progressbar"
												style="width: 85%; height: 10px;" aria-valuenow="5"
												aria-valuemin="0" aria-valuemax="100"></div>
										</div>
									</td>
								</tr>
								<tr>
									<td>上海</td>
									<td>18/08/2018</td>
									<td>
										<div class="progress">
											<div class="progress-bar bg-info" role="progressbar"
												style="width: 55%; height: 10px;" aria-valuenow="5"
												aria-valuemin="0" aria-valuemax="100"></div>
										</div>
									</td>
								</tr>
								<tr>
									<td>山东省-青岛市</td>
									<td>08/08/2018</td>
									<td>
										<div class="progress">
											<div class="progress-bar bg-info" role="progressbar"
												style="width: 45%; height: 10px;" aria-valuenow="5"
												aria-valuemin="0" aria-valuemax="100"></div>
										</div>
									</td>
								</tr>
								<tr>
									<td>上海</td>
									<td>18/08/2018</td>
									<td>
										<div class="progress">
											<div class="progress-bar bg-info" role="progressbar"
												style="width: 55%; height: 10px;" aria-valuenow="5"
												aria-valuemin="0" aria-valuemax="100"></div>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card card-c2">
						<div class="dropdown pull-right menu-sett-card">
							<a id="notifications" class="nav-link" rel="nofollow"
								data-target="#" href="#" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false"> <i
								class="fa fa-ellipsis-v men"></i>
							</a>
							<ul aria-labelledby="notifications" class="dropdown-menu">
								<li><a rel="nofollow" href="#"
									class="dropdown-item nav-link">Edit</a></li>
								<li><a rel="nofollow" href="#"
									class="dropdown-item nav-link">FAQ</a></li>
								<li><a rel="nofollow" href="#"
									class="dropdown-item nav-link">Support</a></li>
							</ul>
						</div>

						<!-- //发往各地区的物流占比 饼状图
                        // 东北（黑龙江省、吉林省、辽宁省）；
                        // 华东（上海市、江苏省、浙江省、安徽省、福建省、江西省、山东省、台湾省）；
                        // 华北（北京市、天津市、山西省、河北省、内蒙古自治区）；
                        // 华中（河南省、湖北省、湖南省）；
                        // 华南（广东省、广西壮族自治区、海南省、香港特别行政区、澳门特别行政区）；
                        // 西南（重庆市、四川省、贵州省、云南省、西藏自治区）；
                        // 西北（陕西省、甘肃省、青海省、宁夏回族自治区、新疆维吾尔自治区） -->
						<div class="header">
							<h4 class="title">仓库货物信息</h4>
							<p class="category">发往各地区物流占比</p>
						</div>
						<div class="content">
							<canvas class="ct-chart" id="polar-chart" height="212"></canvas>
							<!-- <canvas class="ct-chart" id="myChart4" height="250"></canvas> -->
							<div class="footer">
								<div class="legend">
									<i class="fa fa-circle" style="color: #2ecc71"></i> 东北 <i
										class="fa fa-circle" style="color: #3498db"></i> 华东 <i
										class="fa fa-circle" style="color: #95a5a6"></i> 华北 <i
										class="fa fa-circle" style="color: #9b59b6"></i> 华中 <i
										class="fa fa-circle" style="color: #f1c40f"></i> 华南 <i
										class="fa fa-circle" style="color: #e74c3c"></i> 西南 <i
										class="fa fa-circle" style="color: #34495e"></i> 西北
								</div>
								<hr>
								<div class="stats">
									<i class="fa fa-clock-o"></i> 2天 前 更新
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!--***** REPORT-4 *****-->
			<!-- <div class="row" id="report4">
                <div class="col-md-4" id="card-1">
                    <div class="card card-inverse card-info">
                        <img class="card-img-top" src="img/card/c-1.jpg">
                        <div class="card-block"> 
                            <h4 class="card-title">Clean Tech Box </h4>
                            <div class="card-text">
                                Lorem Ipsum has been the industry's standard.
                            </div>
                        </div>
                        <div class="card-footer">
                            <small>Last updated 3 mins ago</small>
                            <button class="btn btn-info float-right btn-sm">Follow</button>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 mt-0" id="card-2">
                    <div class="card hovercard">
                        <div class="cardheader"></div>
                        <div class="avatar">
                            <img alt="" src="img/avatar-1.jpg" class="img-fluid">
                        </div>
                        <div class="info">
                            <div class="title">
                                <a target="_blank" href="#">Steena Ben</a>
                            </div>
                            <div class="desc">Passionate designer</div>
                            <div class="desc">Curious developer</div>
                            <div class="desc">Tech geek</div>
                        </div>
                        <div class="bottom">
                            <a class="btn btn-primary btn-twitter btn-sm" href="https://twitter.com/webmaniac">
                                <i class="fa fa-twitter"></i>
                            </a>
                            <a class="btn btn-danger btn-sm" rel="publisher"
                               href="https://plus.google.com/+ahmshahnuralam">
                                <i class="fa fa-google-plus"></i>
                            </a>
                            <a class="btn btn-primary btn-sm" rel="publisher"
                               href="https://plus.google.com/shahnuralam">
                                <i class="fa fa-facebook"></i>
                            </a>
                            <a class="btn btn-warning btn-sm" rel="publisher" href="https://plus.google.com/shahnuralam">
                                <i class="fa fa-behance"></i>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card card-c2">
                        <div class="dropdown pull-right menu-sett-card" > 
                            <a id="notifications" class="nav-link" rel="nofollow" data-target="#" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-ellipsis-v men"></i> 
                            </a>
                            <ul aria-labelledby="notifications" class="dropdown-menu">
                                <li>
                                    <a rel="nofollow" href="#" class="dropdown-item nav-link">Edit</a>
                                </li>
                                <li>
                                    <a rel="nofollow" href="#" class="dropdown-item nav-link">FAQ</a>
                                </li>
                                <li>
                                    <a rel="nofollow" href="#" class="dropdown-item nav-link">Support</a>
                                </li> 
                            </ul>
                        </div>
                        <div class="header">
                            <h4 class="title">Newsfeed</h4>
                            <p class="category">Latest News Update</p>
                            <hr>
                        </div>
                        <div class="content newsf-list">
                            <ul class="list-unstyled">
                                <li class="border border-primary">
                                    <a rel="nofollow " href="#" class=" d-flex">
                                        <div class="news-f-img"> <img src="img/avatar-2.jpg" alt="..." class="img-fluid rounded-circle"></div>
                                        <div class="msg-body">
                                            <h3 class="h5 msg-nav-h3">New Innovation world</h3>
                                            <small>Tech soft is great innovation for...</small>
                                        </div>
                                    </a>
                                </li>
                                <li class="border border-success">
                                    <a rel="nofollow" href="#" class=" d-flex">
                                        <div class="news-f-img"> <img src="img/avatar-3.jpg" alt="..." class="img-fluid rounded-circle"></div>
                                        <div class="msg-body">
                                            <h3 class="h5 msg-nav-h3">Modified hand-cart steering</h3>
                                            <small>The idea is to incorporate easy...</small>
                                        </div>
                                    </a>
                                </li>
                                <li class="border border-info">
                                    <a rel="nofollow" href="#" class=" d-flex">
                                        <div class="news-f-img"> <img src="img/avatar-4.jpg" alt="..." class="img-fluid rounded-circle"></div>
                                        <div class="msg-body">
                                            <h3 class="h5 msg-nav-h3">Low cost Modern printer</h3>
                                            <small>A dot matrix printer modified at...</small>
                                        </div>
                                    </a>
                                </li> 

                            </ul> 
                        </div>
                    </div>
                </div> 
            </div>  -->

			<!--***** FOOTER *****-->
			<!-- <div class="row" id="report4">
                <div class="col-md-3">
                    <div class="card text-center social-bottom sb-fb">
                        <i class="fa fa-facebook"></i>
                        <div>3250 +</div>
                        <p>Likes</p>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card text-center social-bottom sb-tw">
                        <i class="fa fa-twitter"></i>
                        <div>2345 +</div>
                        <p>Following</p>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card text-center social-bottom sb-gp">
                        <i class="fa fa-google-plus"></i>
                        <div>1253 +</div>
                        <p>Followers</p>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card text-center social-bottom sb-in">
                        <i class="fa fa-instagram"></i>
                        <div>4524 +</div>
                        <p>Likes</p>
                    </div>
                </div>
           
            </div> -->

		</div>


	</div>





	<!--Core Javascript -->
	<script src="js/mychart.js"></script>
</body>
</html>