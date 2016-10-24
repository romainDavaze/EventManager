<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@include file="../partials/header.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/menu.scss">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/events.scss">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/eventModal.scss">
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>

<body>

<div class="events-page">
    <div class="menu">
        <jsp:include page="../partials/menu.jspf"/>
    </div>
    <div class="events">
        <h1 id="events-page-title">Évènements</h1>

        <div class="events-content">
            <div class="row">
                <div class="col s4 event">
                    <span class="event-title">Titre</span>
                    <button data-target="modal-event1" class="btn modal-trigger waves-effect waves-light btn-event"><i
                            class="material-icons">library_add</i></button>
                    <p class="event-element">Date</p>
                    <p class="event-element">Lieu</p>
                    <p class="event-element">Organisateur</p>
                </div>
                <div class="col s4 event">
                    <span class="event-title">Titre</span>
                    <button data-target="modal-event2" class="btn modal-trigger btn-event"><i class="material-icons">library_add</i>
                    </button>
                    <p class="event-element">Date</p>
                    <p class="event-element">Lieu</p>
                    <p class="event-element">Organisateur</p>
                </div>
            </div>
            <div class="row">
                <div class="col s4 event">
                    <span class="event-title">Titre</span>
                    <button data-target="modal-event3" class="btn modal-trigger btn-event"><i class="material-icons">library_add</i>
                    </button>
                    <p class="event-element">Date</p>
                    <p class="event-element">Lieu</p>
                    <p class="event-element">Organisateur</p>
                </div>
                <div class="col s4 event">
                    <span class="event-title">Titre</span>
                    <button data-target="modal-event4" class="btn modal-trigger btn-event"><i class="material-icons">library_add</i>
                    </button>
                    <p class="event-element">Date</p>
                    <p class="event-element">Lieu</p>
                    <p class="event-element">Organisateur</p>
                </div>

                <%--<p>--%>
                <%--<c:forEach var="event" items="${pageContext.request.getAttribute(\"events\")}">--%>
                <%--Event <c:out value="${event.getLabel()}"/> <br>--%>
                <%--</c:forEach>--%>
                <%--</p>--%>
            </div>

        </div>

        <ul class="pagination changePage">
            <li class="disabled"><a href="#!"><i class="material-icons chevron">chevron_left</i></a></li>
            <li class="active"><a href="#!">1</a></li>
            <li class="waves-effect"><a href="#!">2</a></li>
            <li class="waves-effect"><a href="#!">3</a></li>
            <li class="waves-effect"><a href="#!">4</a></li>
            <li class="waves-effect"><a href="#!"><i class="material-icons chevron">chevron_right</i></a></li>
        </ul>

    </div>


    <div id="modal-event1" class="modal">
        <jsp:include page="../partials/eventModal.jspf"/>
    </div>
    <div id="modal-event2" class="modal">
        <jsp:include page="../partials/eventModal.jspf"/>
    </div>
    <div id="modal-event3" class="modal">
        <jsp:include page="../partials/eventModal.jspf"/>
    </div>
    <div id="modal-event4" class="modal">
        <jsp:include page="../partials/eventModal.jspf"/>
    </div>

</div>

<script>
    $(document).ready(function () {
        // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
        $('.modal-trigger').leanModal();
    });

</script>
</body>

</html>