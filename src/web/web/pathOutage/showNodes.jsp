<%--

//
// This file is part of the OpenNMS(R) Application.
//
// OpenNMS(R) is Copyright (C) 2002-2003 The OpenNMS Group, Inc.  All rights reserved.
// OpenNMS(R) is a derivative work, containing both original code, included code and modified
// code that was published under the GNU General Public License. Copyrights for modified 
// and included code are below.
//
// OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
//
// Modifications:
//
//
// Original code base Copyright (C) 1999-2001 Oculan Corp.  All rights reserved.
//
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 2 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
//
// For more information contact:
//      OpenNMS Licensing       <license@opennms.org>
//      http://www.opennms.org/
//      http://www.opennms.com/
//

--%>

<%@page language="java" contentType="text/html" session="true"
	import=" java.util.Iterator,
		java.util.List,
                org.opennms.web.pathOutage.*" %>

<jsp:include page="/includes/header.jsp" flush="false">
  <jsp:param name="title" value="Show Path Outage Nodes" />
  <jsp:param name="headTitle" value="Show Path Outage Nodes" />
  <jsp:param name="breadcrumb" value="Show Path Outage Nodes" />

</jsp:include>

<%  String critIp = request.getParameter("critIp");
    String critSvc = request.getParameter("critSvc");
    List nodeList = PathOutageFactory.getNodesInPath(critIp, critSvc); %>

    <H3 align="center">Node List for Critical Path <%= critIp %> <%= critSvc%></H3>
    <table class="standardfirst">

        <tr>
        <td class="standardheader" width="10%"><%= "Node ID" %></td>
        <td class="standardheader" width="40%" align="center">Node Label</td>
        </tr>

<%      Iterator iter = nodeList.iterator();
        while( iter.hasNext() ) {
            String nodeid = (String)iter.next();
            String labelColor[] = PathOutageFactory.getNodeLabelAndColor(nodeid); %>
            <tr>
            <td class="standard" align="center"><a href="element/node.jsp?node=<%= nodeid %>"><%= nodeid %></a></td>
            <td class="standard" bgcolor="<%= labelColor[1] %>"><%= labelColor[0] %></td>
            </tr>
        <% } %>

    </table>

<%--
    </p>
--%>

<jsp:include page="/includes/footer.jsp" flush="false" />


