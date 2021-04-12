<%-- 
    Document   : admin
    Created on : Jan 25, 2021, 9:46:22 PM
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
		<script src="https://kit.fontawesome.com/1acc75252a.js" crossorigin="anonymous"></script>
		<title>More Admin</title>
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
		</style>
	</head>

	<body>
		<div  style="width: 1800px; height: 937px; padding: 15px;display: block;justify-content: center;align-items: center;margin-left: 110px;">
			<div class="shadow-lg" id="body-content" style="width: 1760px;height: 850px;background-color: #fff;margin-bottom: 0px;border-radius: 10px;margin-left: 10px;">
				<div class="row" style="padding: 20px;">
					<div class="col-4">
						<div style="display: flex;justify-content: center;">
							<button class="btn btn-success" style="width: 100%;font-size: larger" disabled="true">
								Admin
							</button>							
						</div>
						<div style="display: block;">
							<ul style="list-style: none">
								<li  style="padding-top: 10px;padding-bottom: 10px;color: #000;font-size: large"><i style="color: #e0b115;margin-right: 5px" class="far fa-envelope"></i>${sessionScope.USER.email}</li>
								<li  style="padding-top: 10px;padding-bottom: 10px;color: #000;font-size: large"><i style="color: #e0b115;margin-right: 5px" class="far fa-address-card"></i>${sessionScope.USER.fullName} </li>
									<c:if test="${sessionScope.USER.status == true}">
									<li  style="padding-top: 10px;padding-bottom: 10px;color: #000;font-size: large"><i style="color: #e0b115;margin-right: 5px" class="fas fa-graduation-cap"></i>Admin status: Yes</li>
									</c:if>									
							</ul>
						</div>
						<div style="display: flex;justify-content: center;">
							<a href="LogoutController" class="btn btn-success" style="width: 50%;font-size: larger">
								Log Out
							</a>							
						</div>
					</div>
					<div class="col-8">
<!--						---->
					</div>
				</div>    
			</div>
		</div>


		<menu class="menu" style="border-radius: 10px;padding-left: 0px;width: 120px;min-height: 298px;">

			<div  style="margin: 30px;margin-top: 0px;margin-bottom: 0px;display: inline-block;width: 66px;text-align: center;height: 104px">
				<a href="HomeAdmin_Page_Controller" class="menu__item " style="--bgColorItem: #e0b115;">
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
				<a class="menu__item active" style="--bgColorItem: #65ddb7;">
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


			function changeSub(text)
			{
				document.getElementById('subID').innerHTML = text;
				document.getElementById('txtsubId').value = text;
			}
		</script>
	</body>

</html>