import { defineMiddleware } from "astro:middleware";

const BACKEND_URL = "http://api-gateway:8080";

export const onRequest = defineMiddleware(async (context, next) => {

  const pathname = context.url.pathname;

  if (pathname.startsWith("/api")) {

    const targetUrl =
      BACKEND_URL +
      pathname +
      context.url.search;

    const headers = new Headers(context.request.headers);

    headers.delete("origin");
    headers.delete("host");

    const requestInit: RequestInit & { duplex?: "half" } = {
      method: context.request.method,
      headers,
      duplex: "half"
    };

    if (
      context.request.method !== "GET" &&
      context.request.method !== "HEAD"
    ) {
      requestInit.body = context.request.body;
    }

    return fetch(targetUrl, requestInit);
  }

  return next();
});