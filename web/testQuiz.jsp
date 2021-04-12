<%-- 
    Document   : testQuiz
    Created on : Feb 1, 2021, 1:40:21 AM
    Author     : thant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
			  integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<script src="https://kit.fontawesome.com/1acc75252a.js" crossorigin="anonymous"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<title>Test</title>
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

			button:focus {
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

			.sub-left {
				text-decoration: none;
				cursor: pointer;
				color: #000;
			}

			.sub-left:hover {
				text-decoration: none;
				cursor: pointer;
				color: #000;
			}
		</style>
	</head>
	<body>
		<div style="width: 100%;display: flex;justify-content: center;align-items: center">
			<div
			style="width: 1800px; height: 880px; padding: 15px;display: block;justify-content: center;align-items: center;">
			<div class="shadow-lg" id="body-content"
				 style="width: 1760px;height: 850px;background-color: #fff;margin-bottom: 0px;border-radius: 10px;margin-left: 10px;">
				<div class="row" style="padding: 20px;">
					<div class="col-2">
						<div style="display: flex;justify-content: center;padding-left: 25px;">
							<button class="btn btn-success" style="width: 100%;font-size: larger" disabled="true">
								Question
							</button>
						</div>
						<div class="row">
							<jsp:useBean id="listQuiz" class="duytt.dtos.Quiz"></jsp:useBean>
							<jsp:setProperty name="listQuiz" property="*"></jsp:setProperty>
							<c:forEach items="${sessionScope.HAS}" var="has" varStatus="counter">
								<c:if test="${sessionScope.LSITQUIZ == null}">
									<div class="col-lg-3">
										<div style="padding: 20px;">
											<a type="button" id="ques[${counter.count}]" data-target="#carouselExampleIndicators" data-slide-to="${counter.index}" href="#"
											   class="btn btn-outline-secondary">${counter.count}</a>
										</div>
									</div>
								</c:if>
								<c:if test="${sessionScope.LSITQUIZ != null}">
									<c:set var="checkAns" value="0"></c:set>
									<c:forEach items="${sessionScope.LSITQUIZ}" var="listQuiz">
										<c:if test="${has.key==listQuiz.quesId}">
											<c:set var="checkAns" value="1"></c:set>
										</c:if>
									</c:forEach>
									<c:if test="${checkAns == 1}">
										<div class="col-lg-3">
											<div style="padding: 20px;">
												<a type="button" id="ques[${counter.count}]" data-target="#carouselExampleIndicators" data-slide-to="${counter.index}" href="#"
												   class="btn btn-secondary">${counter.count}</a>
											</div>
										</div>
									</c:if>
									<c:if test="${checkAns == 0}">
										<div class="col-lg-3">
											<div style="padding: 20px;">
												<a type="button" id="ques[${counter.count}]" data-target="#carouselExampleIndicators" data-slide-to="${counter.index}" href="#"
												   class="btn btn-outline-secondary">${counter.count}</a>
											</div>
										</div>
									</c:if>
								</c:if>


							</c:forEach>
						</div>
						<div style="display: flex;justify-content: center;margin-top: 30px">
							<table style="font-size: 30px;" >
								<tr>
									<td>
										<form action="http://www.google.fr">
											<input id="time" class="btn" type="hidden" formtarget="_blank">
										</form>
									</td>
									<td>${sessionScope.HOUR}:${sessionScope.MINUTES}:${sessionScope.SECONDS}</td>
								</tr>

							</table>

						</div>
						<div style="display: flex;justify-content: center;margin-top: 30px">
							<div >
								<a style="padding-left: 50px;padding-right: 50px" data-toggle="modal" data-target="#exampleModalCenter" type="button"  href="#" class="btn btn-success">Finish</a>
							</div>
							<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLongTitle">Finish Quiz</h5>
											<button type="button" class="close" data-dismiss="modal" aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body">
											Do you want to finish?
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
											<a id="submitQuiz" href="Finish_Quiz_Controller?subId=${sessionScope.SUBID}&quizTakeId=${sessionScope.QUIZTAKEID}&quizId=${sessionScope.QUIZID}" type="button" class="btn btn-primary">Yes</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-10">
						<div
							style="padding: 20px;display: flex;justify-content: center;height: 765px; align-items: center;">
							<div class="shadow-lg" style="width: 1250px;height: 600px;">
								<div style="padding: 10px;height: 580px;" id="carouselExampleIndicators"
									 class="carousel" data-ride="carousel">
									<div class="carousel-inner">

										<c:forEach items="${sessionScope.HAS}" var="has" varStatus="counter">
											<c:set var="key" value="${has.key}"></c:set>
											<c:if test="${counter.first}">
												<div class="carousel-item active">
													<form action="Quiz_Do_ans_Controller" method="POST">
														<div style="width: 95%;display: flex;justify-content: center">
															<div style="width: 95%">
																<div class="form-group"
																	 style="padding-top: 50px;margin-bottom: 40px;">

																	<c:forEach items="${sessionScope.LISTQUESS}" var="ques">
																		<c:set var="quesId" value="${ques.quesId}"></c:set>
																		<c:if test="${key == quesId}">
																			<p style="font-size: larger;"> <span
																					style="font-weight: bold;">Question ${counter.count}:</span>
																				${ques.quesCont}
																			</p>	
																		</c:if>
																	</c:forEach>

																</div>

																<c:forEach items="${sessionScope.HAS[has.key]}" var="ans" varStatus="ct">

																	<c:if test="${sessionScope.LSITQUIZ == null}">
																		<div class="form-check"
																			 style="margin-bottom: 40px;padding-left: 50px;">
																			<input onclick="clickAns(${ans.ansId},${counter.count})" class="form-check-input" type="radio"
																				   >
																			<p>${ans.ansContent}</p>
																		</div>
																	</c:if>
																	<c:if test="${sessionScope.LSITQUIZ != null}">
																		<div class="form-check"
																			 style="margin-bottom: 40px;padding-left: 50px;">
																			<c:set var="next" value="0"></c:set>
																			<c:forEach items="${sessionScope.LSITQUIZ}" var="listQuiz">
																				<c:if test="${listQuiz.quesId == has.key}">
																					<c:if test="${listQuiz.ansId == ans.ansId}">
																						<input onclick="clickAns(${ans.ansId},${counter.count})" class="form-check-input" type="radio"
																							   checked   >
																						<p>${ans.ansContent}</p> 
																						<c:set var="next" value="1"></c:set>
																					</c:if>
																				</c:if>
																			</c:forEach>


																			<c:forEach items="${sessionScope.LSITQUIZ}" var="listQuiz">
																				<c:if test="${listQuiz.quesId == has.key}">
																					<c:if test="${listQuiz.ansId != ans.ansId}">
																						<input onclick="clickAns(${ans.ansId},${counter.count})" class="form-check-input" type="radio"
																							   >
																						<p>${ans.ansContent}</p>
																						<c:set var="next" value="1"></c:set>
																					</c:if>
																				</c:if>
																			</c:forEach>
																			<c:if test="${next == 0}"> 
																				<c:set var="show" value="0"></c:set>
																				<c:forEach items="${sessionScope.LSITQUIZ}" var="listQuiz">
																					<c:if test="${listQuiz.quesId != has.key}">
																						<c:set var="show" value="1"></c:set>
																					</c:if>
																				</c:forEach>
																				<c:if test="${show == 1}">
																					<input onclick="clickAns(${ans.ansId},${counter.count})" class="form-check-input" type="radio"
																						   >
																					<p>${ans.ansContent}</p>
																				</c:if>
																			</c:if>
																		</div>
																	</c:if>
																</c:forEach>
															</div>
														</div>

														<input type="hidden" name="quesId" value="${has.key}">
														<input type="hidden" name="subId" value="${sessionScope.SUBID}">
														<input type="text" hidden="true" name="ansTmp" id="ansTmp[${counter.count}]" value="">
														<input type="hidden" name="index" value="${counter.count}">
														<input type="hidden" name="quizTakeId" value="${sessionScope.QUIZTAKEID}">
														<input type="hidden" name="quizId" value="${sessionScope.QUIZID}">
														<input type="submit" hidden="true" id="submitAns[${counter.count}]">
													</form>
												</div>
											</c:if>
											<c:if test="${!counter.first}">
												<div class="carousel-item">
													<form action="Quiz_Do_ans_Controller" method="POST">
														<div style="width: 95%;display: flex;justify-content: center">
															<div style="width: 95%">
																<div class="form-group"
																	 style="padding-top: 50px;margin-bottom: 40px;">

																	<c:forEach items="${sessionScope.LISTQUESS}" var="ques">
																		<c:set var="quesId" value="${ques.quesId}"></c:set>
																		<c:if test="${key == quesId}">
																			<p style="font-size: larger;"> <span
																					style="font-weight: bold;">Question ${counter.count}:</span>
																				${ques.quesCont}
																			</p>	
																		</c:if>
																	</c:forEach>

																</div>

																<c:forEach items="${sessionScope.HAS[has.key]}" var="ans" varStatus="ct">

																	<c:if test="${sessionScope.LSITQUIZ == null}">
																		<div class="form-check"
																			 style="margin-bottom: 40px;padding-left: 50px;">
																			<input onclick="clickAns(${ans.ansId},${counter.count})" class="form-check-input" type="radio"
																				   >
																			<p>${ans.ansContent}</p>
																		</div>
																	</c:if>
																	<c:if test="${sessionScope.LSITQUIZ != null}">
																		<div class="form-check"
																			 style="margin-bottom: 40px;padding-left: 50px;">
																			<c:set var="next" value="0"></c:set>
																			<c:forEach items="${sessionScope.LSITQUIZ}" var="listQuiz">
																				<c:if test="${listQuiz.quesId == has.key}">
																					<c:if test="${listQuiz.ansId == ans.ansId}">
																						<input onclick="clickAns(${ans.ansId},${counter.count})" class="form-check-input" type="radio"
																							   checked   >
																						<p>${ans.ansContent}</p> 
																						<c:set var="next" value="1"></c:set>
																					</c:if>
																				</c:if>
																			</c:forEach>


																			<c:forEach items="${sessionScope.LSITQUIZ}" var="listQuiz">
																				<c:if test="${listQuiz.quesId == has.key}">
																					<c:if test="${listQuiz.ansId != ans.ansId}">
																						<input onclick="clickAns(${ans.ansId},${counter.count})" class="form-check-input" type="radio"
																							   >
																						<p>${ans.ansContent}</p>
																						<c:set var="next" value="1"></c:set>
																					</c:if>
																				</c:if>
																			</c:forEach>
																			<c:if test="${next == 0}"> 
																				<c:set var="show" value="0"></c:set>
																				<c:forEach items="${sessionScope.LSITQUIZ}" var="listQuiz">
																					<c:if test="${listQuiz.quesId != has.key}">
																						<c:set var="show" value="1"></c:set>
																					</c:if>
																				</c:forEach>
																				<c:if test="${show == 1}">
																					<input onclick="clickAns(${ans.ansId},${counter.count})" class="form-check-input" type="radio"
																						   >
																					<p>${ans.ansContent}</p>
																				</c:if>
																			</c:if>
																		</div>
																	</c:if>
																</c:forEach>
															</div>
														</div>
														<input type="hidden" name="subId" value="${sessionScope.SUBID}">
														<input type="hidden" name="quesId" value="${has.key}">
														<input type="text" hidden="true" name="ansTmp" id="ansTmp[${counter.count}]" value="">
														<input type="hidden" name="index" value="${counter.count}">
														<input type="hidden" name="quizTakeId" value="${sessionScope.QUIZTAKEID}">
														<input type="hidden" name="quizId" value="${sessionScope.QUIZID}">
														<input type="submit" hidden="true" id="submitAns[${counter.count}]">
													</form>
												</div>
											</c:if>
										</c:forEach>

									</div>
								</div>
							</div>

						</div>

						<div style="display: flex;justify-content: center;margin-top: 5px;margin-left: 10px;">
							<a style="color: #000 !important;font-size: 50px;padding-right: 0px;width: 50px;margin-left: 30px;height: 70px;margin-top: 330px;"
							   class="carousel-control-prev" href="#carouselExampleIndicators" role="button"
							   data-slide="prev">
								<i class="fas fa-angle-left"></i>
							</a>
							<a style="color: #000 !important;font-size: 50px;padding-right: 0px;width: 50px;margin-right: 30px;height: 70px;margin-top: 350px;"
							   class="carousel-control-next" href="#carouselExampleIndicators" role="button"
							   data-slide="next">
								<i class="fas fa-angle-right"></i>
							</a>
						</div>

						<div>
						</div>
					</div>
				</div>
			</div>
		</div>
			<menu  class="menu" style="border-radius: 10px;padding-left: 0px;width: 120px;display: none">
				<div
					style="margin: 30px;margin-top: 0px;margin-bottom: 0px;display: inline-block;width: 66px;text-align: center;">
					<a class="menu__item active" style="--bgColorItem: #e0b115;">
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
				<div
					style="margin: 30px;margin-top: 20px;margin-bottom: 0px;display: inline-block;width: 66px;;text-align: center;">
					<a class="menu__item" style="--bgColorItem: #f54888;">
						<svg class="icon" viewBox="0 0 24 24">
						<path d="M3.4,11.9l8.8,4.4l8.4-4.4" />
						<path d="M3.4,16.2l8.8,4.5l8.4-4.5" />
						<path d="M3.7,7.8l8.6-4.5l8,4.5l-8,4.3L3.7,7.8z" />
						</svg>
					</a>
					<p
						style="color: #fff;font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;margin-top: 0px;margin-bottom: 5px;">
						Create Question</p>
				</div>
				<div
					style="margin: 30px;margin-top: 20px;margin-bottom: 0px; display: inline-block;width: 66px;;text-align: center;">
					<a class="menu__item" style="--bgColorItem: #65ddb7;">
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
				<c:if test="${sessionScope.INDEX != null}">
																						document.getElementById("ques[" +${sessionScope.INDEX} + "]").click();
				</c:if>
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
																						function clickAns(e, index)
																						{
																							var ans = e;
																							document.getElementById('ansTmp[' + index + ']').value = ans;
																							document.getElementById('submitAns[' + index + ']').click();

																						}
																						function toTimeString(seconds) {
																							return (new Date(seconds * 1000)).toUTCString().match(/(\d\d:\d\d:\d\d)/)[0];
																						}

																						function startTimer() {
																							var nextElem = $(this).parents('td').next();
																							var duration = nextElem.text();
																							var a = duration.split(':');
																							var seconds = (+a[0]) * 60 * 60 + (+a[1]) * 60 + (+a[2]);
																							setInterval(function () {
																								seconds--;
																								if (seconds >= 0) {
																									nextElem.html(toTimeString(seconds));
																								}
																								if (seconds <= 20) {
																									nextElem.css('color', 'red');
																								}
																								if (seconds === 0) {
																									document.getElementById('submitQuiz').click();
																									clearInterval(seconds);
																								}
																							}, 1000);
																						}
																						$('#time').on('click', startTimer);

																						jQuery(function () {
																							jQuery('#time').click();
																						});

			</script>
	</body>

</html>