package com.google.mediapipe.examples.facedetection.server

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.http.*
import io.ktor.server.application.Application
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

class KtorServer {

    private var server: ApplicationEngine? = null

    fun startServer() {
        server = embeddedServer(Netty, port = 8080) {
            module()
        }.start(wait = false)
//        embeddedServer(Netty, port = 8080,host = "127.0.0.1", module = Application::module).start(wait = false)

    }

    private fun Application.module() {
        routing {
            get("/") {
                call.respondText("Hello, Ktor!", ContentType.Text.Plain)
            }
        }
    }

    fun stopServer() {
        server?.stop(1000, 10000)
    }
}
