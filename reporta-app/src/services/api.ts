export const API_BASE = '/api/report';

export class ApiError extends Error {
  constructor(
    public status: number,
    message: string
  ) {
    super(message);
    this.name = 'ApiError';
  }
}

export async function apiFetch<T = unknown>(
  endpoint: string,
  options?: RequestInit
): Promise<T> {
  const res = await fetch(`${API_BASE}${endpoint}`, {
    headers: {
      Accept: 'application/json',
      ...(options?.headers ?? {}),
    },
    ...options,
  });

  if (!res.ok) {
    let msg = `API error ${res.status}`;
    try {
      const body = await res.json();
      msg = body?.message ?? msg;
    } catch {}
    throw new ApiError(res.status, msg);
  }

  // Some DELETE endpoints return no body
  const text = await res.text();
  if (!text) return undefined as T;
  try {
    return JSON.parse(text) as T;
  } catch {
    return text as unknown as T;
  }
}

const API_BASE2 =
  typeof window === "undefined"
    ? "http://localhost:4321"
    : "";

export function apiFetch2(
  path: string,
  options?: RequestInit
) {

  const cleanPath =
    path.startsWith("/")
      ? path
      : `/${path}`;

  return fetch(
    `${API_BASE2}/api${cleanPath}`,
    options
  );
}
