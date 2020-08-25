<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Uber</title>
</head>
<body>
<h1>Solicita tu Uber</h1>
<%--@elvariable id="viaje" type="cl.crisgvera.tallerjpa.modelo.Viaje"--%>
<form:form action="${pageContext.request.contextPath}/jsp/solicitar-uber" method="POST" modelAttribute="viaje">
    <label for="ubicacion-inicial">Salida:</label>
    <form:input path="ubicacionSalida" id="ubicacion-inicial"></form:input>
    <br/>
    <label for="ubicacion-final">Destino:</label>
    <form:input path="ubicacionLlegada" id="ubicacion-final"></form:input>
    <br/>
    <button type="submit">Solicitar Uber</button>
</form:form>
<hr/>
<c:if test="${viajeSolicitado != null}">
    <h2>Viaje en curso</h2>
    <table>
        <thead>
        <tr>
            <th>Pasajero ID</th>
            <th>Nombre pasajero</th>
            <th>Email pasajero</th>
            <th>Teléfono pasajero</th>
            <th>Chofer ID</th>
            <th>Nombre chofer</th>
            <th>Viaje ID</th>
            <th>Hora de inicio</th>
            <th>Hora de término</th>
            <th>Lugar de inicio</th>
            <th>Lugar de término</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><c:out value="${viajeSolicitado.pasajero.id}"></c:out></td>
            <td><c:out value="${viajeSolicitado.pasajero.nombre}"></c:out></td>
            <td><c:out value="${viajeSolicitado.pasajero.email}"></c:out></td>
            <td><c:out value="${viajeSolicitado.pasajero.celular}"></c:out></td>
            <td><c:out value="${viajeSolicitado.chofer.id}"></c:out></td>
            <td><c:out value="${viajeSolicitado.chofer.nombre}"></c:out></td>
            <td><c:out value="${viajeSolicitado.id}"></c:out></td>
            <td><c:out value="${viajeSolicitado.horaSalida.toLocalTime()}"></c:out></td>
            <td>
                <c:out value="${viajeSolicitado.horaLlegada == null ? 'Aún en curso' : viajeSolicitado.horaLlegada.toLocalTime()}"></c:out>
            </td>
            <td><c:out value="${viajeSolicitado.ubicacionSalida}"></c:out></td>
            <td><c:out value="${viajeSolicitado.ubicacionLlegada}"></c:out></td>
        </tr>
        </tbody>
    </table>
    <%--@elvariable id="viajeSolicitado" type="cl.crisgvera.tallerjpa.modelo.Viaje"--%>
    <form:form action="${pageContext.request.contextPath}/jsp/terminar-viaje" method="POST"
               modelAttribute="viajeSolicitado">
        <form:hidden path="id.choferId"></form:hidden>
        <form:hidden path="id.pasajeroId"></form:hidden>
        <button type="submit">Terminar viaje</button>
    </form:form>
</c:if>
</body>
</html>