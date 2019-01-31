package io.ktor.server.testing.client

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.content.*
import io.ktor.http.*
import io.ktor.util.*

internal class TestHttpClientRequest(
    override val call: HttpClientCall,
    engine: TestHttpClientEngine,
    requestData: HttpRequestData
) : HttpRequest {
    override val attributes: Attributes = requestData.attributes

    override val method: HttpMethod = requestData.method
    override val url: Url = requestData.url
    override val headers: Headers = requestData.headers

    override val content: OutgoingContent = requestData.body as OutgoingContent
}
