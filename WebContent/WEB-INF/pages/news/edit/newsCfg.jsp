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
<form
	action='<portlet:actionURL><portlet:param name="action" value="savePortletCfg"/></portlet:actionURL>'>
	<label for="fontFamily">Font Family: </label> <select name="fontFamily"
		id="fontFamily">
		<option value="arial" selected="selected">Arial</option>
		<option value="fantasy">Fantasy</option>
		<option value="cursive">Cursive</option>
		<option value="monospace">Monospace</option>
	</select>
	<button type=submit">Save Font Family</button>
</form>