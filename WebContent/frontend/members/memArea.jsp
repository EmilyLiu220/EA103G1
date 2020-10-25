<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.wel_record.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>

<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");

	WelRecordService welRecordSvc = new WelRecordService();
	List <WelRecordVO> list = welRecordSvc.getWelRecordByMemID(memVO.getMem_id());
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">

<head>
<!-- icon -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://cdnjs.cloudflare.com/ajax/libs/material-design-iconic-font/2.2.0/css/material-design-iconic-font.min.css"> -->
<link href="<%=request.getContextPath()%>/frontend/template/Caroline/material-design-iconic-font/css/material-design-iconic-font.min.css" rel="stylesheet">

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

</head>



<body>

	<!-- preloader -->
	<div id="preloader">
		<div class="spinner spinner-round"></div>
	</div>
	<!-- / preloader -->
	<div id="top"></div>
	<!-- header -->

	<header>
	
		<%@include file="/frontend/bar/frontBarTop.jsp"%>

		<!-- header-banner -->
		<div id="header-banner"
			style="background-size: Contain; background-repeat: no-repeat; background-position: center; background-color: white">
			<div class="banner-content single-page text-center">
				<div class="banner-border">
					<div class="banner-info">
						<h1 style="color: #272727">My Account</h1>
					</div>
					<!-- / banner-info -->
				</div>
				<!-- / banner-border -->
			</div>
			<!-- / banner-content -->
		</div>
		<!-- / header-banner -->
	</header>
	<!-- / header -->
	<!-- content -->
	<!-- my-account -->
	<section id="my-account">
		<div class="container">
			<div class="row">
				<div class="col-sm-2 account-sidebar">
					<img
						src="<%=request.getContextPath()%>/members/headphotoHandler.do?action=getPic&mem_id=<%=memVO.getMem_id()%>"
						alt="">
					<p>
						<a href="#personal-info" class="page-scroll">編輯個人資料</a>
					</p>
					<p>
						<a href="#shippingArea" class="page-scroll">編輯收件人資料</a>
					</p>

					<p>
						<a href="#accountArea" class="page-scroll">帳號管理</a>
					</p>
					<p>
						<a href="#welRecordArea" class="page-scroll">錢包管理</a>
					</p>

				</div>
				<!-- / account-sidebar -->
				<div class="col-sm-10 account-info">
					<div id="personal-info" class="account-info-content">
						<h4>編輯個人資料&emsp;&emsp; ${sessionScope.memVO.m_accno}</h4>


						<div class="row">
							<div class="col-md-2" style="padding-right: 0px;">

								<div></div>

								
									<img id="headphoto"
										src="<%=request.getContextPath()%>/members/headphotoHandler.do?action=getPic&mem_id=<%=memVO.getMem_id()%>"
										alt="" style="width: 500px;">
						
								<button id="headphotoBtn"
									class="btn btn-primary btn-xs btn-rounded no-margin"
									type="button" style="font-weight: bold;" data-toggle="modal"
									data-target="#photoModal">
									<i class="fas fa-camera"></i>更換頭像
								</button>



<!-- 								<div>&emsp;</div> -->

<!-- 								<button class="btn btn-primary btn-xs btn-rounded no-margin" -->
<!-- 									type="button" style="font-weight: bold;" data-toggle="collapse" -->
<!-- 									data-target="#personalIntro" aria-expanded="false" -->
<!-- 									aria-controls="personalIntro"> -->
<!-- 									<i class="lnr lnr-pencil"></i>修改簡介 -->
<!-- 								</button> -->
<!-- 								<div>&emsp;</div> -->
<!-- 								<div class="collapse" id="personalIntro"> -->

<!-- 									<div class="form-group"> -->
<!-- 										<textarea class="form-control" -->
<!-- 											id="exampleFormControlTextarea1" rows="8" -->
<!-- 											style="margin: 0px; width: 100%; background-color: white"> 不要先入為主覺得coding很複雜，實際上，coding可能比你想的還要更複雜。coding絕對是史無前例的。本人也是經過了深思熟慮，在每個日日夜夜思考這個問題。老舊的想法已經過時了。這種事實對本人來說意義重大，相信對這個世界也是有一定意義的。</textarea> -->
<!-- 									</div> -->

<!-- 									<div style="text-align: center"> -->
<!-- 										<button type="submit" class="btn btn-primary-filled btn-pill">提交</button> -->
<!-- 									</div> -->

<!-- 								</div> -->




							</div>
							<div class="col-md-10">
								<!-- 							第一行姓名開始 -->
								<FORM METHOD="POST"
									ACTION="<%=request.getContextPath()%>/frontend/members/mem.do">
									<div class="row">
										<div class="col-md-2"
											style="text-align: right; margin-top: 13px;">姓名:</div>
										<div class="col-md-9">
											<input type="text" class="form-control rounded" id="name"
												value="<%=memVO.getM_name()%>"
												style="margin-bottom: 10px; background-color: white"
												name="name">
										</div>



									</div>
									<!-- 							第一行姓名結束-->
									<div class="row">
										<div class="col-md-2"
											style="text-align: right; margin-top: 13px;">性別:</div>
										<div class="col-md-9">
											<select name="gender" id="gender" class="form-control"
												style="margin-bottom: 10px; background-color: white">
												<option value="" disabled>性別</option>
												<option value="M"
													<c:if test="${memVO.m_gender=='M'}">selected</c:if>>男性</option>
												<option value="F"
													<c:if test="${memVO.m_gender=='F'}">selected</c:if>>女性</option>
											</select>
										</div>



									</div>
									<!-- 							第二行性別結束-->
									<div class="row">
										<div class="col-md-2"
											style="text-align: right; margin-top: 13px;">生日:</div>
										<div class="col-md-9">
											<input type="text" class="form-control rounded" id="birthday"
												value="<%=memVO.getM_bday()%>" name="birthday"
												style="margin-bottom: 10px; background-color: white">
										</div>



									</div>
									<!-- 							第三行生日結束-->
									<div class="row">
										<div class="col-md-2"
											style="text-align: right; margin-top: 13px;">電話:</div>
										<div class="col-md-9">
											<input type="text" class="form-control rounded" id="phone"
												name="phone"
												value="<%=(memVO.getM_phone() == null) ? "" : memVO.getM_phone()%>"
												style="margin-bottom: 10px; background-color: white">
										</div>
									</div>


									<!-- 							第四行電話結束-->
									<div class="row">
										<div class="col-md-2"
											style="text-align: right; margin-top: 13px;">手機:</div>
										<div class="col-md-9">
											<input type="text" class="form-control rounded" id="mobile"
												value="<%=memVO.getM_mobile()%>" name="mobile"
												style="margin-bottom: 10px; background-color: white">
										</div>
									</div>
									<!-- 							第五行手機結束-->
									<div class="row">
										<div class="col-md-2"
											style="text-align: right; margin-top: 13px;">電子信箱:</div>
										<div class="col-md-9">
											<input type="email" class="form-control rounded" id="email"
												value="<%=memVO.getM_email()%>" name="email"
												style="margin-bottom: 10px; background-color: white">
										</div>
									</div>
									<!-- 							第六行電子郵箱結束-->
									<div class="row">
										<div class="col-md-2"
											style="text-align: right; margin-top: 13px;">聯絡地址:</div>
										<div class="col-md-4">


											<select name="contactZip" id="contactZip"
												class="form-control"
												style="background-color: white; margin-bottom: 10px">
												<option value="" disabled selected>郵遞區號</option>
												<option value="100 台北市 中正區">100 台北市 中正區</option>
												<option value="103 台北市 大同區">103 台北市 大同區</option>
												<option value="104 台北市 中山區">104 台北市 中山區</option>
												<option value="105 台北市 松山區">105 台北市 松山區</option>
												<option value="106 台北市 大安區">106 台北市 大安區</option>
												<option value="234 新北市 永和區">234 新北市 永和區</option>
												<option value="235 新北市 中和區">235 新北市 中和區</option>
												<option value="236 新北市 土城區">236 新北市 土城區</option>
												<option value="237 新北市 三峽區">237 新北市 三峽區</option>
												<option value="238 新北市 樹林區">238 新北市 樹林區</option>
												<option value="239 新北市 鶯歌區">239 新北市 鶯歌區</option>
												<option value="241 新北市 三重區">241 新北市 三重區</option>
												<option value="242 新北市 新莊區">242 新北市 新莊區</option>
												<option value="243 新北市 泰山區">243 新北市 泰山區</option>
												<option value="244 新北市 林口區">244 新北市 林口區</option>
												<option value="247 新北市 蘆洲區">247 新北市 蘆洲區</option>
												<option value="248 新北市 五股區">248 新北市 五股區</option>
												<option value="249 新北市 八里區">249 新北市 八里區</option>
												<option value="251 新北市 淡水區">251 新北市 淡水區</option>
												<option value="252 新北市 三芝區">252 新北市 三芝區</option>
												<option value="253 新北市 石門區">253 新北市 石門區</option>
												<option value="260 宜蘭縣 宜蘭市">260 宜蘭縣 宜蘭市</option>
												<option value="261 宜蘭縣 頭城鎮">261 宜蘭縣 頭城鎮</option>
												<option value="262 宜蘭縣 礁溪鄉">262 宜蘭縣 礁溪鄉</option>
												<option value="263 宜蘭縣 壯圍鄉">263 宜蘭縣 壯圍鄉</option>
												<option value="264 宜蘭縣 員山鄉">264 宜蘭縣 員山鄉</option>
												<option value="265 宜蘭縣 羅東鎮">265 宜蘭縣 羅東鎮</option>
												<option value="266 宜蘭縣 三星鄉">266 宜蘭縣 三星鄉</option>
												<option value="267 宜蘭縣 大同鄉">267 宜蘭縣 大同鄉</option>
												<option value="268 宜蘭縣 五結鄉">268 宜蘭縣 五結鄉</option>
												<option value="269 宜蘭縣 冬山鄉">269 宜蘭縣 冬山鄉</option>
												<option value="270 宜蘭縣 蘇澳鎮">270 宜蘭縣 蘇澳鎮</option>
												<option value="272 宜蘭縣 南澳鄉">272 宜蘭縣 南澳鄉</option>
												<option value="300 新竹市 北區">300 新竹市 北區</option>
												<option value="300 新竹市 東區">300 新竹市 東區</option>
												<option value="300 新竹市 香山區">300 新竹市 香山區</option>
												<option value="300 新竹市 ">300 新竹市</option>
												<option value="302 新竹縣 竹北市">302 新竹縣 竹北市</option>
												<option value="303 新竹縣 湖口鄉">303 新竹縣 湖口鄉</option>
												<option value="304 新竹縣 新豐鄉">304 新竹縣 新豐鄉</option>
												<option value="305 新竹縣 新埔鎮">305 新竹縣 新埔鎮</option>
												<option value="306 新竹縣 關西鎮">306 新竹縣 關西鎮</option>
												<option value="307 新竹縣 芎林鄉">307 新竹縣 芎林鄉</option>
												<option value="308 新竹縣 寶山鄉">308 新竹縣 寶山鄉</option>
												<option value="310 新竹縣 竹東鎮">310 新竹縣 竹東鎮</option>
												<option value="311 新竹縣 五峰鄉">311 新竹縣 五峰鄉</option>
												<option value="312 新竹縣 橫山鄉">312 新竹縣 橫山鄉</option>
												<option value="313 新竹縣 尖石鄉">313 新竹縣 尖石鄉</option>
												<option value="314 新竹縣 北埔鄉">314 新竹縣 北埔鄉</option>
												<option value="315 新竹縣 峨眉鄉">315 新竹縣 峨眉鄉</option>
												<option value="320 桃園市 中壢區">320 桃園市 中壢區</option>
												<option value="324 桃園市 平鎮區">324 桃園市 平鎮區</option>
												<option value="325 桃園市 龍潭區">325 桃園市 龍潭區</option>
												<option value="326 桃園市 楊梅區">326 桃園市 楊梅區</option>
												<option value="327 桃園市 新屋區">327 桃園市 新屋區</option>
												<option value="328 桃園市 觀音區">328 桃園市 觀音區</option>
												<option value="330 桃園市 桃園區">330 桃園市 桃園區</option>
												<option value="333 桃園市 龜山區">333 桃園市 龜山區</option>
												<option value="334 桃園市 八德區">334 桃園市 八德區</option>
												<option value="335 桃園市 大溪區">335 桃園市 大溪區</option>
												<option value="336 桃園市 復興區">336 桃園市 復興區</option>
												<option value="337 桃園市 大園區">337 桃園市 大園區</option>
												<option value="338 桃園市 蘆竹區">338 桃園市 蘆竹區</option>
											</select>
										</div>
										<div class="col-md-5">

											<input type="text" class="form-control rounded"
												id="contactAddr" value="<%=memVO.getM_addr()%>"
												name="contactAddr"
												style="margin-bottom: 10px; background-color: white;">
										</div>
									</div>

									<!-- 							第七行居住地址結束-->


									<div style="text-align: center; margin-top: 25px;">
										<button type="submit" class="btn btn-primary-filled btn-pill">提交</button>
									</div>
									<input type="hidden" name="action" value="UpdateMem">
								</FORM>
							</div>
						</div>
					</div>
					<!--                       收件人資料區塊開始                          -->
					<div id="shippingArea" class="account-info-content">
						<h4>
							編輯收件人資料<span class="pull-right">
								<button class="btn btn-sm btn-primary btn-rounded no-margin"
									type="button" data-toggle="collapse"
									data-target="#collapseExample2" aria-expanded="false"
									aria-controls="collapseExample2" style="font-weight: bold">
									<i class="lnr lnr-pencil"></i>修改收件人資料
								</button>
							</span>


						</h4>
						<p class="space-bottom">

							收件人姓名:<br><br>
							 收件人電話:<br><br>
                                                                               收件人地址:
						</p>


						<div class="collapse" id="collapseExample2"
							style="margin: auto 50px;">
							<div class="well">
								<FORM>

									<div class="row">
										<div class="col-md-2"
											style="text-align: right; margin-top: 13px;">姓名:</div>
										<div class="col-md-9">
											<input type="text" class="form-control rounded" id="shipName"
												value=""
												style="margin-bottom: 10px; background-color: white"
												>
										</div>
									</div>

									<div class="row">
										<div class="col-md-2"
											style="text-align: right; margin-top: 13px;">手機:</div>
										<div class="col-md-9">
											<input type="text" class="form-control rounded"
												id="shoiMobile" value=""
												style="margin-bottom: 10px; background-color: white">
										</div>
									</div>

									<div class="row">
										<div class="col-md-2"
											style="text-align: right; margin-top: 13px;">收件地址:</div>
										<div class="col-md-4">

											<select name="shipZip" id="shipZip" class="form-control"
												style="background-color: white; margin-bottom: 10px">
												<option value="" disabled selected>郵遞區號</option>
												<option value="100 台北市 中正區">100 台北市 中正區</option>
												<option value="103 台北市 大同區">103 台北市 大同區</option>
												<option value="104 台北市 中山區">104 台北市 中山區</option>
												<option value="105 台北市 松山區">105 台北市 松山區</option>
												<option value="106 台北市 大安區">106 台北市 大安區</option>
												<option value="234 新北市 永和區">234 新北市 永和區</option>
												<option value="235 新北市 中和區">235 新北市 中和區</option>
												<option value="236 新北市 土城區">236 新北市 土城區</option>
												<option value="237 新北市 三峽區">237 新北市 三峽區</option>
												<option value="238 新北市 樹林區">238 新北市 樹林區</option>
												<option value="239 新北市 鶯歌區">239 新北市 鶯歌區</option>
												<option value="241 新北市 三重區">241 新北市 三重區</option>
												<option value="242 新北市 新莊區">242 新北市 新莊區</option>
												<option value="243 新北市 泰山區">243 新北市 泰山區</option>
												<option value="244 新北市 林口區">244 新北市 林口區</option>
												<option value="247 新北市 蘆洲區">247 新北市 蘆洲區</option>
												<option value="248 新北市 五股區">248 新北市 五股區</option>
												<option value="249 新北市 八里區">249 新北市 八里區</option>
												<option value="251 新北市 淡水區">251 新北市 淡水區</option>
												<option value="252 新北市 三芝區">252 新北市 三芝區</option>
												<option value="253 新北市 石門區">253 新北市 石門區</option>
												<option value="260 宜蘭縣 宜蘭市">260 宜蘭縣 宜蘭市</option>
												<option value="261 宜蘭縣 頭城鎮">261 宜蘭縣 頭城鎮</option>
												<option value="262 宜蘭縣 礁溪鄉">262 宜蘭縣 礁溪鄉</option>
												<option value="263 宜蘭縣 壯圍鄉">263 宜蘭縣 壯圍鄉</option>
												<option value="264 宜蘭縣 員山鄉">264 宜蘭縣 員山鄉</option>
												<option value="265 宜蘭縣 羅東鎮">265 宜蘭縣 羅東鎮</option>
												<option value="266 宜蘭縣 三星鄉">266 宜蘭縣 三星鄉</option>
												<option value="267 宜蘭縣 大同鄉">267 宜蘭縣 大同鄉</option>
												<option value="268 宜蘭縣 五結鄉">268 宜蘭縣 五結鄉</option>
												<option value="269 宜蘭縣 冬山鄉">269 宜蘭縣 冬山鄉</option>
												<option value="270 宜蘭縣 蘇澳鎮">270 宜蘭縣 蘇澳鎮</option>
												<option value="272 宜蘭縣 南澳鄉">272 宜蘭縣 南澳鄉</option>
												<option value="300 新竹市 北區">300 新竹市 北區</option>
												<option value="300 新竹市 東區">300 新竹市 東區</option>
												<option value="300 新竹市 香山區">300 新竹市 香山區</option>
												<option value="300 新竹市 ">300 新竹市</option>
												<option value="302 新竹縣 竹北市">302 新竹縣 竹北市</option>
												<option value="303 新竹縣 湖口鄉">303 新竹縣 湖口鄉</option>
												<option value="304 新竹縣 新豐鄉">304 新竹縣 新豐鄉</option>
												<option value="305 新竹縣 新埔鎮">305 新竹縣 新埔鎮</option>
												<option value="306 新竹縣 關西鎮">306 新竹縣 關西鎮</option>
												<option value="307 新竹縣 芎林鄉">307 新竹縣 芎林鄉</option>
												<option value="308 新竹縣 寶山鄉">308 新竹縣 寶山鄉</option>
												<option value="310 新竹縣 竹東鎮">310 新竹縣 竹東鎮</option>
												<option value="311 新竹縣 五峰鄉">311 新竹縣 五峰鄉</option>
												<option value="312 新竹縣 橫山鄉">312 新竹縣 橫山鄉</option>
												<option value="313 新竹縣 尖石鄉">313 新竹縣 尖石鄉</option>
												<option value="314 新竹縣 北埔鄉">314 新竹縣 北埔鄉</option>
												<option value="315 新竹縣 峨眉鄉">315 新竹縣 峨眉鄉</option>
												<option value="320 桃園市 中壢區">320 桃園市 中壢區</option>
												<option value="324 桃園市 平鎮區">324 桃園市 平鎮區</option>
												<option value="325 桃園市 龍潭區">325 桃園市 龍潭區</option>
												<option value="326 桃園市 楊梅區">326 桃園市 楊梅區</option>
												<option value="327 桃園市 新屋區">327 桃園市 新屋區</option>
												<option value="328 桃園市 觀音區">328 桃園市 觀音區</option>
												<option value="330 桃園市 桃園區">330 桃園市 桃園區</option>
												<option value="333 桃園市 龜山區">333 桃園市 龜山區</option>
												<option value="334 桃園市 八德區">334 桃園市 八德區</option>
												<option value="335 桃園市 大溪區">335 桃園市 大溪區</option>
												<option value="336 桃園市 復興區">336 桃園市 復興區</option>
												<option value="337 桃園市 大園區">337 桃園市 大園區</option>
												<option value="338 桃園市 蘆竹區">338 桃園市 蘆竹區</option>

											</select>
										</div>
										<div class="col-md-5">
											<input type="text" class="form-control rounded" id="shipAddr"
												value=""
												style="margin-bottom: 10px; background-color: white">
										</div>
									</div>

									<div class="row" style="text-align: center">
										<div class="col col-md-4">
											<div class="checkbox checkbox-primary space-bottom">
												<label class="hide"><input type="checkbox"></label>
												<input id="sameAsContactBtn" type="checkbox"> <label
													for="sameAsContactBtn" style="color: grey"><span><strong>同聯絡地址</strong></span></label>
											</div>
										</div>
										<div class="col col-md-5">
											<button type="submit" class="btn btn-primary-filled btn-pill">提交</button>
										</div>

									</div>


								</FORM>

							</div>
						</div>

					</div>




					<!-- / accountArea -->
					<div id="accountArea" class="account-info-content">
						<h4>
							帳號管理<span class="pull-right">
								<button id="changePwdBtn" class="btn btn-sm btn-primary btn-rounded no-margin"
									type="button" data-toggle="collapse"
									data-target="#collapseExample" aria-expanded="false"
									aria-controls="collapseExample" style="font-weight: bold">
									<i class="lnr lnr-pencil"></i>修改密碼
								</button>
							</span>


						</h4>
						<p class="space-bottom">
							<span><strong> <c:if
										test="${not empty sessionScope.memVO.m_accno}">
							加入時間:&emsp;${sessionScope.memVO.m_joindate}<br>
										<br>
							會員帳號:&emsp;${sessionScope.memVO.m_accno}
							
									</c:if></strong></span>
						</p>


						<div class="collapse" id="collapseExample"
							style="margin: auto 50px;">
							<div class="well">
								<FORM METHOD="POST"
									ACTION="<%=request.getContextPath()%>/welRecord/welRecord.do">
									<div id="pwdGroup">
										<div class="form-group">
											<div class="row" style="text-align: right;">
												<div class="col col-md-3" style="margin-top: 15px;">
													<h6>
														<label for="InputPassword1"
															style="color: #666666; line-height: 20px">目前的密碼</label>

													</h6>
												</div>
												<div class="col col-md-7">
													<input type="password" class="form-control rounded"
														id="InputPassword1" placeholder="Password"
														name="oldPassword" style="margin-bottom: 10px">
												</div>
												<div class="col col-md-2"
													style="margin-top: 5px; text-align: center">
													<button type="button" id="eye"
														class="btn btn-xs btn-default-filled btn-pill">
														<i class="zmdi zmdi-eye-off"></i>顯示密碼
													</button>
												</div>
											</div>
										</div>
										<div class="form-group">
											<div class="row" style="text-align: right;">
												<div class="col col-md-3" style="margin-top: 15px;">
													<h6>
														<label for="InputPassword2"
															style="color: #666666; line-height: 20px">新密碼</label>
													</h6>
												</div>
												<div class="col col-md-7">
													<input type="password" class="form-control rounded"
														id="InputPassword2" placeholder="Password"
														name="newPassword1" style="margin-bottom: 10px">
												</div>
												<div id="eye2" class="col col-md-2"
													style="margin-top: 18px; text-align: left"></div>
											</div>
										</div>
										<div class="form-group">
											<div class="row" style="text-align: right;">
												<div class="col col-md-3" style="margin-top: 15px;">
													<h6>
														<label for="InputPassword3"
															style="color: #666666; line-height: 20px">再次輸入新密碼</label>
													</h6>
												</div>
												<div class="col col-md-7">
													<input type="password" class="form-control rounded"
														id="InputPassword3" placeholder="Password"
														name="newPassword2" style="margin-bottom: 10px">
												</div>
												<div id="eye3" class="col col-md-2"
													style="margin-top: 18px; text-align: left"></div>
											</div>
										</div>
										<div style="text-align: center">
											<button id="submitPsw" type="button"
												class="btn btn-primary-filled btn-pill">提交</button>
										</div>
									</div>


								</FORM>
							</div>
						</div>

					</div>
					<!-- / welRecordArea -->
	
					<div id="welRecordArea" class="account-info-content">
						<h4>
							錢包管理 &emsp;<strong>目前餘額：NT$${sessionScope.memVO.balance}</strong>
							<span class="pull-right">

								<button type="button" class="btn btn-primary-filled btn-rounded"
									data-toggle="modal" data-target="#myModal1">
									<i class="fa fa-plus"></i>儲值
								</button> &emsp;
								<button type="button"
									class="btn btn-primary-filled btn-rounded no-margin"
									data-toggle="modal" data-target="#myModal2">
									<i class="fa fa-minus"></i>提領
								</button>

							</span>
						</h4>

						<br> <span style="font-size: 20px; font-weight: bold;">交易紀錄</span>

						<p>
							<c:if test="${not empty errorMsgsForMoney}">
								<div
									style="text-align: left; color: red; width: 50%; margin: 0px auto;">
									<c:forEach var="message" items="${errorMsgsForMoney}">
                            ${message}<br>
									</c:forEach>
								</div>
							</c:if>
						</p>
						<div class="table-responsive">
							<table id="example" class="hover" style="width: 100%">
								<thead>
									<tr>
										<th>編號</th>
										<th>交易種類</th>
										<th>來源訂單編號</th>
										<th>交易時間</th>
										<th>交易金額</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="welRecordVO" items="${list}" varStatus="number">

										<tr>
											<td>${number.count}</td>
											<td><c:if test="${welRecordVO.tns_src==10}">儲值</c:if> <c:if
													test="${welRecordVO.tns_src==20}">提領</c:if> <c:if
													test="${welRecordVO.tns_src==30}">平台撥款-一般購買分潤</c:if> <c:if
													test="${welRecordVO.tns_src==31}">平台撥款-預購分潤</c:if> <c:if
													test="${welRecordVO.tns_src==32}">平台撥款-競標分潤</c:if> <c:if
													test="${welRecordVO.tns_src==33}">平台撥款-見面會分潤</c:if> <c:if
													test="${welRecordVO.tns_src==34}">平台撥款-預購折扣金</c:if> 
													<c:if
													test="${welRecordVO.tns_src==35}">平台退款-一般購買</c:if>
													<c:if
													test="${welRecordVO.tns_src==36}">平台退款-預購</c:if>
													<c:if
													test="${welRecordVO.tns_src==37}">平台退款-競標</c:if>
													<c:if
													test="${welRecordVO.tns_src==38}">平台退款-見面會</c:if>
													<c:if
													test="${welRecordVO.tns_src==40}">平台扣款-一般購買訂單</c:if> <c:if
													test="${welRecordVO.tns_src==41}">平台扣款-預購訂單</c:if> <c:if
													test="${welRecordVO.tns_src==42}">平台扣款-競標訂單</c:if> <c:if
													test="${welRecordVO.tns_src==43}">平台扣款-見面會</c:if></td>
											<td><c:if test="${empty welRecordVO.order_id}">N/A</c:if>${welRecordVO.order_id}</td>
											<td>${welRecordVO.tns_time}</td>
											<td>${welRecordVO.tns_amount}</td>

										</tr>
									</c:forEach>
								</tbody>
							</table>


							<!-- Button trigger modal -->



							<div class="panel-group tabbed" style="margin-top: 20px">

								<div class="panel">

									<div class="panel-heading">
										<a class="panel-title collapsed" data-toggle="collapse"
											href="#panel4" style="font-weight: bold">進階訂單查詢</a>
									</div>
									<div id="panel4" class="panel-collapse collapse">
										<div class="panel-body text-gray">
											<button class="btn btn-sm btn-warning-filled btn-rounded">一般訂單查詢</button>
											<button class="btn btn-sm btn-warning-filled btn-rounded">競標訂單查詢</button>
											<button class="btn btn-sm btn-warning-filled btn-rounded">預購訂單查詢</button>
											<button class="btn btn-sm btn-warning-filled btn-rounded">見面會查詢</button>
										</div>
									</div>
								</div>
								<!-- / panel -->
							</div>



						</div>



						<!-- 儲值視窗 -->
						<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel">
							<div class="modal-dialog" role="document">
								<div class="modal-content">


									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h5 style="text-align: center">儲值視窗</h5>
									</div>

									<FORM METHOD="POST"
										ACTION="<%=request.getContextPath()%>/welRecord/welRecord.do">
										<div class="modal-body">

											<p>請輸入儲值金額</p>
											<div class="form-group">
												<input type="text" class="form-control" id="depositAmount"
													placeholder="1000" name="depositAmount" required
													data-error="*Please fill out this field">

												<div class="help-block with-errors"></div>



											</div>


										</div>
										<div class="modal-footer">
											<p class="space-button">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">返回</button>
												<button type="submit" class="btn btn-primary-filled">送出</button>
												<input type="hidden" name="action" value="deposit">
										</div>
									</FORM>
								</div>
							</div>
						</div>

						<!-- 提領視窗 -->
						<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h5 style="text-align: center">提領視窗</h5>
									</div>
									<FORM METHOD="POST"
										ACTION="<%=request.getContextPath()%>/welRecord/welRecord.do">
										<div class="modal-body">

											<p>請輸入提領金額</p>
											<div class="form-group">
												<input type="text" class="form-control" id="withdrawAmount"
													placeholder="1000" name="withdrawAmount" required
													data-error="*Please fill out this field">

												<div class="help-block with-errors"></div>



											</div>


										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-primary "
												data-dismiss="modal">取消</button>
											<button type="submit" class="btn btn-primary">送出</button>
										</div>
										<input type="hidden" name="action" value="withdraw">
									</FORM>
								</div>
							</div>
						</div>


					</div>


					<!-- / wishlist -->
				</div>
				<!-- / account-info -->
			</div>
			<!-- / row -->
		</div>
		<!-- / container -->
		
		<div class="alert alert-warning alert-dismissible" role="alert"
			id="alertWarn" style="display: none; position: fixed; bottom: 0;width:100%">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>密碼設定失敗</strong> 請檢查密碼格式是否正確或密碼是否一致
		</div>
		
	</section>
	<!-- / my-account -->
	<!-- / content -->
	<!-- scroll to top -->
	<a href="#top" class="scroll-to-top page-scroll is-hidden"
		data-nav-status="toggle"><i class="fa fa-angle-up"></i></a>
	<!-- / scroll to top -->
	<!-- footer -->
	<footer class="light-footer">
		<div class="widget-area">
			<div class="container">
				<div class="row">
					<div class="col-md-4 widget">
						<div class="about-widget">
							<div class="widget-title-image">
								<img src="" alt="">
							</div>
							<p>Vivamus consequat lacus quam, nec egestas quam egestas sit
								amet. Suspendisse et risus gravida tellus aliquam ullamcorper.
								Pellentesque elit dolor, ornare ut lorem nec, convallis nibh
								accumsan lacus morbi leo lipsum.</p>
						</div>
						<!-- / about-widget -->
					</div>
					<!-- / widget -->
					<!-- / first widget -->
					<div class="col-md-2 widget">
						<div class="widget-title">
							<h4>BRANDS</h4>
						</div>
						<div class="link-widget">
							<div class="info">
								<a href="#x">Brand 1</a>
							</div>
							<div class="info">
								<a href="#x">Brand 2</a>
							</div>
							<div class="info">
								<a href="#x">Brand 3</a>
							</div>
							<div class="info">
								<a href="#x">Brand 4</a>
							</div>
						</div>
					</div>
					<!-- / widget -->
					<!-- / second widget -->
					<div class="col-md-2 widget">
						<div class="widget-title">
							<h4>SUPPORT</h4>
						</div>
						<div class="link-widget">
							<div class="info">
								<a href="#x">Terms & Conditions</a>
							</div>
							<div class="info">
								<a href="#x">Shipping & Return</a>
							</div>
							<div class="info">
								<a href="faq.html">F.A.Q</a>
							</div>
							<div class="info">
								<a href="contact.html">Contact</a>
							</div>
						</div>
					</div>
					<!-- / widget -->
					<!-- / third widget -->
					<div class="col-md-4 widget">
						<div class="widget-title">
							<h4>CONTACT</h4>
						</div>
						<div class="contact-widget">
							<div class="info">
								<p>
									<i class="lnr lnr-map-marker"></i><span>Miami, S Miami
										Ave, SW 20th, Store No.1</span>
								</p>
							</div>
							<div class="info">
								<a href="tel:+0123456789"><i class="lnr lnr-phone-handset"></i><span>+0123
										456 789</span></a>
							</div>
							<div class="info">
								<a href="mailto:hello@yoursite.com"><i
									class="lnr lnr-envelope"></i><span>office@yoursite.com</span></a>
							</div>
							<div class="info">
								<i class="lnr lnr-thumbs-up"></i> <span class="social text-left">
									<a class="no-margin" href="#"><i class="fa fa-facebook"></i></a>
									<a href="#"><i class="fa fa-twitter"></i></a> <a href="#"><i
										class="fa fa-google-plus"></i></a> <a href="#"><i
										class="fa fa-linkedin"></i></a> <a href="#"><i
										class="fa fa-pinterest"></i></a>
								</span>
							</div>
						</div>
						<!-- / contact-widget -->
					</div>
					<!-- / widget -->
					<!-- / fourth widget -->
				</div>
				<!-- / row -->
			</div>
			<!-- / container -->
		</div>
		<button type="button" id="myTest" class="btn btn-primary btn-lg"
			data-toggle="modal" data-target="#myModal3" style="display: none;">
			Launch demo modal</button>
		<!-- Modal -->
		<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">密碼修改成功通知</h4>
					</div>
					<div class="modal-body" style="text-align: center">
						密碼修改成功，下次登入請使用新密碼</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
					</div>
				</div>
			</div>
		</div>

		<!-- photoModal -->
		<div class="modal fade" id="photoModal" tabindex="-1" role="dialog"
			aria-labelledby="photoModal">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">編輯個人頭像</h4>
					</div>
					<FORM action="<%=request.getContextPath()%>/members/headphotoHandler.do"
						method=post enctype="multipart/form-data">
						<div class="modal-body" style="text-align: center">

							<img id="preview"
								src="<%=request.getContextPath()%>/members/headphotoHandler.do?action=getPic&mem_id=<%=memVO.getMem_id()%>"
								style="width: 400px;">


							<div style="margin-left: 35%">
								<input type="file" id="myFile" name="headPhoto">
							</div>
							<input type="hidden" name="action" value="uploadPhoto">

						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>

							<button type="submit" class="btn btn-primary-filled">確認更換</button>

						</div>
					</FORM>
				</div>
			</div>
		</div>
		<!--  alert-area -->
	


		<!-- / widget-area -->
		<div class="footer-info">
			<div class="container">
				<div class="pull-left copyright">
					<p>
						<strong>© MS - MINIMAL SHOP THEME</strong>
					</p>
				</div>

			</div>
			<!-- / container -->
		</div>
		<!-- / footer-info -->
	</footer>
	<!-- / footer -->

	<%@include file="/frontend/bar/frontBarFooter.jsp"%>

</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Date m_bday = null;
	try {
		m_bday = memVO.getM_bday();
	} catch (Exception e) {
		m_bday = new java.sql.Date(System.currentTimeMillis());
	}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/frontend/members/datetimepicker/jquery.datetimepicker.css" />
<script
	src="<%=request.getContextPath()%>/frontend/members/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/frontend/members/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#birthday').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=m_bday%>',   // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
       
        
</script>

<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	
	$(document).ready(function(){
		//取得聯絡地址及郵遞區號
        	$.ajax({
        		url: "<%=request.getContextPath()%>/frontend/members/mem.do",
				type: "POST",
				data: {
					action: "getContactZip"},
				success: function(data) {
				
					var memVO = JSON.parse(data);
	
					var zip = memVO.m_zip;
					var city = memVO.m_city;
					
					$('#contactZip option[value=\''+zip+' '+city+'\']').attr('selected', true);
					
				}
			});
		//傳送、取得收件地址及郵遞區號	
//         	$.ajax({
<%--         		url: "<%=request.getContextPath()%>/frontend/members/mem.do", --%>
// 								type : "POST",
// 									data : {
// 										action : "getShipZipNAddr",
// 									},
// 									success : function(data) {

// 										$(
// 												'#contactZip option[value=\''+ data + '\']').attr(
// 												'selected', true);
// 									}
// 								});

					});

	
	$("#sameAsContactBtn").click(function() {

		$("#shipName").value($("#name").value());	
		$("shipmobile").value($("#mobile").value());	
		$('#shipZip option[value=\''+$("#contactZip").value()+'\']').attr('selected', true);
		$("#shipAddr").value($("#contactAddr").value());	
		
		});
	
	$("#submitPsw").click(function() {
		$.ajax({
    		url: "<%=request.getContextPath()%>/frontend/members/memLoginHandler.do",
									type : "POST",
									data : {
										action : "changePwd",
										oldPassword : $("#InputPassword1")
												.val(),
										newPassword1 : $("#InputPassword2")
												.val(),
										newPassword2 : $("#InputPassword3")
												.val(),
									},
									success : function(data) {
										$("#InputPassword1").val("");
										$("#InputPassword2").val("");
										$("#InputPassword3").val("");
										
										if (data === 'true') {
											$('#myTest').trigger('click');
											$('#alertWarn').css("display",
											"none");
											$('#changePwdBtn').trigger('click');
										}

										else {
											$('#alertWarn').css("display",
													"block");
										}

									}

								});

					});
</script>
<script>
	$("#headphoto").click(function() {

		$('#headphotoBtn').trigger('click');

	});

	$("#myFile").change(function() {

		var inputfile = $('#myFile')[0].files[0];

		if (inputfile.type.indexOf('image') != -1) {

			var reader = new FileReader();

			reader.addEventListener('load', function(e) {

				$("#preview").attr('src', e.target.result);
				//<img src="讀取到的結果e.target.result" >

			});

			reader.readAsDataURL(inputfile);
			//使用FileReader的readAsDataURL方法將取得的檔案傳入到預覽區塊

		} else {
			alert('請選擇圖片檔案');

		}

	});

	$("#eye").click(function() {

		if ($("#pwdGroup input").attr("type") === "password")

		{
			$("#pwdGroup input").attr("type", "text");
			$("#eye").html("<i class=\"zmdi zmdi-eye-off\"></i>隱藏密碼");

		} else {
			$("#pwdGroup input").attr("type", "password");
			$("#eye").html('<i class=\"zmdi zmdi-eye\"></i>顯示密碼');
		}
	});
</script>




</html>