<%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@taglib
	uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model"
	prefix="portlet-client-model"%><portlet-client-model:init>
	<portlet-client-model:require module="ibm.portal.xml.*" />
	<portlet-client-model:require module="ibm.portal.portlet.*" />
</portlet-client-model:init>
<portlet:defineObjects />
<form action='<portlet:actionURL><portlet:param name="action" value="savePortletConfig"/></portlet:actionURL> '>
<label for="height">Height: </label><input placeholder="${momHeight}" name="momHeight" id="height" type="text"/>
<label for="width">Height: </label><input placeholder="${momWidth}" name="momWidht" id="width" type="text"/>
<button type="submit">Save Settings</button>
</form>