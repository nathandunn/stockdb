<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="layout" content="main" />
  <title>Login</title>
</head>
<body>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <g:form action="signIn">
    <input type="hidden" name="targetUri" value="${targetUri}" />
    <table>
      <tbody>
        <tr>
          <td>Username:</td>
          <td><input type="text" name="username" value="${username}"  size="40" tabindex="1"/></td>
        </tr>
        <tr>
          <td>Password:</td>
          <td><input type="password" name="password" value="" size="40"  tabindex="2"/></td>
        </tr>
        <tr>
          <td>Remember me?:</td>
          <td><g:checkBox name="rememberMe" value="${rememberMe}" /></td>
        </tr>
        <tr>
          <td />
          <td><input type="submit" value="Sign in" /> &nbsp;
          <g:link action="forgotPassword" controller="auth">Forgot Password</g:link>
          </td>
        </tr>
      </tbody>
    </table>
  </g:form>
</body>
</html>
