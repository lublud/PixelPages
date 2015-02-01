<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<c:url var="home" value="./directory.htm" />
	<div id="header">
		<img id="top_left" src="./images/logoamu.png"
			alt="Aix-Marseille Université"
			style="height: auto; width: auto; max-width: 250px;" />
		<div id="top_center"><a id="top_center" href="${home}">
		<pre id="top_center"> ______ _            _ ______                       
(_____ (_)          | (_____ \                      
 _____) ) _   _ ____| |_____) )___  ____  ____  ___ 
|  ____/ ( \ / ) _  ) |  ____/ _  |/ _  |/ _  )/___)
| |    | |) X ( (/ /| | |   ( ( | ( ( | ( (/ /|___ |
|_|    |_(_/ \_)____)_|_|    \_||_|\_|| |\____|___/ 
                                  (_____|           </pre>
		</a></div>
		<span id="top_right">
			<c:choose>
				<c:when test="${user == null}">
					<div id="connect"><a href="./connection.htm">Connection</a></div>
				</c:when>
				<c:when test="${user != null}">
					<c:out value="Welcome ${user.getLastName()} !" />
					<br><a href="./edition.htm">Profile</a>
					<br><a href="./disconnection.htm">Disconnect</a>
				</c:when>
			</c:choose>
		</span>
	</div>
