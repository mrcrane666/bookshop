<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@taglib
	uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model"
	prefix="portlet-client-model"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib
	uri="http://www.ibm.com/xmlns/prod/websphere/portal/v8/portlet/ibm-portlet-ext"
	prefix="portlet-ext"%>

<portlet-client-model:init>
	<portlet-client-model:require module="ibm.portal.xml.*" />
	<portlet-client-model:require module="ibm.portal.portlet.*" />
</portlet-client-model:init>
<portlet:defineObjects />
<link href="<c:url value="/res/css/home/home.css" />" rel="stylesheet">
<h2>
<fmt:setLocale value="de"/>
<fmt:bundle basename="com.ibm.bookshop.nl.BookShopPortletResource"><fmt:message key="bookshop.name"></fmt:message></fmt:bundle>
		
</h2>
<div class="start">
	<div class="choise">
		<a
			href='<portlet:actionURL><portlet:param name="action" value="getBooks"></portlet:param> </portlet:actionURL>'><img
			class="choise border"
			src='<c:url value="/res/img/home/book.jpg"></c:url>'> </a>
	</div>
	<div class="choise">
		<a
			href='<portlet:renderURL><portlet:param name="view" value="ebookList"/></portlet:renderURL>'>
			<img class="choise border"
			src='<c:url value="/res/img/home/ebook.jpg"></c:url>'>
		</a>
	</div>
	<div class="choise">
		<a
			href='<portlet:renderURL><portlet:param name="view" value="commingSoon"/></portlet:renderURL>'>
			<img class="choise border"
			src='<c:url value="/res/img/home/merch.png"></c:url>'>
		</a>
	</div>
	<div class="choise">
		<a
			href='<portlet:renderURL><portlet:param name="view" value="commingSoon"/></portlet:renderURL>'>
			<img class="choise border"
			src='<c:url value="/res/img/home/cart.jpeg"></c:url>'>
		</a>
	</div>
</div>
