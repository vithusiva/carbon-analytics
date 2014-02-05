<!--
 ~ Copyright (c) 2005-2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 ~
 ~ WSO2 Inc. licenses this file to you under the Apache License,
 ~ Version 2.0 (the "License"); you may not use this file except
 ~ in compliance with the License.
 ~ You may obtain a copy of the License at
 ~
 ~    http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ Unless required by applicable law or agreed to in writing,
 ~ software distributed under the License is distributed on an
 ~ "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 ~ KIND, either express or implied.  See the License for the
 ~ specific language governing permissions and limitations
 ~ under the License.
 --><%--<%@page import="java.io.PrintWriter"%><%@
page import="org.apache.axis2.context.ConfigurationContext"%><%@
page import="org.wso2.carbon.CarbonConstants"%><%@
page import="org.wso2.carbon.utils.ServerConstants"%><%@
page import="org.wso2.carbon.ui.CarbonUIUtil"%><%@
page import="org.wso2.carbon.dashboard.mgt.gadgetrepo.ui.GadgetRepoServiceClient"%><%@
page import="org.wso2.carbon.dashboard.mgt.gadgetrepo.stub.types.carbon.Gadget"%><%@
page import="org.apache.axiom.om.util.Base64"%><%@
page import="java.io.OutputStream"%><%
	String backendServerURL = CarbonUIUtil.getServerURL(config
			.getServletContext(), session);
	ConfigurationContext configContext = (ConfigurationContext) config
			.getServletContext().getAttribute(
					CarbonConstants.CONFIGURATION_CONTEXT);
	String cookie = (String) session
			.getAttribute(ServerConstants.ADMIN_SERVICE_COOKIE);

	GadgetRepoServiceClient gadgetRepoServiceClient = new GadgetRepoServiceClient(cookie,
                    backendServerURL,
                    configContext,
                    request.getLocale());

	Gadget[] result = null;

	if (request.getParameter("imgID") != null) {
		int imageId = Integer.parseInt(request.getParameter("imgID"));
		int pg = Integer.parseInt(request.getParameter("pg"));
		int size = Integer.parseInt(request.getParameter("size"));
		result = gadgetRepoServiceClient.getGadgetDataPag(pg, size);
		try {
			byte[] imgData = null;
			if (result != null) {
				Gadget gadget = result[imageId];
				imgData = Base64.decode(gadget.getGadgetScreenBase64());
			}
			if(imgData == null){
                return;
            }
			response.reset();
			OutputStream o = response.getOutputStream();
			o.write(imgData);
			o.close();
			return;
		} catch (Exception e) {
			throw e;
		}
	}else if(request.getParameter("gadgetPath") != null){
		String gadgetPath = request.getParameter("gadgetPath");

		Gadget gadget = gadgetRepoServiceClient.getGadget(gadgetPath);
		byte[] imgData = null;
		imgData = Base64.decode(gadget.getGadgetScreenBase64());
        if(imgData == null){
            return;
        }
		response.reset();
		OutputStream o = response.getOutputStream();
		o.write(imgData);
		o.close();
		return;

	}
%>--%>