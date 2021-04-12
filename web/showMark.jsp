<%-- 
    Document   : showMark
    Created on : Feb 5, 2021, 3:57:01 PM
    Author     : thant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Finish Quiz</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
        integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/1acc75252a.js" crossorigin="anonymous"></script>
    <title>Show_mark</title>
</head>

<body>
    <div style="width: 100%;display: flex;justify-content: center;align-items: center;height: 938px;">
        <div
        style="width: 1800px; height: 880px; padding: 15px;display: block;justify-content: center;align-items: center;">
        <div class="shadow-lg" id="body-content" style="width: 1760px;height: 850px;background-color: #fff;margin-bottom: 0px;border-radius: 10px;margin-left: 10px;display: flex;justify-content: center;align-items: center;">
              <div>
                  <c:if test="${requestScope.TOTAL >= 4}">
					  <h2 style="color: green;font-size: 60px;text-align: center;">Pass</h2>
				  </c:if>
                   <c:if test="${requestScope.TOTAL < 4}">
					  <h2 style="color: red;font-size: 60px;text-align: center;">Not Pass</h2>
				  </c:if>
                  <table style="font-size: larger;text-align: center;" class="table table-borderless">
                    <thead>
                      <tr>
                        <th scope="col">Subject</th>
                        <th scope="col">TimeStart</th>
                        <th scope="col">RightAns</th>
                        <th scope="col">Mark</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
						  <td>${requestScope.SUBID}</td>
						  <td>${requestScope.DATESTART}</td>
						  <td>${requestScope.RIGHTANS}/${requestScope.TOTALQUES}</td>
						  <td>${requestScope.TOTAL}</td>
                      </tr>
                    </tbody>
                  </table>
					  <div style="display: flex;justify-content: center;margin-top: 100px">
						  <a  href="Show_Sub_Home_Controller" class="btn btn-secondary">Back To Home</a>
					  </div>
              </div>
               
        </div>
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
</body>

</html>
