<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Zoo物流 河南管理中心-车辆管理</title>
<link rel="shortcut icon" href="img/favicon.ico">
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

					<!--*****	车辆信息	 *****-->
					<div class="card form" id="form1">
						<div class="card-header">
							<h3>
								<i class="icon icon-bill"></i>&nbsp;&nbsp;车辆管理中心
							</h3>
						</div>
						<br>
						<form class="form-inline">
							<div class="col-md-3">
								<div class="form-group">
									<label for="license-plate-number">车牌号：</label> <input
										type="text" class="form-control w-100"
										id="license-plate-number" placeholder="请输入要查询的车牌号">
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label for="car-type">车辆类型:</label>
									<!-- 所属单位地点 -->
									<select class="form-control w-100" id="car-type"
										style="width: 200px;">
										<option value="">请选择</option>
										<option value="BC">大型货车</option>
										<option value="MC">中型货车</option>
										<option value="LC">小型货车</option>
									</select>
								</div>
							</div>


							<div class="col-md-2">
								<div class="form-group">
									<label for="car-state-type">车辆状态:</label>
									<!-- 所属单位地点 -->
									<select class="form-control w-100" id="car-state-type"
										style="width: 200px;">
										<option value="">请选择</option>
										<option value="free">空闲</option>
										<option value="work">工作</option>
										<option value="service">维修</option>
									</select>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<button class="btn btn-boxs btn-2"
										style="margin-bottom: -10px; width: 100px; height: 50px; padding-top: 3px; text-align: center;">
										<b>查&nbsp;&nbsp;询</b>
									</button>

								</div>
							</div>
							<div class="col-md-1">
								<div class="form-group"></div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<!--<button class="btn btn-boxs btn-4" style="margin-bottom: -10px;width: 100px; height: 50px; padding-top: 3px;" data-toggle="modal" data-target="#add-car"><b>添加车辆</b></button>-->
									<a href="#add-car" class="btn btn-boxs btn-4"
										style="margin-bottom: -10px; width: 100px; height: 50px; padding-top: 13px; text-align: center;"
										data-toggle="modal">添加车辆</a>
								</div>
							</div>

							<br /> <br /> <br /> <br /> <br /> <br />

							<div class="col-md-12">
								<h4 style="border-bottom: 1px solid #ddd;">车辆信息</h4>
							</div>
							<div class="col-md-12">
								<table class="table table-hover">
									<thead>
										<tr class="bg-info text-white">
											<th>车辆编号</th>
											<th>车牌号</th>
											<th>当前状态</th>
											<th>淘汰车辆</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<th scope="row">1</th>
											<td>豫A 33333</td>
											<td>空闲</td>

											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
										<tr class="table-warning">
											<th scope="row">2</th>
											<td>豫A 33333</td>
											<td>空闲</td>

											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
										<tr>
											<th scope="row">3</th>
											<td>豫A 33333</td>
											<td>空闲</td>

											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
										<tr class="table-success">
											<th scope="row">4</th>
											<td>豫A 33333</td>
											<td>空闲</td>

											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
										<tr>
											<th scope="row">5</th>
											<td>豫A 33333</td>
											<td>空闲</td>

											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
										<tr class="table-danger">
											<th scope="row">6</th>
											<td>豫A 33333</td>
											<td>空闲</td>

											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
										<tr>
											<th scope="row">7</th>
											<td>豫A 33333</td>
											<td>空闲</td>

											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
										<tr class="table-info">
											<th scope="row">8</th>
											<td>豫A 33333</td>
											<td>空闲</td>

											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
										<tr>
											<th scope="row">9</th>
											<td>豫A 33333</td>
											<td>空闲</td>

											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
										<tr class="table-warning">
											<th scope="row">10</th>
											<td>豫A 33333</td>
											<td>空闲</td>

											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
										<tr>
											<th scope="row">11</th>
											<td>豫A 33333</td>
											<td>空闲</td>

											<td><a href="#" style="margin-left: 10px;">删除</a></td>
										</tr>
										<tr class="table-success">
											<th scope="row">12</th>
											<td>豫A 33333</td>
											<td>空闲</td>

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


							<!--模态窗 - add-car -->
							<div class="modal fade" id="add-car" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true"
								style="z-index: 10000;">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">
										<div class="modal-header">

											<h4 class="modal-title" id="myModalLabel">
												<i class="icon" data-icon="g"></i>&nbsp;&nbsp;新增车辆登记
											</h4>
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
										</div>
										<div class="modal-body">

											<!--*****	车辆信息	 *****-->
											<div class="card form" id="form1">

												<form>
													<div class="row">
														<div class="col-md-6">
															<div class="form-group">
																<label for="license-plate-number">车牌号</label> <input
																	type="text" class="form-control w-100"
																	id="license-plate-number" aria-describedby="emailHelp"
																	placeholder="车牌号">
															</div>
															<br />
															<div class="form-group">
																<label for="motorcycle-type">车型</label> <input
																	type="text" class="form-control w-100"
																	id="motorcycle-type" aria-describedby="emailHelp"
																	placeholder="车型">
															</div>
															<br />
															<div class="form-group">
																<label for="cargo">载货量</label> <input type="text"
																	class="form-control w-100" id="cargo"
																	aria-describedby="emailHelp" placeholder="载货量（KG）">
															</div>
														</div>

														<div class="col-md-6">

															<div class="form-group">
																<label for="Operation-number">营运证号</label> <input
																	type="text" class="form-control w-100"
																	id="Operation-number" aria-describedby="emailHelp"
																	placeholder="营运证号">
															</div>
															<br />
															<div class="form-group">
																<label for="volume">容积</label> <input type="text"
																	class="form-control w-100" id="volume"
																	aria-describedby="emailHelp" placeholder="容积（m³）">
															</div>
															<br />
															<div class="form-group" style="margin-bottom: 0px;">

																<label for="dtp_input2">购买日期</label>
																<div class="input-group date form_date w-100"
																	data-date="" data-date-format="dd MM yyyy"
																	data-link-field="dtp_input2"
																	data-link-format="yyyy-mm-dd">
																	<input class="form-control" size="16" type="text"
																		value="" readonly id="buy-date2"> <span
																		class="input-group-addon"><i
																		class="icon icon-interface-windows"></i><span
																		class="glyphicon glyphicon-calendar"
																		style="width: 0px;"></span></span> <span
																		class="input-group-addon"><i
																		class="icon icon-close" id="close2"></i></span>

																</div>
																<input type="hidden" id="dtp_input2" value="" /><br />
															</div>

														</div>

													</div>
													<br />
													<div class="row">
														<div class="col-md-12">
															<div class="form-group">
																<label for="affiliated-unit">所属单位</label>
																<!-- 所属单位地点 -->
																<select class="form-control w-100" id="affiliated-unit">
																	<option value="AU">河南</option>
																	<option value="AR">北京</option>
																	<option value="AT">上海</option>
																	<option value="BY">河北</option>
																	<option value="BE">天津</option>
																	<option value="BA">香港</option>
																	<option value="BR">山东</option>
																	<option value="BG">青海</option>
																</select>
															</div>

														</div>
													</div>
													<br />

													<div class="row">
														<div class="col-md-12">

															<div class="form-group">
																<label for="remark">备注</label>
																<textarea class="form-control w-100" rows="3"
																	id="remark"></textarea>
															</div>
															<!--<button type="submit" class="btn btn-general btn-blue mr-2">提交</button>
																<button type="reset" class="btn btn-general btn-white">清空</button>-->

														</div>
													</div>
													<div class="modal-footer">
														<button type="reset" class="btn btn-info">清空</button>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<button type="button" class="btn btn-default"
															data-dismiss="modal">关闭</button>
														<button type="submit" class="btn btn-primary">确认提交</button>
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


	<script type="text/javascript"
		src="datetimepicker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript"
		src="datetimepicker/js/locales/bootstrap-datetimepicker.fr.js"
		charset="UTF-8"></script>
	<script>
		$('.form_date').datetimepicker({
			language : 'fr',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0
		});
		$("#close1").click(function() {
			$("#buy-date1").val("");
		});
		$("#close2").click(function() {
			$("#buy-date2").val("");
		});
	</script>

</body>
</html>