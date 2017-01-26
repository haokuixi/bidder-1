<div id="login-box" style="text-align: center;">

    <h2>Zaloguj</h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty msg}">
        <div class="msg">${msg}</div>
    </c:if>

    <form name='loginForm' action="<c:url value='/' />" method='GET'>

        <table>
            <tr>
                <td>Login:</td>
                <td><input type='text' name='username' value=''></td>
            </tr>
            <tr>
                <td>Haslo:</td>
                <td><input type='password' name='password'/></td>
            </tr>
            <tr>
                <td><br/></td>
            </tr>
            <tr>
                <td colspan='2' align="center"><input name="submit" type="submit" value="Zaloguj"/></td>
            </tr>
        </table>

        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>

    </form>
</div>
