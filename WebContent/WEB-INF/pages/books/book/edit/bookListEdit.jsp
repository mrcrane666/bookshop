<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@taglib
	uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model"
	prefix="portlet-client-model"%><portlet-client-model:init>
	<portlet-client-model:require module="ibm.portal.xml.*" />
	<portlet-client-model:require module="ibm.portal.portlet.*" />
</portlet-client-model:init>
<portlet:defineObjects />
<form action='<portlet:actionURL><portlet:param name="action" value="saveUserConfig"/></portlet:actionURL>'>
<label for="chooseColor">Font color: </label>
	<select id="chooseColor" name="user_color">
		<option value="black" selected="selected" style="color: black">Black</option>
		<option value="green" style="color: green">Green</option>
		<option value="blue" style="color: blue">Blue</option>
		<option value="red" style="color: red">Red</option>
	</select>
	<button type="submit">Save Preferences!</button>
</form>