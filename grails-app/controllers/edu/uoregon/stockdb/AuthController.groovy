package edu.uoregon.stockdb

import cr.co.arquetipos.password.PasswordTools
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.crypto.hash.Sha256Hash
import org.apache.shiro.web.util.WebUtils

class AuthController {

    def shiroSecurityManager

    def stockMailService

    def index = { redirect(action: "login", params: params) }

    def login = {
        return [username: params.username, rememberMe: (params.rememberMe != null), targetUri: params.targetUri]
    }

    def forgotPassword = {
        println "mapped forgotten password "
    }

    def resetPassword(String username) {
        log.warn "resetting forgotten password ${username}"
        Researcher user = Researcher.findByUsername(username)
        if (user) {
            // TODO: create a new password
            // mail the password
            String randomPassword = PasswordTools.generateRandomPassword()
            user.passwordHash = new Sha256Hash(randomPassword).toHex()


            stockMailService.sendPasswordReset(user, randomPassword)
            flash.message = "Password email sent to ${user.username}"
            params.username = username
            redirect(action: "login", params: params)
            return
        } else {
            flash.message = "Could not find user with that email [${user}]"
            params.username = username
            redirect(action: "forgotPassword", params: params)
            return
        }

    }

    def signIn = {
        def authToken = new UsernamePasswordToken(params.username, params.password as String)

        // Support for "remember me"
        if (params.rememberMe) {
            authToken.rememberMe = true
        }

        // If a controller redirected to this page, redirect back
        // to it. Otherwise redirect to the root URI.
        def targetUri = params.targetUri ?: "/"

        // Handle requests saved by Shiro filters.
        def savedRequest = WebUtils.getSavedRequest(request)
        if (savedRequest) {
            targetUri = savedRequest.requestURI - request.contextPath
            if (savedRequest.queryString) targetUri = targetUri + '?' + savedRequest.queryString
        }

        try {
            // Perform the actual login. An AuthenticationException
            // will be thrown if the username is unrecognised or the
            // password is incorrect.
            SecurityUtils.subject.login(authToken)

            log.info "Redirecting to '${targetUri}'."
            redirect(uri: targetUri)
        }
        catch (AuthenticationException ex) {
            // Authentication failed, so display the appropriate message
            // on the login page.
            log.info "Authentication failure for user '${params.username}'."
            flash.message = message(code: "login.failed")

            // Keep the username and "remember me" setting so that the
            // user doesn't have to enter them again.
            def m = [username: params.username]
            if (params.rememberMe) {
                m["rememberMe"] = true
            }

            // Remember the target URI too.
            if (params.targetUri) {
                m["targetUri"] = params.targetUri
            }

            // Now redirect back to the login page.
            redirect(action: "login", params: m)
        }
    }

    def signOut = {
        // Log the user out of the application.
        def principal = SecurityUtils.subject?.principal
        SecurityUtils.subject?.logout()
        // For now, redirect back to the home page.
//        if (ConfigUtils.getCasEnable() && ConfigUtils.isFromCas(principal)) {
//            redirect(uri: ConfigUtils.getLogoutUrl())
//        } else {
        webRequest.getCurrentRequest().session = null
        redirect(uri: "/")
//        }
//        ConfigUtils.removePrincipal(principal)
    }

    def unauthorized = {
//        render "You do not have permission to access this page."
        render(view: "/unauthorized")
    }
}
