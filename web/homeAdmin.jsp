<%-- 
    Document   : homeAdmin
    Created on : Jan 26, 2021, 1:36:08 AM
    Author     : thant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
			  integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
		<script src="https://kit.fontawesome.com/1acc75252a.js" crossorigin="anonymous"></script>
		<title>Home</title>
		<style>
			html {

				box-sizing: border-box;



			}

			html *,
			html *::before,
			html *::after {

				box-sizing: inherit;

			}

			body {

				margin: 0;
				display: flex;
				height: 100vh;
				overflow: hidden;
				justify-content: center;
				-webkit-tap-highlight-color: transparent;
				transition: background-color 1s;

			}

			.menu {

				margin: 0;
				display: inline-block;
				/* Works well with 100% width  */
				width: 12.05em;
				font-size: 1em;

				position: absolute;
				justify-content: center;
				top: 280px;
				left: 5px;
				background-color: #1d1d27;

			}

			.menu__item {

				all: unset;
				flex-grow: 1;
				z-index: 100;
				display: flex;
				cursor: pointer;
				position: relative;
				border-radius: 50%;
				align-items: center;
				will-change: transform;
				justify-content: center;
				padding: 0.55em 0 0.85em;
				transition: transform 1s;
				padding-bottom: 0px !important;

			}

			.menu__item::before {

				content: "";
				z-index: -1;
				width: 4.2em;
				height: 4.2em;
				border-radius: 50%;
				position: absolute;
				transform: scale(0);
				transition: background-color 1s, transform 1s;

			}

			button:focus{
				outline: none;
			}
			.menu__item.active {

				transform: translate3d(0, -.8em, 0);
				border: none;
			}

			.menu__item.active::before {

				transform: scale(1);
				background-color: var(--bgColorItem);

			}

			.icon {

				width: 2.6em;
				height: 2.6em;
				stroke: white;
				fill: transparent;
				stroke-width: 1pt;
				stroke-miterlimit: 10;
				stroke-linecap: round;
				stroke-linejoin: round;
				stroke-dasharray: 400;

			}

			.menu__item.active .icon {

				animation: strok 2s reverse;

			}

			@keyframes strok {

				100% {

					stroke-dashoffset: 400;

				}

			}

			.menu__border {

				left: 0;
				bottom: 99%;
				width: 10.9em;
				height: 2.4em;
				position: absolute;
				clip-path: url(#menu);
				will-change: transform;
				background-color: var #1d1d27;
				transition: transform 1s;
			}

			.svg-container {

				width: 0;
				height: 0;
			}


			@media screen and (max-width: 50em) {
				.menu {
					font-size: .8em;
				}
			}
			.sub-left{
				text-decoration: none;
				cursor: pointer;
				color: #000;
			}
			.sub-left:hover{
				text-decoration: none;
				cursor: pointer;
				color: #000;
			}
			.card{
				border: none !important;
			}
			#ac:hover,#ac{
				text-decoration: none;
			}
		</style>
	</head>

	<body>




		<div  style="width: 1800px; height: 937px; padding: 15px;display: block;justify-content: center;align-items: center;margin-left: 110px;">
			<c:if test="${sessionScope.SUCCESS !=null}">
				
				<div class="alert alert-success alert-dismissible" style="text-align: center">
					<a id="clo" href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Hello ${sessionScope.USER.fullName}. WelCome back to QuizOnline-Admin-Page!</strong>
				</div>
			</c:if>
			<c:if test="${requestScope.SUBID_ERROR != null}">
				<div class="alert alert-danger alert-dismissible" style="text-align: center;">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>${requestScope.SUBID_ERROR}</strong>
				</div>
			</c:if>
			<c:if test="${requestScope.INDEX_ERROR != null}">
				<div class="alert alert-danger alert-dismissible" style="text-align: center">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>${requestScope.INDEX_ERROR}</strong>
				</div>
			</c:if>
			<c:if test="${requestScope.STATUS_ERROR != null}">
				<div class="alert alert-danger alert-dismissible" style="text-align: center">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>${requestScope.STATUS_ERROR}</strong>
				</div>
			</c:if>
			<c:if test="${requestScope.QUESID_ERROR != null}">
				<div class="alert alert-danger alert-dismissible" style="text-align: center">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>${requestScope.QUESID_ERROR}</strong>
				</div>
			</c:if>
			<c:if test="${requestScope.RIGHTANS_ERROR != null}">
				<div class="alert alert-danger alert-dismissible" style="text-align: center">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>${requestScope.RIGHTANS_ERROR}</strong>
				</div>
			</c:if>
			<c:if test="${requestScope.QUESC_ERROR != null}">
				<div class="alert alert-danger alert-dismissible" style="text-align: center">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>${requestScope.QUESC_ERROR}</strong>
				</div>
			</c:if>
			<c:if test="${requestScope.ANSC_ERROR != null}">
				<div class="alert alert-danger alert-dismissible" style="text-align: center">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>${requestScope.ANSC_ERROR}</strong>
				</div>
			</c:if>
			<c:if test="${requestScope.CBX_ERROR != null}">
				<div class="alert alert-danger alert-dismissible" style="text-align: center">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>${requestScope.CBX_ERROR}</strong>
				</div>
			</c:if>
			<div class="shadow-lg" id="body-content" style="width: 1760px;height: 850px;background-color: #fff;margin-bottom: 0px;border-radius: 10px;margin-left: 10px;">
				<jsp:useBean id="listSub" class="duytt.dtos.Subject"></jsp:useBean>
				<jsp:setProperty name="listSub" property="*"></jsp:setProperty>
					<div class="container my-4">
						<!--Carousel Wrapper-->
						<div id="multi-item-example" class="carousel slide carousel-multi-item" data-ride="carousel">

							<!--Controls-->
							<div style="position: relative;height: 20px" class="controls-top">
								<a style="position: absolute;top: 40px;left: -35px" class="btn-floating" href="#multi-item-example" data-slide="prev"><i style="color: #7dc129;font-size: 35px" class="fa fa-chevron-left"></i></a>
								<a style="position: absolute;top: 40px;right: -35px" class="btn-floating" href="#multi-item-example" data-slide="next"><i style="color: #7dc129;font-size: 35px" class="fa fa-chevron-right"></i></a>
								<h2  style="position: absolute;top: 40px;left: -230px">${sessionScope.SUBID}</h2>					
						</div>
						<!--/.Controls-->
						<!--Slides-->

						<div style="margin-top: 20px;" class="carousel-inner" role="listbox">


							<!--First slide-->
							<c:set var="cou" value="1"></c:set>
							<c:forEach items="${sessionScope.LISTSUB}" var="listSub" varStatus="counter" step="3">
								<c:if test="${counter.index<=2}">
									<div class="carousel-item active">
										<div class="row">
											<c:if test="${sessionScope.LISTSUB[counter.index].subId !=null}">
												<c:if test="${sessionScope.LISTSUB[counter.index].subId eq sessionScope.SUBID}">
													<div class="col-md-4">
														<div class="card mb-2">
															<a id="sub[${cou}]" href="HomeAdmin_Page_Controller?sub=${sessionScope.LISTSUB[counter.index].subId}&index=1" onclick="changeSub(${cou})" type="button" class="btn btn-outline-secondary mr-3 active" value="${sessionScope.LISTSUB[counter.index].subId}"> ${sessionScope.LISTSUB[counter.index].subId}</a>
														</div>
													</div>								
												</c:if>
												<c:if test="${sessionScope.LISTSUB[counter.index].subId ne sessionScope.SUBID}">
													<div class="col-md-4">
														<div class="card mb-2">
															<a id="sub[${cou}]" href="HomeAdmin_Page_Controller?sub=${sessionScope.LISTSUB[counter.index].subId}&index=1" onclick="changeSub(${cou})" type="button" class="btn btn-outline-secondary mr-3" value="${sessionScope.LISTSUB[counter.index].subId}"> ${sessionScope.LISTSUB[counter.index].subId}</a>
														</div>
													</div>								
												</c:if>
											</c:if>
											<c:set var="cou" value="${cou+1}"></c:set>
											<c:if test="${sessionScope.LISTSUB[counter.index+1].subId !=null}">
												<c:if test="${sessionScope.LISTSUB[counter.index+1].subId eq sessionScope.SUBID}">
													<div class="col-md-4 clearfix d-none d-md-block">
														<div class="card mb-2">
															<a id="sub[${cou}]" href="HomeAdmin_Page_Controller?sub=${sessionScope.LISTSUB[counter.index+1].subId}&index=1" onclick="changeSub(${cou})" type="button" class="btn btn-outline-secondary mr-3 active" value="${sessionScope.LISTSUB[counter.index+1].subId}" >${sessionScope.LISTSUB[counter.index+1].subId}</a>
														</div>
													</div>
												</c:if>
												<c:if test="${sessionScope.LISTSUB[counter.index+1].subId ne sessionScope.SUBID}">
													<div class="col-md-4 clearfix d-none d-md-block">
														<div class="card mb-2">
															<a id="sub[${cou}]" href="HomeAdmin_Page_Controller?sub=${sessionScope.LISTSUB[counter.index+1].subId}&index=1" onclick="changeSub(${cou})" type="button" class="btn btn-outline-secondary mr-3" value="${sessionScope.LISTSUB[counter.index+1].subId}" >${sessionScope.LISTSUB[counter.index+1].subId}</a>
														</div>
													</div>
												</c:if>
											</c:if>

											<c:set var="cou" value="${cou+1}"></c:set>
											<c:if test="${sessionScope.LISTSUB[counter.index+2].subId !=null}">
												<c:if test="${sessionScope.LISTSUB[counter.index+2].subId eq sessionScope.SUBID}">
													<div class="col-md-4 clearfix d-none d-md-block">
														<div class="card mb-2">
															<a id="sub[${cou}]" href="HomeAdmin_Page_Controller?sub=${sessionScope.LISTSUB[counter.index+2].subId}&index=1" onclick="changeSub(${cou})" type="button" class="btn btn-outline-secondary mr-3 active" value="${sessionScope.LISTSUB[counter.index+2].subId}">${sessionScope.LISTSUB[counter.index+2].subId}</a>
														</div>
													</div>
												</c:if>
												<c:if test="${sessionScope.LISTSUB[counter.index+2].subId ne sessionScope.SUBID}">
													<div class="col-md-4 clearfix d-none d-md-block">
														<div class="card mb-2">
															<a id="sub[${cou}]" href="HomeAdmin_Page_Controller?sub=${sessionScope.LISTSUB[counter.index+2].subId}&index=1" onclick="changeSub(${cou})" type="button" class="btn btn-outline-secondary mr-3" value="${sessionScope.LISTSUB[counter.index+2].subId}">${sessionScope.LISTSUB[counter.index+2].subId}</a>
														</div>
													</div>
												</c:if>
											</c:if>
										</div>

									</div>
								</c:if>
								<c:if test="${counter.index>2}">
									<div class="carousel-item">
										<div class="row">
											<c:set var="cou" value="${cou+1}"></c:set>
											<c:if test="${sessionScope.LISTSUB[counter.index].subId !=null}">
												<c:if test="${sessionScope.LISTSUB[counter.index].subId eq sessionScope.SUBID}">
													<div class="col-md-4">
														<div class="card mb-2">
															<a id="sub[${cou}]" href="HomeAdmin_Page_Controller?sub=${sessionScope.LISTSUB[counter.index].subId}&index=1" onclick="changeSub(${cou})" type="button" class="btn btn-outline-secondary mr-3 active" value="${sessionScope.LISTSUB[counter.index].subId}"> ${sessionScope.LISTSUB[counter.index].subId}</a>
														</div>
													</div>								
												</c:if>
												<c:if test="${sessionScope.LISTSUB[counter.index].subId ne sessionScope.SUBID}">
													<div class="col-md-4">
														<div class="card mb-2">
															<a id="sub[${cou}]" href="HomeAdmin_Page_Controller?sub=${sessionScope.LISTSUB[counter.index].subId}&index=1" onclick="changeSub(${cou})" type="button" class="btn btn-outline-secondary mr-3" value="${sessionScope.LISTSUB[counter.index].subId}"> ${sessionScope.LISTSUB[counter.index].subId}</a>
														</div>
													</div>								
												</c:if>
											</c:if>
											<c:set var="cou" value="${cou+1}"></c:set>
											<c:if test="${sessionScope.LISTSUB[counter.index+1].subId !=null}">
												<c:if test="${sessionScope.LISTSUB[counter.index+1].subId eq sessionScope.SUBID}">
													<div class="col-md-4 clearfix d-none d-md-block">
														<div class="card mb-2">
															<a id="sub[${cou}]" href="HomeAdmin_Page_Controller?sub=${sessionScope.LISTSUB[counter.index+1].subId}&index=1" onclick="changeSub(${cou})" type="button" class="btn btn-outline-secondary mr-3 active" value="${sessionScope.LISTSUB[counter.index+1].subId}" >${sessionScope.LISTSUB[counter.index+1].subId}</a>
														</div>
													</div>
												</c:if>
												<c:if test="${sessionScope.LISTSUB[counter.index+1].subId ne sessionScope.SUBID}">
													<div class="col-md-4 clearfix d-none d-md-block">
														<div class="card mb-2">
															<a id="sub[${cou}]" href="HomeAdmin_Page_Controller?sub=${sessionScope.LISTSUB[counter.index+1].subId}&index=1" onclick="changeSub(${cou})" type="button" class="btn btn-outline-secondary mr-3" value="${sessionScope.LISTSUB[counter.index+1].subId}" >${sessionScope.LISTSUB[counter.index+1].subId}</a>
														</div>
													</div>
												</c:if>
											</c:if>

											<c:set var="cou" value="${cou+1}"></c:set>
											<c:if test="${sessionScope.LISTSUB[counter.index+2].subId !=null}">
												<c:if test="${sessionScope.LISTSUB[counter.index+2].subId eq sessionScope.SUBID}">
													<div class="col-md-4 clearfix d-none d-md-block">
														<div class="card mb-2">
															<a id="sub[${cou}]" href="HomeAdmin_Page_Controller?sub=${sessionScope.LISTSUB[counter.index+2].subId}&index=1" onclick="changeSub(${cou})" type="button" class="btn btn-outline-secondary mr-3 active" value="${sessionScope.LISTSUB[counter.index+2].subId}">${sessionScope.LISTSUB[counter.index+2].subId}</a>
														</div>
													</div>
												</c:if>
												<c:if test="${sessionScope.LISTSUB[counter.index+2].subId ne sessionScope.SUBID}">
													<div class="col-md-4 clearfix d-none d-md-block">
														<div class="card mb-2">
															<a id="sub[${cou}]" href="HomeAdmin_Page_Controller?sub=${sessionScope.LISTSUB[counter.index+2].subId}&index=1" onclick="changeSub(${cou})" type="button" class="btn btn-outline-secondary mr-3" value="${sessionScope.LISTSUB[counter.index+2].subId}">${sessionScope.LISTSUB[counter.index+2].subId}</a>
														</div>
													</div>
												</c:if>
											</c:if>
										</div>

									</div>

								</c:if>
							</c:forEach>
							<!--/.First slide-->
						</div>
						<!--/.Slides-->
					</div>
					<!--/.Carousel Wrapper-->
				</div>
				<div>
					<div style="display: flex;justify-content: center">
						<form action="Search_Ques_Name_Controller" method="POST" style="width: 20%;display: flex;justify-content: center">
							<div style="text-align: center;">
								<input style="width: 300px; margin-left: 20px;" class="form-control" type="text" name="txtSearch" value="${sessionScope.TXTSEARCH}"
									   placeholder="Enter Question">							
							</div>
							<div>
								<input style="margin-left: 15px;" type="submit" value="Search" class="btn btn-success">
							</div>
						</form>
						<select class="form-control" style="width: 15%;margin-left: 150px" onchange="location = this.value;">
							<c:if test="${sessionScope.STATUS == null}">
								<option value="" disabled selected>Choose Status</option>
								<option value="Status_Ques_Controller?status=true">ACTIVE</option>
								<option value="Status_Ques_Controller?status=false">DEACTIVE</option>
							</c:if>
							<c:if test="${sessionScope.STATUS eq 'true'}">
								<option selected value="Status_Ques_Controller?status=true">ACTIVE</option>
								<option value="Status_Ques_Controller?status=false">DEACTIVE</option>
							</c:if>
							<c:if test="${sessionScope.STATUS eq 'false'}">
								<option  value="Status_Ques_Controller?status=true">ACTIVE</option>
								<option selected value="Status_Ques_Controller?status=false">DEACTIVE</option>
							</c:if>
						</select>
					</div>
				</div>
				<div style="display: flex;justify-content: center;padding-top: 10px;max-height: 6px">
					<hr style="border-bottom: 5px solid #7dc129;width: 30%;margin-top: 0px;margin-top: 0px;border-top: 0px">
				</div>
				<c:if test="${sessionScope.CONTROLLER eq 'HomeAdmin_Page_Controller'}">
					<div style="min-height: 670px;overflow-y: scroll;max-height: 670px;height: 649px;margin-top: 20px">
						<div class="row" style="margin:  20px;">

							<c:if test="${requestScope.HASMAP !=null}">
								<jsp:useBean id="ques" class="duytt.dtos.Question"></jsp:useBean>
								<jsp:setProperty name="ques" property="*"></jsp:setProperty>
								<c:forEach items="${requestScope.HASMAP}" var="has" varStatus="counter">
									<c:set var="key" value="${has.key}"></c:set>
										<form class="col-lg-3 mb-4"action="Update_Question_Controller" method="POST">
											<input type="hidden" name="controller" value="${sessionScope.CONTROLLER}">
										<input type="hidden" name="txtSearch" value="${sessionScope.TXTSEARCH}">
										<input type="hidden" name="status" value="${sessionScope.STATUS}">
										<div style="width: 95%;border: 1px solid #65ddb7;border-radius: 10px;display: flex;justify-content: center">
											<div style="width: 95%">
												<c:set var="subIdTmp" value=""></c:set>
												<c:forEach items="${requestScope.LISTQUES}" var="ques">
													<c:set var="quesId" value="${ques.quesId}"></c:set>
													<c:if test="${key == quesId}">
														<label style="font-weight: bold;" for="exampleInputEmail1">Question - #${has.key} - ${ques.subId}</label>
														<c:set var="subIdTmp" value="${ques.subId}"></c:set>
													</c:if>
												</c:forEach>
												<input type="hidden" name="txtQuesId" value="${has.key}">
												<div class="form-group">
													<c:forEach items="${requestScope.LISTQUES}" var="ques">
														<c:set var="quesId" value="${ques.quesId}"></c:set>
														<c:if test="${key == quesId}">
															<textarea name="txtQuesContent" class="form-control"  rows="3">${ques.quesCont}</textarea>
														</c:if>
													</c:forEach>
												</div>
												<c:forEach items="${requestScope.HASMAP[has.key]}" var="ans" varStatus="ct">

													<c:if test="${ans.ansCorrect == true}">
														<c:set var="rightAn" value="${ans.ansId}"></c:set>
													</c:if>
													<c:if test="${ans.ansCorrect == true}">
														<div class="form-check" style="margin-bottom: 10px;">
															<input class="form-check-input" type="radio" name="rightAns[${counter.count}]"  value="${ans.ansId}" checked>
															<textarea  class="form-control" name="ansC[${ct.count}]" rows="2">${ans.ansContent}</textarea>

															<input type="hidden" name="ansD[${ct.count}]"value="${ans.ansId}">
														</div>
													</c:if>
													<c:if test="${ans.ansCorrect != true}">
														<div class="form-check" style="margin-bottom: 10px;">
															<input class="form-check-input" type="radio" name="rightAns[${counter.count}]"  value="${ans.ansId}">
															<textarea  class="form-control" name="ansC[${ct.count}]" rows="2">${ans.ansContent}</textarea>

															<input type="hidden" name="ansD[${ct.count}]"value="${ans.ansId}">
														</div>
													</c:if>
												</c:forEach>
												<select class="form-control" style="width: 100%;margin-bottom: 10px" name="cbxSub">
													<c:forEach items="${sessionScope.LISTSUB}" var="listSub">
														<c:if test="${subIdTmp == listSub.subId}">
															<option selected value="${listSub.subId}">${listSub.subId}</option>
														</c:if>
														<c:if test="${subIdTmp != listSub.subId}">
															<option value="${listSub.subId}">${listSub.subId}</option>
														</c:if>	


													</c:forEach>

												</select>
												<div class="mx-auto pb-2" style="display: flex;justify-content: center">
													<div style="display: block">
														<c:forEach items="${requestScope.LISTQUES}" var="ques">
															<c:set var="quesId" value="${ques.quesId}"></c:set>
															<c:if test="${key == quesId}">
																<c:if test="${ques.status == true}">
																	<input class="form-check-input" type="checkbox" value="on" name="txtCheckBox" id="flexCheckChecked" checked>
																	<label class="form-check-label" for="flexCheckChecked">
																		Status Question
																	</label>
																</c:if>
																<c:if test="${ques.status != true}">
																	<input class="form-check-input" type="checkbox" value="on" name="txtCheckBox" id="flexCheckChecked">
																	<label class="form-check-label" for="flexCheckChecked">
																		Status Question
																	</label>
																</c:if>
															</c:if>
														</c:forEach>
														<input type="submit" value="Update" class="btn btn-outline-secondary mr-3">
														<c:forEach items="${requestScope.LISTQUES}" var="ques">
															<c:set var="quesId" value="${ques.quesId}"></c:set>
															<c:if test="${key == quesId}">
																<c:if test="${ques.status == true}">
																	<a href="Delete_Ques_Controller?txtQuesId=${has.key}&controller=${sessionScope.CONTROLLER}&txtSearch=${sessionScope.TXTSEARCH}&status=${sessionScope.STATUS}" class="btn btn-outline-secondary ml-3">Delete</a>
																</c:if>
																<c:if test="${ques.status == 'false'}">
																	<a href="Delete_Ques_Controller?txtQuesId=${has.key}&controller=${sessionScope.CONTROLLER}&txtSearch=${sessionScope.TXTSEARCH}&status=${sessionScope.STATUS}" class="btn btn-outline-secondary ml-3 disabled">Delete</a>
																</c:if>
															</c:if>
														</c:forEach>
													</div>
												</div>
											</div>
										</div>					
									</form>
								</c:forEach>
							</c:if>
							<c:if test="${requestScope.HASMAP ==null}">
								<div style="display: flex;justify-content: center;width: 100%;height: 630px;align-items: center" >
									<div>
										<h2 style="color: #7dc129;text-align: center">NOT FOUND</h2>
									</div>
								</div>
							</c:if>
						</div>
					</div>
				</c:if>
				<c:if test="${sessionScope.CONTROLLER eq 'Search_Ques_Name_Controller' || sessionScope.CONTROLLER eq 'Status_Ques_Controller'}">
					<div style="min-height: 670px;overflow-y: scroll;max-height: 670px;height: 649px;margin-top: 20px">
						<div class="row" style="margin:  20px;">	
							<c:if test="${requestScope.LISTS !=null}">
								<jsp:useBean id="quesSearchList" class="duytt.dtos.QuesSearch"></jsp:useBean>
								<jsp:setProperty name="quesSearchList" property="*"></jsp:setProperty>
								<c:forEach items="${requestScope.LISTS}" var="quesSearchList" varStatus="counter">
									<div style="min-width: 100%;padding-left: 25px;background-color: #d4edda;padding-top: 5px;padding-bottom: 5px;margin-bottom: 10px">
										<h2 style="margin: 0px">${quesSearchList.subId}</h2>
									</div>
									<c:forEach items="${quesSearchList.has}" var="has">
										<c:set var="key" value="${has.key}"></c:set>
											<form class="col-lg-3 mb-4"action="Update_Question_Controller" method="POST">
												<input type="hidden" name="controller" value="${sessionScope.CONTROLLER}">
											<input type="hidden" name="txtSearch" value="${sessionScope.TXTSEARCH}">
											<input type="hidden" name="status" value="${sessionScope.STATUS}">

											<div style="width: 95%;border: 1px solid #65ddb7;border-radius: 10px;display: flex;justify-content: center">
												<div style="width: 95%">
													<c:set var="subIdTmp" value=""></c:set>
													<c:forEach items="${requestScope.LISTQUES}" var="ques">
														<c:set var="quesId" value="${ques.quesId}"></c:set>
														<c:if test="${key == quesId}">
															<label style="font-weight: bold;" for="exampleInputEmail1">Question - #${has.key} - ${ques.subId}</label>
															<c:set var="subIdTmp" value="${ques.subId}"></c:set>
														</c:if>
													</c:forEach>
													<input type="hidden" name="txtQuesId" value="${has.key}">
													<div class="form-group">
														<c:forEach items="${requestScope.LISTQUES}" var="ques">
															<c:set var="quesId" value="${ques.quesId}"></c:set>
															<c:if test="${key == quesId}">
																<textarea name="txtQuesContent" class="form-control"  rows="3">${ques.quesCont}</textarea>
															</c:if>
														</c:forEach>
													</div>
													<c:forEach items="${quesSearchList.has[has.key]}" var="ans" varStatus="ct">

														<c:if test="${ans.ansCorrect == true}">
															<c:set var="rightAn" value="${ans.ansId}"></c:set>
														</c:if>
														<c:if test="${ans.ansCorrect == true}">
															<div class="form-check" style="margin-bottom: 10px;">
																<input class="form-check-input" type="radio" name="rightAns[${counter.count}]"  value="${ans.ansId}" checked>
																<textarea  class="form-control" name="ansC[${ct.count}]" rows="2">${ans.ansContent}</textarea>

																<input type="hidden" name="ansD[${ct.count}]"value="${ans.ansId}">
															</div>
														</c:if>
														<c:if test="${ans.ansCorrect != true}">
															<div class="form-check" style="margin-bottom: 10px;">
																<input class="form-check-input" type="radio" name="rightAns[${counter.count}]"  value="${ans.ansId}">
																<textarea  class="form-control" name="ansC[${ct.count}]" rows="2">${ans.ansContent}</textarea>

																<input type="hidden" name="ansD[${ct.count}]"value="${ans.ansId}">
															</div>
														</c:if>
													</c:forEach>
													<select class="form-control" style="width: 100%;margin-bottom: 10px" name="cbxSub">
														<c:forEach items="${sessionScope.LISTSUB}" var="listSub">
															<c:if test="${subIdTmp == listSub.subId}">
																<option selected value="${listSub.subId}">${listSub.subId}</option>
															</c:if>
															<c:if test="${subIdTmp != listSub.subId}">
																<option value="${listSub.subId}">${listSub.subId}</option>
															</c:if>	


														</c:forEach>

													</select>
													<div class="mx-auto pb-2" style="display: flex;justify-content: center">
														<div style="display: block">
															<c:forEach items="${requestScope.LISTQUES}" var="ques">
																<c:set var="quesId" value="${ques.quesId}"></c:set>
																<c:if test="${key == quesId}">
																	<c:if test="${ques.status == true}">
																		<input class="form-check-input" type="checkbox" value="on" name="txtCheckBox" id="flexCheckChecked" checked>
																		<label class="form-check-label" for="flexCheckChecked">
																			Status Question
																		</label>
																	</c:if>
																	<c:if test="${ques.status != true}">
																		<input class="form-check-input" type="checkbox" value="on" name="txtCheckBox" id="flexCheckChecked">
																		<label class="form-check-label" for="flexCheckChecked">
																			Status Question
																		</label>
																	</c:if>
																</c:if>
															</c:forEach>

															<input type="submit" value="Update" class="btn btn-outline-secondary mr-3">
															<c:forEach items="${requestScope.LISTQUES}" var="ques">
																<c:set var="quesId" value="${ques.quesId}"></c:set>
																<c:if test="${key == quesId}">
																	<c:if test="${ques.status == true}">
																		<a href="Delete_Ques_Controller?txtQuesId=${has.key}&controller=${sessionScope.CONTROLLER}&txtSearch=${sessionScope.TXTSEARCH}&status=${sessionScope.STATUS}" class="btn btn-outline-secondary ml-3">Delete</a>
																	</c:if>
																	<c:if test="${ques.status == 'false'}">
																		<a href="Delete_Ques_Controller?txtQuesId=${has.key}&controller=${sessionScope.CONTROLLER}&txtSearch=${sessionScope.TXTSEARCH}&status=${sessionScope.STATUS}" class="btn btn-outline-secondary ml-3 disabled">Delete</a>
																	</c:if>
																</c:if>
															</c:forEach>
														</div>
													</div>
												</div>
											</div>					
										</form>

									</c:forEach>
								</c:forEach>
							</c:if>
							<c:if test="${requestScope.LISTS ==null}">
								<div style="display: flex;justify-content: center;width: 100%;height: 630px;align-items: center" >
									<div>
										<h2 style="color: #7dc129;text-align: center">NOT FOUND</h2>
									</div>
								</div>
							</c:if>
						</div>
					</div>
				</c:if>
			</div>
			<div style="display: flex;justify-content: center;margin-top: 5px;margin-left: 10px;">
				<nav aria-label="...">
					<ul class="pagination pagination-md">
						<c:if test="${sessionScope.SOPAGE>=2}">
							<c:forEach begin="1" end="${sessionScope.SOPAGE}" var="count">
								<c:if test="${count == requestScope.INDEX}">
									<li class="page-item active"><a class="page-link" href="${sessionScope.CONTROLLER}?index=${count}&sub=${sessionScope.SUBID}&txtSearch=${sessionScope.TXTSEARCH}&status=${sessionScope.STATUS}">${count}</a></li>
									</c:if>
									<c:if test="${count != requestScope.INDEX}">
									<li class="page-item"><a class="page-link" href="${sessionScope.CONTROLLER}?index=${count}&sub=${sessionScope.SUBID}&txtSearch=${sessionScope.TXTSEARCH}&status=${sessionScope.STATUS}">${count}</a></li>
									</c:if>
								</c:forEach>
							</c:if>
					</ul>
				</nav>
			</div>
			<menu class="menu" style="border-radius: 10px;padding-left: 0px;width: 120px;min-height: 298px">
				<div  style="margin: 30px;margin-top: 0px;margin-bottom: 0px;display: inline-block;width: 66px;text-align: center;height: 104px">
					<a <a href="HomeAdmin_Page_Controller" class="menu__item active" style="--bgColorItem: #e0b115;">
							<svg class="icon" viewBox="0 0 24 24">
							<path d="M5.1,3.9h13.9c0.6,0,1.2,0.5,1.2,1.2v13.9c0,0.6-0.5,1.2-1.2,1.2H5.1c-0.6,0-1.2-0.5-1.2-1.2V5.1
								  C3.9,4.4,4.4,3.9,5.1,3.9z" />
							<path d="M4.2,9.3h15.6" />
							<path d="M9.1,9.5v10.3" />
						</a>
						<p
							style="color: #fff;font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;margin-top: 0px;margin-bottom: 5px;">
							Home</p>
				</div>
				<div style="margin: 30px;margin-top: 20px;margin-bottom: 0px;display: inline-block;width: 66px;;text-align: center;height: 104px">
					<a href="CreateQues_Page_Controller" class="menu__item " style="--bgColorItem: #f54888;">
						<svg class="icon" viewBox="0 0 24 24">
						<path  d="M3.4,11.9l8.8,4.4l8.4-4.4"/>
						<path  d="M3.4,16.2l8.8,4.5l8.4-4.5"/>
						<path  d="M3.7,7.8l8.6-4.5l8,4.5l-8,4.3L3.7,7.8z"/>
						</svg>
					</a>
					<p
						style="color: #fff;font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;margin-top: 0px;margin-bottom: 5px;">
						Create Question</p>
				</div>
				<div  style="margin: 30px;margin-top: 20px;margin-bottom: 0px; display: inline-block;width: 66px;;text-align: center;height: 104px">
					<a href="More_Admin_Controller" class="menu__item" style="--bgColorItem: #65ddb7;">
						<svg class="icon" viewBox="0 0 24 24">
						<path d="M3.8,6.6h16.4" />
						<path d="M20.2,12.1H3.8" />
						<path d="M3.8,17.5h16.4" />
						</svg>
					</a>
					<p
						style="color: #fff;font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;margin-top: 0px;margin-bottom: 0px;">
						More</p>
				</div>
				<div class="menu__border"></div>
			</menu>
			<div class="svg-container">
				<svg viewBox="0 0 202.9 45.5">
				<clipPath id="menu" clipPathUnits="objectBoundingBox"
						  transform="scale(0.0049285362247413 0.021978021978022)">
					<path d="M6.7,45.5c5.7,0.1,14.1-0.4,23.3-4c5.7-2.3,9.9-5,18.1-10.5c10.7-7.1,11.8-9.2,20.6-14.3c5-2.9,9.2-5.2,15.2-7
						  c7.1-2.1,13.3-2.3,17.6-2.1c4.2-0.2,10.5,0.1,17.6,2.1c6.1,1.8,10.2,4.1,15.2,7c8.8,5,9.9,7.1,20.6,14.3c8.3,5.5,12.4,8.2,18.1,10.5
						  c9.2,3.6,17.6,4.2,23.3,4H6.7z" />
				</clipPath>
				</svg>
			</div>

			<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
					integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
			crossorigin="anonymous"></script>
			<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
					integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
			crossorigin="anonymous"></script>
			<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
					integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
			crossorigin="anonymous"></script>
			<script src="https://apis.google.com/js/platform.js" async defer></script>
			<script>
							"use strict";

							const body = document.body;
							const bodycontent = document.getElementById('body-content');
							//        const bgColorsBody = ["#ffe797", "#ff96bd", "#cffff1"];
							const bgColorsBody = ["#fff", "#fff", "#fff"];
							const menu = body.querySelector(".menu");
							const menuItems = menu.querySelectorAll(".menu__item");
							const menuBorder = menu.querySelector(".menu__border");
							let activeItem = menu.querySelector(".active");

							function clickItem(item, index) {

								if (activeItem == item)
									return;

								if (activeItem) {
									activeItem.classList.remove("active");
								}


								item.classList.add("active");

								bodycontent.style.backgroundColor = bgColorsBody[index];
								activeItem = item;
								offsetMenuBorder(activeItem, menuBorder);


							}

							function offsetMenuBorder(element, menuBorder) {

								const offsetActiveItem = element.getBoundingClientRect();
								const left = Math.floor(offsetActiveItem.left - menu.offsetLeft - (menuBorder.offsetWidth - offsetActiveItem.width) / 2) + "px";
								menuBorder.style.transform = `translate3d(${left}, 0 , 0)`;

							}

							offsetMenuBorder(activeItem, menuBorder);

							menuItems.forEach((item, index) => {

								item.addEventListener("click", () => clickItem(item, index));

							})

							window.addEventListener("resize", () => offsetMenuBorder(activeItem, menuBorder));

							$('.carousel').carousel({
								interval: false,
							});

							function changeSub(index) {
								document.getElementById('subIdChange').innerHTML = document.getElementById('sub[' + index + ']').value;

//					console.log(index);
							}
							function setCheckBox(e)
							{
								document.getElementById('txtCheckBox').value = e;
								console.log(document.getElementById('txtCheckBox').value);
							}
							
							


			</script>
	</body>

</html>
