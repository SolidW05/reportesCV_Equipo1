/**
 * fetchWrapper.ts
 * Thin wrapper around the base apiFetch, re-exported for convenience.
 * Satisfies the recommended project structure from the spec.
 */
export { apiFetch, ApiError } from '../services/api';

/**
 * Generic GET helper.
 */
export async function get<T = unknown>(url: string): Promise<T> {
  const res = await fetch(url, { headers: { Accept: 'application/json' } });
  if (!res.ok) throw new Error(`HTTP ${res.status}`);
  const text = await res.text();
  if (!text) return undefined as T;
  return JSON.parse(text) as T;
}

/**
 * Generic POST helper with JSON body.
 */
export async function post<T = unknown>(url: string, body: unknown): Promise<T> {
  const res = await fetch(url, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json', Accept: 'application/json' },
    body: JSON.stringify(body),
  });
  if (!res.ok) throw new Error(`HTTP ${res.status}`);
  const text = await res.text();
  if (!text) return undefined as T;
  return JSON.parse(text) as T;
}
