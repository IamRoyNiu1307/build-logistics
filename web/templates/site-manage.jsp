<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Zoo物流 河南管理中心-站点管理</title>
<link rel="shortcut icon" href="img/favicon.ico">
<link rel="stylesheet" href="css/city-picker.css">
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

			<div class="row">
				<div class="col-md-12">

					<!--*****	站点信息	 *****-->
					<div class="card form" id="form1">
						<div class="card-header">
							<h3>
								<i class="icon icon-bill"></i>&nbsp;&nbsp;站点管理中心
							</h3>
						</div>
						<br>
						<form class="form-inline">
							<div class="col-md-3">
								<div class="form-group">
									<label for="site-number">站点编号：</label> <input type="text"
										class="form-control w-100" id="site-number"
										placeholder="请输入要查询的车牌号">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="city-picker2">站点地址</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input id="city-picker2" class="form-control w-100" type="text"
										value="河南省/郑州市/金水区" data-toggle="city-picker">
								</div>

							</div>

							<!--<div class="col-md-3">
									<div class="form-group">

										<label for="dtp_input2">购买日期:</label><br />
										<div class="input-group date form_date" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
											<input class="form-control" size="16" type="text" value="" readonly id="buy-date1">
											<span class="input-group-addon"><i class="icon icon-interface-windows"></i><span class="glyphicon glyphicon-calendar" style="width: 0px;"></span></span>
											<span class="input-group-addon"><i class="icon icon-close" id="close1"></i></span>

										</div>
										<input type="hidden" id="dtp_input2" value="" /><br/>
									</div>
								</div>-->
							<div class="col-md-1">
								<div class="form-group">
									<button class="btn btn-boxs btn-2"
										style="margin-bottom: -10px; width: 100px; height: 50px; padding-top: 3px;">
										<b>查&nbsp;&nbsp;询</b>
									</button>

								</div>
							</div>

							<div class="col-md-2">
								<div class="form-group">
									<!--<button class="btn btn-boxs btn-4" style="margin-bottom: -10px;width: 100px; height: 50px; padding-top: 3px;" data-toggle="modal" data-target="#add-site"><b>新增站点</b></button>-->
									<a class="btn btn-boxs btn-4" href="#add-site"
										style="margin-bottom: -10px; width: 100px; height: 50px; padding-top: 3px;"
										data-toggle="modal">新增站点</a>
								</div>
							</div>

							<br /> <br /> <br /> <br /> <br /> <br />

							<div class="col-md-12">
								<h4 style="border-bottom: 1px solid #ddd;">站点信息</h4>
							</div>
							<div class="col-md-12">
								<table class="table table-hover">
									<thead>
										<tr class="bg-info text-white">
											<th>站点编号</th>
											<th>所在地址</th>
											<th>联系电话</th>
											<th>站点信息修改</th>
											<th>删除站点</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<th scope="row">001</th>
											<td>XXXXXXXXXXXXXXXXX</td>
											<td>XXXX-XXXXX</td>
											<td><a href="#update-site" style="margin-left: 10px;"
												data-toggle="modal">修改</a></td>
											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
										<tr class="table-warning">
											<th scope="row">002</th>
											<td>XXXXXXXXXXXXXXXXX</td>
											<td>XXXX-XXXXX</td>
											<td><a href="#update-site" style="margin-left: 10px;"
												data-toggle="modal">修改</a></td>
											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
										<tr>
											<th scope="row">003</th>
											<td>XXXXXXXXXXXXXXXXX</td>
											<td>XXXX-XXXXX</td>
											<td><a href="#update-site" style="margin-left: 10px;"
												data-toggle="modal">修改</a></td>
											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
										<tr class="table-success">
											<th scope="row">004</th>
											<td>XXXXXXXXXXXXXXXXX</td>
											<td>XXXX-XXXXX</td>
											<td><a href="#update-site" style="margin-left: 10px;"
												data-toggle="modal">修改</a></td>
											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
										<tr>
											<th scope="row">005</th>
											<td>XXXXXXXXXXXXXXXXX</td>
											<td>XXXX-XXXXX</td>
											<td><a href="#update-site" style="margin-left: 10px;"
												data-toggle="modal">修改</a></td>
											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
										<tr class="table-danger">
											<th scope="row">006</th>
											<td>XXXXXXXXXXXXXXXXX</td>
											<td>XXXX-XXXXX</td>
											<td><a href="#update-site" style="margin-left: 10px;"
												data-toggle="modal">修改</a></td>
											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
										<tr>
											<th scope="row">007</th>
											<td>XXXXXXXXXXXXXXXXX</td>
											<td>XXXX-XXXXX</td>
											<td><a href="#update-site" style="margin-left: 10px;"
												data-toggle="modal">修改</a></td>
											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
										<tr class="table-info">
											<th scope="row">008</th>
											<td>XXXXXXXXXXXXXXXXX</td>
											<td>XXXX-XXXXX</td>
											<td><a href="#update-site" style="margin-left: 10px;"
												data-toggle="modal">修改</a></td>
											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
										<tr>
											<th scope="row">009</th>
											<td>XXXXXXXXXXXXXXXXX</td>
											<td>XXXX-XXXXX</td>
											<td><a href="#update-site" style="margin-left: 10px;"
												data-toggle="modal">修改</a></td>
											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
										<tr class="table-warning">
											<th scope="row">010</th>
											<td>XXXXXXXXXXXXXXXXX</td>
											<td>XXXX-XXXXX</td>
											<td><a href="#update-site" style="margin-left: 10px;"
												data-toggle="modal">修改</a></td>
											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
										<tr>
											<th scope="row">011</th>
											<td>XXXXXXXXXXXXXXXXX</td>
											<td>XXXX-XXXXX</td>
											<td><a href="#update-site" style="margin-left: 10px;"
												data-toggle="modal">修改</a></td>
											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
										<tr class="table-success">
											<th scope="row">012</th>
											<td>XXXXXXXXXXXXXXXXX</td>
											<td>XXXX-XXXXX</td>
											<td><a href="#update-site" style="margin-left: 10px;"
												data-toggle="modal">修改</a></td>
											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
									</tbody>
								</table>
							</div>

							<div class="col-md-12" align="center">
								<a href="#" class="btn btn-outline-info"
									style="width: 110px; height: 55px; margin-right: 0px;">首页</a> <a
									href="#" class="btn btn-outline-info"
									style="width: 110px; height: 55px; margin-right: 0px;">上一页</a>
								<a href="#" class="btn btn-outline-info"
									style="width: 110px; height: 55px; margin-right: 0px;">下一页</a>
								<a href="#" class="btn btn-outline-info"
									style="width: 110px; height: 55px;">尾页</a>
							</div>

							<!-- 模态框（Modal） update-site   -->
							<div class="modal fade" id="update-site" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
								style="z-index: 10000;">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">
										<div class="modal-header">

											<h4 class="modal-title" id="myModalLabel">
												<i class="icon" data-icon="g"></i>&nbsp;&nbsp;修改站点信息
											</h4>
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
										</div>
										<div class="modal-body">

											<!--*****	站点信息	 *****-->
											<div class="card form" id="form1">

												<form>
													<div class="row">
														<div class="col-md-6">
															<div class="form-group">
																<label for="site-number">站点编号</label> <input type="text"
																	class="form-control w-100" id="site-number"
																	aria-describedby="emailHelp" placeholder="站点编号">
															</div>
															<br />
															<div class="form-group">
																<label for="detailed-address">详细地址</label> <input
																	type="text" class="form-control w-100"
																	id="detailed-address" aria-describedby="emailHelp"
																	placeholder="详细地址">
															</div>
															<br />
															<div class="form-group">
																<label for="phone">联系电话</label> <input type="text"
																	class="form-control w-100" id="phone"
																	aria-describedby="emailHelp" placeholder="联系电话">
															</div>
															<br />

															<div class="form-group">

																<label for="first-price">首重价格（￥）</label> <input
																	type="text" class="form-control w-100" id="first-price"
																	aria-describedby="emailHelp" placeholder="首重价格">
															</div>
															<br />

														</div>
														<br />
														<div class="col-md-6">
															<div class="form-group">
																<label for="city-picker2">站点地址</label> <input
																	id="city-picker2" class="form-control w-100"
																	type="text" value="河南省/郑州市/金水区"
																	data-toggle="city-picker">

															</div>
															<br />
															<div class="form-group">
																<label for="Site-manager">站点负责人</label> <input
																	type="text" class="form-control w-100"
																	id="Site-manager" aria-describedby="emailHelp"
																	placeholder="负责人姓名">
															</div>
															<br />
															<div class="form-group">
																<label for="first-heavy">首重范围（KG）</label> <input
																	type="text" class="form-control w-100" id="first-heavy"
																	aria-describedby="emailHelp" placeholder="首重范围">
															</div>
															<br />
															<div class="form-group">
																<label for="second-price">次KG价格（￥）</label> <input
																	type="text" class="form-control w-100"
																	id="second-price" aria-describedby="emailHelp"
																	placeholder="次KG价格">
															</div>
															<br />
														</div>
														<br />
													</div>
													<div class="modal-footer">
														<button type="reset" class="btn btn-info">清空</button>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<button type="button" class="btn btn-default"
															data-dismiss="modal">关闭</button>
														<button type="submit" class="btn btn-primary">提交更改</button>
													</div>
												</form>
											</div>

										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>

							<!--模态窗 - add-site-->
							<div class="modal fade" id="add-site" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true"
								style="z-index: 10000;">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">
										<div class="modal-header">

											<h4 class="modal-title" id="myModalLabel">
												<i class="icon" data-icon="g"></i>&nbsp;&nbsp;新增站点登记
											</h4>
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
										</div>
										<div class="modal-body">

											<!--*****	站点信息	 *****-->
											<div class="card form" id="form1">

												<form>
													<div class="row">
														<div class="col-md-6">
															<div class="form-group">
																<label for="site-number">站点编号</label> <input type="text"
																	class="form-control w-100" id="site-number"
																	aria-describedby="emailHelp" placeholder="站点编号">
															</div>
															<br />
															<div class="form-group">
																<label for="detailed-address">详细地址</label> <input
																	type="text" class="form-control w-100"
																	id="detailed-address" aria-describedby="emailHelp"
																	placeholder="详细地址">
															</div>
															<br />
															<div class="form-group">
																<label for="phone">联系电话</label> <input type="text"
																	class="form-control w-100" id="phone"
																	aria-describedby="emailHelp" placeholder="联系电话">
															</div>
															<br />

															<div class="form-group">

																<label for="first-price">首重价格（￥）</label> <input
																	type="text" class="form-control w-100" id="first-price"
																	aria-describedby="emailHelp" placeholder="首重价格">
															</div>
															<br />

														</div>
														<br />
														<div class="col-md-6">
															<div class="form-group">
																<label for="city-picker2">站点地址</label> <input
																	id="city-picker2" class="form-control" type="text"
																	value="河南省/郑州市/金水区" data-toggle="city-picker">

															</div>
															<br />
															<div class="form-group">
																<label for="Site-manager">站点负责人</label> <input
																	type="text" class="form-control w-100"
																	id="Site-manager" aria-describedby="emailHelp"
																	placeholder="负责人姓名">
															</div>
															<br />
															<div class="form-group">
																<label for="first-heavy">首重范围（KG）</label> <input
																	type="text" class="form-control w-100" id="first-heavy"
																	aria-describedby="emailHelp" placeholder="首重范围">
															</div>
															<br />
															<div class="form-group">
																<label for="second-price">次KG价格（￥）</label> <input
																	type="text" class="form-control w-100"
																	id="second-price" aria-describedby="emailHelp"
																	placeholder="次KG价格">
															</div>
															<br />
														</div>
														<br />
													</div>
													<div class="modal-footer">
														<button type="reset" class="btn btn-info">清空</button>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<button type="button" class="btn btn-default"
															data-dismiss="modal">关闭</button>
														<button type="submit" class="btn btn-primary">提交更改</button>
													</div>
												</form>
											</div>

										</div>

									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal -->
							</div>

						</form>

					</div>

				</div>

			</div>

		</div>
	</div>
</body>
<script src="/static/js/city-picker.data.js"></script>
<script src="/static/js/city-picker.js"></script>
<script src="/static/js/bootstrap.min.js"></script>


</html>